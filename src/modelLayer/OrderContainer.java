package modelLayer;

/**
 * This class defines orders in the System for
 * Vestbjerg Byggecenter. It acts as a collection
 * of all orders in the system. This allows us to
 * create methods that needs to be done on the entire 
 * collection, rather than the individual order.
 * It implement Serializable in order to be able to save it to a file.
 */

import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class OrderContainer implements Serializable
{
	private static final long serialVersionUID = 1L; //is used to identify the state of the Object
	private static OrderContainer uniqueInstance = new OrderContainer();
	private ArrayList<Order> orders = new ArrayList<>();
	public int orderNumberGenerator = 0;
	
	private OrderContainer()
	{
	}
	
	public static OrderContainer getInstance()
	{
		return uniqueInstance;
	}
	
	public static void setInstance(OrderContainer orderC)
	{
		uniqueInstance = orderC;
	}
	
	/**
	 * The method adds an already existing order to
	 * the ArrayList<> orders. If the
	 * addition is successful the method returns true,
	 * if not it returns false.
	 * 
	 * @param order
	 * @return true or false
	 */
	public boolean addOrder(Order order)
	{
		if (orders.add(order))
		{
			order.setOrderNumber(++orderNumberGenerator);
			Serialization.getInstance().serializeClass("modelLayer.OrderContainer");
			return true;
		}
		return false;
	}
	
	/**
	 *
	 *A method that overrides the instance with the object retrieved from deserialization
	 * @param ois
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException
	{
		ois.defaultReadObject();
		uniqueInstance = this;
	}

	/**
	 * used for serialization to return the instance of the singleton class
	 * @return returns a productContainer instance that can be serialized
	 */
	private Object readResolve()
	{
		return uniqueInstance;
	}
	
	/**
	 * Method was created so we can test the createOffer and placeOrder use cases
	 * @return
	 */
	public ArrayList<Order> getOrders()
	{
		return orders;
	}
	
	/**
	 * Returns an order based on its orderNumber
	 */
	public Order findOrder(int orderNumber)
	{
		Order order = null;
		boolean found = false;
		int i = 0;
		while(!found && i < orders.size())
		{
			if(orders.get(i).getOrderNumber() == orderNumber)
			{
				found = true;
				order = orders.get(i);
			}
			else
			{
				i++;
			}
		}
		return order;
	}
	
	/**
	 * Finds an offer based on its
	 * order number and confirms the offer.
	 */
	public boolean confirmOffer(int orderNumber)
	{
		Order order = findOrder(orderNumber);
		order.generatePurchaseDate();
		
		if (order.setStatus("confirmed"))
		{
			Serialization.getInstance().serializeClass("modelLayer.OrderContainer");
			return true;
		}
		return false;
	}
}
