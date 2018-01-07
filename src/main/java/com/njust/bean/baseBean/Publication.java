package com.njust.bean.baseBean;

import javax.persistence.*;

public class Publication {
    @Id
    @Column(name = "publication_id")
    private Integer publicationId;

    @Column(name = "publication_name")
    private String publicationName;

    @Column(name = "publication_score")
    private Double publicationScore;

    public Publication(Integer publicationId, String publicationName, Double publicationScore) {
        this.publicationId = publicationId;
        this.publicationName = publicationName;
        this.publicationScore = publicationScore;
    }

    public Publication() {
        super();
    }

    /**
     * @return publication_id
     */
    public Integer getPublicationId() {
        return publicationId;
    }

    /**
     * @param publicationId
     */
    public void setPublicationId(Integer publicationId) {
        this.publicationId = publicationId;
    }

    /**
     * @return publication_name
     */
    public String getPublicationName() {
        return publicationName;
    }

    /**
     * @param publicationName
     */
    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName == null ? null : publicationName.trim();
    }

    /**
     * @return publication_score
     */
    public Double getPublicationScore() {
        return publicationScore;
    }

    /**
     * @param publicationScore
     */
    public void setPublicationScore(Double publicationScore) {
        this.publicationScore = publicationScore;
    }
}