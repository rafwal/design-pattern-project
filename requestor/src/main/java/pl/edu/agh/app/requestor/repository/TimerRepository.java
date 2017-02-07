package pl.edu.agh.app.requestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.agh.app.requestor.model.entity.execution.SingleExecution;
import pl.edu.agh.app.requestor.model.entity.execution.Timer;

import java.util.List;

@Repository
public interface TimerRepository extends JpaRepository<Timer, Long> {

    @Query("SELECT t FROM Timer t WHERE t.testExecution.id = :id")
    public List<Timer> getByTestExecutionId(@Param("id") Long id);
}
