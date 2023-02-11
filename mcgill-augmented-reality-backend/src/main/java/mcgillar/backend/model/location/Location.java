package mcgillar.backend.model.location;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String postalCode;

    private String address;

    private Double latitude;

    private Double longitude;

    private Double altitude;


    public Location(String postalCode, String address, Double latitude, Double longitude, Double altitude) {
        this.postalCode = postalCode;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }
}
