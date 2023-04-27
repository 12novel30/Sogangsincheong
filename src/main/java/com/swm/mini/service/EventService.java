package com.swm.mini.service;

import com.swm.mini.dto.EventDto;
import com.swm.mini.dto.ParticipantDto;
import com.swm.mini.entity.Event;
import com.swm.mini.entity.Participant;
import com.swm.mini.entity.User;
import com.swm.mini.exception.MiniException;
import com.swm.mini.repository.EventRepository;
import com.swm.mini.repository.ParticipantRepository;
import com.swm.mini.repository.UsersRepository;
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
    private final UsersRepository userRepository;

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
        if (getEventEntity(request.getEventId()).getLimit()
                >= participantRepository.countByEvent_Id(request.getEventId()) + 1)
            validateAlreadyJoinedThenCreateParticipant(request);
        else throw new MiniException(LIMIT_OVER);
    }

    @Transactional(readOnly = true)
    public List<EventDto.Response> getAllEvents() {
        List<EventDto.Response> list = eventRepository.findAll()
                .stream()
                .map(EventDto.Response::fromEntity)
                .collect(Collectors.toList());
        for (EventDto.Response e : list) {
            e.setNowParticipant(
                    (int) participantRepository.countByEvent_Id(e.getId()));
        }
        return list;
    }

    @Transactional(readOnly = true)
    public List<EventDto.Response> getMyEvents(String userId) {
        List<Participant> partiList = participantRepository.findByUser_Id(userId);
        List<EventDto.Response> myEventList = new ArrayList<>();
        for (Participant p : partiList) {
            EventDto.Response response = EventDto.Response.fromEntity(
                    getEventEntity(p.getEvent().getId()));
            response.setNowParticipant(
                    (int) participantRepository.countByEvent_Id(p.getEvent().getId()));
            myEventList.add(response);
        }
        return myEventList;
    }

    @Transactional(readOnly = true)
    public EventDto.Detail getEventsDetail(Long eventId) {
        EventDto.Detail detail = EventDto.Detail.fromEntity(getEventEntity(eventId));
        detail.setPartiList(
                participantRepository.findByEvent_Id(eventId)
                        .stream()
                        .map(ParticipantDto.Response::fromEntity)
                        .collect(Collectors.toList()));
        detail.setNowParticipant(detail.getPartiList().size());
        return detail;
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
