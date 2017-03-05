package no.appspartner.events.device

import spock.lang.Specification

class DeviceServiceSpec extends Specification {
    private DeviceService deviceService
    private DeviceRepository deviceRepository

    void setup() {
        deviceRepository = Mock(DeviceRepository)
        deviceService = new DeviceService(deviceRepository: deviceRepository)
    }

    def "Set registered timestamp"() {
        given:
        def device = new Device(token: "123")

        when:
        def registeredDevice = deviceService.storeDevice(device)

        then:
        1 * deviceRepository.save(_ as Device)
        registeredDevice.token == "123"
        registeredDevice.registered > 0L
    }

    def "Get registered devices"() {
        when:
        def devices = deviceService.getRegisteredDevices()

        then:
        1 * deviceRepository.findAllDevices() >> [new Device()]
        devices.size() == 1
    }

    def "Delete all devices"() {
        when:
        deviceService.deleteAll()

        then:
        1 * deviceRepository.deleteAll()
    }
 }
