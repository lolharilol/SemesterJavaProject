package JavaProject.Semester.dto;

public class LeaderboardDto {

    private Integer rank;
    private Long entryId;
    private String title;
    private String genre;
    private String teamName;
    private String videoLink;
    private Double averageScore;
    private Integer totalEvaluations;

    public LeaderboardDto() {
    }

    public LeaderboardDto(Integer rank, Long entryId, String title, String genre, String teamName, String videoLink, Double averageScore, Integer totalEvaluations) {
        this.rank = rank;
        this.entryId = entryId;
        this.title = title;
        this.genre = genre;
        this.teamName = teamName;
        this.videoLink = videoLink;
        this.averageScore = averageScore;
        this.totalEvaluations = totalEvaluations;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Long getEntryId() {
        return entryId;
    }

    public void setEntryId(Long entryId) {
        this.entryId = entryId;
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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
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
}
