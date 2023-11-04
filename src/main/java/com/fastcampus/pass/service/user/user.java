package com.fastcampus.pass.service.user;

public record user(
    String userId,
    String userName,
    UserStatus status;
) {
}
