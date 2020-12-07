package modelLayer;

/**
 * This class defines orders in the System for
 * Vestbjerg Byggecenter. It acts as a collection
 * of all customers in the system. This allows us to
 * create methods that needs to be done on the entire 
 * collection, rather than the individual customer.
 *
 */

import java.util.ArrayList;

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
