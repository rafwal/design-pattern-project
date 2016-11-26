package pl.edu.agh.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.edu.agh.model.Request;

import java.util.Optional;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query("SELECT r FROM Request r WHERE r.id = :id")
    Optional<Request> findById(@Param("id") Integer id);
}
