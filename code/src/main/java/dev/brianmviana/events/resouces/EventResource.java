package dev.brianmviana.events.resouces;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.brianmviana.events.models.Event;
import dev.brianmviana.events.repository.EventRepository;

@RestController
@RequestMapping("/events")
public class EventResource {

	@Autowired
	private EventRepository eventRepository;
	
	@GetMapping(produces="application/json")
	public @ResponseBody Iterable<Event> listEvent() {
		Iterable<Event> eventslist = eventRepository.findAll();
		return eventslist;
	}
	
	@PostMapping
	public Event saveEvent(@RequestBody @Valid Event event) {
		return eventRepository.save(event);
	}
	
	@DeleteMapping
	public Event deleteEvent(@RequestBody Event event) {
		eventRepository.delete(event);
		return event;
	}
	
	
}
