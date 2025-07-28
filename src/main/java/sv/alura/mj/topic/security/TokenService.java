package sv.alura.mj.topic.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sv.alura.mj.topic.entities.Autor;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String SECRET;

    public String generarToken(Autor usuario) {
        try {
            var algoritmo = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(usuario.getEmail())
                    .withClaim("email", usuario.getEmail())
                    .withClaim("Id", usuario.getId())
                    .withClaim("names", usuario.getNombre())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("error al generar el token JWT", exception);
        }
    }

    private Instant fechaExpiracion() {
        Instant instant = LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-06:00"));
        System.out.println("fechaExpiracion: " + instant);
        return instant;
    }

    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256(SECRET);
            return JWT.require(algoritmo)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();
        } catch (JWTVerificationException exception){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, exception.getMessage(), exception);
            throw new RuntimeException("Token JWT invalido o expirado!");
        }
    }
}
