package mcgillar.backend.repositories.location;

import org.springframework.data.repository.CrudRepository;

import mcgillar.backend.model.location.Route;

public interface RouteRepository extends CrudRepository<Route, Integer> {
    Route findRouteById(Integer id); 
}
