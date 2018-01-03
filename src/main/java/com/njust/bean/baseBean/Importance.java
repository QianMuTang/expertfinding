package com.njust.bean.baseBean;

public class Importance {
    private Long importanceId;

    private Long resultId;

    private Double importanceScore;

    public Importance(Long importanceId, Long resultId, Double importanceScore) {
        this.importanceId = importanceId;
        this.resultId = resultId;
        this.importanceScore = importanceScore;
    }

    public Importance() {
        super();
    }

    public Long getImportanceId() {
        return importanceId;
    }

    public void setImportanceId(Long importanceId) {
        this.importanceId = importanceId;
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public Double getImportanceScore() {
        return importanceScore;
    }

    public void setImportanceScore(Double importanceScore) {
        this.importanceScore = importanceScore;
    }
}