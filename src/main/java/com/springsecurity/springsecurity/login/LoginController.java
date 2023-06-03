package com.springsecurity.springsecurity.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    @GetMapping("/login/fail")
    public Map<String, Object> loginPage() {

        Map<String, Object> loginFail = new HashMap<>();
        loginFail.put("result" , 200);
        return loginFail;
    }

    @GetMapping("/dashboard")
    public String mainPage() {

        return "/index";
    }
}
