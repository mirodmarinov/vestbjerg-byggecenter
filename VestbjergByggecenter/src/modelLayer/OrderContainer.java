package modelLayer;

/**
 * This class defines orders in the System for
 * Vestbjerg Byggecenter. It acts as a collection
 * of all orders in the system. This allows us to
 * create methods that needs to be done on the entire 
 * collection, rather than the individual order.
 */

import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class OrderContainer implements Serializable
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
		return orders.add(order);
		//TODO - any checks?
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
	 * @return returns a productCOntainer instance that can be serialized
	 */
	private Object readResolve()
	{
		return uniqueInstance;
	}

}
