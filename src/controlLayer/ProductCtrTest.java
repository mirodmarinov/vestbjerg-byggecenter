package controlLayer;

//import modelLayer.*;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelLayer.Product;

class ProductCtrTest {

	Product product1;
	Product product2;
	private static ProductCtr productCtr;
	
	@BeforeAll
	static void setUp()
	{
		productCtr = new ProductCtr();
		productCtr.createProduct(10, 10, 10, 10, 10, "Camel", "Nail", "Not a good description", "Kitchen", "Aalborg 50");
		productCtr.createProduct(10, 10, 10, 10, 10, "TestSmth", "Hemmors", "This stepbrother of the hammer", "DIY", "Aalborg 50");
		productCtr.createProduct(10, 10, 10, 10, 10, "Dead", "Victim", "This product is meant to be deleted", "DIY", "Aalborg 50");
	}

	@Test
	void createProduct() 
	{
		productCtr = new ProductCtr();
		productCtr.getProductInfo("Nail");
	}
	
	@Test
	void readProduct()
	{
		productCtr = new ProductCtr();
		assertEquals(productCtr.getProductInfo("nail").size(),1);
	}

	
	@Test
	void updateProduct()
	{
		productCtr = new ProductCtr();
		productCtr.getProductInfo("hemm");
		assertEquals(productCtr.updateParameter(0, 0, "Changed from Hemmors"),true);
	}
	
	@Test
	void deleteProduct()
	{
		productCtr = new ProductCtr();
		productCtr.getProductInfo("vic");
		
		assertEquals(productCtr.deleteProduct(0),true);
	}

}
