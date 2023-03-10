package mcgillar.backend.services.auth;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import mcgillar.backend.model.user.AppUser;
import mcgillar.backend.model.user.SecurityUser;

@Service
public class TokenService {
    
    private final JwtEncoder jwtEncoder;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    /**
     * 
     * @param authentication containing username and password from login
     * @return JWT token for future requests
     */
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();
        List<String> scope = new ArrayList<String>();
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            scope.add(auth.getAuthority());
        }

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(60, ChronoUnit.MINUTES))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    /**
     * 
     * @param user to reset the token
     * @return new token with new username attached
     */
    public String regenerateToken(AppUser user) {
        SecurityUser securityUser = new SecurityUser(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(securityUser, null, securityUser.getAuthorities());
        String token = generateToken(authentication);
        return token;
    }
}
