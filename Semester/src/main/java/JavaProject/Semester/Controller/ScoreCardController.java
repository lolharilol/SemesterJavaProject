package JavaProject.Semester.Controller;

import JavaProject.Semester.dto.ScoreCardRequest;
import JavaProject.Semester.Models.ScoreCard;
import JavaProject.Semester.Services.ScoreCardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scorecards")
@CrossOrigin(origins = "*")
public class ScoreCardController {

    private final ScoreCardService scoreCardService;

    @Autowired
    public ScoreCardController(ScoreCardService scoreCardService) {
        this.scoreCardService = scoreCardService;
    }

    @PostMapping
    public ResponseEntity<ScoreCard> submitScoreCard(@Valid @RequestBody ScoreCardRequest request) {
        ScoreCard created = scoreCardService.submitScoreCard(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ScoreCard>> getAllScoreCards() {
        List<ScoreCard> scoreCards = scoreCardService.getAllScoreCards();
        return ResponseEntity.ok(scoreCards);
    }

    @GetMapping("/entry/{entryId}")
    public ResponseEntity<List<ScoreCard>> getScoreCardsByEntry(@PathVariable Long entryId) {
        List<ScoreCard> scoreCards = scoreCardService.getScoreCardsByEntry(entryId);
        return ResponseEntity.ok(scoreCards);
    }

    @GetMapping("/judge/{judgeId}")
    public ResponseEntity<List<ScoreCard>> getScoreCardsByJudge(@PathVariable Long judgeId) {
        List<ScoreCard> scoreCards = scoreCardService.getScoreCardsByJudge(judgeId);
        return ResponseEntity.ok(scoreCards);
    }
}
