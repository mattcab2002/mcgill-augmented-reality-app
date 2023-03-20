package mcgillar.backend.services.location;

import java.util.Date;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mcgillar.backend.TO.location.EventTO;
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

    public Event createEvent(String organizerName, EventTO eventTO) {
        Event event = new Event();

        AppUser organizer = appUserService.getUserByUsername(eventTO.organizer.username);
        event.setOrganizer(organizer);
        event.setDate(eventTO.date);
        event.setLocation(eventTO.location);
        event.setName(eventTO.name);

        return eventRepository.save(event);
    }

    public Event getEventById(Integer id) {
        Event event = eventRepository.findEventById(id);
        if (event == null) throw new IllegalArgumentException("Route not Found");
        return event;
    }
}
