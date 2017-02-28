package no.appspartner.events.notification

import com.notnoop.apns.ApnsService
import no.appspartner.events.device.Device
import no.appspartner.events.device.DeviceService
import no.appspartner.events.event.Event
import spock.lang.Specification

class NotificationServiceSpec extends Specification {
    private NotificationService notificationService
    private DeviceService deviceService
    private ApnsService apnsService

    void setup() {
        apnsService = Mock(ApnsService)
        deviceService = Mock(DeviceService)
        notificationService = new NotificationService(apnsService: apnsService, deviceService: deviceService)
    }

    def "Push event notification"() {
        given:
        def event = new Event(id: 1)

        when:
        notificationService.push(event)

        then:
        1 * deviceService.getRegisteredDevices() >> [new Device(token: "1"), new Device(token: "2"), new Device(token: "3")]
        3 * apnsService.push(_ as String, _ as String)
    }
}
