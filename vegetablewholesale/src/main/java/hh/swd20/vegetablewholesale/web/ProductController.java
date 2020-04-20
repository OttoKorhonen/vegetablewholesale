package hh.swd20.vegetablewholesale.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editproduct/{productId}", method = RequestMethod.GET) // haetaan endpointia
	public String editProduct(@PathVariable("productId") Long productId, Model model) {
		model.addAttribute("product", productRepository.findById(productId));// käytetään findById-metodia haettaessa
		model.addAttribute("categories", categoryRepository.findAll());
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
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	public String addProductForm(Model model) {
		model.addAttribute("product", new Product()); // "tyhjä" tuote-olio
		model.addAttribute("categories", categoryRepository.findAll());// lisättiin haku categoryrepositorysta
		return "addproduct"; // addproduct.html palautus
	}

	// tuotelomakkeella syötettyjen tietojen vastaanotto ja tallennus
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	// talletetaan yhden tuotteen tiedot tietokantaan
	public String saveProduct(@ModelAttribute @Valid Product product, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryRepository.findAll());
			System.out.println(bindingResult);
			return "addproduct";
		}
		productRepository.save(product); // save osaa tehdä tarpeen mukaan SQL insertin tai updaten
		return "redirect:/productlist";// /productlist-endpointin kutsu
	}

	// tuotteen poistamiseen käytetty metodi, jossa ainoastaan käyttäjä, jolle on
	// annettu rooli ADMIN voi poistaa tuotteen luettelosta
	@RequestMapping(value = "/deleteproduct/{productId}", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteProduct(@PathVariable("productId") Long productId, Model model) {
		productRepository.deleteById(productId);
		return "redirect:../productlist";
	}
}
