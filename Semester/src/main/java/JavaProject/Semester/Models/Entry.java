package JavaProject.Semester.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "entries")
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Film title is required")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Film genre is required")
    @Column(nullable = false)
    private String genre;

    @NotBlank(message = "Video link is required")
    @Column(nullable = false)
    private String videoLink;

    private String teamName;

    @Column(length = 1000)
    private String description;

    private Double averageScore = 0.0;

    private Integer totalEvaluations = 0;

    private LocalDateTime createdAt;

    public Entry() {
        this.createdAt = LocalDateTime.now();
    }

    public Entry(String title, String genre, String videoLink, String teamName, String description) {
        this();
        this.title = title;
        this.genre = genre;
        this.videoLink = videoLink;
        this.teamName = teamName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(Double averageScore) {
        this.averageScore = averageScore;
    }

    public Integer getTotalEvaluations() {
        return totalEvaluations;
    }

    public void setTotalEvaluations(Integer totalEvaluations) {
        this.totalEvaluations = totalEvaluations;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
