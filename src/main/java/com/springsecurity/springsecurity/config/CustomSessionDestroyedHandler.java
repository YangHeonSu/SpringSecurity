package com.springsecurity.springsecurity.config;

import com.springsecurity.springsecurity.login.SessionRepository2;
import com.springsecurity.springsecurity.user.User;
import com.springsecurity.springsecurity.user.UserRepository;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomSessionDestroyedHandler implements ApplicationListener<SessionDestroyedEvent> {

    private final UserRepository userRepository;
    private final SessionRepository2 sessionRepository2;
    @Override
    public void onApplicationEvent(SessionDestroyedEvent event) {

        List<SecurityContext> securityContexts = event.getSecurityContexts();
        for (SecurityContext securityContext : securityContexts) {
            WebAuthenticationDetails webAuthenticationDetails = (WebAuthenticationDetails) securityContext.getAuthentication().getDetails();
            log.info("인증 여부 : {}", webAuthenticationDetails.getSessionId());

            UserDetails userDetails = (UserDetails) securityContext.getAuthentication().getPrincipal();
            Optional<User> user = userRepository.findByUserId(userDetails.getUsername());

            log.info("Session Destroyed User : {}", user.get().getUserId());
        }
    }
}
