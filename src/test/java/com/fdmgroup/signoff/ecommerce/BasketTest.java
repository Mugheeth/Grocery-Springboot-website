package com.fdmgroup.signoff.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.signoff.ecommerce.model.Basket;
import com.fdmgroup.signoff.ecommerce.model.Grocery;
import com.fdmgroup.signoff.ecommerce.service.BasketService;
import com.fdmgroup.signoff.ecommerce.service.GroceryService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BasketTest {

	@Autowired
	private BasketService basketService;

	@Autowired
	private GroceryService groceryService;

	@Test
	void test_ThatABasketCanBeCreated_IfItDoesNotAlreadyExist() {
		Basket newBasket = new Basket();
		boolean created = basketService.createBasket(newBasket);
		assertTrue(created);
	}

	@Test
	void test_ThatABasketCannotBeCreated_IfItAlreadyExists() {
		Optional<Basket> basketFromDb = basketService.retrieveById(1);
		boolean created = basketService.createBasket(basketFromDb.get());
		assertFalse(created);
	}

	@Test
	void test_ThatABasketCanBeRetrievedById_IfItExists() {
		Optional<Basket> basketFromDb = basketService.retrieveById(1);
		assertEquals(1, basketFromDb.get().getBasketId());
	}

	@Test
	void test_ThatABasketCannotBeRetrievedById_IfItDoesNotExist() {
		Optional<Basket> basketFromDb = basketService.retrieveById(1000);
		assertEquals(null, basketFromDb);
	}

	@Test
	void test_ThatAllBasketsCanBeRetrieved() {
		int numberOfBasketsBefore = basketService.retrieveAllBaskets().size();
		Basket dummyBasket = new Basket();
		basketService.createBasket(dummyBasket);
		int numberOfBasketsAfter = basketService.retrieveAllBaskets().size();
		assertNotEquals(numberOfBasketsBefore, numberOfBasketsAfter);
	}

	@Test
	void test_ThatAllGroceryItemsCanBeRetireveFromABasket_IfItExists() {
		Optional<Basket> basketFromBd = basketService.retrieveById(1);
		List<Grocery> itemsInBasket = basketFromBd.get().getItems();
		assertTrue(itemsInBasket.size() >= 0);
	}

	@Test
	void test_ThatAGroceryItemCanBeAddedToABasketById_IfItExists() {
		long basketToAddToId = 1;
		String groceryName = "Oranges";
		Grocery groceryToAdd = groceryService.retrieveGroceryItemByName(groceryName).get();
		Optional<Basket> basketToAddTo = basketService.retrieveById(basketToAddToId);
		boolean groceryAdded = basketService.addGrocery(basketToAddTo, groceryToAdd);
		assertTrue(groceryAdded);
	}

	@Test
	void test_ThatAGroceryItemCanBeRemovedFromTheBasket_IfItExists() {
		long basketToRemoveFromId = 3;
		String groceryNameToRemove = "Oranges";
		Grocery groceryToRemove = groceryService.retrieveGroceryItemByName(groceryNameToRemove).get();
		Optional<Basket> basketToRemoveFrom = basketService.retrieveById(basketToRemoveFromId);
		boolean groceryRemoved = basketService.removeGrocery(basketToRemoveFrom, groceryToRemove);
		assertTrue(groceryRemoved);
	}

}
