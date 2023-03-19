package mcgillar.backend.services.location;

import java.util.Date;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mcgillar.backend.TO.location.LocationTO;
import mcgillar.backend.model.location.Event;
import mcgillar.backend.model.location.Location;
import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.repositories.location.EventRepository;
import mcgillar.backend.services.user.AppUserService;

@Service
@AllArgsConstructor
public class EventService {
    
    private EventRepository eventRepository;
    private AppUserService appUserService;

    public Event createEvent(String organizerName, LocationTO location, Date date, String eventName) {
        AppUser organizer = appUserService.getUserByUsername(organizerName);

        Event event = new Event();
        event.setOrganizer(organizer);
        event.setDate(date);
        event.setLocation(convertLocationTO(location));
        event.setName(eventName);

        return eventRepository.save(event);
    }

    public Event getEventById(Integer id) {
        Event event = eventRepository.findEventById(id);
        if (event == null) throw new IllegalArgumentException("Route not Found");
        return event;
    }

    private Location convertLocationTO(LocationTO locationTO) {
        Location location = new Location();
        location.setPostalCode(locationTO.postalCode);
        location.setAddress(locationTO.address);
        location.setLatitude(locationTO.latitude);
        location.setLongitude(locationTO.longitude);
        location.setAltitude(locationTO.altitude);
    
        return location;
    }
}
