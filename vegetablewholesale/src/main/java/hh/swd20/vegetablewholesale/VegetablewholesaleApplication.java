package hh.swd20.vegetablewholesale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.vegetablewholesale.domain.Category;
import hh.swd20.vegetablewholesale.domain.CategoryRepository;
import hh.swd20.vegetablewholesale.domain.Product;
import hh.swd20.vegetablewholesale.domain.ProductRepository;
import hh.swd20.vegetablewholesale.domain.User;
import hh.swd20.vegetablewholesale.domain.UserRepository;

@SpringBootApplication
public class VegetablewholesaleApplication {
	
	private static final Logger log = LoggerFactory.getLogger(VegetablewholesaleApplication.class);

	public static void main(String[] args) {//mainista käynnistetään sovellus
		SpringApplication.run(VegetablewholesaleApplication.class, args);
	}

	@Bean
	public CommandLineRunner productDemo(ProductRepository productRepository, CategoryRepository categoryRepository, UserRepository userRepository ) {
		return(args) ->{
			log.info("Tallennetaan tuotteita");
			Category vihannes = new Category("Vihannes");
			Category hedelma = new Category("Hedelmä");
			Category marja = new Category("Marja");
			categoryRepository.save(vihannes);
			categoryRepository.save(hedelma);
			categoryRepository.save(marja);
			
			productRepository.save(new Product("Peruna", "Siikli", "Kullanruskea ja soikea", "Pertin perunatila", "Suomi", 10.0, vihannes));
			productRepository.save(new Product("Omena", "Royal Gala", "Punainen, pyöreä ja makea", "Raimon omenatila", "Suomi", 5.0, hedelma));
			productRepository.save(new Product("Mustaherukka", "Brödtorp", "Tumma pallura tertuissa", "Marjan marjatila", "Suomi", 16.0, marja));
			
			User user1 = new User("user", "$2a$04$ZVPp4IY7rkcgQjtRwUltxOh9XTZvln6QSvAjZV5Q/mA8JtXOp1vra", "user@user.com", "USER");
			User user2 = new User("admin", "$2a$04$ZVPp4IY7rkcgQjtRwUltxOh9XTZvln6QSvAjZV5Q/mA8JtXOp1vra", "admin@admin.com", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
			log.info("Hae tuotteita");
			for(Product product : productRepository.findAll()) {
				log.info(product.toString());
			}
		};
	}
}
