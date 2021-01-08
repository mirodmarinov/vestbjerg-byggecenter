package modelLayer;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductContainerTest
{
	Product product1;
	Product product2;
	
	@BeforeEach
	void setUp()
	{
		product1 = new Product(10, 10, 0, 2500, 2000, "123CDB24", "Hammer", "A construction hammer", "Tools", "DIY");
		product2 = new Product(100, 100, 0, 30, 30, "C62CDA24", "Nail", "Standart nail", "Item", "DIY");
	}
	
	@Test
	void notNullTest()
	{

		ProductContainer.getInstance().addProduct(product1);
		ProductContainer.getInstance().addProduct(product2);
		assertNotNull(ProductContainer.getInstance());
	}
	
	@Test
	void getProduct()
	{
		assertEquals(ProductContainer.getInstance().getProducts("Hammer").size(), 1);
		assertEquals(ProductContainer.getInstance().getProducts("Hammer").get(0).getName(), "hammer");
	}


	@Test
	void getNoInput()
	{
		assertEquals(ProductContainer.getInstance().getProducts("1").size(), 0);
	}


}
