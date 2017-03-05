package no.appspartner.events.device

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.hamcrest.CoreMatchers.equalTo
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

class DeviceControllerSpec extends Specification {
    private DeviceController deviceController
    private MockMvc mockMvc
    private DeviceService deviceService

    void setup() {
        deviceService = Mock(DeviceService)
        deviceController = new DeviceController(deviceService: deviceService)
        mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build()
    }

    def "Get registered devices"() {
        when:
        def response = mockMvc.perform(get('/devices'))

        then:
        1 * deviceService.getRegisteredDevices() >> [new Device(token: "123")]
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath('$[0].token').value(equalTo("123")))
    }

    def "Store device"() {
        given:
        def device = new Device(token: "123", registered: System.currentTimeMillis())
        def deviceJSON = new ObjectMapper().writeValueAsString(device)

        when:
        def response = mockMvc.perform(post('/devices').content(deviceJSON).contentType(MediaType.APPLICATION_JSON_UTF8))

        then:
        1 * deviceService.storeDevice(_ as Device)
        response.andExpect(status().isOk())
    }

    def "Delete all devices"() {
        when:
        def response = mockMvc.perform(delete('/devices'))

        then:
        1 * deviceService.deleteAll()
        response.andExpect(status().isOk())
    }

}
