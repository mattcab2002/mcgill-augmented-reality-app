package mcgillar.backend.configuration.startupScripts;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mcgillar.backend.model.location.Location;
import mcgillar.backend.services.AppUserService;
import mcgillar.backend.services.building.BuildingService;
import mcgillar.backend.services.location.LocationService;

@Service
@AllArgsConstructor
public class EntryStartup {
    
    private BuildingService buildingService;

	private LocationService locationService;

	private AppUserService appUserService;

    public void startup() {
        appUserService.createUser("bob", "pass");
        setUpBuildings();
    }

    private void setUpBuildings() {
		Location trottier = locationService.createLocation("QC H3A 2B3", "3630 Rue University, Montréal", 45.507392652505075, -73.57878717972841, null);
		buildingService.createBuilding("Trottier", trottier, "ENGTR");

		Location wong = locationService.createLocation("QC H3A 0C5", "3610 Rue University, Montréal", 45.5066955721648, -73.57862770873622, null);
		buildingService.createBuilding("Wong", wong, "MH");

		Location genomeCenter = locationService.createLocation("Quebec H3A 0G1", "740 Dr Penfield Ave, Montreal", 45.50693645707051, -73.57930870936448, null);
		buildingService.createBuilding("Genome Center", genomeCenter, "GC");

		Location sherby = locationService.createLocation("Quebec H3A 0B8", "680 Sherbrooke St W, Montreal", 45.50482034608999, -73.57340365102091, null);
		buildingService.createBuilding("Sherby", sherby, "680");

		Location mconell = locationService.createLocation("QC H2X 2G6", "3380 Blvd Robert-Bourassa, Montréal", 45.50611767441953, -73.57644933043366, null);
		buildingService.createBuilding("McConnell", mconell, "ENGMC");
	}

}
