package no.appspartner.events.device;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Device {
    @Id
    private String token;
    private long registered;
}
