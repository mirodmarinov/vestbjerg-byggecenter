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
	
	/**
	 * The method adds an already existing offer to
	 * the ArrayList<> orders. If the
	 * addition is successful the method returns true,
	 * if not it returns false.
	 * 
	 * @param offer
	 * @return true or false
	 */
	public boolean addOffer(Offer offer)
	{
		return orders.add(offer);
		//TODO - any checks?
	}

}
