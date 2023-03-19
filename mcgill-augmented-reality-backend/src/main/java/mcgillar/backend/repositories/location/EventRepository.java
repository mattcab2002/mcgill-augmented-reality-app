package mcgillar.backend.repositories.location;

import org.springframework.data.repository.CrudRepository;

import mcgillar.backend.model.location.Event;

public interface EventRepository extends CrudRepository<Event, Integer> {
    Event findEventById(Integer id);
}
