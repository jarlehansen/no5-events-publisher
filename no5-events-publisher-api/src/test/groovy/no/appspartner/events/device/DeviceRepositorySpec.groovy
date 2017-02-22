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

    def "Store and get devices"() {
        given:
        def device1 = new Device(token: "123", registered: System.currentTimeMillis())
        def device2 = new Device(token: "1234", registered: System.currentTimeMillis())

        when:
        repository.save(device1)
        repository.save(device2)
        def devices = repository.findAllDevices()

        then:
        devices.size() == 3
    }
}
