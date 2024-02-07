# Alpine Bookstore 📚

## Overview 🚀
Alpine Bookstore is an e-commerce web application designed to sell books online. This Application has 2 portals:
- **User Portal:** It provides users with features to browse books, add them to the shopping cart, make payments, and manage their orders and account details.
- **Admin Portal:** It allows the admin to manage the available products and users.

## Features ✨
- **User Authentication 🔐**: Users can sign up, log in, and log out securely.
- **Browse Books 🔍**: Users can view a list of available books and filter them by category.
- **Shopping Cart 🛍️**: Users can add books to their shopping cart, view their cart, and update or remove items.
- **Checkout 💳**: Users can proceed to checkout, enter shipping and billing information, and place orders.
- **Order Management 📒**: Users can view their order history, order details, and track the status of their orders.
- **Account Management 💼**: Users can manage their profile, shipping addresses, and payment methods.

## Installation ⬇️
1. Clone the repository: `git clone https://github.com/your_username/alpine.git`
2. Navigate to the project directory: `cd alpine-bookstore`
3. Install dependencies: `npm install`
4. Configure database settings in `application.properties` file.
5. Build and run the application: `mvn spring-boot:run`
6. Access the application in your web browser at `http://localhost:8080`
7. Please make sure you have installed the following technologies on your machine.

## Prerequisites/Technologies Used ⚙️🔧
- Java 17
- Spring Boot 2.7.0
- Thymeleaf
- Spring Security
- Spring Data JPA (with Hibernate)
- MySQL Connector
- Jakarta Persistence API
- Maven

## Usage 📕📗📘
1. Register for an account or log in if you already have one.
2. Browse books by category and add them to your shopping cart.
3. Proceed to checkout, enter shipping and billing information, and place your order.
4. View your order history and manage your account details.

## Screenshots 🖼️
### 1. Admin Portal:
![admin-portal.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2Fadmin-portal.png)

#### a. Adding New Books
Admins are able to add books to the database through the admin portal, this feature allows the admins to assign the book details such as, Book Title, Author, Category, Price, Quantity in Stock, Book Description and Book Cover Image.
![adding-new-book.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2Fadding-new-book.png)

#### b. View the Book List
The admins can see the list of the books and do 2 main operations on them such as deleting the book or editing it.
![book-list.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2Fbook-list.png)

### 2. User Portal:
![user-portal1.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2Fuser-portal1.png)
![user-portal2.png](src%2Fmain%2Fresources%2Fstatic%2Fimages%2Fuser-portal2.png)



