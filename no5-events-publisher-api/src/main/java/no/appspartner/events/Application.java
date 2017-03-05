package no.appspartner.events;

import com.github.springfox.loader.EnableSpringfox;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import io.swagger.annotations.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;

@EnableSpringfox(
        @Info(title = "no5-events-publisher", version = "0.0.1-SNAPSHOT")
)
@SpringBootApplication
public class Application {

    @Value("${CERTIFICATE_PASSWORD:}")
    private String certificatePassword;

    public void init() {
        InputStream inputStream = Application.class.getClassLoader().getResourceAsStream("AppsPartnerPush.p12");
        ApnsService service = APNS.newService().withCert(inputStream, certificatePassword).withSandboxDestination().build();

        String payload = APNS.newPayload().alertBody("Testing testing 123 123").build();
        service.push("", payload);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
