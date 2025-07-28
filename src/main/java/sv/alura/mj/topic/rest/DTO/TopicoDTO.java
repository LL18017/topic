package sv.alura.mj.topic.rest.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import sv.alura.mj.topic.entities.Topico;

import java.time.Instant;

public record TopicoDTO(
        Integer id,

        @NotNull(message = "El título es obligatorio")
        @Size(min = 3, max = 200, message = "El título debe tener entre 3 y 200 caracteres")
        String titulo,

        @NotNull(message = "El mensaje es obligatorio")
        String mensaje,

        Instant fechaCreacion,
        AutorTopicoDTO autor,
        CursoTopicoDTO curso

) {
    public TopicoDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(),
                new AutorTopicoDTO(topico.getAutor().getId(), topico.getAutor().getNombre()),
                new CursoTopicoDTO(topico.getCurso().getId(), topico.getCurso().getNombre())

        );
    }
}

