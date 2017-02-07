package pl.edu.agh.app.requestor.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.agh.app.requestor.model.entity.definition.TestDefinition;

import java.util.Optional;

@Repository
public interface TestDefinitionRepository extends JpaRepository<TestDefinition, Long> {

    @Query("SELECT td FROM TestDefinition td WHERE td.id = :id")
    Optional<TestDefinition> getById(@Param("id") Long id);
}
