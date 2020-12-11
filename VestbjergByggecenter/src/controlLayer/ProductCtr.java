package controlLayer;

import modelLayer.*;
import java.util.*;

/**
 * This class is a part of the System
 * developed for Vestbjerg Byggecenter.
 * It acts a controller for all of the product
 * objects.
 */

public class ProductCtr 
{
	
	public ProductCtr()
	{
	}
	
	/*
	 * The method returns an ArrayList of products
	 * by calling the matching method in the Container
	 * and passing the String parameter name.
	 */
	public ArrayList<Product> getProducts(String name)
	{
		return ProductContainer.getInstance().getProducts(name);
	}
	
	/*
	public Product selectProduct(String barcode, int quantity)
	{
		//TODO - update
		return ProductContainer.getInstance().selectProduct(barcode);
	}
	*/
	
	/**
	 * Made as a test method for the create offer and the create order use cases.
	 * @param threshold
	 * @param quantity
	 * @param discount
	 * @param purchasePrice
	 * @param salesPrice
	 * @param barcode
	 * @param name
	 * @param description
	 * @param group
	 * @param location
	 */
	public void addProduct(int threshold, int quantity,  int discount, long purchasePrice, long salesPrice, String barcode, String name, String description,
	                String group, String location)
	{
		Product product = new Product(threshold,quantity,discount,purchasePrice,salesPrice,barcode,name,description,group,location);
		ProductContainer.getInstance().addProduct(product);
	}
}
