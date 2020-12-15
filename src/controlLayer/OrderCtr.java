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
		orderProducts = new ArrayList<OrderLineItem>();
		foundProducts = new ArrayList<Product>();
	}

	public ArrayList<OrderLineItem> getOrderProducts()
	{
		return orderProducts;
	}

	/**
	 * This method returns the customers name and also stores the customer
	 * Object in the customer field of this class.
	 * Throws and exception if the customer can't be find
	 * 
	 * @param phone
	 * @return String of customer name
	 */
	public String findCustomer(int phone) throws CustomerNotFoundException
	{
		// TODO - check if correct
		Customer customer = customerCtr.getCustomer(phone);
		this.customer = customer;
		if (customer == null)
		{
			throw new CustomerNotFoundException("No customer exists with this phone number: " + phone + "\n Make sure to write the phone number without the country code.");
		}
		return customer.getName();
	}

	/**
	 * This method calls the getProducts() in the product controller and creates
	 * a list of products that are stored in the foundProducts field. Then an
	 * Array of Strings containing info such as the name an description of the
	 * product is created. This can then be used in the UI layer.
	 * 
	 * @param name
	 * @return ArrayList of String Arrays containing product information
	 */
	public ArrayList<String[]> getProducts(String name)
	{
		foundProducts = productCtr.getProducts(name);
		ArrayList<String[]> allProductsInfo = new ArrayList<String[]>();

		for (Product product : foundProducts)
		{
			String[] productInfo = new String[3];
			productInfo[0] = product.getName();
			productInfo[1] = product.getDescription();
			productInfo[2] = String.valueOf(product.getQuantity());
			allProductsInfo.add(productInfo);
		}

		return allProductsInfo;
	}

	/**
	 * This method allows us to have a product selected from the foundProducts
	 * list, and then based on the place in the list add it to the orderProducts
	 * list along with the quantity of the product that is being
	 * ordered/offered. If the product and quantity is successfully added to the
	 * orderProducts list, the method returns true, if not false.
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
	 * This method uses the fields customer and orderProducts to create an order
	 * Object and add it to our collection of orders. It also creates an
	 * expiration date and automatically sets status to pending. This is an
	 * offer because its status is set to pending.
	 * 
	 * @return true or false
	 */
	public boolean createOffer()
	{
		Order offer = new Order(customer, orderProducts);
		offer.calculateExpirationDate();
		offer.setStatus("pending");

		/*
		 * Here we update the threshold of the product, to make sure that the
		 * quantity in stock matches the possible order size.
		 */
		for (OrderLineItem p : orderProducts)
		{
			p.getProduct().updateThreshold(p.getQuantity());
		}

		return OrderContainer.getInstance().addOrder(offer);
	}

	/**
	 * This method creates an Order, by passing the customer and the list of
	 * products and setting the status of the order to confirmed so indicate
	 * that it is an order.
	 * 
	 * 
	 */
	public boolean createOrder()
	{
		Order order = new Order(customer, orderProducts);
		order.setStatus("confirmed");

		/*
		 * Here we update the stock, by changing the quantity of the product in
		 * stock based on the quantity of the orderline item.
		 */
		for (OrderLineItem p : orderProducts)
		{
			p.getProduct().updateQuantity(p.getQuantity() * (-1));
		}

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
		for (OrderLineItem p : orderProducts)
		{
			Product product = p.getProduct();
			int quantity = p.getQuantity();
			totalWithoutDiscount += product.getSalesPrice() * quantity;
			totalWithDiscount += product.getSalesPrice() * quantity * (100 - product.getDiscount(quantity))/100;

		}
		totalWithDiscount *= (100 - customer.getDiscount())/100;
		
		return totalWithDiscount / totalWithoutDiscount < 0.8 ? (long)(0.8 * totalWithoutDiscount) : totalWithDiscount;
	}
}
