package JavaProject.Semester.Repository;

import JavaProject.Semester.Models.ScoreCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScoreCardRepository extends JpaRepository<ScoreCard, Long> {
    boolean existsByJudgeIdAndEntryId(Long judgeId, Long entryId);
    Optional<ScoreCard> findByJudgeIdAndEntryId(Long judgeId, Long entryId);
    List<ScoreCard> findByEntryId(Long entryId);
    List<ScoreCard> findByJudgeId(Long judgeId);
}
