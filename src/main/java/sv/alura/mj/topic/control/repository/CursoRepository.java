package sv.alura.mj.topic.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.alura.mj.topic.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {
    // Puedes agregar consultas personalizadas si las necesitas
}