package com.njust.bean.baseBean;

import java.util.Date;
import javax.persistence.*;

public class Result {
    @Id
    @Column(name = "result_id")
    private Long resultId;

    @Column(name = "result_name")
    private String resultName;

    @Column(name = "result_desc")
    private String resultDesc;

    /**
     * 1论文/2专利/3获利
     */
    @Column(name = "result_type")
    private Integer resultType;

    @Column(name = "result_time")
    private Date resultTime;

    @Column(name = "cited_num")
    private Integer citedNum;

    /**
     * 0国际级/1国家级/2省级
     */
    private Integer award;

    private String score;

    @Column(name = "pubilcation_id")
    private Integer pubilcationId;

    @Column(name = "author_ranking")
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
     * @return result_name
     */
    public String getResultName() {
        return resultName;
    }

    /**
     * @param resultName
     */
    public void setResultName(String resultName) {
        this.resultName = resultName == null ? null : resultName.trim();
    }

    /**
     * @return result_desc
     */
    public String getResultDesc() {
        return resultDesc;
    }

    /**
     * @param resultDesc
     */
    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc == null ? null : resultDesc.trim();
    }

    /**
     * 获取1论文/2专利/3获利
     *
     * @return result_type - 1论文/2专利/3获利
     */
    public Integer getResultType() {
        return resultType;
    }

    /**
     * 设置1论文/2专利/3获利
     *
     * @param resultType 1论文/2专利/3获利
     */
    public void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    /**
     * @return result_time
     */
    public Date getResultTime() {
        return resultTime;
    }

    /**
     * @param resultTime
     */
    public void setResultTime(Date resultTime) {
        this.resultTime = resultTime;
    }

    /**
     * @return cited_num
     */
    public Integer getCitedNum() {
        return citedNum;
    }

    /**
     * @param citedNum
     */
    public void setCitedNum(Integer citedNum) {
        this.citedNum = citedNum;
    }

    /**
     * 获取0国际级/1国家级/2省级
     *
     * @return award - 0国际级/1国家级/2省级
     */
    public Integer getAward() {
        return award;
    }

    /**
     * 设置0国际级/1国家级/2省级
     *
     * @param award 0国际级/1国家级/2省级
     */
    public void setAward(Integer award) {
        this.award = award;
    }

    /**
     * @return score
     */
    public String getScore() {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(String score) {
        this.score = score == null ? null : score.trim();
    }

    /**
     * @return pubilcation_id
     */
    public Integer getPubilcationId() {
        return pubilcationId;
    }

    /**
     * @param pubilcationId
     */
    public void setPubilcationId(Integer pubilcationId) {
        this.pubilcationId = pubilcationId;
    }

    /**
     * @return author_ranking
     */
    public Integer getAuthorRanking() {
        return authorRanking;
    }

    /**
     * @param authorRanking
     */
    public void setAuthorRanking(Integer authorRanking) {
        this.authorRanking = authorRanking;
    }
}