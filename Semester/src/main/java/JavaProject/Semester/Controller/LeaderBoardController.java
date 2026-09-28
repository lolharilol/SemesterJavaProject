package JavaProject.Semester.Controller;

import JavaProject.Semester.dto.LeaderboardDto;
import JavaProject.Semester.Services.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@CrossOrigin(origins = "*")
class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @Autowired
    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping
    public ResponseEntity<List<LeaderboardDto>> getLeaderboard() {
        List<LeaderboardDto> leaderboard = leaderboardService.getLeaderboard();
        return ResponseEntity.ok(leaderboard);
    }

    @GetMapping("/top/{limit}")
    public ResponseEntity<List<LeaderboardDto>> getTopEntries(@PathVariable int limit) {
        List<LeaderboardDto> top = leaderboardService.getTopEntries(limit);
        return ResponseEntity.ok(top);
    }
}
