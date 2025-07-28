package sv.alura.mj.topic.rest.DTO;

import jakarta.validation.constraints.NotNull;


public record CursoTopicoDTO(
        @NotNull Integer id,
        @NotNull String nombre) {
    public CursoTopicoDTO(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}

