package no.appspartner.events.event

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ActiveProfiles("test")
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class EventRepositorySpec extends Specification {

    private static final long FIRST_TIMESTAMP = 1486410780554L
    private static final long SECOND_TIMESTAMP = 1486410780555L
    private static final long THIRD_TIMESTAMP = 1486410780556L

    @Autowired
    private EventRepository eventRepository

    def "Get all events sorted by latest timestamp first"() {
        when:
        def events = eventRepository.findAllEvents()

        then:
        events.size() == 3
        events[0].title == "Title3"
        events[0].timestamp == THIRD_TIMESTAMP

        events[1].title == "Title2"
        events[1].timestamp == SECOND_TIMESTAMP

        events[2].title == "Title1"
        events[2].timestamp == FIRST_TIMESTAMP
    }

    def "Save and verify event id"() {
        given:
        def event = new Event(title: "Title4")

        when:
        def storedEvent = eventRepository.save(event)

        then:
        storedEvent.id > 0
        storedEvent.title == "Title4"
    }
}
