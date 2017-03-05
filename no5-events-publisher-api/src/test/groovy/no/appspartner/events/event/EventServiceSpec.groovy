package no.appspartner.events.event

import no.appspartner.events.notification.NotificationService
import spock.lang.Specification

class EventServiceSpec extends Specification {
    private EventService eventService
    private NotificationService notificationService
    private EventRepository eventRepository

    void setup() {
        eventRepository = Mock(EventRepository)
        notificationService = Mock(NotificationService)
        eventService = new EventService(eventRepository: eventRepository, notificationService: notificationService)
    }

    def "Get all events"() {
        when:
        def events = eventService.getAllEvents()

        then:
        1 * eventRepository.findAllEvents() >> [new Event(), new Event()]
        events.size() == 2
    }

    def "Store and publish event"() {
        given:
        def event = new Event()

        when:
        eventService.publishEvent(event)

        then:
        1 * eventRepository.save(event) >> new Event(id: 1)
        1 * notificationService.push(_ as Event)
    }
}
