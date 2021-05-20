package com.fdmgroup.signoff.ecommerce.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.signoff.ecommerce.model.Producer;

public interface ProducerDao extends JpaRepository<Producer, Long> {

	boolean existsByName(String name);

	@Query(
			value = "select p.producerId, p.name, p.address, p.telephone from Producer p where p.producerId = :producerId and exists(select g.producerId from Grocery g where g.producerId = p.producerId)",
			nativeQuery = true
			)
	List<Producer> checkIfProducerHasGroceryItems(@Param("producerId") long producerId);

	Optional<Producer> findByName(String producerToDeleteName);

}
