package com.fdmgroup.signoff.ecommerce;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.signoff.ecommerce.model.Producer;
import com.fdmgroup.signoff.ecommerce.service.ProducerService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class ProducerTest {

	@Autowired
	private ProducerService producerService;

	@Test
	void test_ThatAProducerCanBeCreated_IfItDoesNotAlreadyExist() {
		Producer producer = new Producer("Producer Test", "Testing producer address, NN1 89A", "0208 190 3314");
		boolean created = producerService.createProducer(producer);
		assertTrue(created);
	}

	@Test
	void test_ThatAProducerCannotBeCreated_IfItAlreadyExist() {
		Optional<Producer> producer = producerService.retrieveProducerById(1);
		boolean created = producerService.createProducer(producer.get());
		assertFalse(created);
	}

	@Test
	void test_ThatAProducerCanBeRetrievedById_IfItAlreadyExist() {
		long producerId = 1;
		Optional<Producer> producerFromDb = producerService.retrieveProducerById(producerId);
		assertEquals(producerId, producerFromDb.get().getProducerId());
	}

	@Test
	void test_ThatAProducerCannotBeRetrievedById_IfItDoesNotAlreadyExist() {
		long producerId = 100;
		Optional<Producer> producerFromDb = producerService.retrieveProducerById(producerId);
		assertEquals(null, producerFromDb);
	}

	@Test
	void test_ThatAllProducerCanBeRetrieved() {
		int numberOfProducerBefore = producerService.retrieveAllProducers().size();
		Producer producer = new Producer("Producer Test", "Testing producer address, NN1 89A", "0208 190 3314");
		producerService.createProducer(producer);
		int numberOfProducerAfter = producerService.retrieveAllProducers().size();
		assertNotEquals(numberOfProducerAfter, numberOfProducerBefore);
	}

	@Test
	void test_ThatAProducerCanBeUpdated_IfItAlreadyExists() {
		long producerToUpdateId = 5;
		Optional<Producer> producerToUpdate = producerService.retrieveProducerById(producerToUpdateId);
		producerToUpdate.get().setName("New producer name");
		boolean updated = producerService.updateProducer(producerToUpdate.get());
		assertTrue(updated);
	}

	@Test
	void test_ThatAProducerCanBeDeletedById_WhenTheProducerHasNoItemsInStore() {
		long producerToDeleteId = 5;
		boolean deleted = producerService.deleteProducerById(producerToDeleteId);
		assertTrue(deleted);
	}

	@Test
	void test_ThatAProducerCannotBeDeletedById_WhenTheProducerHasItemsInStore() {
		long producerToDeleteId = 1;
		boolean deleted = producerService.deleteProducerById(producerToDeleteId);
		assertFalse(deleted);
	}
	
	@Test
	void test_ThatAProducerCanBeDeletedByName_WhenTheProducerHasNoItemsInStore() {
		String producerToDeleteName = "Farmers Dream";
		boolean deleted = producerService.deleteProducerByName(producerToDeleteName);
		assertTrue(deleted);
	}
	
	@Test
	void test_ThatAProducerCannotBeDeletedByName_WhenTheProducerHasItemsInStore() {
		String producerToDeleteName = "Growers Selection";
		boolean deleted = producerService.deleteProducerByName(producerToDeleteName);
		assertFalse(deleted);
	}

}
