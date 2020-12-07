package controlLayer;

import modelLayer.*;
import java.util.ArrayList;

/**
 * This class is a part of the System developed for Vestbjerg Byggecenter. It
 * acts a controller for all of the order objects.
 */

public class OrderCtr
{
	private CustomerCtr customerCtr = new CustomerCtr();
	private ProductCtr productCtr = new ProductCtr();
	private Customer customer;
	private ArrayList<Product> products;

	public OrderCtr()
	{
	}

	public String findCustomer(String phone)
	{
		// TODO - update
		return "";
	}

	public ArrayList<String> getProducts(String name)
	{
		// TODO - update
		return null;
	}

	public boolean selectProduct(String barcode, int quantity)
	{
		// TODO - update
		return true;
	}

	public boolean createOffer()
	{
		// TODO - update
		return true;
	}

}
