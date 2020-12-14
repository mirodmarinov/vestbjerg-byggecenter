package modelLayer;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderLineItemTest
{
	Product product1;
	Product product2;
	OrderLineItem oln;
	OrderLineItem oln2;
	
	
	@BeforeEach
	void setUp()
	{
		product1 = new Product(10, 10, 0, 2500, 2000, "123CDB24", "Hammer", "A construction hammer", "Tools", "DIY");
		product2 = new Product(100, 100, 0, 30, 30, "C62CDA24", "Nail", "Standart nail", "Item", "DIY");
		oln = new OrderLineItem(product1,2);
		oln2 = new OrderLineItem(product2,2);
	}
	
	@Test
	void isNull()
	{
		assertNotNull(oln);
		assertNotNull(oln2);
	}
	
	@Test
	void getInformation()
	{
		assertEquals(oln.getProduct(), product1);
		assertEquals(oln.getQuantity(), 2);
	}

}
