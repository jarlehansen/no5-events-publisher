package no.appspartner.events.event

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.hamcrest.CoreMatchers.equalTo
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class EventControllerSpec extends Specification {
    private EventController eventController
    private EventService eventService
    private MockMvc mockMvc

    void setup() {
        eventService = Mock(EventService)
        eventController = new EventController(eventService: eventService)
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build()
    }

    def "Get all events"() {
        when:
        def response = mockMvc.perform(get("/events"))

        then:
        1 * eventService.getAllEvents() >> [new Event(id: 1, title: "Title1"), new Event(id: 2, title: "Title2")]
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath('$[0].id').value(equalTo(1)))
                .andExpect(jsonPath('$[0].title').value(equalTo("Title1")))
                .andExpect(jsonPath('$[1].id').value(equalTo(2)))
                .andExpect(jsonPath('$[1].title').value(equalTo("Title2")))
    }

    def "Add event"() {
        given:
        def event = new Event()
        def eventJSON = new ObjectMapper().writeValueAsString(event)

        when:
        def response = mockMvc.perform(post("/events").content(eventJSON).contentType(MediaType.APPLICATION_JSON_UTF8))

        then:
        1 * eventService.publishEvent(_ as Event)
        response.andExpect(status().isOk())
    }
}
