package com.springsecurity.springsecurity.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionDTO {

    private String id;
    private String userId;
    private String sessionId;

    public Session toEntity() {
        return Session.builder()
                .userId(userId)
                .sessionId(sessionId)
                .build();
    }
}
