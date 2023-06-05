package com.springsecurity.springsecurity.config;

import com.springsecurity.springsecurity.login.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final SessionRepository sessionRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getCustomInterceptor())
                .addPathPatterns("/**") // 모든 URL에 대해 인터셉터 적용
                .excludePathPatterns("/loginProc")
                .excludePathPatterns("/login/**");



        // 인터셉터 제외할 URL 패턴 설정
    }

    @Bean
    public HandlerInterceptor getCustomInterceptor() {
        return new CustomInterceptor(sessionRepository);
    }
}
