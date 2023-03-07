package mcgillar.backend.repositories.user;

import org.springframework.data.repository.CrudRepository;

import mcgillar.backend.model.schedule.WeeklySchedule;
import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.model.user.AppUserInfo;

public interface AppUserInfoRepository extends CrudRepository<AppUserInfo, Integer>{
    AppUserInfo findAppUserInfoByUser(AppUser user);
    AppUserInfo findAppUserInfoBySchedule(WeeklySchedule schedule);
}
