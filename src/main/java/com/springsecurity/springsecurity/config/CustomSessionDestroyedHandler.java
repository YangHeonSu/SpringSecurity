package com.springsecurity.springsecurity.config;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomSessionDestroyedHandler implements HttpSessionListener {


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 세션 생성 시 실행할 코드 작성
        System.out.println("세션 생성");


    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // 세션 소멸 시 실행할 코드 작성
        System.out.println("세션 삭제");


    }
}
