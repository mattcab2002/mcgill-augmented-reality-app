package mcgillar.backend.user;

import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.model.user.AppUserAuthority;
import mcgillar.backend.model.user.AppUserInfo;
import mcgillar.backend.repositories.user.AppUserInfoRepository;
import mcgillar.backend.repositories.user.AppUserRepository;
import mcgillar.backend.services.user.AppUserInfoService;
import mcgillar.backend.services.user.AppUserService;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.invocation.InvocationOnMock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;


@ExtendWith(MockitoExtension.class)
public class TestUserInfoService {
    @Mock
    private AppUserInfoRepository appUserInfoRepository;
    @Mock
    private AppUserRepository appUserRepository;
    @InjectMocks
    private AppUserInfoService appUserInfoService;
    @InjectMocks
    private AppUserService appUserService;
    private String APPUSER_USERNAME = "TEST_USERNAME";
    private final String APPUSER_PASSWORD = "TEST_PASSWORD_HASH";
    private final String APPUSER_FIRSTNAME = "TEST_FIRSTNAME";
    private final String APPUSER_LASTNAME = "TEST_LASTNAME";
    private final Long APPUSER_STUDENTNUMBER = 233455646L;
    private final String APPUSER_PHONENUMBER = "2340987";
    private final Integer APPUSER_COUNTRYCODE = 514;
    private final String APPUSER_EMAIL = "TEST_EMAIL@mail.ca";


    @BeforeEach
    public void setMockOutput() {

        lenient().when(appUserRepository.findAppUserByUsername(any(String.class))).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(APPUSER_USERNAME)) {
                AppUser user = new AppUser();
                user.setUsername(APPUSER_USERNAME);
                user.setPasswordHash(APPUSER_PASSWORD);
                user.getAuthorities().add(AppUserAuthority.MEMBER);
                return user;
            } else {
                return null;
            }
        });
        //not sure if this mockup is good to verify
        lenient().when(appUserInfoRepository.findAppUserInfoByUser(any(AppUser.class))).thenAnswer((InvocationOnMock invocation) -> {
            AppUser user = invocation.getArgument(0);
            if (user.getUsername().equals(APPUSER_USERNAME)) {
                AppUserInfo info = new AppUserInfo(user);
                info.setFirstName(APPUSER_FIRSTNAME);
                info.setLastName(APPUSER_LASTNAME);
                info.setStudentNumber(APPUSER_STUDENTNUMBER);
                info.setPhoneNumber(APPUSER_PHONENUMBER);
                info.setCountryCode(APPUSER_COUNTRYCODE);
                info.setEmail(APPUSER_EMAIL);
                return info;
            } else {
                return null;
            }
        });
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(appUserInfoRepository.save(any(AppUserInfo.class))).thenAnswer(returnParameterAsAnswer);

    }


}
