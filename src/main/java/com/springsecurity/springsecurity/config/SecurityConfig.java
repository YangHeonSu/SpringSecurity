package com.springsecurity.springsecurity.config;

import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/js/**", "/css/**", "/images/**", "/font/**", "/html/**", "/templates/**", "/static/**");
    }

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizationManager ->
                        authorizationManager.dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                                .requestMatchers("/loginProc").permitAll()
                                .requestMatchers("/login/fail").permitAll()
                                .requestMatchers("/dashboard").permitAll())
        ;

        httpSecurity.formLogin(
                login -> login.loginProcessingUrl("/loginProc")
                        .usernameParameter("userId")
                        .passwordParameter("password")
                        .successHandler(new CustomLoginSuccessHandler())
                        .failureHandler(new CustomLoginFailureHandler())
        );

        httpSecurity.sessionManagement(sessionManagement ->
                sessionManagement.invalidSessionUrl("/invalid")
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
    public AuthenticationProvider authenticationProvider() {
        return new CustomLoginAuthProvider(userDetailsService, encodePassword());
    }

    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }
}
