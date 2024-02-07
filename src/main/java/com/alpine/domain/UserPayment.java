package com.alpine.domain;

import javax.persistence.*;

@Entity
public class UserPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Unique identifier for the user payment
    private Long id;
    private String type;
    private String cardName;
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;
    private int cvc;
    private String holderName;
    private boolean defaultPayment; // Flag indicating if this is the default payment method

    @ManyToOne
    @JoinColumn(name = "user_id")
    // User associated with this payment method
    private User user;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userPayment")
    // Billing address associated with this payment method
    private UserBilling userBilling;

    // Getter and setter methods for each attribute
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getCardName() {
        return cardName;
    }
    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
    public String getCardNumber() {
        return cardNumber;
    }
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
    public int getExpiryMonth() {
        return expiryMonth;
    }
    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }
    public int getExpiryYear() {
        return expiryYear;
    }
    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }
    public int getCvc() {
        return cvc;
    }
    public void setCvc(int cvc) {
        this.cvc = cvc;
    }
    public String getHolderName() {
        return holderName;
    }
    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }
    public boolean isDefaultPayment() {
        return defaultPayment;
    }
    public void setDefaultPayment(boolean defaultPayment) {
        this.defaultPayment = defaultPayment;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public UserBilling getUserBilling() {
        return userBilling;
    }
    public void setUserBilling(UserBilling userBilling) {
        this.userBilling = userBilling;
    }
}
