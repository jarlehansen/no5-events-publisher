package no.appspartner.events.device

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

@ActiveProfiles("test")
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class DeviceRepositorySpec extends Specification {

    @Autowired
    private DeviceRepository repository

    def "Get registered devices"() {
        when:
        def devices = repository.findAllDevices()

        then:
        devices.size() == 1
    }


}
