package no.appspartner.events.event;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventRepository extends CrudRepository<Event, Integer> {
    @Query("select e from Event e order by e.timestamp desc")
    List<Event> findAllEvents();
}
