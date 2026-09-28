package JavaProject.Semester.Repository;

import JavaProject.Semester.Models.Criterion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CriterionRepository extends JpaRepository<Criterion, Long> {
    boolean existsByName(String name);
}
