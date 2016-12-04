package pl.edu.agh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.agh.model.definition.ThreadGroup;

import java.util.Optional;

@Repository
public interface ThreadGroupRepository extends JpaRepository<ThreadGroup, Integer> {

    @Query("SELECT tg FROM ThreadGroup tg WHERE tg.id = :id")
    Optional<ThreadGroup> findById(@Param("id") Integer id);
}
