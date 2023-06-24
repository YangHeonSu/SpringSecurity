package com.springsecurity.springsecurity.config;

import com.springsecurity.springsecurity.login.SessionRepository2;
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
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.HttpSessionEventPublisher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final SessionRepository2 sessionRepository2;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.authorizeHttpRequests(authorizationManager -> authorizationManager
                .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                .requestMatchers("/templates/**", "/static/**").permitAll()
                .requestMatchers("/login/fail").permitAll()
                .requestMatchers("/login/expired").permitAll()
                .requestMatchers("/login/sessionExist").permitAll()
                .anyRequest().authenticated());

        httpSecurity.formLogin(login -> login
                .loginProcessingUrl("/loginProc")
                .usernameParameter("userId")
                .passwordParameter("password")
                .successHandler(new CustomLoginSuccessHandler())
                .failureHandler(loginFailHandler())
                .permitAll());

        httpSecurity.logout(logout -> logout
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .logoutSuccessHandler(logoutSuccessHandler())
                .deleteCookies("JSESSIONID", "remember-me")
                .permitAll());

        httpSecurity.sessionManagement(sessionManagement -> sessionManagement
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
        return new CustomLoginFailureHandler(sessionRepository2);
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new CustomLoginAuthProvider(userDetailsService, encodePassword(), sessionRegistry());
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }
}
