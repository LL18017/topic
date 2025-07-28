package sv.alura.mj.topic.rest.DTO;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sv.alura.mj.topic.entities.Curso;

public record CursoDTO(
        Integer id,

        @NotNull(message = "El nombre es obligatorio")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String nombre,

        @NotNull(message = "El código es obligatorio")
        @Size(min = 2, max = 50, message = "El código debe tener entre 2 y 50 caracteres")
        String codigo
) {
    public CursoDTO(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getCodigo());
    }
}
