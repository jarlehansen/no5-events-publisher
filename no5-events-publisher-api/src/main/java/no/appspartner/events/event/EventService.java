package no.appspartner.events.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAllEvents();
    }

    public void publishEvent(Event event) {
        eventRepository.save(event);
    }
}
