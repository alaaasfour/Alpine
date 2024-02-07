package com.alpine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Unique identifier for the CartItem entity
    private Long id;
    private int qty;
    // Subtotal for this cart item
    private BigDecimal subtotal;
    @OneToOne
    // The associated book entity
    private Book book;

    @OneToMany(mappedBy = "cartItem")
    @JsonIgnore
    // List of associations between this cart item and books
    private List<BookToCartItem> bookToCartItemList;

    @ManyToOne
    @JoinColumn(name="shopping_cart_id")
    // The shopping cart to which this cart item belongs
    private ShoppingCart shoppingCart;

    @ManyToOne
    @JoinColumn(name="order_id")
    // The order associated with this cart item (if any)
    private Order order;

    // Getter and setter methods for each attribute
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public List<BookToCartItem> getBookToCartItemList() {
        return bookToCartItemList;
    }
    public void setBookToCartItemList(List<BookToCartItem> bookToCartItemList) {
        this.bookToCartItemList = bookToCartItemList;
    }
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
}
