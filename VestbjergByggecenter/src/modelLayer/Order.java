package modelLayer;

import java.util.*;

/**
 * This class defines orders in the System for
 * Vestbjerg Byggecenter. It defines all the elements
 * of knowledge that is necessary to manage orders.
 */
public class Order 
{
	
	private int orderNumber; // The unique identifier
	private int discount;
	private long totalPrice;
	private Calendar purchaseDate;
	private String status; // The status of the sales process, is it still just an offer or how far along has it come
	private String delivery;
	private Customer customer;
	private ArrayList<OrderLineItem> products;
	
	
	public Order(Customer customer, ArrayList<OrderLineItem> products) 
	{
		this.customer = customer;
		this.products = products;
	
		//TODO automatically add purchaseDate, status, delivery, totalPrice
	}

	/**
 	* The following methods are the getters 
 	* and setters for all the fields.
 	*/
	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public int getDiscount() 
	{
		return discount;
	}

	public void setDiscount(int discount) 
	{
		this.discount = discount;
	}

	public String getDelivery() 
	{
		return delivery;
	}

	public void setDelivery(String delivery) 
	{
		this.delivery = delivery;
	}

	public long getTotalPrice() 
	{
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) 
	{
		this.totalPrice = totalPrice;
	}

	public Customer getCustomer() 
	{
		return customer;
	}

	public void setCustomer(Customer customer) 
	{
		this.customer = customer;
	}

	public ArrayList<OrderLineItem> getProducts() 
	{
		return products;
	}

	public void setProducts(ArrayList<OrderLineItem> products) 
	{
		this.products = products;
	}

	public int getOrderNumber() 
	{
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) 
	{
		this.orderNumber = orderNumber;
	}

	public Calendar getPurchaseDate() 
	{
		return purchaseDate;
	}

	public void setPurchaseDate(Calendar purchaseDate) 
	{
		this.purchaseDate = purchaseDate;
	}

}
