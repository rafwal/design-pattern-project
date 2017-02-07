package pl.edu.agh.app.requestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.agh.app.requestor.model.entity.execution.TestExecution;

@Repository
public interface TestExecutionRepository extends JpaRepository<TestExecution, Long> {

}
