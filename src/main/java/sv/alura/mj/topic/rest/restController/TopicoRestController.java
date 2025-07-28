package sv.alura.mj.topic.rest.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.alura.mj.topic.control.repository.TopicoRepository;
import sv.alura.mj.topic.entities.Autor;
import sv.alura.mj.topic.entities.Curso;
import sv.alura.mj.topic.entities.Topico;
import sv.alura.mj.topic.rest.DTO.TopicoDTO;
import sv.alura.mj.topic.security.AuthenticationService;
import sv.alura.mj.topic.security.Authentification.UserDetail;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topicos")
public class TopicoRestController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    AuthenticationService authenticationService;

    // Listar todos los tópicos
    @GetMapping
    public ResponseEntity<List<TopicoDTO>> listar() {
        List<TopicoDTO> topicos = topicoRepository.findAll().stream().map(TopicoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(topicos);
    }

    // Obtener detalle de un tópico por ID
    @GetMapping("/{id}")
    public ResponseEntity<TopicoDTO> detalle(@PathVariable Integer id) {
        Optional<TopicoDTO> optionalTopico = topicoRepository.findById(id).map(TopicoDTO::new);
        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(optionalTopico.get());
    }

    // Crear un nuevo tópico
    @PostMapping("")
    public ResponseEntity crearTopico(@RequestBody TopicoDTO dto) {
        // Obtener el usuario autenticado
        UserDetail userDetails =  new UserDetail(new Autor(dto.autor()));
        Autor autor = userDetails.getUser();
        Curso curso =new Curso(dto.curso().id(),dto.curso().nombre());

        // Crear el tópico
        Topico topico = new Topico();
        topico.setTitulo(dto.titulo());
        topico.setMensaje(dto.mensaje());
        topico.setFechaCreacion(Instant.now());
        topico.setAutor(autor);
        topico.setCurso(curso);// Muy importante asignar el autor aquí

        topicoRepository.save(topico);

        return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }


    // Modificar un tópico existente
    // Modificar un tópico existente
    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> modificar(@PathVariable Integer id, @RequestBody TopicoDTO dto) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Topico topicoExistente = optionalTopico.get();
        topicoExistente.setTitulo(dto.titulo());
        topicoExistente.setMensaje(dto.mensaje());

        // Opcional: también puedes permitir cambiar el curso
        if (dto.curso() != null) {
            topicoExistente.setCurso(new Curso(dto.curso().id(), dto.curso().nombre()));
        }

        topicoRepository.save(topicoExistente);

        return ResponseEntity.ok(new TopicoDTO(topicoExistente));
    }


    // Eliminar un tópico por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
