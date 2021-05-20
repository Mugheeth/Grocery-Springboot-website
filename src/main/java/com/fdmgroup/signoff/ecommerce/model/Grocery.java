package com.fdmgroup.signoff.ecommerce.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Grocery")
public class Grocery {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grocery_gen")
	@SequenceGenerator(name = "grocery_gen", sequenceName = "GROCERY_SEQ", allocationSize = 1)
	private long groceryId;

	@Column
	private String name;

	@Column
	private String description;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "producerId")
	private Producer producer;

	@Column
	private long price;

	@Column
	private long quantity;

	public Grocery(String name, String description, Producer producer, long price, long quantity) {
		super();
		this.name = name;
		this.description = description;
		this.producer = producer;
		this.price = price;
		this.quantity = quantity;
	}

	public Grocery() {
		super();
	}

	public long getGroceryId() {
		return groceryId;
	}

	public void setGroceryId(long groceryId) {
		this.groceryId = groceryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (groceryId ^ (groceryId >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (price ^ (price >>> 32));
		result = prime * result + ((producer == null) ? 0 : producer.hashCode());
		result = prime * result + (int) (quantity ^ (quantity >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grocery other = (Grocery) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (groceryId != other.groceryId)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price != other.price)
			return false;
		if (producer == null) {
			if (other.producer != null)
				return false;
		} else if (!producer.equals(other.producer))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grocery [groceryId=" + groceryId + ", name=" + name + ", description=" + description + ", producer="
				+ producer + ", price=" + price + ", quantity=" + quantity + "]";
	}

}
