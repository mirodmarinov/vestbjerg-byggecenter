package modelLayer;

/**
 * This class defines orders in the System for
 * Vestbjerg Byggecenter. It acts as a collection
 * of all orders in the system. This allows us to
 * create methods that needs to be done on the entire 
 * collection, rather than the individual order.
 */

import java.util.ArrayList;

public class OrderContainer 
{
	private static OrderContainer uniqueInstance = new OrderContainer();
	private ArrayList<Order> orders = new ArrayList<>();
	
	private OrderContainer()
	{
	}
	
	public static OrderContainer getInstance()
	{
		return uniqueInstance;
	}
	
	public boolean addOffer(Offer offer)
	{
		//TODO - update
		return true;
	}

}
