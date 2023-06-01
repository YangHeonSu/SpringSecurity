package com.springsecurity.springsecurity.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/js/**", "/css/**", "/images/**", "/font/**", "/html/**", "/templates/**", "/static/**");
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .anyRequest().authenticated()	// 어떠한 요청이라도 인증필요
                );
        httpSecurity.formLogin(
                login ->
                        //login.loginPage("/login")
                        login.loginProcessingUrl("/loginProc")
                        .usernameParameter("userId")
                        .passwordParameter("password")
                        .successHandler(new CustomLoginSuccessHandler())
                        .failureHandler(new CustomLoginFailureHandler())
                        //.defaultSuccessUrl("/dashboard", true) // 로그인 성공 시 이동 페이지
                        .permitAll() // 로그인에 대한 것은 모두 허용
        );

/*
        httpSecurity.sessionManagement()
                .invalidSessionUrl("/invalid")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredUrl("/expired");
*/


        return httpSecurity.build();
    }
}
