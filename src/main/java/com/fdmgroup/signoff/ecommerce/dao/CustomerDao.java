package com.fdmgroup.signoff.ecommerce.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.signoff.ecommerce.model.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long>{

	Optional<Customer> getByEmail(String customerEmail);

	Optional<Customer> getByEmailAndPassword(String email, String password);

}
