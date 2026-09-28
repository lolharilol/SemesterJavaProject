package JavaProject.Semester.Services;

import JavaProject.Semester.Models.Entry;
import JavaProject.Semester.Models.ScoreCard;
import JavaProject.Semester.Exception.ResourceNotFoundException;
import JavaProject.Semester.Repository.EntryRepository;
import JavaProject.Semester.Repository.ScoreCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class EntryService {

    private static final Logger logger = LoggerFactory.getLogger(EntryService.class);

    private final EntryRepository entryRepository;
    private final ScoreCardRepository scoreCardRepository;

    @Autowired
    public EntryService(EntryRepository entryRepository, ScoreCardRepository scoreCardRepository) {
        this.entryRepository = entryRepository;
        this.scoreCardRepository = scoreCardRepository;
    }

    public Entry createEntry(Entry entry) {
        logger.info("Submitting new film entry: '{}' by team '{}'", entry.getTitle(), entry.getTeamName());
        return entryRepository.save(entry);
    }

    public List<Entry> getAllEntries() {
        return entryRepository.findAll();
    }

    public Entry getEntryById(Long id) {
        return entryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film entry not found with ID: " + id));
    }

    public Entry updateEntry(Long id, Entry details) {
        Entry existing = getEntryById(id);
        existing.setTitle(details.getTitle());
        existing.setGenre(details.getGenre());
        existing.setVideoLink(details.getVideoLink());
        existing.setTeamName(details.getTeamName());
        existing.setDescription(details.getDescription());
        logger.info("Updated film entry details for ID: {}", id);
        return entryRepository.save(existing);
    }

    public void deleteEntry(Long id) {
        Entry existing = getEntryById(id);
        entryRepository.delete(existing);
        logger.info("Deleted film entry with ID: {}", id);
    }

    @Transactional
    public Entry recalculateAverageScore(Long entryId) {
        Entry entry = getEntryById(entryId);
        List<ScoreCard> scoreCards = scoreCardRepository.findByEntryId(entryId);

        if (scoreCards.isEmpty()) {
            entry.setAverageScore(0.0);
            entry.setTotalEvaluations(0);
        } else {
            double total = scoreCards.stream().mapToDouble(ScoreCard::getScore).sum();
            double avg = total / scoreCards.size();

            // Round to 2 decimal places
            BigDecimal roundedAvg = BigDecimal.valueOf(avg).setScale(2, RoundingMode.HALF_UP);
            entry.setAverageScore(roundedAvg.doubleValue());
            entry.setTotalEvaluations(scoreCards.size());
        }

        Entry saved = entryRepository.save(entry);
        logger.info("[NOTIFICATION] Entry ID {} ('{}') score updated: New Average = {} across {} judge evaluations",
                saved.getId(), saved.getTitle(), saved.getAverageScore(), saved.getTotalEvaluations());
        return saved;
    }
}
