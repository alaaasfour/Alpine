/**
 * Controller class responsible for handling checkout related operations in the Alpine Book Store application.
 * This controller manages the checkout process, including setting up shipping and billing addresses, processing payments, and placing orders.
 */
package com.alpine.controller;

import com.alpine.domain.*;
import com.alpine.service.*;
import com.alpine.utility.MailConstructor;
import com.alpine.utility.StatesProvincesConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Controller
public class CheckoutController {
    private ShippingAddress shippingAddress = new ShippingAddress();
    private BillingAddress billingAddress = new BillingAddress();
    private Payment payment = new Payment();
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MailConstructor mailConstructor;
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private ShippingAddressService shippingAddressService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private BillingAddressService billingAddressService;
    @Autowired
    private UserShippingService userShippingService;
    @Autowired
    private UserPaymentService userPaymentService;
    @Autowired
    private OrderService orderService;
    /**
     * Handles the GET request for the checkout page.
     * @param cartId The ID of the shopping cart.
     * @param missingRequiredField Flag indicating if there are missing required fields.
     * @param model The Model object to add attributes to.
     * @param principal The Principal object representing the currently authenticated user.
     * @return The view name for the checkout page.
     */
    @RequestMapping("/checkout")
    public String checkout(@RequestParam("id") Long cartId,
                           @RequestParam(value = "missingRequiredField", required = false)
                           boolean missingRequiredField, Model model, Principal principal
    ) {
        // Retrieving the current user
        User user = userService.findByUsername(principal.getName());
        // Validating the cart ownership
        if (cartId != user.getShoppingCart().getId()) {
            return "badRequestPage";
        }
        // Retrieving cart items
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
        if (cartItemList.size() == 0){
            model.addAttribute("emptyCart", true);
            return "forward:/shoppingCart/cart";
        }
        // Checking stock availability for items in the cart
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getBook().getInStockNumber() < cartItem.getQty()) {
                model.addAttribute("notEnoughStock", true);
                return "forward:/shoppingCart/cart";
            }
        }
        // Retrieving user's shipping and payment information
        List<UserShipping> userShippingList = user.getUserShippingList();
        List<UserPayment> userPaymentList = user.getUserPaymentList();

        model.addAttribute("userShippingList", userShippingList);
        model.addAttribute("userPaymentList", userPaymentList);

        if (userPaymentList.size() == 0) {
            model.addAttribute("emptyPaymentList", true);
        } else {
            model.addAttribute("emptyPaymentList", false);
        }
        if (userShippingList.size() == 0) {
            model.addAttribute("emptyShippingList", true);
        } else {
            model.addAttribute("emptyShippingList", false);
        }

        // Setting up default shipping and billing addresses
        ShoppingCart shoppingCart = user.getShoppingCart();
        for (UserShipping userShipping : userShippingList) {
            if (userShipping.isUserShippingDefault()) {
                shippingAddressService.setByUserShipping(userShipping, shippingAddress);
            }
        }
        for (UserPayment userPayment : userPaymentList) {
            if (userPayment.isDefaultPayment()) {
                paymentService.setByUserPayment(userPayment, payment);
                billingAddressService.setByUserBilling(userPayment.getUserBilling(), billingAddress);
            }
        }

        // Adding attributes to the model
        model.addAttribute("shippingAddress", shippingAddress);
        model.addAttribute("payment", payment);
        model.addAttribute("billingAddress", billingAddress);
        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingCart", user.getShoppingCart());

        // Adding state, province, and country lists for selection
        List<String> stateList = StatesProvincesConstants.listOfUSStatesCode;
        List<String> provinceList = StatesProvincesConstants.listOfCanadianProvincesCode;
        List<String> countryList = new ArrayList<>();
        countryList.add(StatesProvincesConstants.US);
        countryList.add(StatesProvincesConstants.CA);
        Collections.sort(stateList);
        Collections.sort(provinceList);
        model.addAttribute("stateList", stateList);
        model.addAttribute("provinceList", provinceList);
        model.addAttribute("countryList", countryList);
        model.addAttribute("classActiveShipping", true);
        if (missingRequiredField) {
            model.addAttribute("missingRequiredField", true);
        }
        return "checkout";
    }

    /**
     * Handles the POST request for the checkout page.
     * @param shippingAddress The shipping address submitted by the user.
     * @param billingAddress The billing address submitted by the user.
     * @param payment The payment information submitted by the user.
     * @param billingSameAsShipping Flag indicating if billing address is same as shipping address.
     * @param shippingMethod The selected shipping method.
     * @param principal The Principal object representing the currently authenticated user.
     * @param model The Model object to add attributes to.
     * @return The view name for the order confirmation page.
     */
    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public String checkoutPost(
            @ModelAttribute("shippingAddress") ShippingAddress shippingAddress,
            @ModelAttribute("billingAddress") BillingAddress billingAddress,
            @ModelAttribute("payment") Payment payment,
            @ModelAttribute("billingSameAsShipping") String billingSameAsShipping,
            @ModelAttribute("shippingMethod") String shippingMethod,
            Principal principal, Model model
    ) {
        // Retrieving the shopping cart of the current user
        ShoppingCart shoppingCart = userService.findByUsername(principal.getName()).getShoppingCart();

        // Retrieving cart items
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
        model.addAttribute("cartItemList", cartItemList);

        // Setting billing address same as shipping address if requested
        if (billingSameAsShipping.equals("true")) {
            billingAddress.setBillingAddressName(shippingAddress.getShippingAddressName());
            billingAddress.setBillingAddressStreet1(shippingAddress.getShippingAddressStreet1());
            billingAddress.setBillingAddressStreet2(shippingAddress.getShippingAddressStreet2());
            billingAddress.setBillingAddressCity(shippingAddress.getShippingAddressCity());
            billingAddress.setBillingAddressStateProvince(shippingAddress.getShippingAddressStateProvince());
            billingAddress.setBillingAddressZipPostalCode(shippingAddress.getShippingAddressZipPostalCode());
            billingAddress.setBillingAddressCountry(shippingAddress.getShippingAddressCountry());
        }

        // Checking for missing required fields
        if (shippingAddress.getShippingAddressName().isEmpty() ||
                shippingAddress.getShippingAddressStreet1().isEmpty() ||
                shippingAddress.getShippingAddressCity().isEmpty() ||
                shippingAddress.getShippingAddressStateProvince().isEmpty() ||
                shippingAddress.getShippingAddressZipPostalCode().isEmpty() ||
                shippingAddress.getShippingAddressCountry().isEmpty() ||
                payment.getCardNumber().isEmpty() ||
                payment.getCvc() == 0 ||
                billingAddress.getBillingAddressName().isEmpty() ||
                billingAddress.getBillingAddressStreet1().isEmpty() ||
                billingAddress.getBillingAddressCity().isEmpty() ||
                billingAddress.getBillingAddressStateProvince().isEmpty() ||
                billingAddress.getBillingAddressZipPostalCode().isEmpty() ||
                billingAddress.getBillingAddressCountry().isEmpty()
        )
            return "redirect:/checkout?id="+shoppingCart.getId()+"&missingRequiredField=true";

        // Creating order and processing payment
        User user = userService.findByUsername(principal.getName());
        Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, shippingMethod, user);

        // Sending order confirmation email
        mailSender.send(mailConstructor.constructOrderConfirmationEmail(user, order, Locale.ENGLISH));
        // Clearing the shopping cart
        shoppingCartService.clearShoppingCart(shoppingCart);

        // Calculating estimated delivery date
        LocalDate today = LocalDate.now();
        LocalDate estimatedDeliveryDate;
        if (shippingMethod.equals("groundShipping")) {
            estimatedDeliveryDate = today.plusDays(5);
        } else {
            estimatedDeliveryDate = today.plusDays(3);
        }
        model.addAttribute("estimatedDeliveryDate", estimatedDeliveryDate);

        return "orderSubmittedPage";
    }

    /**
     * Handles setting the shipping address during checkout.
     * @param userShippingId The ID of the user shipping address.
     * @param principal The Principal object representing the currently authenticated user.
     * @param model The Model object to add attributes to.
     * @return The view name for the checkout page.
     */
    @RequestMapping("/setShippingAddress")
    public String setShippingAddress(@RequestParam("userShippingId") Long userShippingId, Principal principal, Model model){
        // Retrieving the current user
        User user = userService.findByUsername(principal.getName());
        UserShipping userShipping = userShippingService.findById(userShippingId);

        // Validating ownership of user shipping address
        if (userShipping.getUser().getId() != user.getId()) {
            return "badRequestPage";
        } else {
            // Setting shipping address
            shippingAddressService.setByUserShipping(userShipping, shippingAddress);
            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());

            // Adding attributes to the model
            model.addAttribute("shippingAddress", shippingAddress);
            model.addAttribute("payment", payment);
            model.addAttribute("billingAddress", billingAddress);
            model.addAttribute("cartItemList", cartItemList);
            model.addAttribute("shoppingCart", user.getShoppingCart());

            // Adding state, province, and country lists for selection
            List<String> stateList = StatesProvincesConstants.listOfUSStatesCode;
            List<String> provinceList = StatesProvincesConstants.listOfCanadianProvincesCode;
            List<String> countryList = new ArrayList<>();
            countryList.add(StatesProvincesConstants.US);
            countryList.add(StatesProvincesConstants.CA);
            Collections.sort(stateList);
            Collections.sort(provinceList);
            model.addAttribute("stateList", stateList);
            model.addAttribute("provinceList", provinceList);
            model.addAttribute("countryList", countryList);

            List<UserShipping> userShippingList = user.getUserShippingList();
            List<UserPayment> userPaymentList = user.getUserPaymentList();
            model.addAttribute("userShippingList", userShippingList);
            model.addAttribute("userPaymentList", userPaymentList);
            model.addAttribute("shippingAddress", shippingAddress);
            model.addAttribute("classActiveShipping", true);

            if (userPaymentList.size() == 0) {
                model.addAttribute("emptyPaymentList", true);
            } else {
                model.addAttribute("emptyPaymentList", false);
            }
            model.addAttribute("emptyShippingList", false);
            return "checkout";
        }
    }

    /**
     * Handles setting the payment method during checkout.
     * @param userPaymentId The ID of the user payment method.
     * @param principal The Principal object representing the currently authenticated user.
     * @param model The Model object to add attributes to.
     * @return The view name for the checkout page.
     */
    @RequestMapping("/setPaymentMethod")
    public String setPaymentMethod(@RequestParam("userPaymentId") Long userPaymentId, Principal principal, Model model) {
        // Retrieving the current user
        User user = userService.findByUsername(principal.getName());
        UserPayment userPayment = userPaymentService.findById(userPaymentId);
        UserBilling userBilling = userPayment.getUserBilling();

        // Validating ownership of user payment method
        if (userPayment.getUser().getId() != user.getId()) {
            return "badRequestPage";
        } else {
            // Setting payment method
            paymentService.setByUserPayment(userPayment, payment);

            List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
            billingAddressService.setByUserBilling(userBilling, billingAddress);
            model.addAttribute("shippingAddress", shippingAddress);
            model.addAttribute("payment", payment);
            model.addAttribute("billingAddress", billingAddress);
            model.addAttribute("cartItemList", cartItemList);
            model.addAttribute("shoppingCart", user.getShoppingCart());

            List<String> stateList = StatesProvincesConstants.listOfUSStatesCode;
            List<String> provinceList = StatesProvincesConstants.listOfCanadianProvincesCode;
            List<String> countryList = new ArrayList<>();
            countryList.add(StatesProvincesConstants.US);
            countryList.add(StatesProvincesConstants.CA);
            Collections.sort(stateList);
            Collections.sort(provinceList);
            model.addAttribute("stateList", stateList);
            model.addAttribute("provinceList", provinceList);
            model.addAttribute("countryList", countryList);

            List<UserShipping> userShippingList = user.getUserShippingList();
            List<UserPayment> userPaymentList = user.getUserPaymentList();
            model.addAttribute("userShippingList", userShippingList);
            model.addAttribute("userPaymentList", userPaymentList);
            model.addAttribute("shippingAddress", shippingAddress);
            model.addAttribute("classActivePayment", true);
            model.addAttribute("emptyPaymentList", false);
            if (userShippingList.size() == 0) {
                model.addAttribute("emptyShippingList", true);
            } else {
                model.addAttribute("emptyShippingList", false);
            }
            return "checkout";
        }
    }
}