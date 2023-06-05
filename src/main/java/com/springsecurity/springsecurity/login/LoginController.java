package com.springsecurity.springsecurity.login;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final SessionRepository sessionRepository;

    @GetMapping("/login/fail")
    public ResponseEntity<Map<String, Object>> getLoginFailInfo() {

        Map<String, Object> loginFail = new HashMap<>();
        loginFail.put("result" , "fail");
        return ResponseEntity.ok(loginFail);
    }

    @GetMapping("/login/sessionExist")
    public ResponseEntity<Map<String, Object>> getSessionExistInfo() {

        Map<String, Object> session = new HashMap<>();
        session.put("result" , "이미 로그인된 계정");
        return ResponseEntity.ok(session);
    }
    

    @GetMapping("/login/success")
    public ResponseEntity<Map<String, Object>> getLoginInfo(HttpServletRequest request, Principal principal) {

        HttpSession session = request.getSession();
        Map<String, Object> loginSuccess = new HashMap<>();
        loginSuccess.put("result" , "success");

        SessionDTO sessionDTO = new SessionDTO();

        sessionDTO.setUserId(principal.getName());
        sessionDTO.setSessionId(session.getId());
        Session sessionEntity = sessionDTO.toEntity();

        sessionRepository.save(sessionEntity);

        return ResponseEntity.ok(loginSuccess);
    }

    @GetMapping("/main")
    public String mainPage() {
        return "/mainPage";
    }
}
