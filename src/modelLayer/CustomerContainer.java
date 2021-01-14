package modelLayer;

import java.util.ArrayList;

/**
 * This class defines orders in the System for
 * Vestbjerg Byggecenter. It acts as a collection
 * of all customers in the system. This allows us to
 * create methods that needs to be done on the entire 
 * collection, rather than the individual customer.
 *
 */


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
/**
 *  Customer Container implements Serializable in order to be able to save it in a local file.
 *  It is a singleton class that contains all customers of the system.
 * @author Miroslav
 *
 */
public class CustomerContainer implements Serializable
{
	
	private static final long serialVersionUID = 1L;
    private static CustomerContainer uniqueInstance = new CustomerContainer();
	private ArrayList<Customer> customers = new ArrayList<>();

    private CustomerContainer()
    {
    	
    	
    }
    
    public static CustomerContainer getInstance()
    {
        return uniqueInstance;
    }

    /**
     * The method returns a Customer
     * by searching through the collection
     * and finding a customer based on their phone number
     * since this is the unique identifier for
     * Customer objects.
     * 
     * @param phone
     * @return Customer
     */
	public Customer getCustomer(int phone)
	{
		Customer customer = null;
		boolean found = false;
		
		for(int i = 0; i < customers.size() && found == false; i++)
		{
			if(customers.get(i).getPhone() == phone)
			{
				customer = customers.get(i);
				found = true;
			}
		}
		
		
		return customer;
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
	
	/**
	 * Made as a test method for the CustomerContainerTest test class, the create offer
	 * and the create order use cases.
	 * @param customer
	 */
	public boolean addCustomer(Customer customer)
	{
		for (Customer c: customers)
		{
			if (customer.getPhone() == c.getPhone())
			{
				return false;
			}
		}
		return customers.add(customer);
	}
	
	public ArrayList<Customer> getCustomers()
	{
		return customers;
	}
	
}
