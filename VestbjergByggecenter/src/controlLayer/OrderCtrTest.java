package controlLayer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelLayer.Customer;
import modelLayer.CustomerContainer;
import modelLayer.Product;
import modelLayer.ProductContainer;

class OrderCtrTest
{

	//TODO MAKE THIS CONTROLLER
	private static CustomerCtr customerCtr;
	private static ProductCtr productCtr;
	private static OrderCtr orderCtr;
	Customer customer1;
	Customer customer2;
	Product product1;
	Product product2;
	@BeforeEach
	void setUpBeforeClass() throws Exception
	{
		productCtr = new ProductCtr();
		customerCtr = new CustomerCtr();
		customer1 = new Customer(126589,2,"Bob","Aalborg 12","Customer");
		customer2 = new Customer(165598,0,"Tobias","Aarhus 50","Customer");
		CustomerContainer.getInstance().addCustomer(customer1);
		CustomerContainer.getInstance().addCustomer(customer2);
		product1 = new Product(10, 10, 0, 2500, 2000, "123CDB24", "Hammer", "A construction hammer", "Tools", "DIY");
		product2 = new Product(100, 100, 0, 30, 30, "C62CDA24", "Nail", "Standart nail", "Item", "DIY");
		ProductContainer.getInstance().addProduct(product1);
		ProductContainer.getInstance().addProduct(product2);
	}

	@Test
	void findCustomer()
	{
		try{
		assertEquals(orderCtr.findCustomer(126589),"Bob");
		assertNotEquals(orderCtr.findCustomer(165598), "Bob");
		}
		catch(Exception e)
		{
			
		}
	}
	
	@Test
	void calculateTotal()
	{
		try {
		assertNotEquals(orderCtr.calculateTotal(),0);
		System.out.println("works");
		}
		catch(Exception e) {}
	}


}
