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
@Table(name = "session")
public class Session extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;
    @Column(name = "user_id")
    private String userId;
    @Column(name = "session_id")
    private String sessionId;
}
