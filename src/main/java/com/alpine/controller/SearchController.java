package com.alpine.controller;

import com.alpine.domain.Book;
import com.alpine.domain.User;
import com.alpine.service.BookService;
import com.alpine.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private UserService userService;
    @Autowired
    private BookService bookService;

    // Method for searching books by category
    @RequestMapping("/searchByCategory")
    public String searchByCategory(@RequestParam("category") String category, Model model, Principal principal) {
        // Add user information to the model if user is logged in
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        // Adjust category name for class attribute in HTML
        String classActiveCategory = "active"+category;
        classActiveCategory = classActiveCategory.replaceAll("\\s+", "");
        classActiveCategory = classActiveCategory.replaceAll("&", "");
        model.addAttribute(classActiveCategory, true);

        // Retrieve list of books by category
        List<Book> bookList = bookService.findByCategory(category);

        // Check if book list is empty
        if (bookList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "bookshelf";
        }

        // Add book list to the model and return bookshelf view
        model.addAttribute("bookList", bookList);
        return "bookshelf";
    }

    // Method for searching books by keyword
    @RequestMapping("/searchBook")
    public String searchBook(@ModelAttribute("keyword") String keyword, Principal principal, Model model) {
        // Add user information to the model if user is logged in
        if (principal != null) {
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }

        // Retrieve list of books by keyword
        List<Book> bookList = bookService.blurrySearch(keyword);
        // Check if book list is empty
        if (bookList.isEmpty()) {
            model.addAttribute("emptyList", true);
            return "bookshelf";
        }
        // Add book list to the model and return bookshelf view
        model.addAttribute("bookList", bookList);
        return "bookshelf";
    }
}
