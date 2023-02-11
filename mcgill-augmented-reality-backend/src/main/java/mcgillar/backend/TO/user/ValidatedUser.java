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

    public ValidatedUser(AppUser user) {
        this.username = user.getUsername();
        this.authorities = user.getAuthorities();
    }
    
}
