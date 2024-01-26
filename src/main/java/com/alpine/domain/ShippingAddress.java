package com.alpine.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ShippingAddressName;
    private String ShippingAddressStreet1;
    private String ShippingAddressStreet2;
    private String ShippingAddressCity;
    private String ShippingAddressStateProvince;
    private String ShippingAddressCountry;
    private String ShippingAddressZipPostalCode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getShippingAddressName() {
        return ShippingAddressName;
    }
    public void setShippingAddressName(String shippingAddressName) {
        ShippingAddressName = shippingAddressName;
    }
    public String getShippingAddressStreet1() {
        return ShippingAddressStreet1;
    }
    public void setShippingAddressStreet1(String shippingAddressStreet1) {
        ShippingAddressStreet1 = shippingAddressStreet1;
    }
    public String getShippingAddressStreet2() {
        return ShippingAddressStreet2;
    }
    public void setShippingAddressStreet2(String shippingAddressStreet2) {
        ShippingAddressStreet2 = shippingAddressStreet2;
    }
    public String getShippingAddressCity() {
        return ShippingAddressCity;
    }
    public void setShippingAddressCity(String shippingAddressCity) {
        ShippingAddressCity = shippingAddressCity;
    }
    public String getShippingAddressStateProvince() {
        return ShippingAddressStateProvince;
    }
    public void setShippingAddressStateProvince(String shippingAddressStateProvince) {
        ShippingAddressStateProvince = shippingAddressStateProvince;
    }
    public String getShippingAddressCountry() {
        return ShippingAddressCountry;
    }
    public void setShippingAddressCountry(String shippingAddressCountry) {
        ShippingAddressCountry = shippingAddressCountry;
    }
    public String getShippingAddressZipPostalCode() {
        return ShippingAddressZipPostalCode;
    }
    public void setShippingAddressZipPostalCode(String shippingAddressZipPostalCode) {
        ShippingAddressZipPostalCode = shippingAddressZipPostalCode;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
