package com.springsecurity.springsecurity.login;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final SessionRepository2 sessionRepository2;

    /**
     * 로그인 실패 메시지 출력
     *
     * @return ResponseEntity<Map<String, Object>> LoginFailInfo
     */
    @GetMapping("/login/fail")
    public ResponseEntity<Map<String, Object>> getLoginFailInfo() {

        Map<String, Object> loginFail = new HashMap<>();
        loginFail.put("failMessage" , "아이디 또는 비밀번호를 확인해주세요.");
        return ResponseEntity.ok(loginFail);
    }

    /**
     * 로그인 시도 시 이미 로그인된 계정이 있을경우 안내 메세지
     * 
     * @param request HttpServletRequest request
     * @return ResponseEntity<Map<String, Object>> LoginExistInfo
     */
    @GetMapping("/login/sessionExist")
    public ResponseEntity<Map<String, Object>> getSessionExistInfo(HttpServletRequest request) {

        Map<String, Object> session = new HashMap<>();
        session.put("result" , "이미 로그인된 계정");

        return ResponseEntity.ok(session);
    }
    
    /**
     * 로그인 성공 시 안내 메세지
     *
     * @param request HttpServletRequest request
     * @param principal Principal principal
     * @return ResponseEntity<Map<String, Object>> LoginSuccessInfo
     */
    @GetMapping("/login/success")
    public ResponseEntity<Map<String, Object>> getLoginInfo(HttpServletRequest request, HttpServletResponse response, Principal principal) {

        Map<String, Object> loginSuccess = new HashMap<>();
        loginSuccess.put("result" , "success");

        return ResponseEntity.ok(loginSuccess);
    }

    @GetMapping("/main")
    public String mainPage() {
        return "/mainPage";
    }
}
