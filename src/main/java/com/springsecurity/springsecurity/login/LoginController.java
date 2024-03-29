package com.springsecurity.springsecurity.login;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LoginController {


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
     * @return ResponseEntity<Map<String, Object>> LoginExistInfo
     */
    @GetMapping("/login/sessionExist")
    public ResponseEntity<Map<String, Object>> getSessionExistInfo() {

        Map<String, Object> session = new HashMap<>();
        session.put("result" , "이미 로그인된 계정");

        return ResponseEntity.ok(session);
    }

    /**
     * 세션 만료 및 로그아웃 시 안내 메세지
     *
     * @return ResponseEntity<Map<String, Object>> sessionExpiredMessage
     */
    @GetMapping("/login/expired")
    public ResponseEntity<Map<String, Object>> getSessionExpired() {
        Map<String, Object> sessionExpiredResult = new HashMap<>();
        sessionExpiredResult.put("status" , 200);
        sessionExpiredResult.put("message" , "로그아웃 성공");

        return ResponseEntity.ok(sessionExpiredResult);
    }
    
    /**
     * 로그인 성공 시 안내 메세지
     *
     * @return ResponseEntity<Map<String, Object>> LoginSuccessInfo
     */
    @GetMapping("/login/success")
    public ResponseEntity<Map<String, Object>> getLoginInfo() {

        Map<String, Object> loginSuccess = new HashMap<>();
        loginSuccess.put("result" , "success");

        return ResponseEntity.ok(loginSuccess);
    }

    @GetMapping("/main")
    public String mainPage() {
        return "/mainPage";
    }
}
