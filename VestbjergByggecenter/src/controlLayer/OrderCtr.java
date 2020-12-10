package controlLayer;

import modelLayer.*;
import java.util.ArrayList;

/**
 * This class is a part of the System developed for Vestbjerg Byggecenter. It
 * acts as a controller for all of the order objects.
 */

public class OrderCtr
{
	private CustomerCtr customerCtr = new CustomerCtr();
	private ProductCtr productCtr = new ProductCtr();
	private Customer customer;
	private ArrayList<OrderLineItem> orderProducts;
	private ArrayList<Product> foundProducts;
	

	public OrderCtr()
	{
	}

	public ArrayList<OrderLineItem> getOrderProducts()
	{
		return orderProducts;
	}
	
	/**
	 * This method returns the customers name 
	 * and also stores the customer Object in the customer field
	 * of this class.
	 * 
	 * @param phone
	 * @return String of customer name
	 */
	public String findCustomer(int phone)
	{
		// TODO - check if correct
		Customer customer = customerCtr.getCustomer(phone);
		this.customer = customer;
		return customer.getName();
	}

	/**
	 * This method calls the getProducts() in the product controller
	 * and creates a list of products that are stored in the foundProducts
	 * field. Then an Array of Strings containing info such
	 * as the name an description of the product. This can then be used
	 * in the UI layer.
	 * 
	 * @param name
	 * @return ArrayList of String Arrays containing product information
	 */
	public ArrayList<String[]> getProducts(String name)
	{
		foundProducts = productCtr.getProducts(name);
		ArrayList<String[]> allProductsInfo = new ArrayList<String[]>();
		
		for(Product product: foundProducts)
		{
			String[] productInfo = new String[2];
			productInfo[0] = product.getName();
			productInfo[1] = product.getDescription();
			
			allProductsInfo.add(productInfo);
		}
		
		return allProductsInfo;
	}

	/**
	 * This method allows us to have a product
	 * selected from the foundProducts list,
	 * and then based on the place in the list
	 * add it to the orderProducts list
	 * along with the quantity of the product
	 * that is being ordered/offered. If the product
	 * and quantity is successfully added to the orderProducts
	 * list, the method returns true, if not
	 * false.
	 * 
	 * @param placeInList
	 * @param quantity
	 * @return true or false
	 */
	public boolean selectProduct(int placeInList, int quantity)
	{
		Product product = foundProducts.get(placeInList);
		OrderLineItem orderLineItem = new OrderLineItem(product, quantity);
		
		return orderProducts.add(orderLineItem);
	}

	/**
	 *This method uses the fields customer and  
	 *orderProducts to create
	 *an order Object and add
	 *it to our collection of orders.
	 *It also creates an expiration date and automatically
	 *sets status to pending.
	 * 
	 * @return true or false
	 */
	public boolean createOffer()
	{
		Order offer = new Order(customer, orderProducts);
		offer.calculateExpirationDate();
		offer.setStatus("pending");
		
		/*
		 * Here we update the threshold of the product, 
		 * to make sure that the quantity in stock matches
		 * the possible order size. The threshold however, should
		 * be changed again, if the offer is not
		 * accepted within the agreed expiration date
		 */
		for(OrderLineItem p: orderProducts)
		{
			p.getProduct().updateThreshold(p.getQuantity());
		}
		
		return OrderContainer.getInstance().addOrder(offer);
	}

	public boolean createOrder()
	{
		Order order = new Order(customer, orderProducts);
		order.setStatus("confirmed");
		
		return OrderContainer.getInstance().addOrder(order);
	}

	/**
	 * This method allows us to calculate the price for the offer/order.
	 * 
	 * @return total cost of order/offer
	 */
	public long calculateTotal()
	{
		long totalWithoutDiscount = 0;
		long totalWithDiscount = 0;
		for(OrderLineItem p : orderProducts) 
		{
			Product product = p.getProduct();
			int quantity = p.getQuantity();
			totalWithoutDiscount += product.getSalesPrice() * quantity;
			totalWithDiscount += product.getSalesPrice() * quantity * (100 - product.getDiscount(quantity));
		}
		
		totalWithDiscount *= (100-customer.getDiscount());
		
		return totalWithDiscount/totalWithoutDiscount < 0.8 ? (int)0.8 * totalWithoutDiscount : totalWithDiscount;
	}
}
