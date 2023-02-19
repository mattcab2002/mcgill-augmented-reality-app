package mcgillar.backend.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class AuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    @Override
    public AbstractAuthenticationToken convert(Jwt source) {
        List<String> auth = new ArrayList<String>(source.getClaimAsStringList("scope"));
        List<SimpleGrantedAuthority> a = new ArrayList<SimpleGrantedAuthority>();
        for (String s : auth) {
            a.add(new SimpleGrantedAuthority(s));
        }
        return new JwtAuthenticationToken(source, a, source.getClaim("sub"));
    }
    
}
