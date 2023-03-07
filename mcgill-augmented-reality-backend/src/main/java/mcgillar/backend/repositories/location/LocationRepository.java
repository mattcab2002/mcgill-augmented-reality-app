package mcgillar.backend.repositories.location;

import org.springframework.data.repository.CrudRepository;

import mcgillar.backend.model.location.Location;

public interface LocationRepository extends CrudRepository<Location, Integer> {
    
}
