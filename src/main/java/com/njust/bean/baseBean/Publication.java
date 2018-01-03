package com.njust.bean.baseBean;

public class Publication {
    private Integer publicationId;

    private String publicationName;

    private Double publicationScore;

    public Publication(Integer publicationId, String publicationName, Double publicationScore) {
        this.publicationId = publicationId;
        this.publicationName = publicationName;
        this.publicationScore = publicationScore;
    }

    public Publication() {
        super();
    }

    public Integer getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Integer publicationId) {
        this.publicationId = publicationId;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String publicationName) {
        this.publicationName = publicationName == null ? null : publicationName.trim();
    }

    public Double getPublicationScore() {
        return publicationScore;
    }

    public void setPublicationScore(Double publicationScore) {
        this.publicationScore = publicationScore;
    }
}