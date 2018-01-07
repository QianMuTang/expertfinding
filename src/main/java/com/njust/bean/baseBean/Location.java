package com.njust.bean.baseBean;

import javax.persistence.*;

public class Location {
    @Id
    @Column(name = "location_id")
    private Integer locationId;

    private String country;

    private String province;

    private String city;

    public Location(Integer locationId, String country, String province, String city) {
        this.locationId = locationId;
        this.country = country;
        this.province = province;
        this.city = city;
    }

    public Location() {
        super();
    }

    /**
     * @return location_id
     */
    public Integer getLocationId() {
        return locationId;
    }

    /**
     * @param locationId
     */
    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
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
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    /**
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }
}