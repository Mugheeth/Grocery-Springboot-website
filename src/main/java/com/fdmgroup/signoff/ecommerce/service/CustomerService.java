package com.fdmgroup.signoff.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.signoff.ecommerce.dao.CustomerDao;
import com.fdmgroup.signoff.ecommerce.model.Customer;

@Service
public class CustomerService {

	private static Logger LOGGER = LogManager.getLogger(CustomerService.class);

	@Autowired
	private CustomerDao customerDao;

	public boolean createCustomer(Customer newCustomer) {
		boolean created = false;
		String customerEmail = newCustomer.getEmail();
		if (customerDao.getByEmail(customerEmail).isEmpty()) {
			customerDao.save(newCustomer);
			created = true;
		} else {
			LOGGER.info("Cannot create user as user already exists.");
			created = false;
		}
		return created;
	}

	public Optional<Customer> retrieveById(long id) {
		if (customerDao.existsById(id)) {
			return customerDao.findById(id);
		} else {
			LOGGER.info("Customer Does not exists.");
			return null;
		}
	}

	public Optional<Customer> retrieveByEmailAndPassword(String email, String password) {
		if (customerDao.getByEmail(email).isPresent()) {
			return customerDao.getByEmailAndPassword(email, password);
		} else {
			LOGGER.info("Customer with this email does not exist");
			return null;
		}
	}

	public List<Customer> retrieveAllCustomer() {
		return customerDao.findAll();
	}

	public boolean deleteCustomerById(long customerIdToDelete) {
		boolean deleted = false;
		if (customerDao.findById(customerIdToDelete).isPresent()) {
			customerDao.deleteById(customerIdToDelete);
			deleted = true;
		} else {
			LOGGER.info("Customer not found - cannot be deleted.");
			deleted = false;
		}
		return deleted;
	}

	public boolean deleteCustomerByEmail(String customerEmailToDelete) {
		boolean deleted = false;
		Optional<Customer> customerFromDb = customerDao.getByEmail(customerEmailToDelete);
		if (customerFromDb.isPresent()) {
			customerDao.deleteById(customerFromDb.get().getCustomerId());
			deleted = true;
		} else {
			LOGGER.info("Customer with this email does not exist - cannot delete.");
			deleted = false;
		}
		return deleted;
	}

	public boolean updateCustomer(Customer customer) {
		boolean updated = false;
		if (customerDao.existsById(customer.getCustomerId())) {
			customerDao.save(customer);
			updated = true;
		}
		return updated;
	}

}
