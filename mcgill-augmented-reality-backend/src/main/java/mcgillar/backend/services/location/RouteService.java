package mcgillar.backend.services.location;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mcgillar.backend.TO.location.RouteRequestTO;
import mcgillar.backend.model.location.Location;
import mcgillar.backend.model.location.Route;
import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.repositories.location.RouteRepository;
import mcgillar.backend.services.user.AppUserService;

@Service
@AllArgsConstructor
public class RouteService {

    private AppUserService appUserService;
    private LocationService locationService;
    private RouteRepository routeRepository;
    
    public Route createRoute(String username, RouteRequestTO route) {
        AppUser user = appUserService.getUserByUsername(username);
        Location start = locationService.createLocation(route.getStartLocation());
        Location end = locationService.createLocation(route.getEndLocationTO());
        Route newRoute = new Route();
        newRoute.setStartLocation(start);
        newRoute.setEndLocation(end);
        newRoute.setUser(user);
        return routeRepository.save(newRoute);
    }
}
