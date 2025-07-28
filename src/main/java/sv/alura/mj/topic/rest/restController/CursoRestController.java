package sv.alura.mj.topic.rest.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.alura.mj.topic.control.repository.CursoRepository;
import sv.alura.mj.topic.entities.Curso;
import sv.alura.mj.topic.rest.DTO.CursoDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cursos")
public class CursoRestController {

    @Autowired
    private CursoRepository cursoRepository;

    // Listar todos los cursos
    @GetMapping
    public ResponseEntity<List<CursoDTO>> listar() {
        List<CursoDTO> cursos = cursoRepository.findAll().stream()
                .map(CursoDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cursos);
    }

    // Obtener detalle de un curso por ID
    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> detalle(@PathVariable Integer id) {
        Optional<Curso> cursoOpt = cursoRepository.findById(id);
        return cursoOpt.map(curso -> ResponseEntity.ok(new CursoDTO(curso)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo curso
    @PostMapping
    public ResponseEntity<CursoDTO> crear(@RequestBody CursoDTO dto) {
        Curso curso = new Curso();
        curso.setNombre(dto.nombre());
        curso.setCodigo(dto.codigo());

        Curso cursoGuardado = cursoRepository.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CursoDTO(cursoGuardado));
    }

    // Modificar un curso existente
    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> modificar(@PathVariable Integer id, @RequestBody CursoDTO dto) {
        Optional<Curso> cursoOpt = cursoRepository.findById(id);
        if (cursoOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Curso curso = cursoOpt.get();
        curso.setNombre(dto.nombre());
        curso.setCodigo(dto.codigo());

        Curso actualizado = cursoRepository.save(curso);
        return ResponseEntity.ok(new CursoDTO(actualizado));
    }

    // Eliminar un curso por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        if (!cursoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cursoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
