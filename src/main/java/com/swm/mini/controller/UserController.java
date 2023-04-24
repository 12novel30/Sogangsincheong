package com.swm.mini.controller;

import com.swm.mini.dto.UserDto;
import com.swm.mini.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    @PostMapping("/users")
    public void createUser(@Valid @RequestBody UserDto.Request request) {
        userService.createUser(request);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable final String userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/users/{userId}")
    public UserDto.Response getUser(@PathVariable final String userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/users/{userId}")
    public UserDto.Response updateUser(
            @PathVariable final String userId,
            @Valid @RequestBody UserDto.Request request) {
        return userService.updateUser(userId, request);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody UserDto.Login loginUser) {
        return userService.login(loginUser);
    }

}
