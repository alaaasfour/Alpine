package com.alpine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Unique identifier for the shopping cart
    private Long id;
    private BigDecimal GrandTotal;
    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    // List of items in the shopping cart
    private List<CartItem> cartItemList;

    @OneToOne(cascade = CascadeType.ALL)
    // User associated with the shopping cart
    private User user;

    // Getter and setter methods for each attribute
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BigDecimal getGrandTotal() {
        return GrandTotal;
    }
    public void setGrandTotal(BigDecimal grandTotal) {
        GrandTotal = grandTotal;
    }
    public List<CartItem> getCartItemList() {
        return cartItemList;
    }
    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
