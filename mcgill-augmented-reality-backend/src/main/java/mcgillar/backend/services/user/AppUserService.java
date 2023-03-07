package mcgillar.backend.services.user;

import java.util.Collection;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import mcgillar.backend.TO.user.ValidatedUser;
import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.model.user.AppUserAuthority;
import mcgillar.backend.model.user.AppUserInfo;
import mcgillar.backend.model.user.SecurityUser;
import mcgillar.backend.repositories.user.AppUserInfoRepository;
import mcgillar.backend.repositories.user.AppUserRepository;
import mcgillar.backend.services.auth.TokenService;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;
    private TokenService tokenService;
    private AppUserInfoRepository appUserInfoRepository;

    /**
     * @param username
     * @return SecurityUser, Wrapper over AppUser for spring to use in the context. Returns excpetion if username not found for spring to handle
     *  !! only for Spring to use !!
     *  
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findAppUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username + " not found");

        return new SecurityUser(user);
    }

    /**
     * 
     * @param username
     * @param password
     * @return new AppUser with MEMBER authority
     */
    public AppUser createUser(String username, String password) {
        
        if (username == null || username.isEmpty()) throw new IllegalArgumentException("Username can't be empty");
        if (password == null || password.isEmpty()) throw new IllegalArgumentException("Password can't be empty");
        if (password.length() < 8) throw new IllegalArgumentException("Password needs to be at least 8 characters");
        
        if (getUserByUsername(username) != null) throw new IllegalArgumentException("This email already exists");
        
        boolean isValid = checkPassword(password);
        if (!isValid) throw new IllegalArgumentException("Password is invalid");
        
        AppUser newUser = new AppUser();
        newUser.setUsername(username);
        newUser.getAuthorities().add(AppUserAuthority.MEMBER);
        newUser.setPasswordHash(passwordEncoder.encode(password));
        return appUserRepository.save(newUser);
    }

    private boolean checkPassword(String password) throws IllegalArgumentException {
        Pattern capitalLetter = Pattern.compile("[A-Z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasCapitalLetter = capitalLetter.matcher(password);
        Matcher hasDigit = digit.matcher(password);
        Matcher hasSpecial = special.matcher(password);
        return hasCapitalLetter.find() && hasDigit.find() && hasSpecial.find();
        
    }


    /**
     * 
     * @param username 
     * @return AppUser with username, could be used to get the currently signed in user with 
     */
    public AppUser getUserByUsername(String username) {
        return appUserRepository.findAppUserByUsername(username);
    }

    /**
     *
     * @param newPassword
     * @param userEmail
     * @return null if newPassword == existing password, returns updated AppUser otherwise
     */
    public ValidatedUser changePassword(String newPassword, String userEmail) {
        AppUser user = getUserByUsername(userEmail);
        if (passwordEncoder.matches(newPassword, user.getPasswordHash())) return null;
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        user = appUserRepository.save(user);
        return new ValidatedUser(user);
    }

    /**
     * 
     * @param newUsername
     * @param username
     * @return Validated user with regenerated token and updated username
     */
    public ValidatedUser changeUsername(String newUsername, String username) {
        AppUser user = getUserByUsername(username);
        AppUser existingUserWithUsername = appUserRepository.findAppUserByUsername(newUsername);
        if (existingUserWithUsername != null) return null;
        user.setUsername(newUsername);
        user = appUserRepository.save(user);
        String token = tokenService.regenerateToken(user);
        return new ValidatedUser(user, token);
    }

    
    /**
     * 
     * @param username
     * @param authorities
     * @return wrapper function for developper comfort
     */
    public ValidatedUser addAuthorities(String username, AppUserAuthority... authorities) {
        Collection<AppUserAuthority> auths = Set.of(authorities);
        return addAuthorities(username, auths);
    }
    /**
     * 
     * @param username of the user to add authorities
     * @param authorities to add
     * @return ValidatedUser with new token
     */
    public ValidatedUser addAuthorities(String username, Collection<AppUserAuthority> authorities) {
        AppUser user = getUserByUsername(username);
        user.getAuthorities().addAll(authorities);
        user = appUserRepository.save(user);
        String token = tokenService.regenerateToken(user);
        return new ValidatedUser(user, token);
    }

    /**
     * 
     * @param username
     * @param authority
     * @return ValidatedUser with new token and removed authority
     */
    public ValidatedUser removeAuthority(String username, AppUserAuthority authority) {
        AppUser user = getUserByUsername(username);
        Set<AppUserAuthority> authorities = user.getAuthorities();
        if (authorities.contains(authority)) authorities.remove(authority);
        user = appUserRepository.save(user);
        String token = tokenService.regenerateToken(user);
        return new ValidatedUser(user, token);
    }

    /**
     * 
     * @param username
     * @return ValidatedUser object of the deleted user
     */
    public void deleteAccount(String username) {
        AppUser user = getUserByUsername(username);
        deleteAppUserInfo(user);
        appUserRepository.delete(user);
    }

    public void deleteAppUserInfo(AppUser user) {
        AppUserInfo info = appUserInfoRepository.findAppUserInfoByUser(user);
        if (info != null) appUserInfoRepository.delete(info);
    }
}
