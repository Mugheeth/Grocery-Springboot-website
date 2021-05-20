package com.fdmgroup.signoff.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.signoff.ecommerce.model.Grocery;
import com.fdmgroup.signoff.ecommerce.service.GroceryService;
import com.fdmgroup.signoff.ecommerce.service.ProducerService;

@Controller
public class MainController {
	
	@Autowired
	private ProducerService producerService;
	
	@Autowired
	private GroceryService groceryService;
	
	@GetMapping("/main")
	public ModelAndView main() {
		return new ModelAndView("WEB-INF/main.jsp");
	}
	
	@GetMapping("/addGrocery")
	public ModelAndView addGrocery() {
		ModelAndView model = new ModelAndView("WEB-INF/addGrocery.jsp");
		model.addObject("grocery", new Grocery());
		model.addObject("allProducers", producerService.retrieveAllProducers());
		return model;
	}
	
	@PostMapping("/addGrocerySubmit")
	public ModelAndView addGrocerySubmit(Grocery grocery, ModelMap model) {
		boolean created = groceryService.createGroceryItem(grocery);
		if(created) {
			model.addAttribute("message", "The grocery " + grocery.getName() + " has been added to the store.");
		} else {
			model.addAttribute("message", "The grocery " + grocery.getName() + " could not be added to the store.");
		}
		return new ModelAndView("WEB-INF/main.jsp", "allGroceries", groceryService.retrieveAllGroceries());
	}
	

}
