package mcgillar.backend.controllers.location;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import mcgillar.backend.TO.location.RouteRequestTO;
import mcgillar.backend.TO.location.RouteResponseTO;
import mcgillar.backend.model.location.Route;
import mcgillar.backend.services.location.RouteService;

@RestController
@RequestMapping("/route")
@AllArgsConstructor
public class RouteController {

    private RouteService routeService;
 
    @GetMapping
    public ResponseEntity<?> getRouteById(@RequestParam Integer Id, Authentication authentication) {
        try {
            Route route = routeService.getRouteById(authentication, Id);
            return new ResponseEntity<RouteResponseTO>(RouteResponseTO.from(route), HttpStatus.ACCEPTED);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }catch (BadCredentialsException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createRoute(
        @RequestBody RouteRequestTO routeRequest,
        Authentication authentication
    ) {
        try {
            Route route = routeService.createRoute(authentication.getName(), routeRequest);
            RouteResponseTO to = RouteResponseTO.from(route);
            return new ResponseEntity<RouteResponseTO>(to, HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    } 
}
