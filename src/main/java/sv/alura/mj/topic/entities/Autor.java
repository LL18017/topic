package sv.alura.mj.topic.entities;

import jakarta.persistence.*;
import sv.alura.mj.topic.rest.DTO.AutorDTO;
import sv.alura.mj.topic.rest.DTO.AutorTopicoDTO;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_id_gen")
    @SequenceGenerator(name = "autor_id_gen", sequenceName = "autor_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "contrasenia", nullable = false)
    private String contrasenia;

    public Autor() {
    }

    public Autor(AutorDTO autorDTO) {
        this.id = autorDTO.id();
        this.contrasenia = autorDTO.contrasenia();
        this.nombre = autorDTO.nombre();
        this.email = autorDTO.email();
    }
    public Autor(AutorTopicoDTO autorTopicoDTO) {
        this.id = autorTopicoDTO.id();
        this.nombre = autorTopicoDTO.nombre();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

}