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
		ProductContainer.getInstance().addProduct(product1);
		ProductContainer.getInstance().addProduct(product2);
	}
	
	@Test
	void notNullTest()
	{
		assertNotNull(ProductContainer.getInstance());
	}
	
	@Test
	void getProduct()
	{
		//1. Damn hate arraylists
		//2. How to write the return type of the arraylist?
		assertEquals(ProductContainer.getInstance().getProducts("Hammer"), "Hammer");
	}
	
	@Test
	void getNull()
	{
		assertEquals(ProductContainer.getInstance().getProducts(null), null);
	}
	
	
	@Test
	void getNoInput()
	{
		//Null or empty arraylist. We have to change it later
		assertEquals(ProductContainer.getInstance().getProducts("1"), null);
	}


}
