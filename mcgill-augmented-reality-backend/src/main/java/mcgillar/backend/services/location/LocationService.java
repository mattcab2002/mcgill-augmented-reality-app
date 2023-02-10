package mcgillar.backend.services.location;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mcgillar.backend.model.location.Location;
import mcgillar.backend.repositories.location.LocationRepository;

@Service
@AllArgsConstructor
public class LocationService {
    
    private LocationRepository locationRepository;
    
    public Location createLocation(String postalCode, String address, Double latitude, Double longitude, Double altitude) {
        if (latitude == null || longitude == null) return null;
        Location location = new Location(postalCode, address, latitude, longitude, altitude);
        return locationRepository.save(location);
    }

}
