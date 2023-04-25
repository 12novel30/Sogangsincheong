package com.swm.mini.service;

import com.swm.mini.dto.EventDto;
import com.swm.mini.dto.ParticipantDto;
import com.swm.mini.entity.Event;
import com.swm.mini.entity.Participant;
import com.swm.mini.entity.User;
import com.swm.mini.exception.MiniException;
import com.swm.mini.repository.EventRepository;
import com.swm.mini.repository.ParticipantRepository;
import com.swm.mini.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.swm.mini.exception.MiniErrorCode.*;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final ParticipantRepository participantRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createEvent(EventDto.Request request, String userId) {
        participantRepository.save(
                Participant.builder()
                        .event(eventRepository.save(
                                Event.builder()
                                        .title(request.getTitle())
                                        .limit(request.getLimit())
                                        .endDate(request.getEndDate())
                                        .startDate(request.getStartDate())
                                        .category(request.getCategory())
                                        .build()))
                        .user(getUserEntity(userId))
                        .build());
    }

    @Transactional
    public void validateEventLimitThenCreateParticipant(
            ParticipantDto.Request request) {
        if (eventRepository.countById(request.getEventId())
                > participantRepository.countByEvent_Id(request.getEventId()) + 1)
            validateAlreadyJoinedThenCreateParticipant(request);
        else throw new MiniException(LIMIT_OVER);
    }

    @Transactional(readOnly = true)
    public List<EventDto.Response> getAllEvents() {
        return eventRepository.findAll()
                .stream().map(EventDto.Response::fromEntity).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EventDto.Response> getMyEvents(String userId) {
        List<Participant> partiList = participantRepository.findByUser_Id(userId);
        List<EventDto.Response> myEventList = new ArrayList<>();
        for (Participant p : partiList) {
            myEventList.add(
                    EventDto.Response.fromEntity(
                            getEventEntity(p.getEvent().getId())));
        }
        return myEventList;
    }

    private User getUserEntity(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new MiniException(NO_USER));
    }

    private void validateAlreadyJoinedThenCreateParticipant(
            ParticipantDto.Request request) {
        if (!participantRepository.existsByUser_IdAndEvent_Id(
                request.getUserId(), request.getEventId()))
            participantRepository.save(
                    Participant.builder()
                            .user(getUserEntity(request.getUserId()))
                            .event(getEventEntity(request.getEventId()))
                            .memo(request.getMemo())
                            .build());
        else throw new MiniException(ALREADY_JOINED);
    }

    @Transactional(readOnly = true)
    private Event getEventEntity(Long eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new MiniException(NO_EVENT));
    }

}
