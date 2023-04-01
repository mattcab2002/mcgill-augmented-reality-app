package mcgillar.backend.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mcgillar.backend.services.user.AppUserService;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class LoginTest {
    
    @Autowired
    private TestRestTemplate client;

    @Autowired
    private AppUserService appUserService;

    @Test
    public void loginSuccess() {
        String username = "test_name";
        String pass = "Password123!";
        appUserService.createUser(username, pass);

        ResponseEntity<String> response = getResponse(username, pass);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void unregisteredUser() {
        String username = "DNE";
        String pass = "DNE";

        ResponseEntity<String> response = getResponse(username, pass);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void wrongPass() {
        String username = "test_name";
        String wrongPass = "wrong_password";

        ResponseEntity<String> response = getResponse(username, wrongPass);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
    
    private ResponseEntity<String> getResponse(String username, String pass) {
        HttpHeaders headers = new HttpHeaders();
        String creds = username + ":" + pass;
        byte[] plainCreds = creds.getBytes();
        byte[] base64PlainCreds = Base64.getEncoder().encode(plainCreds);
        String newCreds = new String(base64PlainCreds);
        
        headers.add("Authorization", "Basic " + newCreds);
        HttpEntity<Object> entity = new HttpEntity<Object>(null, headers);
        ResponseEntity<String> response = client.postForEntity("/token", entity, String.class);
        return response;
    }
}
