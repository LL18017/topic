package sv.alura.mj.topic.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.alura.mj.topic.entities.Autor;


public interface AutorRepository extends JpaRepository<Autor, Integer> {
    Autor findByEmail(String email);
}
