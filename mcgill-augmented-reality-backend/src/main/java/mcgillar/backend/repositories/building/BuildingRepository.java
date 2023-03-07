package mcgillar.backend.repositories.building;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgillar.backend.model.building.Building;

public interface BuildingRepository extends CrudRepository<Building, Integer> {
    List<Building> findAll();
}
