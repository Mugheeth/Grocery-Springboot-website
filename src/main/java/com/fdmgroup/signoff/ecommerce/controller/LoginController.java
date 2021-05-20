package com.fdmgroup.signoff.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.signoff.ecommerce.model.Customer;
import com.fdmgroup.signoff.ecommerce.service.CustomerService;
import com.fdmgroup.signoff.ecommerce.service.GroceryService;

@Controller
public class LoginController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private GroceryService groceryService;	
	
	@GetMapping("/login")
	public String login() {
		return "login.jsp";
	}
	
	@PostMapping("/loginSubmit")
	public ModelAndView loginSubmit(@ModelAttribute("customer") Customer customer, ModelAndView modelAndView) {
		Optional<Customer> customerFromDb = customerService.retrieveByEmailAndPassword(customer.getEmail(), customer.getPassword());
		if (customerFromDb == null) {
			modelAndView.addObject("message", "Incorrect details - try again");
			modelAndView.setViewName("login.jsp");
			return modelAndView;
		} else {
			modelAndView.setViewName("WEB-INF/main.jsp");
			modelAndView.addObject("customerId", customerFromDb.get().getCustomerId());
			modelAndView.addObject("allGroceries", groceryService.retrieveAllGroceries());
			modelAndView.addObject("numberOfItems", customerFromDb.get().getBasket().getItems().size());
			return modelAndView;
			
		}
		
	}

}
