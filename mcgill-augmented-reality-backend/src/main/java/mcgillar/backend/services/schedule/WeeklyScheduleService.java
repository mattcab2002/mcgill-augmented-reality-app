package mcgillar.backend.services.schedule;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mcgillar.backend.TO.schedule.CourseTO;
import mcgillar.backend.TO.schedule.WeeklyScheduleTO;
import mcgillar.backend.model.schedule.Course;
import mcgillar.backend.model.schedule.WeeklySchedule;
import mcgillar.backend.model.user.AppUserInfo;
import mcgillar.backend.repositories.schedule.WeeklyScheduleRepository;
import mcgillar.backend.services.user.AppUserInfoService;

@Service
@AllArgsConstructor
public class WeeklyScheduleService {

    private WeeklyScheduleRepository weeklyScheduleRepository;
    private AppUserInfoService appUserInfoService;
    private CourseService courseService;

    public WeeklySchedule createWeeklySchedule(
        String username, 
        Set<CourseTO> courses
    ) {
        AppUserInfo userInfo = appUserInfoService.createOrReturnExistingInfoByUser(username);
        WeeklySchedule schedule = new WeeklySchedule();
        schedule = weeklyScheduleRepository.save(schedule);
        userInfo.setSchedule(schedule);
        appUserInfoService.save(userInfo);
        for (CourseTO course : courses) {
            courseService.createCourse(course, schedule);
        }
        return schedule;
    }

    public WeeklyScheduleTO from(WeeklySchedule schedule) {
        List<Course> courses = courseService.getCoursesBySchedule(schedule);
        AppUserInfo info = appUserInfoService.findInfoBySchdule(schedule);
        return WeeklyScheduleTO.getInstance(info.getUser(), courses);
    }
    
}
