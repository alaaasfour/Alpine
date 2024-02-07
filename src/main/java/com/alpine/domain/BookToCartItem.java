package com.alpine.domain;

import javax.persistence.*;

@Entity
public class BookToCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // Unique identifier for the BookToCartItem entity
    private Long id;
    @ManyToOne
    @JoinColumn(name = "book_id")
    // The associated book entity
    private Book book;
    @ManyToOne
    @JoinColumn(name = "cart_item_id")
    // The associated cart item entity
    private CartItem cartItem;

    // Getter and setter methods for each attribute
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
    public CartItem getCartItem() {
        return cartItem;
    }
    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }
}
