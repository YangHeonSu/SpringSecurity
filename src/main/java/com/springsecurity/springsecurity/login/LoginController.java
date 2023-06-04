package com.springsecurity.springsecurity.login;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @GetMapping("/login/fail")
    public ResponseEntity<Map<String, Object>> getLoginFailInfo() {

        Map<String, Object> loginFail = new HashMap<>();
        loginFail.put("result" , "fail");
        return ResponseEntity.ok(loginFail);
    }

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
