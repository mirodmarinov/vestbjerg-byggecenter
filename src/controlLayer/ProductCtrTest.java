package controlLayer;

import modelLayer.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

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

	/*@Test
	void createProduct() 
	{
		System.out.println();
		System.out.println("CREATEPRODUCT");

		ArrayList<Product> products = ProductContainer.getInstance().products;
		for (Product pr : products) System.out.println(pr.getName());
		productCtr = new ProductCtr();
		productCtr.getProduct("Nail");
		assertEquals(productCtr.products.size(),1);
		//assertEquals(ProductContainer.getInstance().getProducts("Nail").get(0).getName(),"nail");
	}*/
	
	/*@Test
	void readProduct()
	{
		System.out.println();
		System.out.println("READPRODUCT");
		ArrayList<Product> products = ProductContainer.getInstance().products;
		for (Product pr : products) System.out.println(pr.getName());
		productCtr = new ProductCtr();
		assertEquals(productCtr.getProduct("nail").size(),1);
	}

	
	@Test
	void updateProduct()
	{
		System.out.println();
		System.out.println("UPDATEPRODUCT");
		ArrayList<Product> products = ProductContainer.getInstance().products;
		for (Product pr : products) System.out.println(pr.getName());
		productCtr = new ProductCtr();
		productCtr.getProduct("hemm");
		assertEquals(productCtr.updateParameter(0, 0, "Changed from Hemmors"),true);
	}*/
	
	@Test
	void deleteProduct()
	{
		System.out.println();
		System.out.println("DELETEPRODUCT");
		ArrayList<Product> products = ProductContainer.getInstance().products;
		for (Product pr : products) System.out.println(pr.getName());
		productCtr = new ProductCtr();
		productCtr.getProduct("vic");
		assertEquals(productCtr.deleteProduct(0),true);
	}

}
