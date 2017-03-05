package no.appspartner.events.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public Device storeDevice(Device device) {
        device.setRegistered(System.currentTimeMillis());
        deviceRepository.save(device);
        return device;
    }

    public List<Device> getRegisteredDevices() {
        return deviceRepository.findAllDevices();
    }

    public void deleteAll() {
        deviceRepository.deleteAll();
    }
}
