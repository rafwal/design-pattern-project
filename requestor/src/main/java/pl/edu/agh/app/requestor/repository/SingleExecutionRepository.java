package pl.edu.agh.app.requestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.agh.app.requestor.model.entity.execution.SingleExecution;

import java.util.List;

@Repository
public interface SingleExecutionRepository extends JpaRepository<SingleExecution, Long> {

    @Query("SELECT se FROM SingleExecution se WHERE se.testExecution.id = :id")
    public List<SingleExecution> getByTestExecutionId(@Param("id") Long id);
}
