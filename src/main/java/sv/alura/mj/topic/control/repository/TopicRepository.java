package sv.alura.mj.topic.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.alura.mj.topic.entities.Topico;

public interface TopicRepository extends JpaRepository<Topico, Integer> {
}
