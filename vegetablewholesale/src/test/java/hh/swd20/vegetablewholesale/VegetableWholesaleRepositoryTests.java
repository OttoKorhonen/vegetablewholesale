package hh.swd20.vegetablewholesale;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.vegetablewholesale.domain.Category;
import hh.swd20.vegetablewholesale.domain.CategoryRepository;
import hh.swd20.vegetablewholesale.domain.Product;
import hh.swd20.vegetablewholesale.domain.ProductRepository;
import hh.swd20.vegetablewholesale.domain.User;
import hh.swd20.vegetablewholesale.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class VegetableWholesaleRepositoryTests {

	@Autowired
	private ProductRepository productRepository;
	@Autowired 
	private CategoryRepository categoryRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void findByName() {
		List<Product> products = productRepository.findByName("Peruna");
		assertThat(products).hasSize(1);
		assertThat(products.get(0).getName()).isEqualTo("Peruna");
	}
	
	@Test
	public void createNewProduct() {
		Product product = new Product("Paprika", "Chili", "Ring burner", "Pablo's chili", "Espanja", 5.0, new Category("Paprikat"));
		productRepository.save(product);
		assertThat(product.getProductId()).isNotNull();
		assertThat(product.getName()).isNotNull();
	}
	
	@Test
	public void findCategory() {
		List<Category> categories = categoryRepository.findByName("Vihannes");
		assertThat(categories.get(0).getName()).isEqualTo("Vihannes");
	}
	
	@Test
	public void getUser() {
		User user = userRepository.findByUsername("admin");
		assertThat(user.getUsername()).isNotNull();
	}
	
}
