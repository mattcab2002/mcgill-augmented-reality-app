package mcgillar.backend.services.location;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
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

    public Route getRouteById(Authentication authentication, Integer Id) {
        AppUser user = appUserService.getUserByUsername(authentication.getName());
        Route route = routeRepository.findRouteById(Id);
        if (route == null) throw new IllegalArgumentException("Route not Found");
        if (route.getUser().getId() != user.getId()) throw new BadCredentialsException("Forbidden to view this ressource");
        return route;
    }
}
