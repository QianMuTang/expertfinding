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

    private byte[] ico;

    public Expert(Integer expertId, String expertName, Integer gender, String contact, String email, String education, String nation, String politicalStatus, Date birthday, Double expertScore, byte[] ico) {
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
        this.ico = ico;
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
     * @return ico
     */
    public byte[] getIco() {
        return ico;
    }

    /**
     * @param ico
     */
    public void setIco(byte[] ico) {
        this.ico = ico;
    }
}