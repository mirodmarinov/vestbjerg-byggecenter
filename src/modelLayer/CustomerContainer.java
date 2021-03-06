package modelLayer;

import java.util.ArrayList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
/**
 *  Customer Container implements Serializable in order to be able to save it in a local file.
 *  It is a singleton class that contains all customers of the system.
 *
 */
public class CustomerContainer implements Serializable
{
	private static final long serialVersionUID = 1L; //is used to identify the state of the Object
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
		customers.add(customer);
		serializeClass();
		return true;
	}
	
	/**
	 * Used to delete a customer from the container
	 * @param customer
	 * @return true if successful
	 */
	public boolean deleteCustomer(Customer customer)
	{
		return customers.remove(customer);
	}
	
	/**
	 * Is used in the controlLayer to retrieve
	 * the entire ArrayList of products saved
	 * in the container.
	 */
	public ArrayList<Customer> getCustomers()
	{
		return customers;
	}
	
	private void serializeClass()
	{
		Serialization.getInstance().serializeClass("modelLayer.CustomerContainer");
	}
	
	/**
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
	 * Used for serialization to return the instance of the singleton class
	 * @return returns a productCOntainer instance that can be serialized
	 */
	private Object readResolve()
	{
		return uniqueInstance;
	}
}
