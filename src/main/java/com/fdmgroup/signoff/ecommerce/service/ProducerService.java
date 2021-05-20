package com.fdmgroup.signoff.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.signoff.ecommerce.dao.ProducerDao;
import com.fdmgroup.signoff.ecommerce.model.Producer;

@Service
public class ProducerService {

	private static Logger LOGGER = LogManager.getLogger(ProducerService.class);

	@Autowired
	private ProducerDao producerDao;

	public boolean createProducer(Producer producer) {
		boolean created = false;
		if (!producerDao.existsByName(producer.getName())) {
			producerDao.save(producer);
			created = true;
		} else {
			LOGGER.info("Producer already exists.");
		}
		return created;
	}

	public Optional<Producer> retrieveProducerById(long id) {
		if (producerDao.findById(id).isPresent()) {
			return producerDao.findById(id);
		} else {
			LOGGER.info("Producer does not exist.");
			return null;
		}
	}

	public List<Producer> retrieveAllProducers() {
		return producerDao.findAll();
	}

	public boolean updateProducer(Producer producer) {
		boolean updated = false;
		if (producerDao.existsById(producer.getProducerId())) {
			producerDao.save(producer);
			updated = true;
			return updated;
		}
		LOGGER.info("Producer does not exist.");
		return updated;
	}

	public boolean deleteProducerById(long producerToDeleteId) {
		boolean deleted = false;
		if (producerDao.existsById(producerToDeleteId)) {
			if (producerDao.checkIfProducerHasGroceryItems(producerToDeleteId).isEmpty()) {
				producerDao.deleteById(producerToDeleteId);
				deleted = true;
			} else {
				LOGGER.info("Producer has existing grocery items in stock.");
				deleted = false;
			}
		} else {
			LOGGER.info("Producer does not exist.");
			deleted = false;
		}
		return deleted;
	}

	public boolean deleteProducerByName(String producerToDeleteName) {
		boolean deleted = false;
		Optional<Producer> producer = producerDao.findByName(producerToDeleteName);
		if (producer.isPresent()) {
			if (producerDao.checkIfProducerHasGroceryItems(producer.get().getProducerId()).isEmpty()) {
				producerDao.deleteById(producer.get().getProducerId());
				deleted = true;
			} else {
				LOGGER.info("Producer has existing grocery items in stock.");
				deleted = false;
			}
		} else {
			LOGGER.info("Producer does not exist.");
			deleted = false;
		}
		return deleted;
	}

}
