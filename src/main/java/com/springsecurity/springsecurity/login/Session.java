package com.springsecurity.springsecurity.login;

import com.springsecurity.springsecurity.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spring_session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String PRIMARY_ID;
    private String SESSION_ID;
    private String CREATION_TIME;
    private String LAST_ACCESS_TIME;
    private String MAX_INACTIVE_INTERVAL;
    private String EXPIRY_KEY;
    @Column(name = "PRINCIPAL_NAME")
    private String userId;


}
