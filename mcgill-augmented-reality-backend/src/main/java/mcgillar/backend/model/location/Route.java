package mcgillar.backend.model.location;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mcgillar.backend.model.user.AppUser;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private AppUser user;

    @OneToOne
    private Location startLocation;

    @OneToOne
    private Location endLocation;

    // ... maybe has a list of locations

}
