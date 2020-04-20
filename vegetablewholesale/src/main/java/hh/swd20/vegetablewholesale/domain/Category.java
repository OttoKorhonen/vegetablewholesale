package hh.swd20.vegetablewholesale.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)//luodaan automaattisesti id-numero categorylle
	@NotNull
	private Long categoryId;
	@Size(min=2,max=15)
	private String name;
	
	@JsonBackReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category") //luodaan yhden suhde moneen relaatio product listaan
	private List<Product>products;

	public Category() {
		super();
		
	}
	
	public Category(Long categoryId, String name) {
		super();
		this.categoryId = categoryId;
		this.name = name;
	}

	public Category(String name) {
		super();
		this.name = name;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", name=" + name + "]";
	}
}
