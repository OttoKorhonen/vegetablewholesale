package hh.swd20.vegetablewholesale.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.vegetablewholesale.domain.CategoryRepository;
import hh.swd20.vegetablewholesale.domain.Product;
import hh.swd20.vegetablewholesale.domain.ProductRepository;

@CrossOrigin
@Controller
public class RestController{
	
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductRepository productRepository;

	
//Tehdään RESTful metodi hakemaan kaikki tuotteet
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public @ResponseBody List<Product> productListRest() {
		return (List<Product>) productRepository.findAll();
	}

	// RESTful palvelu, jolla etsitään tuotteen id:n perusteella
	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
	public @ResponseBody Optional<Product> findProductRest(@PathVariable("productId") Long productId) {
		return productRepository.findById(productId);
	}
	// Rest palvelu, jolla voi lisätä uuden tuotteen
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public @ResponseBody Product addNewProduct(@RequestBody Product product) {
		//kategorian käsittely org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : hh.swd20.vegetablewholesale.domain.Product.category -> hh.swd20.vegetablewholesale.domain.Category; nested exception is java.lang.IllegalStateException: org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : hh.swd20.vegetablewholesale.domain.Product.category -> hh.swd20.vegetablewholesale.domain.Category"
		productRepository.save(product);
		return product;
	}
//	//REST metodi tuotteen muokkaamiseen
//	@RequestMapping(value = "/products/{productId}", method = RequestMethod.POST)
//	public @ResponseBody Product editProduct(@RequestBody Product product, Model model) {
//		model.addAttribute("product", productRepository.findById(productId));
//		return product;
//	}
	
//	//REST metodi tuotteen muokkaamiseen
//		@RequestMapping(value = "/products/{productId}", method = RequestMethod.POST)
//		public @ResponseBody Product editProduct(@PathVariable("id") Long productId,@RequestBody Product product, Model model) {
//			model.addAttribute("product", productRepository.findById(productId));
//			return product;
//		}

//	// REST metodi tuotteen muokkaamiseen
//	@RequestMapping(value = "/products/{id}", method = RequestMethod.POST)
//	public @ResponseBody Optional<Product> editProduct(@PathVariable("id") Long productId, @RequestBody Optional<Product> p) {
//		// model.addAttribute("product", productRepository.findById(productId));
//		//productRepository.findById(productId).get().setPrice(0);
//		//Product product = productRepository.findById(productId).get();
//		//product.setPrice(p.getPrice());
//		//productRepository.save(product);
//		//return productRepository.findById(productId);
//		return p;
//	}

	// REST metodi kyselyn poistamiseen
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Optional<Product> deleteProductRest(@PathVariable("id") Long productId) {
		productRepository.deleteById(productId);
		return productRepository.findById(productId);
	}
}