package mcgillar.backend.configuration;

import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

import mcgillar.backend.configuration.keys.JWKey;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
    

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf().disable()
            .oauth2ResourceServer().jwt().jwtAuthenticationConverter(new AuthConverter())
            .and().and()
            .authorizeHttpRequests().anyRequest().authenticated()
            .and()
            .httpBasic().and()
            .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JWKey rsaKeys() throws Exception {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        kg.initialize(2048);
        var kp = kg.generateKeyPair();

        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)kp.getPrivate();
        RSAPublicKey rsaPublicKey = (RSAPublicKey)kp.getPublic();

        return new JWKey(rsaPrivateKey, rsaPublicKey);
    }

    @Bean
    JwtEncoder jwtEncoder() throws Exception {
        JWK jwk = new RSAKey.Builder(rsaKeys().getPublicKey()).privateKey(rsaKeys().getPrivateKey()).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    JwtDecoder jwtDecoder() throws Exception {
        return NimbusJwtDecoder.withPublicKey(rsaKeys().getPublicKey()).build();   
    }

}
