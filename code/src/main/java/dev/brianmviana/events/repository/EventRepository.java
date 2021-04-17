package dev.brianmviana.events.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.brianmviana.events.models.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, String> {
	
	Event findById(long id);
	
}
