package JavaProject.Semester.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ScoreCardRequest {

    @NotNull(message = "Judge ID is required")
    private Long judgeId;

    @NotNull(message = "Entry ID is required")
    private Long entryId;

    @Min(value = 0, message = "Story score must be at least 0")
    @Max(value = 20, message = "Story score cannot exceed 20")
    private Double story;

    @Min(value = 0, message = "Direction score must be at least 0")
    @Max(value = 20, message = "Direction score cannot exceed 20")
    private Double direction;

    @Min(value = 0, message = "Acting score must be at least 0")
    @Max(value = 20, message = "Acting score cannot exceed 20")
    private Double acting;

    @Min(value = 0, message = "Cinematography score must be at least 0")
    @Max(value = 20, message = "Cinematography score cannot exceed 20")
    private Double cinematography;

    @Min(value = 0, message = "Editing score must be at least 0")
    @Max(value = 20, message = "Editing score cannot exceed 20")
    private Double editing;

    @Min(value = 0, message = "Score must be at least 0")
    @Max(value = 100, message = "Score cannot exceed 100")
    private Double score;

    private String feedback;

    public ScoreCardRequest() {
    }

    public ScoreCardRequest(Long judgeId, Long entryId, Double story, Double direction, Double acting, Double cinematography, Double editing, String feedback) {
        this.judgeId = judgeId;
        this.entryId = entryId;
        this.story = story;
        this.direction = direction;
        this.acting = acting;
        this.cinematography = cinematography;
        this.editing = editing;
        this.feedback = feedback;
        if (story != null || direction != null || acting != null || cinematography != null || editing != null) {
            double s = story != null ? story : 0.0;
            double d = direction != null ? direction : 0.0;
            double a = acting != null ? acting : 0.0;
            double c = cinematography != null ? cinematography : 0.0;
            double e = editing != null ? editing : 0.0;
            this.score = s + d + a + c + e;
        }
    }

    public ScoreCardRequest(Long judgeId, Long entryId, Double score, String feedback) {
        this.judgeId = judgeId;
        this.entryId = entryId;
        this.score = score;
        this.feedback = feedback;
    }

    public Long getJudgeId() {
        return judgeId;
    }

    public void setJudgeId(Long judgeId) {
        this.judgeId = judgeId;
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
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
}
