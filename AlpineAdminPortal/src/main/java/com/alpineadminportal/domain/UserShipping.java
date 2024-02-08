package com.alpineadminportal.domain;

import javax.persistence.*;

@Entity
public class UserShipping {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userShippingName;
    private String userShippingStreet1;
    private String userShippingStreet2;
    private String userShippingCity;
    private String userShippingStateProvince;
    private String userShippingCountry;
    private String userShippingZipPostalCode;
    private boolean userShippingDefault;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserShippingName() {
        return userShippingName;
    }
    public void setUserShippingName(String userShippingName) {
        this.userShippingName = userShippingName;
    }
    public String getUserShippingStreet1() {
        return userShippingStreet1;
    }
    public void setUserShippingStreet1(String userShippingStreet1) {
        this.userShippingStreet1 = userShippingStreet1;
    }
    public String getUserShippingStreet2() {
        return userShippingStreet2;
    }
    public void setUserShippingStreet2(String userShippingStreet2) {
        this.userShippingStreet2 = userShippingStreet2;
    }
    public String getUserShippingCity() {
        return userShippingCity;
    }
    public void setUserShippingCity(String userShippingCity) {
        this.userShippingCity = userShippingCity;
    }
    public String getUserShippingStateProvince() {
        return userShippingStateProvince;
    }
    public void setUserShippingStateProvince(String userShippingStateProvince) {
        this.userShippingStateProvince = userShippingStateProvince;
    }
    public String getUserShippingCountry() {
        return userShippingCountry;
    }
    public void setUserShippingCountry(String userShippingCountry) {
        this.userShippingCountry = userShippingCountry;
    }
    public String getUserShippingZipPostalCode() {
        return userShippingZipPostalCode;
    }
    public void setUserShippingZipPostalCode(String userShippingZipPostalCode) {
        this.userShippingZipPostalCode = userShippingZipPostalCode;
    }
    public boolean isUserShippingDefault() {
        return userShippingDefault;
    }
    public void setUserShippingDefault(boolean userShippingDefault) {
        this.userShippingDefault = userShippingDefault;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
