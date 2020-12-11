package controlLayer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modelLayer.Customer;
import modelLayer.CustomerContainer;
import modelLayer.CustomerNotFoundException;
import modelLayer.OrderLineItem;
import modelLayer.Product;
import modelLayer.ProductContainer;

class OrderCtrTest
{

	//TODO MAKE THIS CONTROLLER
	private static CustomerCtr customerCtr;
	private static ProductCtr productCtr;
	private static OrderCtr orderCtr;
	//Customer customer1;
	//Customer customer2;
	Product product1;
	Product product2;
	ArrayList<OrderLineItem> olns;
	OrderLineItem oln;
	
	@BeforeEach
	void setUpBeforeClass() throws Exception
	{
		productCtr = new ProductCtr();
		customerCtr = new CustomerCtr();
		//customer1 = new Customer(126589,2,"Bob","Aalborg 12","Customer");
		//customer2 = new Customer(165598,0,"Tobias","Aarhus 50","Customer");
		customerCtr.addCustomer(126589,2,"Bob","Aalborg 12","Customer");
		customerCtr.addCustomer(165598,0,"Tobias","Aarhus 50","Customer");
		productCtr.addProduct(10, 10, 0, 2500, 2000, "123CDB24", "Hammer", "A construction hammer", "Tools", "DIY");
		productCtr.addProduct(100, 100, 0, 30, 30, "C62CDA24", "Nail", "Standart nail", "Item", "DIY");
		//product1 = new Product(10, 10, 0, 2500, 2000, "123CDB24", "Hammer", "A construction hammer", "Tools", "DIY");
		//product2 = new Product(100, 100, 0, 30, 30, "C62CDA24", "Nail", "Standart nail", "Item", "DIY");
		//ProductContainer.getInstance().addProduct(product1);
		//ProductContainer.getInstance().addProduct(product2);
		oln = new OrderLineItem(product1,2);
		olns.add(oln);
		oln = new OrderLineItem(product2,4);
		olns.add(oln);
	}

	@Test
	void findCustomer()
	{	
		assertNotNull(CustomerContainer.getInstance().getCustomer(126589));
	}
	


}
