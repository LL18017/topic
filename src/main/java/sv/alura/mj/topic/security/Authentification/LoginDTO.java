package sv.alura.mj.topic.security.Authentification;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import sv.alura.mj.topic.rest.DTO.MensajesExcepciones;

public record LoginDTO(
        @Email @NotNull(message = MensajesExcepciones.AGREGAR_EMAIL_VALIDO) String email,
        @NotNull(message = MensajesExcepciones.AGREGAR_CONTRASENIA_VALIDO)  String contrasenia) {
}
