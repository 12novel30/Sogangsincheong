package com.swm.mini.controller;

import com.swm.mini.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class EventController {

    // createEvent
    @PostMapping("/events/create/{userId}")
    public void createUser(@Valid @RequestBody UserDto.Request request) {
        userService.createUser(request);
    }

    // joinEvent
    @PostMapping("/events/join/{userId}")
    public void createUser(@Valid @RequestBody UserDto.Request request) {
        userService.createUser(request);
    }

    // 이벤트에서 나가기
    @DeleteMapping("/events/out/{userId}")
    public void createUser(@Valid @RequestBody UserDto.Request request) {
        userService.createUser(request);
    }

    // 이벤트 삭제하기
    @DeleteMapping("/events/{userId}")
    public void createUser(@Valid @RequestBody UserDto.Request request) {
        userService.createUser(request);
    }

    // 모든 이벤트 띄우기
    @GetMapping("/events")
    public void createUser(@Valid @RequestBody UserDto.Request request) {
        userService.createUser(request);
    }

    // 내가 참여한 이벤트 띄우기
    @GetMapping("/events/{userId}")
    public void createUser(@Valid @RequestBody UserDto.Request request) {
        userService.createUser(request);
    }

}
