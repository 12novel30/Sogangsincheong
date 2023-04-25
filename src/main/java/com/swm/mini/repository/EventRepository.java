package com.swm.mini.repository;

import com.swm.mini.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    long countById(long id);
}
