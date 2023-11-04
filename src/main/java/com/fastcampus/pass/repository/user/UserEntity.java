package com.fastcampus.pass.repository.user;


import com.fastcampus.pass.repository.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    @Id
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private String phone;
    private String meta;

    protected  UserEntity() {}

    private UserEntity(
            String userId,
            String userName,
            UserStatus status,
            String phone,
            String meta
    ) {
        this.userId = userId;
        this.userName = userName;
        this.status = status;
        this.phone = phone;
        this.meta = meta;
    }

    public static UserEntity of(
            String userId,
            String userName,
            UserStatus status,
            String phone,
            String meta
    ) {
        return new UserEntity(
                userId,
                userName,
                status,
                phone,
                meta
        );
    }

    public static UserEntity of(
            String userId,
            String userName,
            UserStatus status
    ) {
        return new UserEntity(
                userId,
                userName,
                status,
                null,
                null
        );
    }
}
