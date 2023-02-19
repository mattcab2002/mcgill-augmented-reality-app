package mcgillar.backend.model.building;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mcgillar.backend.model.location.Location;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Location location;

    private String name;

    private String shortCode;

    public Building(Location location, String name, String shortCode) {
        this.location = location;
        this.name = name;
    }
}
