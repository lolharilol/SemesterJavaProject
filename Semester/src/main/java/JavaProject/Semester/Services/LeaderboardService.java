package JavaProject.Semester.Services;

import JavaProject.Semester.dto.LeaderboardDto;
import JavaProject.Semester.Models.Entry;
import JavaProject.Semester.Repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeaderboardService {

    private final EntryRepository entryRepository;

    @Autowired
    public LeaderboardService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public List<LeaderboardDto> getLeaderboard() {
        List<Entry> rankedEntries = entryRepository.findAllByOrderByAverageScoreDesc();
        List<LeaderboardDto> leaderboard = new ArrayList<>();

        for (int i = 0; i < rankedEntries.size(); i++) {
            Entry entry = rankedEntries.get(i);
            LeaderboardDto dto = new LeaderboardDto(
                    i + 1, // rank starts at 1
                    entry.getId(),
                    entry.getTitle(),
                    entry.getGenre(),
                    entry.getTeamName(),
                    entry.getVideoLink(),
                    entry.getAverageScore(),
                    entry.getTotalEvaluations()
            );
            leaderboard.add(dto);
        }

        return leaderboard;
    }

    public List<LeaderboardDto> getTopEntries(int limit) {
        return getLeaderboard().stream()
                .limit(limit)
                .collect(Collectors.toList());
    }
}
