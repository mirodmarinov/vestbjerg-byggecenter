package modelLayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerContainerTest
{

	Customer customer1;
	Customer customer2;
	@BeforeEach
	void setUp()
	{
		
		customer1 = new Customer(126589,2,"Bob","Aalborg 12","Customer");
		customer2 = new Customer(165598,0,"Tobias","Aarhus 50","Customer");
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
		//1. Damn hate arraylists
		//2. How to write the return type of the arraylist?
		assertEquals(CustomerContainer.getInstance().getCustomer(126589), "Bob");
	}
	
	@Test
	void getNull()
	{
		assertEquals(CustomerContainer.getInstance().getCustomer(0), null);
	}


}
