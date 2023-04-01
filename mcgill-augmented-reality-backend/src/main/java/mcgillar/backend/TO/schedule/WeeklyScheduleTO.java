package mcgillar.backend.TO.schedule;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import mcgillar.backend.TO.user.ValidatedUser;
import mcgillar.backend.model.schedule.Course;
import mcgillar.backend.model.user.AppUser;

@Getter
@Setter
public class WeeklyScheduleTO {

    private Set<Course> courses;
    private ValidatedUser user;

    public static WeeklyScheduleTO getInstance(AppUser user, List<Course> courses) {
        WeeklyScheduleTO to = new WeeklyScheduleTO();
        to.setUser(ValidatedUser.getInstance(user));
        to.setCourses(new HashSet<>(courses));
        return to;
    }
}
