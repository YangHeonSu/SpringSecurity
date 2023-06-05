package com.springsecurity.springsecurity.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * @MappedSuperclass JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 해당 필드도 컬럼으로 인식하기 위함
 * @EntityListeners(AuditingEntityListener.class) BaseTimeEntity 클래스에 Auditing 기능을 포함시키기 위함
 * @CreatedDate Entity가 생성되어 저장될 때 시간이 자동저장되기 위함
 * @LastModifiedDate 조회한 Entity의 값을 변경할 때 시간이 자동으로 저장되기 위함
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime create_date;
    @LastModifiedDate
    private LocalDateTime update_date;
}
