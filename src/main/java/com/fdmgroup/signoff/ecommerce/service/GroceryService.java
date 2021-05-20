package com.fdmgroup.signoff.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.signoff.ecommerce.dao.GroceryDao;
import com.fdmgroup.signoff.ecommerce.model.Grocery;

@Service
public class GroceryService {

	private static Logger LOGGER = LogManager.getLogger(GroceryService.class);

	@Autowired
	private GroceryDao groceryDao;

	public boolean createGroceryItem(Grocery grocery) {
		boolean created = false;
		if (groceryDao.findById(grocery.getGroceryId()).isEmpty()) {
			groceryDao.save(grocery);
			created = true;
		} else {
			LOGGER.info("This item already exists.");
			created = false;
		}
		return created;
	}

	public Optional<Grocery> retrieveGroceryItemById(long id) {
		if (groceryDao.findById(id).isPresent()) {
			return groceryDao.findById(id);
		} else {
			LOGGER.info("Grocery item does not exist.");
			return null;
		}
	}

	public List<Grocery> retrieveAllGroceries() {
		return groceryDao.findAll();
	}

	public Optional<Grocery> retrieveGroceryItemByName(String groceryName) {
		if (groceryDao.existsByName(groceryName)) {
			return groceryDao.findByName(groceryName);
		} else {
			LOGGER.info("Grocery item not found.");
			return null;
		}
	}

	public boolean updateGrocery(Grocery grocery) {
		boolean updated = false;
		if (groceryDao.existsById(grocery.getGroceryId())) {
			groceryDao.save(grocery);
			updated = true;
			return updated;
		}
		LOGGER.info("Grocery item does not exist.");
		return updated;
	}

	public boolean deleteGroceryItemById(long groceryItemToDeleteId) {
		boolean deleted = false;
		Optional<Grocery> groceryItemFromDb = groceryDao.findById(groceryItemToDeleteId);
		if (groceryItemFromDb.isPresent()) {
			if(groceryDao.checkGroceryIsNotInBasket(groceryItemFromDb.get().getGroceryId()).isEmpty()) {
				groceryDao.delete(groceryItemFromDb.get());
				deleted = true;				
			} else {
				LOGGER.info("This grocery is in someones basket - cannot delete.");
				deleted = false;
			}
		} else {
			LOGGER.info("Grocery item does not exist.");
			deleted = false;
		}
		return deleted;
	}

	public boolean deleteGroceryItemByName(String groceryItemToDeleteName) {
		boolean deleted = false;
		Optional<Grocery> groceryItemFromDb = groceryDao.findByName(groceryItemToDeleteName);
		if (groceryItemFromDb.isPresent()) {
			if(groceryDao.checkGroceryIsNotInBasket(groceryItemFromDb.get().getGroceryId()).isEmpty()) {
				groceryDao.delete(groceryItemFromDb.get());
				deleted = true;				
			} else {
				LOGGER.info("This grocery is in someones basket - cannot delete.");
				deleted = false;
			}
		} else {
			LOGGER.info("Grocery item does not exist.");
			deleted = false;
		}
		return deleted;
	}

}
