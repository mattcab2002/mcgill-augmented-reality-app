package mcgillar.backend.controllers.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import mcgillar.backend.TO.user.ValidatedUser;
import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.services.AppUserService;

@RestController
@RequestMapping("/user/")
@AllArgsConstructor
public class AppUserController {
    
    private AppUserService appUserService;

    @PostMapping("register")
    public ResponseEntity<ValidatedUser> registerUser(@RequestParam String username, @RequestParam String password) {
        if (username.length() > 0 && password.length() > 0) {
            AppUser user = appUserService.createUser(username, password);
            if (user != null) {
                ValidatedUser validatedUser = new ValidatedUser(user);
                return new ResponseEntity<ValidatedUser>(validatedUser, HttpStatus.CREATED);
            }
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
