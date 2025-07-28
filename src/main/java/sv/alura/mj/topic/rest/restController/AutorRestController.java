package sv.alura.mj.topic.rest.restController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import sv.alura.mj.topic.control.repository.AutorRepository;
import sv.alura.mj.topic.entities.Autor;
import sv.alura.mj.topic.rest.DTO.AutorDTO;

import java.util.List;

@RestController
@RequestMapping("/autor")
public class AutorRestController {
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @PostMapping
    public ResponseEntity createAutor(@RequestBody @Valid AutorDTO user, UriComponentsBuilder uriComponentsBuilder) {
        Autor Users = new Autor(user);
        Users.setContrasenia(passwordEncoder.encode(user.contrasenia()));
        autorRepository.save(Users);
        var uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(Users.getId()).toUri();
        return ResponseEntity.created(uri).body(new AutorDTO(Users));
    }
    @GetMapping
    public ResponseEntity getAllUsers() {
        List<AutorDTO> usuarios = autorRepository.findAll().stream().map(AutorDTO::new).toList();
        return ResponseEntity.ok(usuarios);
    }
}
