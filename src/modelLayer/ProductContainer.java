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

import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ProductContainer implements Serializable
{
	
	private static final long serialVersionUID = 1L;
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
		name = name.toLowerCase();
		
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
	
	/**
	 *
	 *A method that overrides the instance with the object retrieved from deserialization
	 * @param ois
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	
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
	
	
	/**
	 * Made as a test method for the ProductContainerTest test class, the create offer
	 * and the create order use cases.
	 * @param product
	 */
	public boolean addProduct(Product product)
	{
		for (Product p: products)
		{
			if (product.getBarcode().equals(p.getBarcode()))
			{
				return false;
			}
		}
		return products.add(product);
	}
	
	public boolean deleteProduct(Product product)
	{
		Iterator<Product> it = products.iterator();
		while (it.hasNext())
		{
			Product p = it.next();
			if (p.getBarcode().equals(p.getBarcode()))
			{
				return products.remove(p);
			}
		}
		return false;
	}
	
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException
	{
		ois.defaultReadObject();
		uniqueInstance = this;
	}

	/**
	 * used for serialization to return the instance of the singleton class
	 * @return returns a productCOntainer instance that can be serialized
	 */
	private Object readResolve()
	{
		return uniqueInstance;
	}
}
