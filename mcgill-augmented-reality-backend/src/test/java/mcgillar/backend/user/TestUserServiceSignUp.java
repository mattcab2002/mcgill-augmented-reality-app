package mcgillar.backend.user;

import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.repositories.AppUserRepository;
import mcgillar.backend.services.AppUserService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;


@ExtendWith(MockitoExtension.class)
public class TestUserServiceSignUp {
    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    private AppUserService appUserService;
    private static final String USERNAME_KEY = "tester";
    private static final String PASSWORD = "Password123!";

//    private String EMPLOYEE_USERNAME = "TEST_USERNAME";
//    private String EMPLOYEE_EMAIL = "TEST_EMAIL@mail.ca";
//    private String EMPLOYEE_PASSWORD = "TEST_PASSWORD";
//    private String EMPLOYEE_ADDRESS = "TEST_ADDRESS";
    @BeforeEach
    public void setMockOutput() {
        lenient().when(passwordEncoder.encode(PASSWORD)).thenReturn("hashedPassword");

        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(appUserRepository.save(any(AppUser.class))).thenAnswer(returnParameterAsAnswer);

    }
    @Test
    public void testCreateUser() {

        AppUser user = null;
        try {
            user = appUserService.createUser(USERNAME_KEY, PASSWORD);
        } catch (IllegalArgumentException e) {
            // Check that no error occurred
            fail();
        }
        assertNotNull(user);
    }

    @Test
    public void testCreateUserNameNull() {

        String name = null;
        AppUser user = null;
        String error = null;

        try {
            user = appUserService.createUser(name, PASSWORD);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(user);
        // check error
        assertEquals("Username can't be empty", error);
    }
    @Test
    public void testCreateUserPasswordNull() {

        String password = null;
        AppUser user = null;
        String error = null;

        try {
            user = appUserService.createUser(USERNAME_KEY, password);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(user);
        // check error
        assertEquals("Password can't be empty", error);
    }
    @Test
    public void testCreateUserNameEmpty() {

        String name = "";
        String error = null;
        AppUser user = null;
        try {
            user = appUserService.createUser(name, PASSWORD);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(user);
        // check error
        assertEquals("Username can't be empty", error);
    }

    @Test
    public void testCreateUserPasswordEmpty() {

        String password = "";
        String error = null;
        AppUser user = null;
        try {
            user = appUserService.createUser(USERNAME_KEY, password);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(user);
        // check error
        assertEquals("Password can't be empty", error);
    }
    @Test
    public void testCreateUserPasswordWithoutCapitalLetter() {

        String password = "password123!";
        String error = null;
        AppUser user = null;
        try {
            user = appUserService.createUser(USERNAME_KEY, password);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(user);
        // check error
        assertEquals("Password needs a capital letter", error);
    }
    @Test
    public void testCreateUserPasswordShortLength() {

        String password = "Pass";
        String error = null;
        AppUser user = null;
        try {
            user = appUserService.createUser(USERNAME_KEY, password);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(user);
        // check error
        assertEquals("Password needs to be at least 8 characters", error);
    }
    @Test
    public void testCreateUserPasswordWithoutNumber() {

        String password = "Password!";
        String error = null;
        AppUser user = null;
        try {
            user = appUserService.createUser(USERNAME_KEY, password);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(user);
        // check error
        assertEquals("Password needs a number", error);
    }
    @Test
    public void testCreateUserPasswordWithoutSpecialCharac() {

        String password = "Password123";
        String error = null;
        AppUser user = null;
        try {
            user = appUserService.createUser(USERNAME_KEY, password);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNull(user);
        // check error
        assertEquals("Password needs a special character", error);
    }

    @Test
    public void testCreateUserDuplicateUsername() {

        String name = "Bob";
        AppUser user1 = null;
        AppUser user2 = null;
        String error = null;

        ArrayList<AppUser> ls = new ArrayList<>();
        when(appUserRepository.findAll()).thenReturn(ls);

        try {
            user1 = appUserService.createUser(name, PASSWORD);
            ls.add(user1);
            user2 = appUserService.createUser(name, PASSWORD);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(user1);
        assertNull(user2);
        assertEquals("An identical customer already exists.", error);
    }

}
