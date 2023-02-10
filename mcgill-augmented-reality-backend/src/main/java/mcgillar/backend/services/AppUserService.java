package mcgillar.backend.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import mcgillar.backend.model.AppUser;
import mcgillar.backend.model.AppUserAuthority;
import mcgillar.backend.model.SecurityUser;
import mcgillar.backend.repositories.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService {

    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    public AppUserService(
        AppUserRepository appUserRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findAppUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username + " not found");

        return new SecurityUser(user);
    }

    public AppUser createUser(String username, String password) {
        AppUser newUser = new AppUser();
        newUser.setUsername(username);
        newUser.getAuthorities().add(AppUserAuthority.MEMBER);
        newUser.setPasswordHash(passwordEncoder.encode(password));
        return appUserRepository.save(newUser);
    }
    
}
