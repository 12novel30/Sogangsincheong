package com.swm.mini.service;

import com.swm.mini.dto.UserDto;
import com.swm.mini.entity.User;
import com.swm.mini.exception.MiniException;
import com.swm.mini.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.swm.mini.exception.MiniErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void createUser(@NonNull UserDto.Request request) {
        if (!validateUserIDRegistered(request.getUserId()))
            saveUserEntity(
                    User.builder()
                            .id(request.getUserId())
                            .nickname(request.getNickname())
                            .password(request.getPassword())
                            .build());
        else throw new MiniException(ALREADY_REGISTERED);
    }

    @Transactional
    public void deleteUser(String userId) {
        userRepository.deleteByUserId(userId);
    }

    public UserDto.Response getUser(String userId) {
        return UserDto.Response.fromEntity(getUserEntity(userId));
    }

    public UserDto.Response updateUser(String userId, UserDto.Request request) {
        User user = getUserEntity(userId);
        if (request.getUserId() != null)
            if (!validateUserIDRegistered(request.getUserId())
                    || request.getUserId().equals(user.getId()))
                user.setId(request.getUserId());
            else throw new MiniException(ALREADY_REGISTERED);
        if (request.getNickname() != null)
            user.setNickname(request.getNickname());
        if (request.getPassword() != null)
            user.setPassword(request.getPassword());
        return UserDto.Response.fromEntity(saveUserEntity(user));
    }

    public String login(UserDto.Login loginUser) {
        User user = getUserEntity(loginUser.getUserId());
        if (loginUser.getPassword().equals(user.getPassword()))
            return user.getId();
        else throw new MiniException(WRONG_PASSWORD);
    }

    @Transactional(readOnly = true)
    private boolean validateUserIDRegistered(@NonNull String userId) {
        return userRepository.existsByUserId(userId);
    }

    @Transactional
    private User saveUserEntity(@NonNull User user) {
        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    private User getUserEntity(String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new MiniException(NO_USER));
    }
}
