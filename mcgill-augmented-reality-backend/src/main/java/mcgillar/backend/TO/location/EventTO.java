package mcgillar.backend.TO.location;

import java.util.Date;

import mcgillar.backend.TO.user.ValidatedUser;
import mcgillar.backend.model.location.Event;
import mcgillar.backend.model.location.Location;

public class EventTO {

    public Integer id;
    public Location location;
    public ValidatedUser organizer;
    public Date date;
    public String name;

    public static EventTO convert(Event event) {
        EventTO eventTO = new EventTO();
        eventTO.id = event.getId();
        eventTO.location = event.getLocation();
        eventTO.organizer = new ValidatedUser(event.getOrganizer());
        eventTO.date = event.getDate();
        eventTO.name = event.getName();
        return eventTO;
    }
}
