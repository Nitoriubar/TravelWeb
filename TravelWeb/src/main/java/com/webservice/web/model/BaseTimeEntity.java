package com.webservice.web.model;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity Ŭ�������� BaseTimeEntity�� ����� ��� ��¥ �ʵ嵵 Į������ �ν�
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate //������ �� �ڵ�����
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate //������ �� �ڵ�����
    @Column(nullable = false, updatable = false)
    private LocalDateTime modifiedDate;
}