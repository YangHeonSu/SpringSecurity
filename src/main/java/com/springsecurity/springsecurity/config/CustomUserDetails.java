package com.springsecurity.springsecurity.config;

import com.springsecurity.springsecurity.user.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.ArrayList;
import java.util.Collection;

@AllArgsConstructor
@EqualsAndHashCode(of = "username")
public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private String auth;

    public CustomUserDetails(User user) {
        this.username = user.getUserId();
        this.password = user.getPassword();
        this.auth = user.getAuth();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(() -> "" + auth);
        return collection;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
