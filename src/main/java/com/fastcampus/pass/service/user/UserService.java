package com.fastcampus.pass.service.user;


import com.fastcampus.pass.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(final String userId) {
        return User.from(userRepository.findByUserId(userId));
    }
}
