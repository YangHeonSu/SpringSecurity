package com.springsecurity.springsecurity.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "DEPARTMENT_NAME")
    private String department_name;

    @Column(name = "auth")
    private String auth;
}
