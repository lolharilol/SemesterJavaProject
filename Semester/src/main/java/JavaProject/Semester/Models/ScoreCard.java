package JavaProject.Semester.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "score_cards",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"judge_id", "entry_id"})
    }
)
public class ScoreCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Judge reference is required")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "judge_id", nullable = false)
    private Judge judge;

    @NotNull(message = "Entry reference is required")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "entry_id", nullable = false)
    private Entry entry;

    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score cannot be less than 0")
    @Max(value = 100, message = "Score cannot exceed 100")
    @Column(nullable = false)
    private Double score;

    @Column(length = 1000)
    private String feedback;

    private LocalDateTime submittedAt;

    public ScoreCard() {
        this.submittedAt = LocalDateTime.now();
    }

    public ScoreCard(Judge judge, Entry entry, Double score, String feedback) {
        this();
        this.judge = judge;
        this.entry = entry;
        this.score = score;
        this.feedback = feedback;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Judge getJudge() {
        return judge;
    }

    public void setJudge(Judge judge) {
        this.judge = judge;
    }

    public Entry getEntry() {
        return entry;
    }

    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }
}
