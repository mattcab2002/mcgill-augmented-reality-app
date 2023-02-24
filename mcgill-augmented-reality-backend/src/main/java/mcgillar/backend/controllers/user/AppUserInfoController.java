package mcgillar.backend.controllers.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import mcgillar.backend.TO.user.UserInfoTO;
import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.model.user.AppUserInfo;
import mcgillar.backend.services.user.AppUserInfoService;
import mcgillar.backend.services.user.AppUserService;

@AllArgsConstructor
@RestController
@RequestMapping("/user-info/")
public class AppUserInfoController {
    
    private AppUserInfoService appUserInfoService;
    private AppUserService appUserService;

    
    /**
     * 
     * @param authentication
     * @return all user info
     * 
     * Return all user info for given user
     */
    @GetMapping("all-user-info")
    public ResponseEntity<UserInfoTO> getUserInfo(
        Authentication authentication
    ) {
        String username = authentication.getName();
        AppUserInfo info = appUserInfoService.createOrReturnExistingInfoByUser(username);
        AppUser user = appUserService.getUserByUsername(username);
        UserInfoTO to = UserInfoTO.getInstance(user, info);
        return new ResponseEntity<UserInfoTO>(to, HttpStatus.ACCEPTED);
    }

    /**
     * 
     * @param firstName
     * @param lastName
     * @param authentication
     * @return Updated User Info
     * 
     * If the parameter is not null or empty it will be set
     */
    @PostMapping("first-last-name")
    public ResponseEntity<UserInfoTO> addOrModifyFirstNameOrLastName(
        @RequestParam String firstName, 
        @RequestParam String lastName, 
        Authentication authentication
    ) {
        String username = authentication.getName();
        AppUserInfo info = appUserInfoService.addOrModifyFirstAndLastName(firstName, lastName, username);
        AppUser user = appUserService.getUserByUsername(username);
        UserInfoTO to = UserInfoTO.getInstance(user, info);
        return new ResponseEntity<UserInfoTO>(to, HttpStatus.ACCEPTED);
    }

    /**
     * 
     * @param countryCode
     * @param phoneNumber
     * @param authentication
     * @return updated user info
     * 
     * If the parameter is not null or empty it will be set
     */
    @PostMapping("country-code-phone-number")
    public ResponseEntity<UserInfoTO> addOrModifyCountryCodeAndPhoneNumber(
        @RequestParam Integer countryCode,
        @RequestParam String phoneNumber,
        Authentication authentication
    ) {
        String username = authentication.getName();
        AppUserInfo info = appUserInfoService.addOrModifyCountryCodeAndPhoneNumber(countryCode, phoneNumber, username);
        AppUser user = appUserService.getUserByUsername(username);
        UserInfoTO to = UserInfoTO.getInstance(user, info);
        return new ResponseEntity<UserInfoTO>(to, HttpStatus.ACCEPTED);
    }

    /**
     * 
     * @param email
     * @param authentication
     * @return updated user info
     * 
     * If the parameter is not null or empty it will be set
     */
    @PostMapping("email")
    public ResponseEntity<UserInfoTO> addOrModifyEmail(
        @RequestParam String email, 
        Authentication authentication
    ) {
        String username = authentication.getName();
        AppUserInfo info = appUserInfoService.addOrModifyEmail(email, username);
        AppUser user = appUserService.getUserByUsername(username);
        UserInfoTO to = UserInfoTO.getInstance(user, info);
        return new ResponseEntity<UserInfoTO>(to, HttpStatus.ACCEPTED);
    }

    /**
     * 
     * @param studentNumber
     * @param authentication
     * @return updated user info
     */
    @PostMapping("student-number")
    public ResponseEntity<UserInfoTO> addOrModifyStudentNumber(
        @RequestParam Long studentNumber,
        Authentication authentication
    ) {
        String username = authentication.getName();
        AppUserInfo info = appUserInfoService.addOrModifyStudentNumber(studentNumber, username);
        AppUser user = appUserService.getUserByUsername(username);
        UserInfoTO to = UserInfoTO.getInstance(user, info);
        return new ResponseEntity<UserInfoTO>(to, HttpStatus.ACCEPTED);
    }
 
}
