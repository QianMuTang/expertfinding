package com.njust.bean.baseBean;

import java.util.Date;
import javax.persistence.*;

public class Expert {
    @Id
    @Column(name = "expert_id")
    private Integer expertId;

    @Column(name = "expert_name")
    private String expertName;

    /**
     * 1男2女
     */
    private Integer gender;

    private String contact;

    private String email;

    private String education;

    private String nation;

    @Column(name = "political_status")
    private String politicalStatus;

    private Date birthday;

    @Column(name = "expert_score")
    private Double expertScore;

    @Column(name = "ico_name")
    private String icoName;

    @Column(name = "field_id")
    private Integer fieldId;

    private String introduce;

    private String homepage;

    private String country;

    private String tag;

    @Column(name = "h_index")
    private Integer hIndex;

    @Column(name = "g_index")
    private Integer gIndex;

    private Double sociability;

    private Double diversity;

    @Column(name = "professional_title")
    private String professionalTitle;

    @Column(name = "paper_num")
    private Integer paperNum;

    @Column(name = "patent_num")
    private Integer patentNum;

    @Column(name = "award_num")
    private Integer awardNum;

    private String employer;

    public Expert(Integer expertId, String expertName, Integer gender, String contact, String email, String education, String nation, String politicalStatus, Date birthday, Double expertScore, String icoName, Integer fieldId, String introduce, String homepage, String country, String tag, Integer hIndex, Integer gIndex, Double sociability, Double diversity, String professionalTitle, Integer paperNum, Integer patentNum, Integer awardNum, String employer) {
        this.expertId = expertId;
        this.expertName = expertName;
        this.gender = gender;
        this.contact = contact;
        this.email = email;
        this.education = education;
        this.nation = nation;
        this.politicalStatus = politicalStatus;
        this.birthday = birthday;
        this.expertScore = expertScore;
        this.icoName = icoName;
        this.fieldId = fieldId;
        this.introduce = introduce;
        this.homepage = homepage;
        this.country = country;
        this.tag = tag;
        this.hIndex = hIndex;
        this.gIndex = gIndex;
        this.sociability = sociability;
        this.diversity = diversity;
        this.professionalTitle = professionalTitle;
        this.paperNum = paperNum;
        this.patentNum = patentNum;
        this.awardNum = awardNum;
        this.employer = employer;
    }

    public Expert() {
        super();
    }

    /**
     * @return expert_id
     */
    public Integer getExpertId() {
        return expertId;
    }

    /**
     * @param expertId
     */
    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    /**
     * @return expert_name
     */
    public String getExpertName() {
        return expertName;
    }

    /**
     * @param expertName
     */
    public void setExpertName(String expertName) {
        this.expertName = expertName == null ? null : expertName.trim();
    }

    /**
     * 获取1男2女
     *
     * @return gender - 1男2女
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置1男2女
     *
     * @param gender 1男2女
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * @return contact
     */
    public String getContact() {
        return contact;
    }

    /**
     * @param contact
     */
    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return education
     */
    public String getEducation() {
        return education;
    }

    /**
     * @param education
     */
    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    /**
     * @return nation
     */
    public String getNation() {
        return nation;
    }

    /**
     * @param nation
     */
    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    /**
     * @return political_status
     */
    public String getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * @param politicalStatus
     */
    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus == null ? null : politicalStatus.trim();
    }

    /**
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return expert_score
     */
    public Double getExpertScore() {
        return expertScore;
    }

    /**
     * @param expertScore
     */
    public void setExpertScore(Double expertScore) {
        this.expertScore = expertScore;
    }

    /**
     * @return ico_name
     */
    public String getIcoName() {
        return icoName;
    }

    /**
     * @param icoName
     */
    public void setIcoName(String icoName) {
        this.icoName = icoName == null ? null : icoName.trim();
    }

    /**
     * @return field_id
     */
    public Integer getFieldId() {
        return fieldId;
    }

    /**
     * @param fieldId
     */
    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    /**
     * @return introduce
     */
    public String getIntroduce() {
        return introduce;
    }

    /**
     * @param introduce
     */
    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    /**
     * @return homepage
     */
    public String getHomepage() {
        return homepage;
    }

    /**
     * @param homepage
     */
    public void setHomepage(String homepage) {
        this.homepage = homepage == null ? null : homepage.trim();
    }

    /**
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country
     */
    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    /**
     * @return tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag
     */
    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    /**
     * @return h_index
     */
    public Integer gethIndex() {
        return hIndex;
    }

    /**
     * @param hIndex
     */
    public void sethIndex(Integer hIndex) {
        this.hIndex = hIndex;
    }

    /**
     * @return g_index
     */
    public Integer getgIndex() {
        return gIndex;
    }

    /**
     * @param gIndex
     */
    public void setgIndex(Integer gIndex) {
        this.gIndex = gIndex;
    }

    /**
     * @return sociability
     */
    public Double getSociability() {
        return sociability;
    }

    /**
     * @param sociability
     */
    public void setSociability(Double sociability) {
        this.sociability = sociability;
    }

    /**
     * @return diversity
     */
    public Double getDiversity() {
        return diversity;
    }

    /**
     * @param diversity
     */
    public void setDiversity(Double diversity) {
        this.diversity = diversity;
    }

    /**
     * @return professional_title
     */
    public String getProfessionalTitle() {
        return professionalTitle;
    }

    /**
     * @param professionalTitle
     */
    public void setProfessionalTitle(String professionalTitle) {
        this.professionalTitle = professionalTitle == null ? null : professionalTitle.trim();
    }

    /**
     * @return paper_num
     */
    public Integer getPaperNum() {
        return paperNum;
    }

    /**
     * @param paperNum
     */
    public void setPaperNum(Integer paperNum) {
        this.paperNum = paperNum;
    }

    /**
     * @return patent_num
     */
    public Integer getPatentNum() {
        return patentNum;
    }

    /**
     * @param patentNum
     */
    public void setPatentNum(Integer patentNum) {
        this.patentNum = patentNum;
    }

    /**
     * @return award_num
     */
    public Integer getAwardNum() {
        return awardNum;
    }

    /**
     * @param awardNum
     */
    public void setAwardNum(Integer awardNum) {
        this.awardNum = awardNum;
    }

    /**
     * @return employer
     */
    public String getEmployer() {
        return employer;
    }

    /**
     * @param employer
     */
    public void setEmployer(String employer) {
        this.employer = employer == null ? null : employer.trim();
    }
}