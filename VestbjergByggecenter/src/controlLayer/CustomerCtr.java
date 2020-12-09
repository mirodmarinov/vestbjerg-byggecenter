package controlLayer;

import modelLayer.*;

/**
 * This class is a part of the System for
 * Vestbjerg Byggecenter. It acts as the 
 * controller for interacting with the 
 * customer objects in the system.
 */

public class CustomerCtr 
{

	public CustomerCtr()
	{
	}
	
	/**
	 * The method calls a matching method
	 * inside the container, and passes the phone
	 * parameter to find a customer
	 * with the phone number. The method then returns
	 * the found customer.
	 */
	public Customer getCustomer(int phone)
	{
		return CustomerContainer.getInstance().getCustomer(phone);
	}
}
