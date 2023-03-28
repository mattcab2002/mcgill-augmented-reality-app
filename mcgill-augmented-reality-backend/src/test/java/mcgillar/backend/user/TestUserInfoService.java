package mcgillar.backend.user;

import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.model.user.AppUserInfo;
import mcgillar.backend.repositories.user.AppUserInfoRepository;
import mcgillar.backend.repositories.user.AppUserRepository;
import mcgillar.backend.services.user.AppUserInfoService;
import mcgillar.backend.services.user.AppUserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.invocation.InvocationOnMock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.PasswordEncoder;


@ExtendWith(MockitoExtension.class)
public class TestUserInfoService {
    @Mock
    private AppUserInfoRepository appUserInfoRepository;
    @Mock
    private AppUserRepository appUserRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @InjectMocks
    private AppUserInfoService appUserInfoService;
    @Mock
    private AppUserService appUserService;
    private final String APP_USER_USERNAME = "TEST_USERNAME";
    private final String APP_USER_PASSWORD = "Password123!";
    private final String APP_USER_PASSWORD_HASH = "TEST_PASSWORD_HASH";
    private final String APP_USER_FIRSTNAME = "TEST_FIRSTNAME";
    private final String APP_USER_LASTNAME = "TEST_LASTNAME";
    private final Long APP_USER_STUDENT_NUMBER = 233455646L;
    private final String APP_USER_PHONE_NUMBER = "2340987";
    private final Integer APP_USER_COUNTRY_CODE = 514;
    private final String APP_USER_EMAIL = "TEST_EMAIL@mail.ca";

//TODO: test case for phone number (check length) and test case for wrong id
    @BeforeEach
    public void setMockOutput() {
        lenient().when(appUserRepository.findAppUserByUsername(any(String.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(APP_USER_USERNAME)) {
                AppUser user = null;
                user = appUserService.createUser(APP_USER_USERNAME, APP_USER_PASSWORD);
                return user;
            } else {
                return null;
            }
        });
        //not sure if this mockup is good to verify
        lenient().when(appUserInfoRepository.findAppUserInfoByUser(any(AppUser.class))).thenAnswer((InvocationOnMock invocation) -> {
            AppUser user = invocation.getArgument(0);
            if (user.getUsername().equals(APP_USER_USERNAME)) {
                return new AppUserInfo(user);
            } else {
                return null;
            }
        });
        lenient().when(passwordEncoder.encode(APP_USER_PASSWORD)).thenReturn(APP_USER_PASSWORD_HASH);
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> invocation.getArgument(0);
        lenient().when(appUserInfoRepository.save(any(AppUserInfo.class))).thenAnswer(returnParameterAsAnswer);
        lenient().when(appUserRepository.save(any(AppUser.class))).thenAnswer(returnParameterAsAnswer);

    }
    @Test
    public void testAddOrModifyFirstAndLastname() {
        AppUserInfo userInfo = null;
        String error = null;

        try {
        userInfo = appUserInfoService.addOrModifyFirstAndLastName(APP_USER_FIRSTNAME, APP_USER_LASTNAME, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(userInfo.getFirstName(), APP_USER_FIRSTNAME);
        assertEquals(userInfo.getLastName(), APP_USER_LASTNAME);
    }
    @Test
    public void testAddOrModifyNullFirstname() {
        AppUserInfo userInfo = null;
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyFirstAndLastName(null,APP_USER_LASTNAME, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
       assertEquals("Please enter a first name", error);
    }
    @Test
    public void testAddOrModifyEmptyFirstname() {
        AppUserInfo userInfo = null;
        String error = null;
        String newFirstName = "";

        try {
            userInfo = appUserInfoService.addOrModifyFirstAndLastName(newFirstName,APP_USER_LASTNAME, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Please enter a first name", error);
    }

    @Test
    public void testAddOrModifyNullLastname() {
        AppUserInfo userInfo = null;
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyFirstAndLastName(APP_USER_FIRSTNAME,null, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Please enter a last name", error);
    }
    @Test
    public void testAddOrModifyEmptyLastname() {
        AppUserInfo userInfo = null;
        String error = null;
        String newLastName = "";

        try {
            userInfo = appUserInfoService.addOrModifyFirstAndLastName(APP_USER_FIRSTNAME,newLastName, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Please enter a last name", error);
    }
    @Test
    public void testAddOrModifyBothNullNames() {
        AppUserInfo userInfo = null;
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyFirstAndLastName(null,null, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Please enter a first name", error);
    }
    @Test
    public void testAddOrModifyBothNamesEmpty() {
        AppUserInfo userInfo = null;
        String error = null;
        String newFirstName = "";
        String newLastName = "";

        try {
            userInfo = appUserInfoService.addOrModifyFirstAndLastName(newFirstName,newLastName, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Please enter a first name", error);
    }
    @Test
    public void testAddOrModifyStudentNumber() {
        AppUserInfo userInfo = null;
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyStudentNumber(APP_USER_STUDENT_NUMBER, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(userInfo.getStudentNumber(), APP_USER_STUDENT_NUMBER);
    }
    @Test
    public void testAddOrModifyStudentNumberNull() {
        AppUserInfo userInfo = null;
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyStudentNumber(null, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Please enter your Student ID", error);
    }
    @Test
    public void testAddOrModifyCountryCodeAndPhoneNumber() {
        AppUserInfo userInfo = null;
        String error = null;


        try {
            userInfo = appUserInfoService.addOrModifyCountryCodeAndPhoneNumber(APP_USER_COUNTRY_CODE, APP_USER_PHONE_NUMBER, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(userInfo.getCountryCode(), APP_USER_COUNTRY_CODE);
        assertEquals(userInfo.getPhoneNumber(), APP_USER_PHONE_NUMBER);
    }
    @Test
    public void testAddOrModifyCountryCodeNull() {
        AppUserInfo userInfo = null;
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyCountryCodeAndPhoneNumber(null, APP_USER_PHONE_NUMBER, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Please enter your Country Code", error);
    }
    @Test
    public void testAddOrModifyPhoneNumberNull() {
        AppUserInfo userInfo = null;
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyCountryCodeAndPhoneNumber(APP_USER_COUNTRY_CODE, null, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Please enter your Phone number", error);
    }
    @Test
    public void testAddOrModifyPhoneNumberEmpty() {
        AppUserInfo userInfo = null;
        String error = null;
        String phoneNumber = "";

        try {
            userInfo = appUserInfoService.addOrModifyCountryCodeAndPhoneNumber(APP_USER_COUNTRY_CODE, phoneNumber, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Please enter your Phone number", error);
    }
    @Test
    public void testAddOrModifyEmail() {
        AppUserInfo userInfo = null;
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyEmail(APP_USER_EMAIL, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals(userInfo.getEmail(), APP_USER_EMAIL);
    }
    @Test
    public void testAddOrModifyNullEmail() {
        AppUserInfo userInfo = null;
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyEmail(null, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Please enter your email.", error);
    }
    @Test
    public void testUAddOrModifyEmptyEmail(){
        AppUserInfo userInfo = null;
        String email = "";
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyEmail(email, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Please enter your email.", error);
    }
    @Test
    public void testAddOrModifyEmptySpaceEmail(){
        AppUserInfo userInfo = null;
        String email = " ";
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyEmail(email, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Email can't be empty", error);
    }
    @Test
    public void testAddOrModifyEmailAtSymbolFirst(){
        AppUserInfo userInfo = null;
        String email = "@gmail";
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyEmail(email, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Invalid email format", error);
    }
    @Test
    public void testAddOrModifyEmailAtSymbolLast(){
        AppUserInfo userInfo = null;
        String email = "john@";
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyEmail(email, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Invalid email format", error);
    }
    @Test
    public void testAddOrModifyEmailAtSymbolAfterDot(){
        AppUserInfo userInfo = null;
        String email = "john.gma@";
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyEmail(email, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Invalid email format", error);
    }
    @Test
    public void testAddOrModifyEmailDotSymbolLast(){
        AppUserInfo userInfo = null;
        String email = "john@gmail.";
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyEmail(email, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Invalid email format", error);
    }
    @Test
    public void testAddOrModifyEmailNoDotSymbol(){
        AppUserInfo userInfo = null;
        String email = "john@gmailcom";
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyEmail(email, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Invalid email format", error);
    }
    @Test
    public void testAddOrModifyEmailNoAtSymbol(){
        AppUserInfo userInfo = null;
        String email = "gmail.com";
        String error = null;

        try {
            userInfo = appUserInfoService.addOrModifyEmail(email, APP_USER_USERNAME);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertNotNull(error);
        assertEquals("Invalid email format", error);
    }

}