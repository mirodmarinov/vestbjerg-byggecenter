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
	
	public ArrayList<Product> getProducts(String name)
	{
		//TODO - update the returned list
		return products;
	}
	
	public Product selectProduct(String barcode, int quantity)
	{
		//TODO - update
		return null;
	}
}
