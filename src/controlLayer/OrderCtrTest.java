package controlLayer;


import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import modelLayer.CustomerNotFoundException;
import modelLayer.OrderContainer;
import modelLayer.OrderLineItem;
import modelLayer.Product;

class OrderCtrTest
{

	private static CustomerCtr customerCtr;
	private static ProductCtr productCtr;
	private static OrderCtr orderCtr = new OrderCtr();
	Product product1;
	Product product2;
	private ArrayList<OrderLineItem> olns = new ArrayList<>();
	OrderLineItem oln;
	
	@BeforeEach
	void setUpBeforeClass() throws Exception
	{
		productCtr = new ProductCtr();
		customerCtr = new CustomerCtr();
	}
	
	@Test
	void Setup()
	{	
		customerCtr.addCustomer(126589,2,"Bob","Aalborg 12","Customer");
		customerCtr.addCustomer(165598,0,"Tobias","Aarhus 50","Customer");
		productCtr.addProduct(10, 10, 0, 2500, 2000, "123CDB24", "Hammer", "A construction hammer", "Tools", "DIY");
		productCtr.addProduct(100, 100, 0, 30, 30, "C62CDA24", "Nail", "Standart nail", "Item", "DIY");
		product1 = new Product(10, 10, 0, 2500, 2000, "123CDB24", "Hammer", "A construction hammer", "Tools", "DIY");
		product2 = new Product(100, 100, 0, 30, 30, "C62CDA24", "Nail", "Standart nail", "Item", "DIY");
		oln = new OrderLineItem(product1,2);
		olns.add(oln);
		oln = new OrderLineItem(product2,4);
		olns.add(oln);
		
	}

	@Test
	void findCustomer() throws CustomerNotFoundException
	{	
		assertEquals(orderCtr.findCustomer(126589),"Bob");
	}
	
	
	@Test
	void findCustomerNegativeTest()
	{	
		Exception exception = assertThrows(CustomerNotFoundException.class, () -> {orderCtr.findCustomer(111111);});
	    exception.getMessage();
	}
	
	@Test
	void getProducts()
	{	
		assertEquals(orderCtr.getProducts("Ham").get(0)[0],"Hammer");
		assertEquals(orderCtr.getProducts("Ham").get(0)[1],"A construction hammer");
		assertEquals(orderCtr.getProducts("Ham").size(),1);
		
	}
	
	/*@Test
	void selectProduct()
	{	
		assertEquals(orderCtr.selectProduct(0, 2),true);
	}*/
	
	/*void selectProductNegativeTest()
	{	
		Exception exception = assertThrows(CustomerNotFoundException.class, () -> {orderCtr.selectProduct(1, 2));});
	    exception.getMessage();
	}*/
	
	@Test
	void getProductsNegativeTest()
	{	
		assertEquals(orderCtr.getProducts("Ham").size(),0);
		
	}
	
	/*@Test
	void createOffer()
	{	
		int currentthreshold = orderCtr.getOrderProducts().get(0).getProduct().getThreshold();
		int currentquantity = orderCtr.getOrderProducts().get(0).getProduct().getQuantity();
		assertEquals(orderCtr.createOffer(),true);
		assertEquals(OrderContainer.getInstance().getOrders().get(0).getStatus(),"pending");
		assertEquals(OrderContainer.getInstance().getOrders().get(0).getProducts().get(0).getProduct().getName(),"Hammer");
		assertEquals(OrderContainer.getInstance().getOrders().get(0).getProducts().get(0).getProduct().getThreshold(),
				currentthreshold+currentquantity);
		assertEquals(OrderContainer.getInstance().getOrders().get(0).getProducts().size(),0);
		
	}*/
	


}
