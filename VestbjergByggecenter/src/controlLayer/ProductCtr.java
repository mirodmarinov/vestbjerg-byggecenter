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
}
