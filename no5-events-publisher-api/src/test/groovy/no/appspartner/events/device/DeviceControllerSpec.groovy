package no.appspartner.events.device

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class DeviceControllerSpec extends Specification {
    private DeviceController deviceController
    private MockMvc mockMvc

    void setup() {
        deviceController = new DeviceController()
        mockMvc = MockMvcBuilders.standaloneSetup(deviceController).build()
    }

    def "Get registered devices"() {
        when:
        def response = mockMvc.perform(get('/devices'))

        then:
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
    }


}
