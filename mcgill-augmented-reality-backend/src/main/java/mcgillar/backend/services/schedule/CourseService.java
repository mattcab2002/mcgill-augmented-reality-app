package mcgillar.backend.services.schedule;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mcgillar.backend.TO.schedule.CourseTO;
import mcgillar.backend.model.building.Building;
import mcgillar.backend.model.schedule.Course;
import mcgillar.backend.model.schedule.WeeklySchedule;
import mcgillar.backend.repositories.schedule.CourseRepository;
import mcgillar.backend.services.building.BuildingService;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseRepository courseRepository;
    private BuildingService buildingService;

    public Course createCourse(
        WeeklySchedule schedule,
        String courseCode,
        String name,
        String buildingShortCode,
        Set<Date> times,
        Double duration
    ) {
        Building building = buildingService.findBuildingByShortCode(buildingShortCode);
        if (building == null) throw new IllegalArgumentException("Invalid building");

        Course course = new Course();
        course.setBuilding(building);
        course.setCourseCode(courseCode);
        course.setDuration(duration);
        course.setName(name);
        course.getTimes().addAll(times);
        course.setWeeklySchedule(schedule);
        
        return courseRepository.save(course);
    }

    public Course createCourse(CourseTO course, WeeklySchedule schedule) {
        return createCourse(
            schedule,
            course.getCourseCode(), 
            course.getName(), 
            course.getBuildingShortCode(), 
            new HashSet<>(course.getTimes()), 
            course.getDuration()
        );
    }

    public List<Course> getCoursesBySchedule(WeeklySchedule schedule) {
        return courseRepository.findAllCourseByWeeklySchedule(schedule);
    }
    
}
