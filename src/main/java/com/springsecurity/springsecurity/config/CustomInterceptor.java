package com.springsecurity.springsecurity.config;

import com.springsecurity.springsecurity.login.SessionRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@RequiredArgsConstructor
public class CustomInterceptor implements HandlerInterceptor {

    private final SessionRepository sessionRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        String username = userDetails.getUsername();

        log.info("login User : {}" , username);

        if (sessionRepository.existsByUserId(username)) {
            log.info("login result : {}" ,true );
            return true;
        } else {
            log.info("login result : {}" ,false);
            response.sendRedirect("/login/sessionExist");
            return false;
        }
    }
}
