package com.alpine.domain;

import com.alpine.domain.security.Authority;
import com.alpine.domain.security.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false, updatable = false)
    // Unique identifier for the user
    private Long id;
    private String username; // User's username for authentication
    private String password; // User's password for authentication
    private String firstName; // User's first name
    private String lastName; // User's last name
    @Column(name = "email", nullable = false, updatable = false)
    private String email; // User's email address
    private String phone; // User's phone number
    private boolean enabled = true; // Indicates whether the user account is enabled
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    // User's shopping cart
    private ShoppingCart shoppingCart;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    // List of user's shipping addresses
    private List<UserShipping> userShippingList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    // List of user's payment methods
    private List<UserPayment> userPaymentList;
    @OneToMany(mappedBy = "user")
    // List of user's orders
    private List<Order> orderList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    // Set of user's roles
    private Set<UserRole> userRoles = new HashSet<>();

    // Getter and setter methods for each attribute
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public Set<UserRole> getUserRoles() {
        return userRoles;
    }
    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
    public List<UserShipping> getUserShippingList() {
        return userShippingList;
    }
    public void setUserShippingList(List<UserShipping> userShippingList) {
        this.userShippingList = userShippingList;
    }
    public List<UserPayment> getUserPaymentList() {
        return userPaymentList;
    }
    public void setUserPaymentList(List<UserPayment> userPaymentList) {
        this.userPaymentList = userPaymentList;
    }
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    public List<Order> getOrderList() {
        return orderList;
    }
    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        userRoles.forEach(ur -> authorities.add(new Authority(ur.getRole().getName())));
        return authorities;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    public boolean isEnabled() {
        return enabled;
    }
}
