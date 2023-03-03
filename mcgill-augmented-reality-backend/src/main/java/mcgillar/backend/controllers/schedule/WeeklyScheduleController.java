package mcgillar.backend.controllers.schedule;

import java.util.HashSet;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import mcgillar.backend.TO.schedule.CourseTO;
import mcgillar.backend.TO.schedule.WeeklyScheduleTO;
import mcgillar.backend.model.schedule.WeeklySchedule;
import mcgillar.backend.services.schedule.WeeklyScheduleService;

@Controller
@AllArgsConstructor
@RequestMapping("/weekly-schedule/")
public class WeeklyScheduleController {

    private WeeklyScheduleService weeklyScheduleService;

    @PostMapping("create")
    public ResponseEntity<?> createWeeklySchedule(
        @RequestBody List<CourseTO> courses,
        Authentication authentication
    ) {
        try {
            WeeklySchedule schedule = weeklyScheduleService.createWeeklySchedule(authentication.getName(), new HashSet<>(courses));
            WeeklyScheduleTO to = weeklyScheduleService.from(schedule);
            return new ResponseEntity<WeeklyScheduleTO>(to, HttpStatus.CREATED);
        }catch(IllegalArgumentException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
}
