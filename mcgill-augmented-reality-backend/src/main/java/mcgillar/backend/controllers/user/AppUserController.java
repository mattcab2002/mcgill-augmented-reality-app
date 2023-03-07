package mcgillar.backend.controllers.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import mcgillar.backend.TO.user.ValidatedUser;
import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.services.user.AppUserService;

@RestController
@RequestMapping("/user/")
@AllArgsConstructor
public class AppUserController {
    
    private AppUserService appUserService;

    @PostMapping("register")
    public ResponseEntity<ValidatedUser> registerUser(@RequestParam String username, @RequestParam String password) {
        AppUser user = appUserService.createUser(username, password);
        if (user != null) {
            ValidatedUser validatedUser = new ValidatedUser(user);
            return new ResponseEntity<ValidatedUser>(validatedUser, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    /**
     * 
     * @param newPassword
     * @return ValidatedUser with status 202 if successful, 400 otherwise
     */
    @PutMapping("change-password")
    public ResponseEntity<ValidatedUser> changePassword(@RequestParam String newPassword) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ValidatedUser user = appUserService.changePassword(newPassword, username);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<ValidatedUser>(user, HttpStatus.ACCEPTED);
    }

    /**
     * 
     * @param newUsername
     * @return ValidatedUser with status 202 if successful, 400 otherwise
     * 
     * !! NOTE !!
     * Make sure to reset the token on the front end with the token in validatedUser when a change of username occurs.
     * 
     */
    @PutMapping("change-username")
    public ResponseEntity<ValidatedUser> changeUsername(@RequestParam String newUsername) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ValidatedUser user = appUserService.changeUsername(newUsername, username);
        if (user == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<ValidatedUser>(user, HttpStatus.ACCEPTED);
    }

    /**
     * 
     * @param authentication
     * @return deleted user
     * 
     * !! Danger !!
     */
    @DeleteMapping("delete-account")
    public ResponseEntity<String> deleteAccount(
        Authentication authentication
    ) {
        String username = authentication.getName();
        appUserService.deleteAccount(username);
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }

}
