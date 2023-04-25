package com.swm.mini.service;

import com.swm.mini.dto.ParticipantDto;
import com.swm.mini.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantRepository participantRepository;
    @Transactional
    public void deleteParticipant(Long participantId) {
        participantRepository.deleteById(participantId);
    }
}
