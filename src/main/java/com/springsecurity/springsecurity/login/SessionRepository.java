package com.springsecurity.springsecurity.login;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, String> {

    boolean existsByUserId(String userId);

    void deleteByUserId(String userId);
}
