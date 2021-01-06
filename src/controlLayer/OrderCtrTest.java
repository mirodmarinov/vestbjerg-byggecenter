package controlLayer;

import modelLayer.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import modelLayer.Customer;
import modelLayer.CustomerContainer;
import modelLayer.CustomerNotFoundException;
import modelLayer.OrderLineItem;
import modelLayer.Product;
import modelLayer.ProductContainer;

class OrderCtrTest
{
	private static OrderCtr orderCtr;
	Product product1;
	Product product2;
	
	@BeforeAll
	static void Setup()
	{	
		orderCtr = new OrderCtr();
		Customer customer1 = new Customer(12658989,2,"Bob", "Aalborg 12", "Customer");
		Customer customer2 = new Customer(16559898,0,"Tobias", "Aarhus 50", "Customer");
		CustomerContainer.getInstance().addCustomer(customer1);
		CustomerContainer.getInstance().addCustomer(customer2);
		Product p1 = new Product(10, 50, 0, 5000, 10000, "123456789", "nails", "Huge nails , fix houses", "the nail shelf", "3.12.50");
		Product p2 = new Product(10, 50, 0, 6000, 11000, "123456788", "different nails", "Bigger nails , used for fixing different houses", "the nail shelf", "3.12.51");
		Product p3 = new Product(10, 10, 0, 2500, 2000, "123456787", "hammer", "A construction hammer", "Tools", "3.12.52");
		
		ProductContainer.getInstance().addProduct(p1);
		ProductContainer.getInstance().addProduct(p2);
		ProductContainer.getInstance().addProduct(p3);
		
		ArrayList<OrderLineItem> olns = new ArrayList<>();
		OrderLineItem oln;
		
		oln = new OrderLineItem(p1,2);
		olns.add(oln);
		oln = new OrderLineItem(p2,4);
		olns.add(oln);
		oln = new OrderLineItem(p3,10);
		olns.add(oln);

	}

	@Test
	void findCustomer() throws CustomerNotFoundException
	{	
		assertEquals(orderCtr.findCustomer(12658989),"Bob");
	}
	
	
	@Test
	void findCustomerNegativeTest()
	{	
		Exception exception = assertThrows(CustomerNotFoundException.class, () -> {orderCtr.findCustomer(11111111);});
	    exception.getMessage();
	}
	
	@Test
	void getProducts()
	{	
		assertEquals(orderCtr.getProducts("Ham").get(0)[0],"hammer");
		assertEquals(orderCtr.getProducts("Ham").get(0)[1],"A construction hammer");
		
	}
	
	@Test
	void selectProduct()
	{	
		assertEquals(orderCtr.selectProduct(0, 2), true);
	}
	
	@Test
	void selectProductOutOfBoundTest()
	{	
		Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {orderCtr.selectProduct(-1, 2);});
	    exception = assertThrows(IndexOutOfBoundsException.class, () -> {orderCtr.selectProduct(100, 2);});
	    exception.getMessage();
	}
	
	@Test
	void getProductsNegativeTest()
	{	
		assertNotEquals(orderCtr.getProducts("Ham").size(), 0);
	}
	
	@Test
	void createOffer()
	{	
		orderCtr = new OrderCtr();
		
		try{orderCtr.findCustomer(12658989);}
		catch(Exception e) {}
		orderCtr.getProducts("Ham");
		orderCtr.selectProduct(0, 2);
		orderCtr.getProducts("nai");
		orderCtr.selectProduct(0, 2);
		int currentthreshold = orderCtr.getOrderProducts().get(0).getProduct().getThreshold();
		int currentquantity = 2;
		
		assertEquals(orderCtr.createOffer(),true);

		assertEquals(OrderContainer.getInstance().getOrders().get(0).getStatus(),"pending");
		assertEquals(OrderContainer.getInstance().getOrders().get(0).getProducts().get(0).getProduct().getName(),"hammer");
		assertEquals(OrderContainer.getInstance().getOrders().get(0).getProducts().get(0).getProduct().getThreshold(),
				currentthreshold+currentquantity);
		assertEquals(OrderContainer.getInstance().getOrders().get(0).getProducts().size(),2);
	}
	
	@Test
	void placeOrder()
	{	
		orderCtr = new OrderCtr();
		
		try{orderCtr.findCustomer(12658989);}
		catch(Exception e) {}
		orderCtr.getProducts("Ham");
		orderCtr.selectProduct(0, 2);
		int currentquantity = orderCtr.getOrderProducts().get(0).getProduct().getQuantity();
		
		assertEquals(orderCtr.createOrder(),true);

		assertEquals(OrderContainer.getInstance().getOrders().get(1).getStatus(),"confirmed");
		assertEquals(OrderContainer.getInstance().getOrders().get(1).getProducts().get(0).getProduct().getName(),"hammer");
		assertEquals(OrderContainer.getInstance().getOrders().get(1).getProducts().get(0).getQuantity(), 2); //Product amount in the order
		assertEquals(OrderContainer.getInstance().getOrders().get(1).getProducts().get(0).getProduct().getQuantity(), currentquantity-2); //Product amount left in the stock
		assertEquals(OrderContainer.getInstance().getOrders().get(1).getProducts().size(),1);
	}
	

	
	@Test
	void isGenerateInvoiceNull()
	{
		orderCtr = new OrderCtr();
		assertNotNull(orderCtr.generateInvoice(1));
		System.out.println(orderCtr.generateInvoice(1));
	}

}
