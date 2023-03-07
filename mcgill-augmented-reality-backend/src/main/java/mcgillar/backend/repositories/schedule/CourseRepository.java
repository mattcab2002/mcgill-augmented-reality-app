package mcgillar.backend.repositories.schedule;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mcgillar.backend.model.schedule.Course;
import mcgillar.backend.model.schedule.WeeklySchedule;

public interface CourseRepository extends CrudRepository<Course, Integer> {
    List<Course> findAllCourseByWeeklySchedule(WeeklySchedule weeklySchedule);
}
