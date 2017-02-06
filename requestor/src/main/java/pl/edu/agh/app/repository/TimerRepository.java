package pl.edu.agh.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.app.model.entity.execution.Timer;

public interface TimerRepository extends JpaRepository<Timer, Long> {

}
