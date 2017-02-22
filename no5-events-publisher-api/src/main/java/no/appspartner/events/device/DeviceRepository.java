package no.appspartner.events.device;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DeviceRepository extends CrudRepository<Device, String> {

    @Query("select d from Device d")
    List<Device> findAllDevices();

}
