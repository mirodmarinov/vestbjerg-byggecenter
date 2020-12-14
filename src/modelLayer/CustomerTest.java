package modelLayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomerTest
{

	Customer customer1;
	Customer customer2;
	@BeforeEach
	void setUp()
	{
		
		customer1 = new Customer(12658956,2,"Bob","Aalborg 12","Customer");
		customer2 = new Customer(16559865,0,"Tobias","Aarhus 50","Customer");
	}
	
	@Test
	void customerGetMethods()
	{
		assertEquals(customer1.getPhone(), 12658956);
		assertEquals(customer1.getDiscount(), 2);
		assertEquals(customer1.getName(), "Bob");
		assertEquals(customer1.getAddress(), "Aalborg 12");
		assertEquals(customer1.getGroup(), "Customer");
	}
	
	@Test
	void isCreated()
	{
		assertNotNull(customer1);
		assertNotNull(customer2);
	}
}
