package JavaProject.Semester.Repository;

import JavaProject.Semester.Models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {
    List<Entry> findAllByOrderByAverageScoreDesc();
    boolean existsByTitle(String title);
    java.util.Optional<Entry> findByTitle(String title);
}
