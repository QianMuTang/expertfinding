package com.njust.bean.baseBean;

import javax.persistence.*;

public class Importance {
    @Id
    @Column(name = "importance_id")
    private Long importanceId;

    @Column(name = "result_id")
    private Long resultId;

    @Column(name = "importance_score")
    private Double importanceScore;

    public Importance(Long importanceId, Long resultId, Double importanceScore) {
        this.importanceId = importanceId;
        this.resultId = resultId;
        this.importanceScore = importanceScore;
    }

    public Importance() {
        super();
    }

    /**
     * @return importance_id
     */
    public Long getImportanceId() {
        return importanceId;
    }

    /**
     * @param importanceId
     */
    public void setImportanceId(Long importanceId) {
        this.importanceId = importanceId;
    }

    /**
     * @return result_id
     */
    public Long getResultId() {
        return resultId;
    }

    /**
     * @param resultId
     */
    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    /**
     * @return importance_score
     */
    public Double getImportanceScore() {
        return importanceScore;
    }

    /**
     * @param importanceScore
     */
    public void setImportanceScore(Double importanceScore) {
        this.importanceScore = importanceScore;
    }
}