package dev.brianmviana.events.resouces;

import java.util.ArrayList;
import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.brianmviana.events.models.Event;
import dev.brianmviana.events.repository.EventRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="Events rest API")
@RestController
@RequestMapping("/events")
public class EventResource {

	@Autowired
	private EventRepository eventRepository;
	
	@ApiOperation(value="Return a events list")
	@GetMapping(produces="application/json")
	public @ResponseBody ArrayList<Event> listEvent() {
		Iterable<Event> eventslist = eventRepository.findAll();
		ArrayList<Event> events = new ArrayList<Event>();
		for (Event event : eventslist) {
			long id = event.getId();
			//Method method = EventResource.class.getMethod("getEvent", Long.class);
			//Link link = linkTo(method, id).withSelfRel();
			event.add(linkTo(methodOn(EventResource.class).getEvent(id)).withSelfRel());
			events.add(event);
		}
		return events;
	}
	
	@ApiOperation(value="Return a event")
	@GetMapping(value = "/{id}",produces = "application/json")
	public @ResponseBody Event getEvent(@PathVariable(value = "id") long id) {
		Event event = eventRepository.findById(id);
		event.add(linkTo(methodOn(EventResource.class).listEvent()).withRel("Events list"));
		return event;
	}

	@ApiOperation(value="Save a new event")
	@PostMapping
	public Event saveEvent(@RequestBody @Valid Event event) {
		return eventRepository.save(event);
	}

	@ApiOperation(value="delete a event")
	@DeleteMapping
	public Event deleteEvent(@RequestBody Event event) {
		eventRepository.delete(event);
		return event;
	}
	
	
}
