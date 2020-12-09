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

public class CustomerContainer 
{
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
			if(phone == customers.get(i).getPhone())
			{
				customer = customers.get(i);
				found = true;
			}
		}
		return customer;
	}
}
