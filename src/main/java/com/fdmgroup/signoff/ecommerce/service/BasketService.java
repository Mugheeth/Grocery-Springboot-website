package com.fdmgroup.signoff.ecommerce.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.signoff.ecommerce.dao.BasketDao;
import com.fdmgroup.signoff.ecommerce.model.Basket;
import com.fdmgroup.signoff.ecommerce.model.Grocery;

@Service
public class BasketService {

	private static Logger LOGGER = LogManager.getLogger(BasketService.class);
	List<Grocery> itemsInBasket = new ArrayList<>();

	@Autowired
	private BasketDao basketDao;

	public boolean createBasket(Basket newBasket) {
		boolean created = false;
		if (basketDao.findById(newBasket.getBasketId()).isEmpty()) {
			basketDao.save(newBasket);
			created = true;
		} else {
			LOGGER.info("This basket already exists");
			created = false;
		}
		return created;
	}

	public Optional<Basket> retrieveById(long id) {
		if (basketDao.findById(id).isPresent()) {
			return basketDao.findById(id);
		} else {
			LOGGER.info("Basket not found");
			return null;
		}
	}

	public List<Basket> retrieveAllBaskets() {
		return basketDao.findAll();
	}

	private boolean update(Basket basket) {
		boolean updated = false;
		if (basketDao.findById(basket.getBasketId()).isPresent()) {
			basketDao.save(basket);
			updated = true;
		} else {
			LOGGER.info("Basket not found could not add game to basket");
			updated = false;
		}
		return updated;
	}

	public boolean addGrocery(Optional<Basket> basketToAddTo, Grocery groceryToAdd) {
		boolean added = false;
		if (basketToAddTo.isPresent()) {
			basketToAddTo.get().getItems().add(groceryToAdd);
			this.update(basketToAddTo.get());
			return added = true;
		}
		LOGGER.info("This basket does not exist.");
		return added;
	}

	public boolean removeGrocery(Optional<Basket> basketToRemoveFrom, Grocery groceryToRemove) {
		boolean groceryRemoved = false;
		if (basketToRemoveFrom.isPresent()) {
			if (basketToRemoveFrom.get().getItems().contains(groceryToRemove)) {
				itemsInBasket = basketToRemoveFrom.get().getItems();
				for (Grocery grocery : itemsInBasket) {
					if (grocery.getName().equals(groceryToRemove.getName())) {
						itemsInBasket.remove(grocery);
						break;
					}
				}
				basketToRemoveFrom.get().setItems(itemsInBasket);
				this.update(basketToRemoveFrom.get());
				groceryRemoved = true;
			} else {
				LOGGER.info("This grocery is not in the basket - cannot remove.");
				groceryRemoved = false;
			}
		} else {
			LOGGER.info("This basket does not exist.");
			groceryRemoved = false;
		}
		return groceryRemoved;
	}

}
