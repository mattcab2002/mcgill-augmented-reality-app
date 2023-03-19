package mcgillar.backend.controllers.location;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import mcgillar.backend.TO.location.EventTO;
import mcgillar.backend.TO.location.LocationTO;
import mcgillar.backend.model.location.Event;
import mcgillar.backend.services.location.EventService;

@RestController
@RequestMapping("/event/")
@AllArgsConstructor
public class EventController {
    
    private EventService eventService;

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody LocationTO location, Authentication authentication, @DateTimeFormat(pattern = "dd.MM.yyyy") Date date, @RequestParam String eventName) {
        try {
            Event event = eventService.createEvent(authentication.getName(), location, date, eventName);
            return new ResponseEntity<EventTO>(EventTO.convert(event), HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getEvent(@RequestParam Integer id, Authentication authentication) {
        try {
            Event event = eventService.getEventById(id);
            return new ResponseEntity<EventTO>(EventTO.convert(event), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
