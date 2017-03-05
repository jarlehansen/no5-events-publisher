package no.appspartner.events.event;

import lombok.extern.slf4j.Slf4j;
import no.appspartner.events.notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired(required = false)
    private NotificationService notificationService;

    public List<Event> getAllEvents() {
        return eventRepository.findAllEvents();
    }

    public void publishEvent(Event event) {
        eventRepository.save(event);
        if (notificationService == null) {
            log.info("NotificationService is not initialized, will not send notifications");
        } else {
            notificationService.push(event);
        }
    }

    public void deleteAll() {
        eventRepository.deleteAll();
    }
}
