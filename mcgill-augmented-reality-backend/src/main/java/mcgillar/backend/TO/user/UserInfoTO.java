package mcgillar.backend.TO.user;

import java.util.Set;

import lombok.Data;
import mcgillar.backend.model.building.Building;
import mcgillar.backend.model.schedule.WeeklySchedule;
import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.model.user.AppUserInfo;

@Data
public class UserInfoTO {

    public String firstName;

    public String lastName;

    public Long studentNumber;

    public String phoneNumber;

    public Integer countryCode;

    public String email;

    public WeeklySchedule schedule;

    public Set<AppUser> friends;

    public Set<Building> favouriteBuildings;

    public ValidatedUser user;

    public UserInfoTO(AppUser user, AppUserInfo info) {
        setFirstName(info.getFirstName());
        setLastName(info.getLastName());
        setStudentNumber(info.getStudentNumber());
        setPhoneNumber(info.getPhoneNumber());
        setCountryCode(info.getCountryCode());
        setEmail(info.getEmail());
        setSchedule(schedule);
        setFavouriteBuildings(info.getFavouriteBuildings());
        ValidatedUser vUser = ValidatedUser.getInstance(user);
        setUser(vUser);
    }

    public static UserInfoTO getInstance(AppUser user, AppUserInfo info) {
        return new UserInfoTO(user, info);
    }
}
