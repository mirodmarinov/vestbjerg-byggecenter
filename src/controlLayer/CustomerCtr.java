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
	
	public void updateCustomer(int phone, int discount, String name, String address, String group)
	{
		Customer customer = getCustomer(phone);
		customer.setPhone(phone);
		customer.setDiscount(discount);
		customer.setName(name);
		customer.setAddress(address);
		customer.setGroup(group);
	}
	
	public ArrayList<String[]> getCustomers(int index)
	{
		int customerAmount = 0;
		ArrayList<String[]> returnValue = new ArrayList<>();
		ArrayList<Customer> customers = CustomerContainer.getInstance().getCustomers();	
		if (customers.size() == 0)
		{
			return returnValue;
		}
		if ((int)(Math.floor(customers.size() / 50))+1 > index)
		{
			customerAmount = 50;
		}
		else if ((int)(Math.floor(customers.size() / 50))+1 == index)
		{
			customerAmount = (int)(customers.size()%50);
		}
		else
		{
			return returnValue;
		}
		

		for (int e = (index-1)*50; e < (index-1)*50+customerAmount; e++)
		{
			returnValue.add(customers.get(e).tableFill());
			
		}
		return returnValue;
		
	}
	
	/**
	 * This method is used only for the searchField in the GUI. We had to implement a new
	 * method because the findOffer method doesn't return every necessary information.
	 * 
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
	
	public boolean checkValues(String name,String value,boolean string)
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
