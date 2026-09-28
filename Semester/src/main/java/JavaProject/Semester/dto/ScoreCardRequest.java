package JavaProject.Semester.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ScoreCardRequest {

    @NotNull(message = "Judge ID is required")
    private Long judgeId;

    @NotNull(message = "Entry ID is required")
    private Long entryId;

    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score must be at least 0")
    @Max(value = 100, message = "Score cannot exceed 100")
    private Double score;

    private String feedback;

    public ScoreCardRequest() {
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
