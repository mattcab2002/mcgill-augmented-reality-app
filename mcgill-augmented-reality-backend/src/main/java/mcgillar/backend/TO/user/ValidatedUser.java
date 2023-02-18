package mcgillar.backend.TO.user;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.model.user.AppUserAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidatedUser {

    public String username;

    public Set<AppUserAuthority> authorities;

    public String token; // for when a user changes user name, the token is regenerated

    public ValidatedUser(AppUser user) {
        this.username = user.getUsername();
        this.authorities = user.getAuthorities();
    }

    public ValidatedUser(AppUser user, String token) {
        this.username = user.getUsername();
        this.authorities = user.getAuthorities();
        this.token = token;
    }
    
}
