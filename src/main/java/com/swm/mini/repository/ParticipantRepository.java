package com.swm.mini.repository;

import com.swm.mini.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    long countByEvent_Id(long id);

    boolean existsByUser_IdAndEvent_Id(String id, long id1);

    List<Participant> findByEvent_Id(long id);

    List<Participant> findByUser_Id(String id);

}
