package mcgillar.backend.services.user;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.model.user.AppUserInfo;
import mcgillar.backend.repositories.user.AppUserInfoRepository;

@Service
@AllArgsConstructor
public class AppUserInfoService {
    
    private AppUserInfoRepository appUserInfoRepository;
    private AppUserService appUserService;

    /**
     * 
     * @param user to find or create AppUserInfo by
     * @return new or existing AppUserInfo
     * 
     * to avoid conflicts when searching for non existing AppUserInfo
     * 
     */
    public AppUserInfo createOrReturnExistingInfoByUser(String username) {
        AppUser user = appUserService.getUserByUsername(username);
        AppUserInfo info = appUserInfoRepository.findAppUserInfoByUser(user);
        if (info != null) return info;

        info = new AppUserInfo(user);
        return appUserInfoRepository.save(info);
    }

    /**
     * 
     * @param firstName
     * @param lastName
     * @param username of the user
     * @return Updated user info
     * 
     * Sets the first name and last name if given a non-null and non-empty value
     */
    public AppUserInfo addOrModifyFirstAndLastName(String firstName, String lastName, String username) {
        AppUserInfo info = createOrReturnExistingInfoByUser(username);
        if (firstName != null && firstName.length() > 0) info.setFirstName(firstName);
        if (lastName != null && lastName.length() > 0) info.setLastName(lastName);
        return appUserInfoRepository.save(info);   
    }

    /**
     * 
     * @param countryCode
     * @param phoneNumber
     * @param username
     * @return Updated user info
     * 
     * Sets the country code and phone name if given a non-null and non-empty value
     */
    public AppUserInfo addOrModifyCountryCodeAndPhoneNumber(Integer countryCode, String phoneNumber, String username) {
        AppUserInfo info = createOrReturnExistingInfoByUser(username);
        if (countryCode != null) info.setCountryCode(countryCode);
        if (phoneNumber != null && phoneNumber.length() > 0) info.setPhoneNumber(phoneNumber);
        return appUserInfoRepository.save(info);
    }

    /**
     * 
     * @param email
     * @param username
     * @return updated user info
     * 
     * Sets the email if given a non-null and non-empty value
     */
    public AppUserInfo addOrModifyEmail(String email, String username) {
        AppUserInfo info = createOrReturnExistingInfoByUser(username);
        if (email != null && email.length() > 0) info.setEmail(email);
        return appUserInfoRepository.save(info);
    }

    /**
     * 
     * @param studentNumber
     * @param username
     * @return updated user info
     * 
     * Sets the studentNumber if given a non-null value
     */
    public AppUserInfo addOrModifyStudentNumber(Long studentNumber, String username) {
        AppUserInfo info = createOrReturnExistingInfoByUser(username);
        if (studentNumber != null) info.setStudentNumber(studentNumber);
        return appUserInfoRepository.save(info);
    }
}
