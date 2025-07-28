package sv.alura.mj.topic.security.Authentification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.alura.mj.topic.entities.Autor;
import sv.alura.mj.topic.security.TokenService;

@RestController
    @RequestMapping("/login")
    public class LoginRestController {
        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO data) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(data.email(), data.contrasenia());
            Authentication authenticate = authenticationManager.authenticate(token);

            UserDetail userDetails = (UserDetail) authenticate.getPrincipal();
            Autor userAccount = userDetails.getUser();

            var tokenJWT = tokenService.generarToken(userAccount);
            return ResponseEntity.ok(tokenJWT);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Error al autenticar: " + e.getMessage());
        }
    }

}