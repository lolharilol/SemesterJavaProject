package JavaProject.Semester.Services;

import JavaProject.Semester.dto.ScoreCardRequest;
import JavaProject.Semester.Models.Entry;
import JavaProject.Semester.Models.Judge;
import JavaProject.Semester.Models.ScoreCard;
import JavaProject.Semester.Exception.DuplicateSubmissionException;
import JavaProject.Semester.Exception.ResourceNotFoundException;
import JavaProject.Semester.Repository.EntryRepository;
import JavaProject.Semester.Repository.JudgeRepository;
import JavaProject.Semester.Repository.ScoreCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScoreCardService {

    private static final Logger logger = LoggerFactory.getLogger(ScoreCardService.class);

    private final ScoreCardRepository scoreCardRepository;
    private final EntryRepository entryRepository;
    private final JudgeRepository judgeRepository;
    private final EntryService entryService;

    @Autowired
    public ScoreCardService(ScoreCardRepository scoreCardRepository,
                            EntryRepository entryRepository,
                            JudgeRepository judgeRepository,
                            EntryService entryService) {
        this.scoreCardRepository = scoreCardRepository;
        this.entryRepository = entryRepository;
        this.judgeRepository = judgeRepository;
        this.entryService = entryService;
    }

    @Transactional
    public ScoreCard submitScoreCard(ScoreCardRequest request) {
        Long judgeId = request.getJudgeId();
        Long entryId = request.getEntryId();

        // 1. Verify Entry exists
        Entry entry = entryRepository.findById(entryId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot submit scorecard. Entry not found with ID: " + entryId));

        // 2. Verify Judge exists
        Judge judge = judgeRepository.findById(judgeId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot submit scorecard. Judge not found with ID: " + judgeId));

        // 3. BUSINESS RULE: A judge can submit only ONE scorecard per entry.
        // Enforce in Service Layer before persisting.
        if (scoreCardRepository.existsByJudgeIdAndEntryId(judgeId, entryId)) {
            logger.warn("REJECTED SUBMISSION: Judge ID {} has already evaluated Entry ID {}", judgeId, entryId);
            throw new DuplicateSubmissionException(
                    "Judge with ID " + judgeId + " (" + judge.getName() + ") has already submitted a scorecard for Entry ID " + entryId + " ('" + entry.getTitle() + "')"
            );
        }

        // 4. Create and persist ScoreCard
        ScoreCard scoreCard = new ScoreCard(judge, entry, request.getScore(), request.getFeedback());
        ScoreCard savedScoreCard = scoreCardRepository.save(scoreCard);

        logger.info("[NOTIFICATION] ScoreCard #{} recorded: Judge '{}' rated Entry '{}' with score {}",
                savedScoreCard.getId(), judge.getName(), entry.getTitle(), request.getScore());

        // 5. Trigger average score calculation on Entry
        entryService.recalculateAverageScore(entryId);

        return savedScoreCard;
    }

    public List<ScoreCard> getAllScoreCards() {
        return scoreCardRepository.findAll();
    }

    public List<ScoreCard> getScoreCardsByEntry(Long entryId) {
        if (!entryRepository.existsById(entryId)) {
            throw new ResourceNotFoundException("Entry not found with ID: " + entryId);
        }
        return scoreCardRepository.findByEntryId(entryId);
    }

    public List<ScoreCard> getScoreCardsByJudge(Long judgeId) {
        if (!judgeRepository.existsById(judgeId)) {
            throw new ResourceNotFoundException("Judge not found with ID: " + judgeId);
        }
        return scoreCardRepository.findByJudgeId(judgeId);
    }
}
