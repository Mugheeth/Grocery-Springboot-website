package com.fdmgroup.signoff.ecommerce.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Basket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "basket_gen")
	@SequenceGenerator(name = "basket_gen", sequenceName = "BASKET_SEQ", allocationSize = 1)
	private long basketId;
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "groceryId")
	private List<Grocery> items = new ArrayList<>();

	public Basket() {
		super();
	}

	public long getBasketId() {
		return basketId;
	}

	public void setBasketId(long basketId) {
		this.basketId = basketId;
	}

	public List<Grocery> getItems() {
		return items;
	}

	public void setItems(List<Grocery> items) {
		this.items = items;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (basketId ^ (basketId >>> 32));
		result = prime * result + ((items == null) ? 0 : items.hashCode());
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
		Basket other = (Basket) obj;
		if (basketId != other.basketId)
			return false;
		if (items == null) {
			if (other.items != null)
				return false;
		} else if (!items.equals(other.items))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Basket [basketId=" + basketId + ", items=" + items + "]";
	}
	
}
