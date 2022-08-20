package com.example.market.oauth2;


import com.example.market.entity.member.Member;
import com.example.market.repository.JpaMemberRepository;
import com.example.market.service.basket.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final JpaMemberRepository memberRepository;
    private final HttpSession httpSession;
    private final BasketService basketService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        //DefaultOAuth2UserService 는 OAuth2UserService 의 구현체, 해당 클래스를 이용해 userRequest 에 있는 정보를 빼낼 수 있다.
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        //DefaultOAuth2User 의 권한을 가진 User 를 load 함
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        //registrationId : 현재 로그인 진행중인 서비스를 구분함
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        //userNameAttributeName : OAuth2 로그인 진행 시 키가 되는 필드값을 이야기 함, Primary Key와 같은 의미
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Member member = saveOrUpdate(attributes);
        httpSession.setAttribute("loginMember", member.getEmail());

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private Member saveOrUpdate(OAuthAttributes attributes) {
        Member member = memberRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName()))
                .orElse(attributes.toEntity());
        Member saveMember = memberRepository.save(member);

        if (saveMember.getBasket() == null) {
            basketService.addBasket(member);
        }

        return saveMember;
    }
}
