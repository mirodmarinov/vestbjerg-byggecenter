package modelLayer;

import java.util.Calendar;
import java.util.ArrayList;

/**
 * This class extends the Order class,
 * since offers are orders with an expiration date.
 * Therefore it contains all the same elements.
 *
 */
public class Offer extends Order 
{
	
	private Calendar expirationDate;
	
	public Offer(Customer customer, ArrayList<OrderLineItem> products) 
	{
		super(customer, products);
		expirationDate = null;
		//TODO make automatically generated expirationDate
	}

	/**
	 * These are the getters and setters
	 * for the field expirationDate
	 */
	public Calendar getExpirationDate() 
	{
		return expirationDate;
	}

	public void setExpirationDate(Calendar expirationDate) 
	{
		this.expirationDate = expirationDate;
	}
	
}
