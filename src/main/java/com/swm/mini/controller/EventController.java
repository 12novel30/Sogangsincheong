package com.swm.mini.controller;

import com.swm.mini.dto.EventDto;
import com.swm.mini.dto.ParticipantDto;
import com.swm.mini.service.EventService;
import com.swm.mini.service.ParticipantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class EventController {
    private final EventService eventService;
    private final ParticipantService participantService;


    @PostMapping("/events/create/{userId}")
    public void createEvent(@Valid @RequestBody EventDto.Request request,
                            @PathVariable final String userId) {
        eventService.createEvent(request, userId);
    }

    @PostMapping("/events/join")
    public void joinEvent(@Valid @RequestBody ParticipantDto.Request request) {
        eventService.validateEventLimitThenCreateParticipant(request);
    }

    @DeleteMapping("/events/out/{participantId}")
    public void deleteParticipant(@PathVariable final Long participantId) {
        participantService.deleteParticipant(participantId);
    }

    // 이벤트 삭제하기 -> 만들지 말자 @DeleteMapping("/events/{userId}")

    // 모든 이벤트 띄우기
    @GetMapping("/events")
    public List<EventDto.Response> getAllEvents() {
        return eventService.getAllEvents();
    }

    // 내가 참여한 이벤트 띄우기
    @GetMapping("/events/{userId}")
    public List<EventDto.Response> getMyEvents(@PathVariable final String userId) {
        return eventService.getMyEvents(userId);
    }

    // 이벤트 업데이트 필요함

    @GetMapping("/events/detail/{eventId}")
    public EventDto.Detail getEventsDetail(@PathVariable final Long eventId) {
        return eventService.getEventsDetail(eventId);
    }


}
