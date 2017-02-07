package pl.edu.agh.app.requestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.agh.app.requestor.model.entity.execution.Timer;

public interface TimerRepository extends JpaRepository<Timer, Long> {

}
