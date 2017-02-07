package pl.edu.agh.app.requestor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.agh.app.requestor.model.entity.definition.ThreadGroup;

import java.util.Optional;

@Repository
public interface ThreadGroupRepository extends JpaRepository<ThreadGroup, Long> {

    @Query("SELECT tg FROM ThreadGroup tg WHERE tg.id = :id")
    Optional<ThreadGroup> findById(@Param("id") Long id);
}
