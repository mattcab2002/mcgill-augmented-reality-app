package mcgillar.backend.controllers.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mcgillar.backend.services.TokenService;

@RestController
public class TokenController {

    private TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * 
     *  @return token upon login
     *  
     * send request to this endpoint with credentials as follows:
     *  headers: {
     *      "Authorization": "Basic ${base64(username:password)}"
     *  }
     *  use token in headers for every next request
     *  headers: {
     *      "Authorization": "Bearer ${token}"
     *  }
     * 
     */
    @PostMapping("/token")
    public String getToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = tokenService.generateToken(authentication);
        return token;
    }
    
}
