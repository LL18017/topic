package sv.alura.mj.topic.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sv.alura.mj.topic.entities.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Integer> {
    // Puedes agregar consultas personalizadas si las necesitas
}
