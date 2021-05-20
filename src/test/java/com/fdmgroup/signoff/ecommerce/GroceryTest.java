package com.fdmgroup.signoff.ecommerce;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.signoff.ecommerce.model.Grocery;
import com.fdmgroup.signoff.ecommerce.model.Producer;
import com.fdmgroup.signoff.ecommerce.service.GroceryService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class GroceryTest {

	@Autowired
	private GroceryService groceryService;

	@Test
	void test_ThatAGroceryItemCanBeCreated_IfItDoesNotAlreadyExist() {
		Producer dummyProducer = new Producer("Dummy producer", "Dummy address", "1234567890");
		Grocery grocery = new Grocery("New Grocery Item", "This is a new dummy grocery.", dummyProducer, 10, 20);
		boolean created = groceryService.createGroceryItem(grocery);
		assertTrue(created);
	}

	@Test
	void test_ThatAGroceryItemCannotBeCreated_IfItAlreadyExists() {
		Optional<Grocery> grocery = groceryService.retrieveGroceryItemById(1);
		boolean created = groceryService.createGroceryItem(grocery.get());
		assertFalse(created);
	}

	@Test
	void test_ThatAGroceryItemCanBeRetrievedById_IfItAlreadyExists() {
		long groceryId = 1;
		Optional<Grocery> groceryFromDb = groceryService.retrieveGroceryItemById(groceryId);
		assertEquals(groceryId, groceryFromDb.get().getGroceryId());
	}

	@Test
	void test_ThatAGroceryItemCannotBeRetrievedById_IfItDoesNotExist() {
		long groceryId = 100;
		Optional<Grocery> groceryFromDb = groceryService.retrieveGroceryItemById(groceryId);
		assertEquals(null, groceryFromDb);
	}

	@Test
	void test_ThatAGroceryItemCanBeRetrievedByName_IfItAlreadyExists() {
		String groceryName = "Apples";
		Optional<Grocery> groceryFromDb = groceryService.retrieveGroceryItemByName(groceryName);
		assertEquals(groceryName, groceryFromDb.get().getName());
	}

	@Test
	void test_ThatAGroceryItemCannotBeRetrievedByName_IfItDoesNotAlreadyExist() {
		String groceryName = "Item Does not exist";
		Optional<Grocery> groceryFromDb = groceryService.retrieveGroceryItemByName(groceryName);
		assertEquals(null, groceryFromDb);
	}

	@Test
	void test_ThatAllGroceryItemCanBeRetrieved() {
		int numberOfGroceriesBefore = groceryService.retrieveAllGroceries().size();
		Producer dummyProducer = new Producer("Dummy producer", "Dummy address", "1234567890");
		Grocery grocery = new Grocery("New Grocery Item", "This is a new dummy grocery.", dummyProducer, 10, 20);
		groceryService.createGroceryItem(grocery);
		int numberOfGroceriesAfter = groceryService.retrieveAllGroceries().size();
		assertNotEquals(numberOfGroceriesBefore, numberOfGroceriesAfter);
	}

	@Test
	void test_ThatAGroceryItemCanBeUpdated_IfItAlreadyExists() {
		long groceryItemToUpdateId = 1;
		Optional<Grocery> grocerToUpdate = groceryService.retrieveGroceryItemById(groceryItemToUpdateId);
		grocerToUpdate.get().setName("Baby Apples");
		boolean updated = groceryService.updateGrocery(grocerToUpdate.get());
		assertTrue(updated);
	}
	
	@Test
	void test_ThatAGroceryItemCanBeDeletedById_IfItAlreadyExists_AndNotInSomeonesBasket() {
		long groceryItemToDeleteId = 5;
		boolean deleted = groceryService.deleteGroceryItemById(groceryItemToDeleteId);
		assertTrue(deleted);
	}
	
	@Test
	void test_ThatAGroceryItemCannotBeDeletedById_IfItDoesNotExist() {
		long groceryItemToDeleteId = 100;
		boolean deleted = groceryService.deleteGroceryItemById(groceryItemToDeleteId);
		assertFalse(deleted);
	}
	
	@Test
	void test_ThatAGroceryItemCanBeDeletedByName_IfItAlreadyExists_AndIsNotInSomonesBasket() {
		String groceryItemToDeleteName = "Banana";
		boolean deleted = groceryService.deleteGroceryItemByName(groceryItemToDeleteName);
		assertTrue(deleted);
	}
	
	@Test
	void test_ThatAGroceryItemCannotBeDeletedByName_IfItDoesNotExist() {
		String groceryItemToDeleteName = "Does not exist";
		boolean deleted = groceryService.deleteGroceryItemByName(groceryItemToDeleteName);
		assertFalse(deleted);
	}

}