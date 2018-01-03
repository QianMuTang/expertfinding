package com.njust.bean.baseBean;

import java.util.Date;

public class Expert {
    private Integer expertId;

    private String expertName;

    private Integer gender;

    private String contact;

    private String email;

    private String education;

    private String nation;

    private String politicalStatus;

    private Date birthday;

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

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName == null ? null : expertName.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus == null ? null : politicalStatus.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getExpertScore() {
        return expertScore;
    }

    public void setExpertScore(Double expertScore) {
        this.expertScore = expertScore;
    }

    public byte[] getIco() {
        return ico;
    }

    public void setIco(byte[] ico) {
        this.ico = ico;
    }
}