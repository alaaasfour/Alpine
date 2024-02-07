package com.alpine.controller;

import com.alpine.domain.Book;
import com.alpine.domain.CartItem;
import com.alpine.domain.ShoppingCart;
import com.alpine.domain.User;
import com.alpine.service.BookService;
import com.alpine.service.CartItemService;
import com.alpine.service.ShoppingCartService;
import com.alpine.service.UserService;
import com.alpine.utility.StatesProvincesConstants;
import com.alpine.utility.TaxRateConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private BookService bookService;

    // Method to display the shopping cart
    @RequestMapping("/cart")
    public String shoppingCart(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();

        // Retrieve cart items associated with the shopping cart
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
        // Update the shopping cart total
        shoppingCartService.updateShoppingCart(shoppingCart);
        // Add cart items and shopping cart information to the model
        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingCart", shoppingCart);
        // Add tax rates to the model
        List<String> taxList = TaxRateConstants.listOfTaxRates;
        model.addAttribute("taxList", taxList);

        return "shoppingCart";
    }

    // Method to add an item to the shopping cart
    @RequestMapping("/addItem")
    public String addItem(
            @ModelAttribute("book") Book book,
            @ModelAttribute("qty") String qty,
            Model model, Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());
        book = bookService.findOne(book.getId());

        // Check if the requested quantity exceeds available stock
        if (Integer.parseInt(qty) > book.getInStockNumber()) {
            model.addAttribute("notEnoughStock", true);
            return "forward:/bookDetails?id="+book.getId();
        }

        // Add the book to the cart item
        CartItem cartItem = cartItemService.addBookToCartItem(book, user, Integer.parseInt(qty));
        model.addAttribute("addBookSuccess", true);

        // Update the stock count of the book
        book.setInStockNumber(book.getInStockNumber() - Integer.parseInt(qty));
        bookService.save(book);

        return "forward:/bookDetails?id="+book.getId();
    }

    // Method to update the quantity of a cart item
    @RequestMapping("/updateCartItem")
    public String updateShoppingCart(
            @ModelAttribute("id") Long cartItemId,
            @ModelAttribute("qty") int newQty,
            Model model
    ) {
        CartItem cartItem = cartItemService.findById(cartItemId);
        int oldQty = cartItem.getQty();
        Book book = cartItem.getBook();

        // Check if the new quantity is greater than the available stock
        if (newQty > book.getInStockNumber()) {
            model.addAttribute("notEnoughStock", true);
        } else {
            // Update the cart item quantity
            cartItem.setQty(newQty);
            cartItemService.updateCartItem(cartItem);

            // Adjust the stock based on the changes in quantity
            int qtyDifference = newQty - oldQty;
            book.setInStockNumber(book.getInStockNumber() - qtyDifference);
            bookService.save(book);
        }

        return "forward:/shoppingCart/cart";
    }

    // Method to remove an item from the shopping cart
    @RequestMapping("/removeItem")
    public String removeItem(@RequestParam("id") Long id) {
        CartItem cartItem = cartItemService.findById(id);

        Book book = cartItem.getBook();
        int removedQty = cartItem.getQty();
        // Restore the removed quantity to the book's stock count
        book.setInStockNumber(book.getInStockNumber() + removedQty);
        bookService.save(book);

        // Remove the cart item from the database
        cartItemService.removeCartItem(cartItemService.findById(id));

        // Redirect to the shopping cart page
        return "forward:/shoppingCart/cart";
    }
}
