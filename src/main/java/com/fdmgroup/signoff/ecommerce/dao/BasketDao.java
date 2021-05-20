package com.fdmgroup.signoff.ecommerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.signoff.ecommerce.model.Basket;

public interface BasketDao extends JpaRepository<Basket, Long>{

}
