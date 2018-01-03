package com.njust.bean.baseBean;

public class Location {
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

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }
}