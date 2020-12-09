package modelLayer;

/**
 * This class is a part of the System for
 * Vestbjerg Byggecenter. It acts as a collection
 * of all products in the system. This allows us to
 * create methods that needs to be done on the entire 
 * collection, rather than the individual product.
 * I am making this change now.
 *
 */

import java.util.*;

public class ProductContainer 
{
	private static ProductContainer uniqueInstance = new ProductContainer();
	private ArrayList<Product> products = new ArrayList<>();
	
	private ProductContainer()
	{
	}
	
	public static ProductContainer getInstance()
	{
		return uniqueInstance;
	}
	
	/**
	 * This method creates and returns an ArrayList
	 * of all the products whose name, somewhat matches
	 * the search word.
	 * 
	 * @param name
	 * @return A list of products found
	 */
	public ArrayList<Product> getProducts(String name)
	{
		ArrayList<Product> specifiedProducts = new ArrayList<Product>();
		
		for(Product a: products)
		{
			if(a.getName().contains(name))
			{
				specifiedProducts.add(a);
			}
		}
		
		return specifiedProducts;
		//TODO - check this code
	}
	
	/*
	public Product selectProduct(String barcode) //quantity removed for now?
	{
		boolean found = false;
		Product product = null;
		
		for(int i = 0; i < products.size() && found == false; i++)
		{
			if(barcode.equals(products.get(i).getBarcode()))
			{
				product = products.get(i);
				found = true;
			}
		}
			
		return product;
		//TODO - quantity????
	}
	*/
}
