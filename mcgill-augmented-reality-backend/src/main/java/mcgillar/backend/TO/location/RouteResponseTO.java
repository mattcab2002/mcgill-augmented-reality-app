package mcgillar.backend.TO.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mcgillar.backend.TO.user.ValidatedUser;
import mcgillar.backend.model.location.Location;
import mcgillar.backend.model.location.Route;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RouteResponseTO {

    public Integer Id;
    public ValidatedUser user;
    public Location startLocation;
    public Location endLocation;

    public static RouteResponseTO from(Route route) {
        RouteResponseTO to = new RouteResponseTO();
        to.setEndLocation(route.getEndLocation());
        to.setStartLocation(route.getStartLocation());
        to.setId(route.getId());
        to.setUser(ValidatedUser.getInstance(route.getUser()));
        return to;
    }
    
}
