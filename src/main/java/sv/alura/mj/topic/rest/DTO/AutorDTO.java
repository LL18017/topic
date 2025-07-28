package sv.alura.mj.topic.rest.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import sv.alura.mj.topic.entities.Autor;

public record AutorDTO(
        Integer id,
        @NotNull(message = MensajesExcepciones.AGREGAR_NOMBRE_VALIDO) String nombre,
        @Email @NotNull(message = MensajesExcepciones.AGREGAR_EMAIL_VALIDO) String email,
        @NotNull(message = MensajesExcepciones.AGREGAR_CONTRASENIA_VALIDO) String contrasenia) {
    public AutorDTO(Autor autor) {
        this(autor.getId(), autor.getNombre(), autor.getEmail(), autor.getContrasenia());
    }
}
