package mcgillar.backend.model.location;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mcgillar.backend.model.user.AppUser;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Location location;
    
    private AppUser organizer;

    private Date date;

}
