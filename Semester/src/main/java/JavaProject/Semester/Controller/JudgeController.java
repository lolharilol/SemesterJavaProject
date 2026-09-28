package JavaProject.Semester.Controller;

import JavaProject.Semester.Models.Judge;
import JavaProject.Semester.Services.JudgeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/judges")
@CrossOrigin(origins = "*")
public class JudgeController {

    private final JudgeService judgeService;

    @Autowired
    public JudgeController(JudgeService judgeService) {
        this.judgeService = judgeService;
    }

    @PostMapping
    public ResponseEntity<Judge> registerJudge(@Valid @RequestBody Judge judge) {
        Judge created = judgeService.registerJudge(judge);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Judge>> getAllJudges() {
        List<Judge> judges = judgeService.getAllJudges();
        return ResponseEntity.ok(judges);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Judge> getJudgeById(@PathVariable Long id) {
        Judge judge = judgeService.getJudgeById(id);
        return ResponseEntity.ok(judge);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Judge> updateJudge(@PathVariable Long id, @Valid @RequestBody Judge judge) {
        Judge updated = judgeService.updateJudge(id, judge);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJudge(@PathVariable Long id) {
        judgeService.deleteJudge(id);
        return ResponseEntity.noContent().build();
    }
}
