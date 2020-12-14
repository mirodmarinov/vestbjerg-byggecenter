package modelLayer;

import static org.junit.Assert.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

class CustomerContainerTest
{


	@BeforeAll
	static void setUp()
	{
		Customer customer1 = new Customer(12658989,2,"Bob", "Aalborg 12", "Customer");
		Customer customer2 = new Customer(16559898,0,"Tobias", "Aarhus 50", "Customer");
		CustomerContainer.getInstance().addCustomer(customer1);
		CustomerContainer.getInstance().addCustomer(customer2);
	}
	@Test
	void notNullTest()
	{
		assertNotNull(CustomerContainer.getInstance());
	}
	
	@Test
	void getProduct()
	{
		assertEquals(CustomerContainer.getInstance().getCustomer(12658989).getName(), "Bob");
	}
	
	@Test
	void getNull()
	{
		assertEquals(CustomerContainer.getInstance().getCustomer(0), null);
	}


}
