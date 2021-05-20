package com.fdmgroup.signoff.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.fdmgroup.signoff.ecommerce.model.Basket;
import com.fdmgroup.signoff.ecommerce.model.Customer;
import com.fdmgroup.signoff.ecommerce.service.CustomerService;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CustomerTest {

	@Autowired
	private CustomerService customerService;

	@Test
	void test_ThatACustomerCanBeCreated_IfItDoesNotAlreadyExist() {
		Customer newCustomer = new Customer("Steven", "Strange", "strange@hotmail.com", "magic", new Basket());
		boolean created = customerService.createCustomer(newCustomer);
		assertTrue(created);
	}
	
	@Test
	void test_ThatACustomerCannotBeCreated_IfItDoesAlreadyExists() {
		Optional<Customer> existingCustomer = customerService.retrieveById(1);
		boolean created = customerService.createCustomer(existingCustomer.get());
		assertFalse(created);
	}
	
	@Test
	void test_ThatACustomerCanBeRetrieved_UsingEmailAndPassword_IfUserAlreadyExist() {
		Optional<Customer> customerFromDb = customerService.retrieveByEmailAndPassword("stark@hotmail.com", "password");
		assertEquals("stark@hotmail.com", customerFromDb.get().getEmail());
	}
	
	@Test
	void test_ThatACustomerCannotBeRetrieved_UsingEmailAndPassword_IfUserDoesNotExist() {
		Optional<Customer> customerFromDb = customerService.retrieveByEmailAndPassword("doesNotExist@hotmail.com", "somepassword");
		assertEquals(null, customerFromDb);
	}
	
	@Test
	void test_ThatAllCustomerCanBeRetrieved() {
		int customersBeforeAdding = customerService.retrieveAllCustomer().size();
		Customer newCustomer = new Customer("Customer", "Test", "test@hotmail.com", "testing", new Basket());
		customerService.createCustomer(newCustomer);
		int customersAfterAdding = customerService.retrieveAllCustomer().size();
		assertNotEquals(customersBeforeAdding, customersAfterAdding);
	}
	
	@Test
	void test_ThatACustomerCanBeDeletedById_IfAlreadyExists() {
		long customerIdToDelete = 1;
		boolean customerDeleted = customerService.deleteCustomerById(customerIdToDelete);
		assertTrue(customerDeleted);
	}
	
	@Test
	void test_ThatACustomerCannotBeDeletedById_IfDoesNotExist() {
		long customerIdToDelete = 100;
		boolean customerDeleted = customerService.deleteCustomerById(customerIdToDelete);
		assertFalse(customerDeleted);
	}
	
	@Test
	void test_ThatACustomerCanBeDeletedByEmail_IfCustomerAlreadyExists() {
		String customerEmailToDelete = "stark@hotmail.com";
		boolean customerDeleted = customerService.deleteCustomerByEmail(customerEmailToDelete);
		assertTrue(customerDeleted);
	}

	@Test
	void test_ThatACustomerCannotBeDeletedByEmail_IfCustomerDoesNotExist() {
		String customerEmailToDelete = "dummy@notexist.com";
		boolean customerDeleted = customerService.deleteCustomerByEmail(customerEmailToDelete);
		assertFalse(customerDeleted);
	}
	
	@Test
	void test_ThatACustomerCanBeUpdatedById_IfCustomerAlreadyExists() {
		long customerToUpdateId = 1;
		Optional<Customer> customerToUpdate = customerService.retrieveById(customerToUpdateId);
		customerToUpdate.get().setFirstName("Howard Stark");
		boolean updated = customerService.updateCustomer(customerToUpdate.get());
		assertTrue(updated);
	}
	
	@Test
	void test_ThatACustomerCanBeUpdatedByEmailAndPassword_IfCustomerAlreadyExists() {
		String email = "stark@hotmail.com";
		String password = "password";
		Optional<Customer> customerToUpdate = customerService.retrieveByEmailAndPassword(email, password);
		customerToUpdate.get().setFirstName("Howard Stark");
		boolean updated = customerService.updateCustomer(customerToUpdate.get());
		assertTrue(updated);
	}

}
