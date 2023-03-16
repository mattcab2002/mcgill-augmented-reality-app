package mcgillar.backend.TO.location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RouteRequestTO {
    
    public LocationTO startLocation;
    public LocationTO endLocationTO;

}
