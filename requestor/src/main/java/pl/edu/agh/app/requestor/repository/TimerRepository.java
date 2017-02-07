package pl.edu.agh.app.requestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.app.requestor.model.entity.execution.Timer;

@Repository
public interface TimerRepository extends JpaRepository<Timer, Long> {

}
