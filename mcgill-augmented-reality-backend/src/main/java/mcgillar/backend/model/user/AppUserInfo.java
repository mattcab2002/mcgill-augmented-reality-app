package mcgillar.backend.model.user;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import mcgillar.backend.model.building.Building;
import mcgillar.backend.model.schedule.WeeklySchedule;

@Entity
@Data
@NoArgsConstructor
public class AppUserInfo {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;

    private String lastName;

    private Long studentNumber;

    private String phoneNumber;

    private Integer countryCode;

    private String email;

    @OneToOne
    private AppUser user;

    @OneToOne
    private WeeklySchedule schedule;

    @OneToMany
    private Set<AppUser> friends = new HashSet<AppUser>();

    @OneToMany
    private Set<Building> favouriteBuildings = new HashSet<Building>();

    public AppUserInfo(AppUser user) {
        this.user = user;
    }

    // open ended ...

}
