package no.appspartner.events.event

import spock.lang.Specification

class EventServiceSpec extends Specification {
    private EventService eventService
    private EventRepository eventRepository

    void setup() {
        eventRepository = Mock(EventRepository)
        eventService = new EventService(eventRepository: eventRepository)
    }

    def "Get all events"() {
        when:
        def events = eventService.getAllEvents()

        then:
        1 * eventRepository.findAllEvents() >> [new Event(), new Event()]
        events.size() == 2
    }

    def "Store event"() {
        given:
        def event = new Event()

        when:
        eventService.publishEvent(event)

        then:
        1 * eventRepository.save(event) >> new Event(id: 1)
    }
}
