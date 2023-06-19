package com.springsecurity.springsecurity.config;

import com.springsecurity.springsecurity.login.SessionRepository2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final SessionRepository2 sessionRepository2;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getCustomInterceptor())
                .addPathPatterns("/**") // 모든 URL에 대해 인터셉터 적용
                .excludePathPatterns("/loginProc")
                .excludePathPatterns("/login/success")
                .excludePathPatterns("/login/fail")
                .excludePathPatterns("/login/sessionExist");
    }

    @Bean
    public HandlerInterceptor getCustomInterceptor() {
        return new CustomInterceptor(sessionRepository2);
    }
}
