package sv.alura.mj.topic.rest.DTO;

import jakarta.validation.constraints.NotNull;


public record AutorTopicoDTO(
        @NotNull Integer id,
        @NotNull String nombre) {
    public AutorTopicoDTO(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}
