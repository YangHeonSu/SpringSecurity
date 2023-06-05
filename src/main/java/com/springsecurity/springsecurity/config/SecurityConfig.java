package com.springsecurity.springsecurity.config;

import com.springsecurity.springsecurity.login.SessionRepository;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final SessionRepository sessionRepository;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(authorizationManager -> authorizationManager
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .requestMatchers("/templates/**", "/static/**").permitAll()
                .requestMatchers("/login/fail").permitAll()
                .requestMatchers("/login/success").permitAll()
                .requestMatchers("/login/sessionExist").permitAll()
                .anyRequest().authenticated());

        httpSecurity.formLogin(login -> login
                .loginProcessingUrl("/loginProc")
                .usernameParameter("userId")
                .passwordParameter("password")
                .successHandler(new CustomLoginSuccessHandler())
                .failureHandler(loginFailHandler())
                .permitAll());

        httpSecurity.sessionManagement(sessionManagement -> sessionManagement
                .invalidSessionUrl("/invalid")
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry()));

        httpSecurity.authenticationProvider(authenticationProvider());

        return httpSecurity.build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public AuthenticationFailureHandler loginFailHandler() {
        return new CustomLoginFailureHandler(sessionRepository);
    }


    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomLoginAuthProvider(userDetailsService, encodePassword());
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }
}
