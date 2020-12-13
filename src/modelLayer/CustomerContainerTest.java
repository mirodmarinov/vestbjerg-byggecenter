package modelLayer;

import static org.junit.Assert.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

class CustomerContainerTest
{

	Customer customer1;
	Customer customer2;
	@BeforeEach
	void setUp()
	{
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
