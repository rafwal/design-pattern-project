package pl.edu.agh.app.requestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.agh.app.requestor.model.entity.execution.TestExecution;

import java.util.Optional;

@Repository
public interface TestExecutionRepository extends JpaRepository<TestExecution, Long> {

    @Query("SELECT te FROM TestExecution te WHERE te.id = :id")
    Optional<TestExecution> findById(@Param("id") Long id);
}
