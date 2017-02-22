package no.appspartner.events.event;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(name = "seq_event", sequenceName = "seq_event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_event")
    private Integer id;
    private String title;
    private String eventInfo;
    private Long timestamp;
    private String eventUrl;
}
