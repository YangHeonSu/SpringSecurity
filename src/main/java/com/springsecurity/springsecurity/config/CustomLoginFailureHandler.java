package com.springsecurity.springsecurity.config;

import com.springsecurity.springsecurity.login.SessionRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;

@RequiredArgsConstructor
public class CustomLoginFailureHandler implements AuthenticationFailureHandler {

    private final SessionRepository sessionRepository;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response
            , AuthenticationException exception) throws IOException, ServletException {

        if (exception instanceof BadCredentialsException) {
            response.sendRedirect("/login/fail");
        } else if (sessionRepository.existsByUserId(request.getParameter("userId"))) {
            response.sendRedirect("/login/sessionExist");
        }
    }
}
