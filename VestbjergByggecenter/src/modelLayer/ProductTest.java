package modelLayer;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

class ProductTest
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
	void productsShouldBeCreated()
	{
		assertNotNull(product1);
		assertNotNull(product2);
	}
	
	
	@Test
	void productsShouldNotBeCreated()
	{
		Product product3 = null;
		assertNull(product3);
	}
	
	@Test
	void productGetMethods()
	{
		assertEquals(product1.getName(), "Hammer");
		assertEquals(product1.getGroup(), "Tools");
		assertEquals(product1.getThreshold(), 10);
		assertEquals(product1.getQuantity(), 10);
		assertEquals(product1.getDiscount(0), 0);
		assertEquals(product1.getDiscount(10), 2);
		assertEquals(product1.getPurchasePrice(), 2500);
		assertEquals(product1.getSalesPrice(), 2000);
		assertEquals(product1.getBarcode(), "123CDB24");
		assertEquals(product1.getDescription(), "A construction hammer");
		assertEquals(product1.getLocation(), "DIY");
	}
	
	@Test
	void productNameShouldNotBeThat()
	{
		assertNotEquals(product2.getName(), "Wood");
	}

}
