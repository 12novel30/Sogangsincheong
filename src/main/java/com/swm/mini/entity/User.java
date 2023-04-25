package com.swm.mini.entity;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id", length = 30, nullable = false)
    private String id;
    @Column(name = "nickname", length = 30, nullable = false)
    private String nickname;
    @Column(name = "password", length = 50, nullable = false)
    private String password;

}
