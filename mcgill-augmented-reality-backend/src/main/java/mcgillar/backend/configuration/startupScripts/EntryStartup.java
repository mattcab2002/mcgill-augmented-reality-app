package mcgillar.backend.configuration.startupScripts;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mcgillar.backend.model.location.Location;
import mcgillar.backend.services.building.BuildingService;
import mcgillar.backend.services.location.LocationService;
import mcgillar.backend.services.user.AppUserInfoService;
import mcgillar.backend.services.user.AppUserService;

@Service
@AllArgsConstructor
public class EntryStartup {
    
    private BuildingService buildingService;

	private LocationService locationService;

	private AppUserService appUserService;

	private AppUserInfoService appUserInfoService;

    public void startup() {
        appUserService.createUser("dev_user", "dev_pword");
        appUserService.createUser("AR_CLIENT_ID", "AR_CLIENT_SECRET");
        appUserInfoService.addOrModifyCountryCodeAndPhoneNumber(1, "5147437101", "dev_user");
		appUserInfoService.addOrModifyEmail("dev.user@mail.mcgill.ca", "dev_user");
		appUserInfoService.addOrModifyFirstAndLastName("Dev", "User", "dev_user");
		appUserInfoService.addOrModifyStudentNumber((long) 123456789, "dev_user");
        setUpBuildings();
    }

    private void setUpBuildings() {
		Location trottier = locationService.createLocation("H3A 2B3", "3630 Rue University, Montreal QC", 45.507392652505075, -73.57878717972841, null);
		buildingService.createBuilding("Troitter Building", trottier, "ENGTR");

		Location wong = locationService.createLocation("H3A 0C5", "3610 Rue University, Montreal QC", 45.5066955721648, -73.57862770873622, null);
		buildingService.createBuilding("M. H. Wong Building", wong, "MH");

		Location genomeCenter = locationService.createLocation("H3A 0G1", "740 Dr Penfield Ave, Montreal QC", 45.50693645707051, -73.57930870936448, null);
		buildingService.createBuilding("McGill Genome Center", genomeCenter, "GC");

		Location sherby = locationService.createLocation("H3A 0B8", "680 Sherbrooke St W, Montreal QC", 45.50482034608999, -73.57340365102091, null);
		buildingService.createBuilding("680 Sherbrooke", sherby, "680");

		Location mcConell = locationService.createLocation("H2X 2G6", "3380 Blvd Robert-Bourassa, Montreal QC", 45.50611767441953, -73.57644933043366, null);
		buildingService.createBuilding("McConnell Engineering Building", mcConell, "ENGMC");

		Location macdonald = locationService.createLocation("H3A 0C3", "817 Sherbrooke St W, Montreal QC", 45.5055989496, -73.5766544175, null);
		buildingService.createBuilding("Macdonald Engineering Building", macdonald, "MC");

		Location leacock = locationService.createLocation("H3A 2T7", "855 Sherbrooke St W, Montreal, QC", 45.50432, -73.57793, null);
		buildingService.createBuilding("Stephen Leacock Building", leacock, "LEA");

		Location adams = locationService.createLocation("H3A 0E8", "3450 University Street, Montreal QC", 45.5057672817931, -73.57522258870566, null);
		buildingService.createBuilding("Frank Dawson Adams Building", adams, "FDA");

		Location harrington = locationService.createLocation("H3A OC2", "815 Sherbrooke St W, Montreal QC", 45.505550605528406, -73.57575977336441, null);
		buildingService.createBuilding("Macdonald-Harrington Building", harrington, "MDHAR");

		Location maassChemistry = locationService.createLocation("H3A 0B8", "801 Sherbrooke St W, Montreal QC", 45.50512340287696, -73.57406918870562, null);
		buildingService.createBuilding("Maass Chemistry Building", maassChemistry, "MAASS");
	}

}
