package JavaProject.Semester.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity
@Table(name = "criteria")
public class Criterion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Criterion name is required")
    @Column(nullable = false, unique = true)
    private String name;

    @NotNull(message = "Maximum score is required")
    @Positive(message = "Maximum score must be greater than 0")
    private Integer maxScore = 10;

    private Double weight = 1.0;

    public Criterion() {
    }

    public Criterion(String name, Integer maxScore, Double weight) {
        this.name = name;
        this.maxScore = maxScore;
        this.weight = weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
