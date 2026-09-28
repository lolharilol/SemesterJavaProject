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

    @Min(value = 0, message = "Story score cannot be less than 0")
    @Max(value = 20, message = "Story score cannot exceed 20")
    private Double story = 0.0;

    @Min(value = 0, message = "Direction score cannot be less than 0")
    @Max(value = 20, message = "Direction score cannot exceed 20")
    private Double direction = 0.0;

    @Min(value = 0, message = "Acting score cannot be less than 0")
    @Max(value = 20, message = "Acting score cannot exceed 20")
    private Double acting = 0.0;

    @Min(value = 0, message = "Cinematography score cannot be less than 0")
    @Max(value = 20, message = "Cinematography score cannot exceed 20")
    private Double cinematography = 0.0;

    @Min(value = 0, message = "Editing score cannot be less than 0")
    @Max(value = 20, message = "Editing score cannot exceed 20")
    private Double editing = 0.0;

    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score cannot be less than 0")
    @Max(value = 100, message = "Score cannot exceed 100")
    @Column(nullable = false)
    private Double score = 0.0;

    @Column(length = 1000)
    private String feedback;

    private LocalDateTime submittedAt;

    public ScoreCard() {
        this.submittedAt = LocalDateTime.now();
    }

    public ScoreCard(Judge judge, Entry entry, Double story, Double direction, Double acting, Double cinematography, Double editing, String feedback) {
        this();
        this.judge = judge;
        this.entry = entry;
        this.story = story != null ? story : 0.0;
        this.direction = direction != null ? direction : 0.0;
        this.acting = acting != null ? acting : 0.0;
        this.cinematography = cinematography != null ? cinematography : 0.0;
        this.editing = editing != null ? editing : 0.0;
        this.score = this.story + this.direction + this.acting + this.cinematography + this.editing;
        this.feedback = feedback;
    }

    public ScoreCard(Judge judge, Entry entry, Double score, String feedback) {
        this();
        this.judge = judge;
        this.entry = entry;
        this.score = score != null ? score : 0.0;
        double part = this.score / 5.0;
        this.story = part;
        this.direction = part;
        this.acting = part;
        this.cinematography = part;
        this.editing = part;
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

    public Double getStory() {
        return story;
    }

    public void setStory(Double story) {
        this.story = story;
    }

    public Double getDirection() {
        return direction;
    }

    public void setDirection(Double direction) {
        this.direction = direction;
    }

    public Double getActing() {
        return acting;
    }

    public void setActing(Double acting) {
        this.acting = acting;
    }

    public Double getCinematography() {
        return cinematography;
    }

    public void setCinematography(Double cinematography) {
        this.cinematography = cinematography;
    }

    public Double getEditing() {
        return editing;
    }

    public void setEditing(Double editing) {
        this.editing = editing;
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
