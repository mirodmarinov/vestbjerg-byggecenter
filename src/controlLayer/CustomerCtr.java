package controlLayer;

import java.util.ArrayList;

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
	 * @param phone
	 */
	public Customer getCustomer(int phone)
	{
		return CustomerContainer.getInstance().getCustomer(phone);
	}
	
	/**
	 * Made as a test method for the create order use case.
	 * @param phone
	 * @param discount
	 * @param name
	 * @param address
	 * @param group
	 */
	public void addCustomer(int phone, int discount, String name, String address, String group)
	{
		Customer customer = new Customer(phone,discount,name,address,group);
		CustomerContainer.getInstance().addCustomer(customer);
	}
	
	/**
	 * Finds and deletes customer based on phoneNumber.
	 * 
	 * @param phone
	 * @return true if successful
	 */
	public boolean deleteCustomerByPhone(int phone)
	{
		boolean result = false;
		Customer customer = getCustomer(phone);
		if (customer != null)
		{
			result = CustomerContainer.getInstance().deleteCustomer(customer);
		}
		return result;
	}
	
	/**
	 * Finds customer based on phone
	 * and updates the information inside it.
	 * 
	 * @param phone
	 * @param discount
	 * @param name
	 * @param address
	 * @param group
	 */
	public void updateCustomer(int phone, int discount, String name, String address, String group, int originalNumber)
	{
		Customer customer = getCustomer(originalNumber);
		customer.setPhone(phone);
		customer.setDiscount(discount);
		customer.setName(name);
		customer.setAddress(address);
		customer.setGroup(group);
	}
	
	/**
	 * This method return a list of Arrays
	 * containing string containing customer
	 * information.
	 * 
	 * @param index
	 * @return
	 */
	public ArrayList<String[]> getCustomers(int index)
	{
		int customerAmount = 0;
		ArrayList<String[]> returnValue = new ArrayList<>();
		ArrayList<Customer> customers = CustomerContainer.getInstance().getCustomers();	
		if (customers.size() == 0)
		{
			return returnValue;
		}
		//If we have more than 50 elements we set the customerAmount to 50
		if ((int)(Math.floor(customers.size() / 50))+1 > index)
		{
			customerAmount = 50;
		}
		//If a page contains less then 50 elements, the customerAmount is set to the leftover amount.
		else if ((int)(Math.floor(customers.size() / 50))+1 == index)
		{
			customerAmount = (int)(customers.size()%50);
		}
		else
		{
			return returnValue;
		}
		//All of the customers are added to returnValue based on the customerAmount
		for (int e = (index-1)*50; e < (index-1)*50+customerAmount; e++)
		{
			returnValue.add(customers.get(e).tableFill());
		}
		return returnValue;
	}
	
	/**
	 * This method is used only for the searchField in the GUI.
	 * The method retrieves all customers, whose phone number matches
	 * the sequence of numbers in the search bar. And live-updating
	 * the table, so the user has suggestions for customers based on the phone
	 * number.
	 * 
	 * @param id
	 * @return If order is found returns the data from it, otherwise returns null
	 */
	public ArrayList<String[]> searchField(String phone)
	{
		ArrayList<Customer> customers = CustomerContainer.getInstance().getCustomers();	
		ArrayList<String[]> data = new ArrayList<>();
		if (customers == null)
		{
			return data;
		}
		for (Customer customer : customers)
		{
			if (Integer.toString(customer.getPhone()).contains(phone))
			{
				data.add(customer.tableFill());
			}
		}
		return data;
	}
	
	/**
	 * This method is used to do error handling in the GUI
	 * we check if the inputs are correct, for the given
	 * type.
	 */
	public boolean checkValues(String name, String value, boolean string)
	{
		if (string)
		{
			if ((name+"...").equals(value) || (value.equals("")))
			{
				return false;
			}
		}
		else
		{
			try
			{
				Integer.parseInt(value);
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
		}
		return true;
	}
}
