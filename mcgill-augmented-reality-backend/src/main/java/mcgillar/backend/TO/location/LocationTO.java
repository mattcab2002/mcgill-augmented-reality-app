package mcgillar.backend.TO.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LocationTO {

    public String postalCode;
    public String address;
    public Double latitude;
    public Double longitude;
    public Double altitude;
    
}
