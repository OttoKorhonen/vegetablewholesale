package hh.swd20.vegetablewholesale.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class ProductController {

	// Model - vastuussa sovellusdatan ylläpidosta
	// View - vastuussa datan näyttämisestä käyttäjälle
	// Controller - käsittelee käyttäjän syöttämää tietoa (inputs) ja toimii yhdessä
	// modelin kanssa

	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	ProductRepository productRepository;

	// login controller kirja-sovellukseen
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/editproduct/{productId}", method = RequestMethod.GET) // haetaan endpointia
	public String editProduct(@PathVariable("productId") Long productId, Model model) {
		model.addAttribute("product", productRepository.findById(productId));// käytetään findById-metodia haettaessa
		// productrepositorystä tiettyä tuotetta Id-tunnuksella
		return "editproduct"; // editproduct.html palautus
	}

	// listaa tuotteet
	@RequestMapping(value = "/productlist", method = RequestMethod.GET)
	public String getProducts(Model model) {
		List<Product> products = (List<Product>) productRepository.findAll();// haetaan tietokannasta tuotteet
		model.addAttribute("products", products); // välitetään tuotelista templatelle model-olion avulla
		return "productlist"; // DispatcherServlet saa tämän template-nimen ja kutsuu seuraavaksi
								// productlist.html-templatea
	} // joka prosessoidaan palvelimella

	// tyhjän tuotelomakkeen muodostaminen, uuden tuotteen luominen
	//@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public String addProductForm(Model model) {
		model.addAttribute("product", new Product()); // "tyhjä" tuote-olio
		model.addAttribute("categories", categoryRepository.findAll());// lisättiin haku categoryrepositorysta
		return "addproduct"; // addproduct.html palautus
	}

	// tuotelomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute Product product) {
		// talletetaan yhden tuotteen tiedot tietokantaan
		productRepository.save(product); // save osaa tehdä tarpeen mukaan SQL insertin tai updaten
		return "redirect:/productlist";// /productlist-endpointin kutsu
	}

	// tuotteen poistamiseen käytetty metodi, jossa ainoastaan käyttäjä, jolle on
	// annettu rooli ADMIN voi poistaa tuotteen luettelosta
	@RequestMapping(value = "/deleteproduct/{productId}", method = RequestMethod.GET)
	//@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteProduct(@PathVariable("productId") Long productId, Model model) {
		productRepository.deleteById(productId);
		return "redirect:../productlist";
	}

	/*
	 * Tässä on REST metodit
	 */
	
	// Tehdään RESTful metodi hakemaan kaikki tuotteet
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public @ResponseBody List<Product> productListRest() {
		return (List<Product>) productRepository.findAll();
	}

//	// RESTful palvelu, jolla etsitään tuotteen id:n perusteella
//	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
//	public @ResponseBody Optional<Product> findProductRest(@PathVariable("productId") Long productId) {
//		return productRepository.findById(productId);
//	}
	// Rest palvelu, jolla voi lisätä uuden tuotteen
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public @ResponseBody Product addNewProduct(@RequestBody Product product) {
		productRepository.save(product);
		return product;
	}
//	//REST metodi tuotteen muokkaamiseen
//	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET)
//	public @ResponseBody Product editProduct(@RequestBody Product product, Model model) {
//		model.addAttribute("product", productRepository.findById(productId));
//		return product;
//	}
	
	//REST metodi tuotteen muokkaamiseen
		@RequestMapping(value = "/products/{id}", method = RequestMethod.POST)
		public @ResponseBody Optional <Product> editProduct(@PathVariable("id") Long productId) {
			//model.addAttribute("product", productRepository.findById(productId));
			return productRepository.findById(productId);
		}
	//REST metodi kyselyn poistamiseen
	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Optional <Product> deleteProductRest(@PathVariable("id") Long productId) {
		 productRepository.deleteById(productId);
		 return productRepository.findById(productId);
		
	}
}
