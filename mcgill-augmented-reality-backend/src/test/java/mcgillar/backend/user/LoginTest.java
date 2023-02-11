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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import mcgillar.backend.services.AppUserService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class LoginTest {
    
    @Autowired
    private TestRestTemplate client;

    @Autowired
    private AppUserService appUserService;

    @Test
    public void loginSuccess() {
        String username = "test_name";
        String pass = "test_password";
        appUserService.createUser(username, pass);
        HttpHeaders headers = new HttpHeaders();
        String creds = username + ":" + pass;
        byte[] plainCreds = creds.getBytes();
        byte[] base64PlainCreds = Base64.getEncoder().encode(plainCreds);
        String newCreds = new String(base64PlainCreds);
        
        headers.add("Authorization", "Basic " + newCreds);
        HttpEntity<Object> entity = new HttpEntity<Object>(null, headers);
        ResponseEntity<String> token = client.postForEntity("/token", entity, String.class);

        String body = token.getBody();

        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer " + body);

        HttpEntity<Object> entity2 = new HttpEntity<Object>(null, headers2);
        // ResponseEntity<String> response = client.getForEntity("/", entity2, String.class);
        ResponseEntity<String> response = client.exchange("/", HttpMethod.GET, entity2, String.class);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
          
    }

    @Test
    public void loginBad() {
        String username = "DNE";
        String pass = "DNE";
        HttpHeaders headers = new HttpHeaders();
        String creds = username + ":" + pass;
        byte[] plainCreds = creds.getBytes();
        byte[] base64PlainCreds = Base64.getEncoder().encode(plainCreds);
        String newCreds = new String(base64PlainCreds);
        
        headers.add("Authorization", "Basic " + newCreds);
        HttpEntity<Object> entity = new HttpEntity<Object>(null, headers);
        ResponseEntity<String> token = client.postForEntity("/token", entity, String.class);

        
        assertEquals(HttpStatus.UNAUTHORIZED, token.getStatusCode());
    }
}
