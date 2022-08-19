
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
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2Login() //OAuth2 로그인 기능에 대한 설정의 진입점
                .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 성보를 가져올때의 설정들을 담당
                .userService(customOAuth2UserService);
    }
}
