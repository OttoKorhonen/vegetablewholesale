package hh.swd20.vegetablewholesale.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)//luodaan automaattisesti id-numeroita tuotteille
	private Long productId;
	private String name;
	private String cultivar;
	private String description;
	private String producer;
	private String country;
	private double price;
	
	@ManyToOne//luodaan monen suhde yhteen yhteys relaatiotietokantaan
	@JsonIgnore
	@JsonManagedReference
	@JoinColumn(name = "categoryId")//viitataan category-luokan categoryId:hen
	private Category category;

	//luodaan parametriton ja parametrilliset konstruktorit, getterit ja setterit sekä toString
	public Product() {
		super();
		this.name = null;
		this.cultivar = null;
		this.description = null;
		this.producer = null;
		this.country = null;
		this.price = 0;
		this.category = null;
		
	}

	public Product(String name, String cultivar, String description, String producer, String country, double price,
			Category category) {
		super();
		this.name = name;
		this.cultivar = cultivar;
		this.description = description;
		this.producer = producer;
		this.country = country;
		this.price = price;
		this.category = category;
	}

	public Product(Long productId, String name, String cultivar, String description, String producer, String country,
			double price, Category category) {
		super();
		this.productId = productId;
		this.name = name;
		this.cultivar = cultivar;
		this.description = description;
		this.producer = producer;
		this.country = country;
		this.price = price;
		this.category = category;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCultivar() {
		return cultivar;
	}

	public void setCultivar(String cultivar) {
		this.cultivar = cultivar;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		if(this.category != null)
		return "Product [productId=" + productId + ", name=" + name + ", cultivar=" + cultivar + ", description="
				+ description + ", producer=" + producer + ", country=" + country + ", price=" + price + ", category="
				+ category + "]";
		else
		return "Product [productId=" + productId + ", name=" + name + ", cultivar=" + cultivar + ", description="
				+ description + ", producer=" + producer + ", country=" + country + ", price=" + price + "]";
	}
	
	
	
	
}
