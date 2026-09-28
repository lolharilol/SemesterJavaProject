package JavaProject.Semester.Config;

import JavaProject.Semester.Models.Entry;
import JavaProject.Semester.Models.Judge;
import JavaProject.Semester.dto.ScoreCardRequest;
import JavaProject.Semester.Exception.DuplicateSubmissionException;
import JavaProject.Semester.Repository.EntryRepository;
import JavaProject.Semester.Repository.JudgeRepository;
import JavaProject.Semester.Services.ScoreCardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    private final EntryRepository entryRepository;
    private final JudgeRepository judgeRepository;
    private final ScoreCardService scoreCardService;

    @Autowired
    public DataInitializer(EntryRepository entryRepository,
                           JudgeRepository judgeRepository,
                           ScoreCardService scoreCardService) {
        this.entryRepository = entryRepository;
        this.judgeRepository = judgeRepository;
        this.scoreCardService = scoreCardService;
    }

    @Override
    public void run(String... args) {
        logger.info("Ensuring core sample datasets (10 film entries, 5 judges) are present in database...");

        // 1. Ensure 5 Sample Judges exist
        List<Judge> judges = new ArrayList<>();
        judges.add(getOrCreateJudge("Christopher Nolan", "nolan@filmjury.org", "Directing & Narrative Structure"));
        judges.add(getOrCreateJudge("Greta Gerwig", "greta@filmjury.org", "Screenwriting & Acting Direction"));
        judges.add(getOrCreateJudge("Roger Deakins", "deakins@filmjury.org", "Cinematography & Visual Composition"));
        judges.add(getOrCreateJudge("Guillermo del Toro", "guillermo@filmjury.org", "Production Design & Storytelling"));
        judges.add(getOrCreateJudge("Thelma Schoonmaker", "thelma@filmjury.org", "Editing & Pacing"));

        // 2. Ensure 10 Sample Film Entries exist
        List<Entry> entries = new ArrayList<>();
        entries.add(getOrCreateEntry("Echoes of Silence", "Sci-Fi / Drama", "https://vimeo.com/769412345", "Starlight Studios", "A lone astronaut on Mars discovers mysterious acoustic signals beneath the red dust."));
        entries.add(getOrCreateEntry("The Last Canvas", "Drama / Art", "https://vimeo.com/769412346", "Prism Collective", "An aging painter completes his final masterpiece while confronting lost memories."));
        entries.add(getOrCreateEntry("Velocity", "Action / Thriller", "https://vimeo.com/769412347", "Apex Cinema", "A high-stakes courier races against time through futuristic city streets."));
        entries.add(getOrCreateEntry("Whispers in the Mist", "Horror / Mystery", "https://vimeo.com/769412348", "Shadowbox Films", "A group of college students explore an abandoned mountain observatory."));
        entries.add(getOrCreateEntry("Parallel Lines", "Romance / Sci-Fi", "https://vimeo.com/769412349", "Horizon Pictures", "Two strangers communicate across timeline splits using an ancient ham radio."));
        entries.add(getOrCreateEntry("Neon Dreams", "Cyberpunk / Crime", "https://vimeo.com/769412350", "Grid Runner Media", "A hacker uncovers a corporate conspiracy in a neon-drenched metropolis."));
        entries.add(getOrCreateEntry("Symphony of Solitude", "Documentary / Music", "https://vimeo.com/769412351", "Harmonic Lens", "An intimate portrait of a street musician crafting instruments from reclaimed wood."));
        entries.add(getOrCreateEntry("Beyond the Horizon", "Adventure / Fantasy", "https://vimeo.com/769412352", "Voyager Visuals", "A young cartographer discovers an unmapped island floating above the clouds."));
        entries.add(getOrCreateEntry("Paper Boats", "Animation / Family", "https://vimeo.com/769412353", "Origami Toons", "A heartwarming animated tale of childhood friendship across a river."));
        entries.add(getOrCreateEntry("The Golden Hour", "Comedy / Slice of Life", "https://vimeo.com/769412354", "Sunburst Crew", "Three friends try to record a viral video in one single golden-hour sunset."));

        // 3. Ensure ScoreCards exist for the sample entries using 5 Criteria breakdown (Story /20, Direction /20, Acting /20, Cinematography /20, Editing /20)
        // Entry 0: Echoes of Silence
        submitScore(judges.get(0), entries.get(0), 19.5, 19.0, 18.5, 20.0, 19.0, "Breathtaking visuals and brilliant sound design. Masterpiece!");
        submitScore(judges.get(1), entries.get(0), 19.0, 18.5, 19.0, 19.5, 18.5, "Strong narrative arc and emotional performance.");
        submitScore(judges.get(2), entries.get(0), 18.5, 19.5, 18.0, 20.0, 19.5, "Cinematography is unmatched. Stunning camera work.");

        // Entry 1: The Last Canvas
        submitScore(judges.get(1), entries.get(1), 18.5, 18.0, 19.0, 18.0, 17.5, "Deeply moving performances and emotional script.");
        submitScore(judges.get(3), entries.get(1), 18.0, 19.0, 18.5, 18.5, 18.0, "Great artistic direction and palette choice.");

        // Entry 2: Velocity
        submitScore(judges.get(0), entries.get(2), 16.0, 18.0, 16.5, 18.5, 19.0, "Pacing was relentless! Editing kept us on the edge of our seats.");
        submitScore(judges.get(4), entries.get(2), 15.5, 17.5, 16.0, 18.0, 19.5, "Seamless action cuts and rhythm.");

        // Entry 3: Whispers in the Mist
        submitScore(judges.get(3), entries.get(3), 17.0, 16.5, 16.0, 17.5, 16.5, "Creepy atmosphere and solid lighting.");

        // Entry 4: Parallel Lines
        submitScore(judges.get(1), entries.get(4), 18.0, 17.5, 18.0, 17.0, 17.5, "Heartwarming sci-fi concept with great chemistry.");
        submitScore(judges.get(2), entries.get(4), 17.5, 18.0, 17.5, 18.0, 17.0, "Beautiful lighting and clean frame composition.");

        // Entry 5: Neon Dreams
        submitScore(judges.get(2), entries.get(5), 16.5, 17.0, 16.0, 19.0, 17.5, "Neon aesthetics were superb!");

        // Entry 6: Symphony of Solitude
        submitScore(judges.get(4), entries.get(6), 19.0, 18.0, 17.5, 18.0, 18.5, "Incredible documentary editing and sound texture.");

        // Entry 7: Beyond the Horizon
        submitScore(judges.get(3), entries.get(7), 17.5, 18.0, 17.0, 18.5, 17.0, "Wondrous world-building and fantasy visuals.");

        // Entry 8: Paper Boats
        submitScore(judges.get(1), entries.get(8), 18.0, 18.5, 18.0, 17.5, 18.0, "Charming animation style and storytelling.");

        // Entry 9: The Golden Hour
        submitScore(judges.get(0), entries.get(9), 16.0, 16.5, 17.0, 17.0, 16.5, "Fun and engaging slice of life!");

        logger.info("Sample datasets initialized/verified successfully.");
    }

    private Judge getOrCreateJudge(String name, String email, String expertise) {
        return judgeRepository.findByEmail(email)
                .orElseGet(() -> judgeRepository.save(new Judge(name, email, expertise)));
    }

    private Entry getOrCreateEntry(String title, String genre, String videoLink, String teamName, String description) {
        return entryRepository.findByTitle(title)
                .orElseGet(() -> entryRepository.save(new Entry(title, genre, videoLink, teamName, description)));
    }

    private void submitScore(Judge judge, Entry entry, double story, double direction, double acting, double cinematography, double editing, String feedback) {
        try {
            ScoreCardRequest req = new ScoreCardRequest(
                    judge.getId(),
                    entry.getId(),
                    story,
                    direction,
                    acting,
                    cinematography,
                    editing,
                    feedback
            );
            scoreCardService.submitScoreCard(req);
        } catch (DuplicateSubmissionException ignored) {
            // Already scored, preserve existing evaluation
        }
    }
}
