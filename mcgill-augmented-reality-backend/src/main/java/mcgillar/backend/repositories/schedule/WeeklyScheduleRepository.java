package mcgillar.backend.repositories.schedule;

import org.springframework.data.repository.CrudRepository;

import mcgillar.backend.model.schedule.WeeklySchedule;

public interface WeeklyScheduleRepository extends CrudRepository<WeeklySchedule, Integer> {
    
}
