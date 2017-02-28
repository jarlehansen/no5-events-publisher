package no.appspartner.events.notification;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import no.appspartner.events.Application;
import no.appspartner.events.device.Device;
import no.appspartner.events.device.DeviceService;
import no.appspartner.events.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.util.List;

@Service
@Profile("!test")
public class NotificationService {
    private ApnsService apnsService;

    @Autowired
    private DeviceService deviceService;

    @Value("${certificate-password}")
    private String certificatePassword;

    @PostConstruct
    public void init() {
        InputStream inputStream = Application.class.getClassLoader().getResourceAsStream("AppsPartnerPush.p12");
        apnsService = APNS.newService().withCert(inputStream, certificatePassword).withSandboxDestination().build();
    }

    public void push(Event event) {
        List<Device> registeredDevices = deviceService.getRegisteredDevices();
        String payload = APNS.newPayload().alertBody(event.getTitle()).build();
        registeredDevices.forEach(device ->
                apnsService.push(device.getToken(), payload)
        );
    }
}
