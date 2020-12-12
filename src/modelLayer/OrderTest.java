package modelLayer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderTest
{

	Customer customer1;
	Customer customer2;
	Product product1;
	Product product2;
	OrderLineItem oln;
	Order order;
	Order order2;
	ArrayList<OrderLineItem> olns;
	
	@BeforeEach
	void setUp()
	{
		olns = new ArrayList<>();
		product1 = new Product(10, 10, 0, 2500, 2000, "123CDB24", "Hammer", "A construction hammer", "Tools", "DIY");
		product2 = new Product(100, 100, 0, 30, 30, "C62CDA24", "Nail", "Standart nail", "Item", "DIY");
		oln = new OrderLineItem(product1,2);
		olns.add(oln);
		oln = new OrderLineItem(product2,4);
		olns.add(oln);
		customer1 = new Customer(126589,2,"Bob","Aalborg 12","Customer");
		customer2 = new Customer(165598,0,"Tobias","Aarhus 50","Customer");
		order = new Order(customer1,olns);
		order2 = new Order(customer2,olns);
	}
	
	@Test
	void testNull()
	{
		assertNotNull(order);
		assertNotNull(order2);
	}
	
	@Test
	void checkGetters()
	{
		assertEquals(order.getCustomer(), customer1);
		assertEquals(order.getProducts(), olns);
	}

}
