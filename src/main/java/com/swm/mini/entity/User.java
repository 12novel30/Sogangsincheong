package com.swm.mini.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@DynamicInsert
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "members")
public class User {
    @Id
    @Column(name = "id", length = 30, nullable = false)
    private String id;
    @Column(name = "nickname", length = 30, nullable = false)
    private String nickname;
    @Column(name = "password", length = 50, nullable = false)
    private String password;

}
