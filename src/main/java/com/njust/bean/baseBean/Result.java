package com.njust.bean.baseBean;

import java.util.Date;

public class Result {
    private Long resultId;

    private String resultName;

    private String resultDesc;

    private Integer resultType;

    private Date resultTime;

    private Integer citedNum;

    private Integer award;

    private String score;

    private Integer pubilcationId;

    private Integer authorRanking;

    public Result(Long resultId, String resultName, String resultDesc, Integer resultType, Date resultTime, Integer citedNum, Integer award, String score, Integer pubilcationId, Integer authorRanking) {
        this.resultId = resultId;
        this.resultName = resultName;
        this.resultDesc = resultDesc;
        this.resultType = resultType;
        this.resultTime = resultTime;
        this.citedNum = citedNum;
        this.award = award;
        this.score = score;
        this.pubilcationId = pubilcationId;
        this.authorRanking = authorRanking;
    }

    public Result() {
        super();
    }

    public Long getResultId() {
        return resultId;
    }

    public void setResultId(Long resultId) {
        this.resultId = resultId;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName == null ? null : resultName.trim();
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc == null ? null : resultDesc.trim();
    }

    public Integer getResultType() {
        return resultType;
    }

    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    public Date getResultTime() {
        return resultTime;
    }

    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }

    public Integer getCitedNum() {
        return citedNum;
    }

    public void setCitedNum(Integer citedNum) {
        this.citedNum = citedNum;
    }

    public Integer getAward() {
        return award;
    }

    public void setAward(Integer award) {
        this.award = award;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    public Integer getPubilcationId() {
        return pubilcationId;
    }

    public void setPubilcationId(Integer pubilcationId) {
        this.pubilcationId = pubilcationId;
    }

    public Integer getAuthorRanking() {
        return authorRanking;
    }

    public void setAuthorRanking(Integer authorRanking) {
        this.authorRanking = authorRanking;
    }
}