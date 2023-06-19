package com.springsecurity.springsecurity.config;

import com.springsecurity.springsecurity.login.SessionRepository2;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomSessionEvent implements HttpSessionListener {
    private final SessionRegistry sessionRegistry;
    private final SessionRepository2 sessionRepository2;
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info("인증 여부 : {}", httpSessionEvent.getSession().getId());
        log.info("인증 여부 : {}", httpSessionEvent.getSession());

        log.info("인증 여부 : {}", httpSessionEvent.getSession().getAttributeNames());
        log.info("인증 여부 : {}", httpSessionEvent.getSession().getAttributeNames().nextElement());

       // sessionRegistry.removeSessionInformation(httpSessionEvent.getSession().getId());
        //sessionRepository2.deleteByUserId(httpSessionEvent.getSession().getAttributeNames());
    }
}
