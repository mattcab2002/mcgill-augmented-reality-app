package mcgillar.backend.controllers.building;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import mcgillar.backend.model.building.Building;
import mcgillar.backend.services.building.BuildingService;

@RestController
@AllArgsConstructor
@RequestMapping("/building/")
public class BuildingController {

    private BuildingService buildingService;

    @GetMapping("get-all-mcgill-buildings")
    public ResponseEntity<List<Building>> getAllMcgillBuildings() {
        List<Building> allMcgillBuildings = buildingService.getBuildingList();
        if (allMcgillBuildings.size() > 0)
            return new ResponseEntity<List<Building>>(allMcgillBuildings, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
