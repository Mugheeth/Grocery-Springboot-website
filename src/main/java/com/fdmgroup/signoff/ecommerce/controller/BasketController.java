package com.fdmgroup.signoff.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.signoff.ecommerce.model.Basket;
import com.fdmgroup.signoff.ecommerce.model.Customer;
import com.fdmgroup.signoff.ecommerce.model.Grocery;
import com.fdmgroup.signoff.ecommerce.service.BasketService;
import com.fdmgroup.signoff.ecommerce.service.CustomerService;
import com.fdmgroup.signoff.ecommerce.service.GroceryService;

@Controller
public class BasketController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private GroceryService groceryService;

	@Autowired
	private BasketService basketService;

	@PostMapping("/addToBasket/{customerId}/{groceryId}")
	public ModelAndView addGroceryToBasket(@PathVariable("groceryId") Long groceryId,
			@PathVariable("customerId") Long customerId, @RequestParam("numberToAdd") int numberToAdd, Model model) {
		Customer customer = customerService.retrieveById(customerId).get();
		Grocery grocery = groceryService.retrieveGroceryItemById(groceryId).get();
		Optional<Basket> basket = basketService.retrieveById(customer.getBasket().getBasketId());

		boolean added = false;
		if (numberToAdd <= grocery.getQuantity()) {
			for (int i = 0; i < numberToAdd; i++) {
				added = basketService.addGrocery(basket, grocery);
			}
		}

		if (added) {
			long quantity = grocery.getQuantity() - numberToAdd;
			grocery.setQuantity(quantity);
			groceryService.updateGrocery(grocery);

			model.addAttribute("message", "Grocery has been added to your basket successfully.");
		} else {
			model.addAttribute("message", "Grocery of that quantity could not be added to your basket.");
		}

		model.addAttribute("customerId", customer.getCustomerId());
		model.addAttribute("allGroceries", groceryService.retrieveAllGroceries());
		model.addAttribute("numberOfItems", customer.getBasket().getItems().size());

		return new ModelAndView("forward:/WEB-INF/main.jsp");
	}

	@GetMapping("viewBasket/{customerId}")
	public ModelAndView viewBasket(@PathVariable("customerId") Long customerId, Model model) {
		Customer customer = customerService.retrieveById(customerId).get();
		long totalCost = 0;

		for (Grocery g : customer.getBasket().getItems()) {
			totalCost += g.getPrice();
		}

		model.addAttribute("totalCost", totalCost);
		return new ModelAndView("forward:/WEB-INF/viewBasket.jsp", "allGroceries", customer.getBasket().getItems());
	}

	@GetMapping("viewBasket/removeFromBasket/{customerId}/{groceryId}")
	public ModelAndView removeGrocery(@PathVariable("customerId") Long customerId,
			@PathVariable("groceryId") Long groceryId, Model model) {
		Customer customer = customerService.retrieveById(customerId).get();
		Grocery grocery = groceryService.retrieveGroceryItemById(groceryId).get();
		Optional<Basket> basket = basketService.retrieveById(customer.getBasket().getBasketId());

		boolean removed = basketService.removeGrocery(basket, grocery);

		if (removed) {
			model.addAttribute("message", "Grocery has been removed successfully.");
		} else {
			model.addAttribute("message", "Grocery could not be removed.");
		}

		long totalCost = 0;

		for (Grocery g : customer.getBasket().getItems()) {
			totalCost += g.getPrice();
		}

		model.addAttribute("totalCost", totalCost);
		return new ModelAndView("forward:/WEB-INF/viewBasket.jsp", "allGroceries", customer.getBasket().getItems());
	}

}
