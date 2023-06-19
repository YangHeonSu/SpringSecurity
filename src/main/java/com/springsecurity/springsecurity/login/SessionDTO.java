package com.springsecurity.springsecurity.login;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessionDTO {

    private String PRIMARY_ID;
    private String SESSION_ID;
    private String CREATION_TIME;
    private String LAST_ACCESS_TIME;
    private String MAX_INACTIVE_INTERVAL;
    private String EXPIRY_KEY;
    private String PRINCIPAL_NAME;
}
