
package com.example.market.config;

import com.example.market.oauth2.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable()
                .and()
                .authorizeRequests()  //요청에 대한 권한 지정. Security 처리에 HttpServletRequest를 이용한다는 것을 의미
                .antMatchers("/**").permitAll()  //어떤 사용자든지 접근할 수 있습니다.
                .anyRequest().authenticated() //설정한 경로 외에 모든 경로는 인증된 사용자만이 접근 가능
                .and()
                .oauth2Login() //OAuth2 로그인 기능에 대한 설정의 진입점
                .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 성보를 가져올때의 설정들을 담당, auth2Login 성공 이후의 설정을 시작
                .userService(customOAuth2UserService); //customOAuth2UserService에서 처리
    }
}
