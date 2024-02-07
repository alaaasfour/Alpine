package com.alpine.domain;

import javax.persistence.*;

@Entity
public class UserBilling {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Unique identifier for the user billing address
    private Long id;
    private String userBillingName; // Name associated with the billing address
    private String userBillingStreet1; // First line of the billing address
    private String userBillingStreet2; // Second line of the billing address (optional)
    private String userBillingCity; // City of the billing address
    private String userBillingStateProvince; // State or province of the billing address
    private String userBillingCountry; // Country of the billing address
    private String userBillingZipPostalCode; // Zip or postal code of the billing address

    @OneToOne(cascade = CascadeType.ALL)
    // User payment associated with this billing address
    private UserPayment userPayment;

    // Getter and setter methods for each attribute
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserBillingName() {
        return userBillingName;
    }
    public void setUserBillingName(String userBillingName) {
        this.userBillingName = userBillingName;
    }
    public String getUserBillingStreet1() {
        return userBillingStreet1;
    }
    public void setUserBillingStreet1(String userBillingStreet1) {
        this.userBillingStreet1 = userBillingStreet1;
    }
    public String getUserBillingStreet2() {
        return userBillingStreet2;
    }
    public void setUserBillingStreet2(String userBillingStreet2) {
        this.userBillingStreet2 = userBillingStreet2;
    }
    public String getUserBillingCity() {
        return userBillingCity;
    }
    public void setUserBillingCity(String userBillingCity) {
        this.userBillingCity = userBillingCity;
    }
    public String getUserBillingStateProvince() {
        return userBillingStateProvince;
    }
    public void setUserBillingStateProvince(String userBillingStateProvince) {
        this.userBillingStateProvince = userBillingStateProvince;
    }
    public String getUserBillingCountry() {
        return userBillingCountry;
    }
    public void setUserBillingCountry(String userBillingCountry) {
        this.userBillingCountry = userBillingCountry;
    }
    public String getUserBillingZipPostalCode() {
        return userBillingZipPostalCode;
    }
    public void setUserBillingZipPostalCode(String userBillingZipPostalCode) {
        this.userBillingZipPostalCode = userBillingZipPostalCode;
    }
    public UserPayment getUserPayment() {
        return userPayment;
    }
    public void setUserPayment(UserPayment userPayment) {
        this.userPayment = userPayment;
    }
}
