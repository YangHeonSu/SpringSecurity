package com.springsecurity.springsecurity.config;

import com.springsecurity.springsecurity.login.SessionRepository2;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    private final SessionRepository2 sessionRepository2;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response
            , AuthenticationException exception) throws IOException, ServletException {
        log.info("login Fail Message : {}", exception.getMessage());
        if (exception instanceof UsernameNotFoundException) {
            response.sendRedirect("/login/fail");
        }
        if (exception instanceof BadCredentialsException) {
            response.sendRedirect("/login/fail");
        }
        if (sessionRepository2.existsByUserId(request.getParameter("userId"))) {
            response.sendRedirect("/login/sessionExist");
        }
    }
}
