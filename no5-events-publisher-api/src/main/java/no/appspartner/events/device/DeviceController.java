package no.appspartner.events.device;

import com.google.common.collect.Lists;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/devices", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DeviceController {

    @RequestMapping(method = RequestMethod.GET)
    public List<Device> getRegisteredDevices() {
        Device device = new Device();
        device.setRegistered(123L);
        device.setToken("2123123");
        return Lists.newArrayList(device);
    }

}
