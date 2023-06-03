package com.springsecurity.springsecurity.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserDTO {

    private String id;
    private String userId="";
    private String name ="";
    private String password="";
    private String companyName="";
    private String department_name="";
    private String auth = "";

    public void bCryptPasswordEncoder(String bCryptPassword) {
        this.password = bCryptPassword;
    }
}
