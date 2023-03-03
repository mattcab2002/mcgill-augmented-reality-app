package mcgillar.backend.TO.schedule;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseTO {
    
    public String courseCode;
    public String name;
    public String buildingShortCode;
    public List<Date> times;
    public Double duration;
    // in minutes

}
