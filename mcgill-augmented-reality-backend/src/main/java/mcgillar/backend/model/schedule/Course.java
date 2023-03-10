package mcgillar.backend.model.schedule;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import mcgillar.backend.model.building.Building;

@Entity
@Data
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String courseCode;

    private String name;

    @ManyToOne
    private Building building;

    @ManyToOne
    private WeeklySchedule weeklySchedule;

    private Set<Date> times = new HashSet<Date>();
}
