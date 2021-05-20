package com.fdmgroup.signoff.ecommerce.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.signoff.ecommerce.model.Grocery;

public interface GroceryDao extends JpaRepository<Grocery, Long>{

	boolean existsByName(String groceryName);

	Optional<Grocery> findByName(String groceryName);

	@Query(
			value = "SELECT g.groceryId FROM Grocery g WHERE g.groceryId = :groceryId AND EXISTS(SELECT bi.Items_GroceryId FROM Basket_Items bi WHERE bi.Items_GroceryId = :groceryId)",
			nativeQuery = true
			)
	List<Grocery> checkGroceryIsNotInBasket(@Param("groceryId") long groceryId);

}
