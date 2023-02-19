package mcgillar.backend.services.building;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mcgillar.backend.model.building.Building;
import mcgillar.backend.model.location.Location;
import mcgillar.backend.repositories.building.BuildingRepository;

@Service
@AllArgsConstructor
public class BuildingService {

    private BuildingRepository buildingRepository;
    
    public Building createBuilding(String name, Location location, String shortCode) {
        if (name == null || name.length() == 0 || location == null) return null;
        Building building = new Building(location, name, shortCode);
        return buildingRepository.save(building);
    }

    public List<Building> getBuildingList() {
        return buildingRepository.findAll();
    }


}
