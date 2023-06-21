package com.springsecurity.springsecurity.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@RequiredArgsConstructor
public class CustomLoginAuthProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String userId = authentication.getName();
        String password = (String) authentication.getCredentials();
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(userId);

        if (userDetails.getUsername() == null) {
            throw new UsernameNotFoundException("아이디를 찾을 수 없습니다.");
        }
        if (!this.bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
            throw new UsernameNotFoundException("비밀번호를 다시 확인해주세요.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
