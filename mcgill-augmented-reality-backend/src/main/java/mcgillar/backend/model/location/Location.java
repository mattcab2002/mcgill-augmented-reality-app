package mcgillar.backend.model.location;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Location {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String postalCode;

    private String address;

    private Long latitude;

    private Long longitude;

    private Long altitude;

    public Location(String postalCode, String address, Long latitude, Long longitude, Long altitude) {
        this.postalCode = postalCode;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }
}
