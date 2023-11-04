package com.fastcampus.pass.service.user;

import com.fastcampus.pass.repository.user.UserEntity;
import com.fastcampus.pass.repository.user.UserStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User{
    private String userId;
    private String userName;
    private UserStatus status;

    protected User() {}

    private User(
            String userId,
            String userName,
            UserStatus status
    ) {
        this.userId = userId;
        this.userName = userName;
        this.status = status;
    }

    public static User of(
            String userId,
            String userName,
            UserStatus status
    ) {
        return new User(
          userId,
          userName,
          status
        );
    }

    public static User from(UserEntity entity) {
        return new User(
                entity.getUserId(),
                entity.getUserName(),
                entity.getStatus()
        );
    }
}
