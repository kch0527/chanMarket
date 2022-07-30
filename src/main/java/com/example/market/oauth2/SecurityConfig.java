package com.example.market.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.transaction.Transactional;

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
                //.antMatchers("/add/**").access("Role(Role_USER)")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login/oauth2/client/google")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/chanMarket/board")
                .and()
                .oauth2Login()
                .loginPage("/chanMarket/login")
                .and()
                .logout() //로그아웃 시 할 일들의 진입점
                .logoutSuccessUrl("/chanMarket/board")
                .and()
                .oauth2Login() //OAuth2 로그인 기능에 대한 설정의 진입점
                .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 성보를 가져올때의 설정들을 담당
                .userService(customOAuth2UserService);
    }
}
