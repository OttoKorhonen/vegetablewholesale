package hh.swd20.vegetablewholesale;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.vegetablewholesale.web.ProductController;

@RunWith(SpringRunner.class)
@SpringBootTest
class VegetablewholesaleApplicationTests {

	@Autowired
	private ProductController controller;
	
	@Test
	void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}

}
