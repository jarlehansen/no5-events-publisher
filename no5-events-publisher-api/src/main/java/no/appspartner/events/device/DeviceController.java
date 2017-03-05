package no.appspartner.events.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/devices", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Device> getRegisteredDevices() {
        return deviceService.getRegisteredDevices();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void storeDevice(@RequestBody Device device) {
        deviceService.storeDevice(device);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllDevices() {
        deviceService.deleteAll();
    }

}
