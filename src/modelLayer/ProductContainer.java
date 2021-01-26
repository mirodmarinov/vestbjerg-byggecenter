package modelLayer;

/**
 * This class is a part of the System for
 * Vestbjerg Byggecenter. It acts as a collection
 * of all products in the system. This allows us to
 * create methods that needs to be done on the entire 
 * collection, rather than the individual product.
 * It implement Serializable in order to be able to save it to a file.
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class ProductContainer implements Serializable
{
	private static final long serialVersionUID = 1L; //is used to identify the state of the Object
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
	 * This method creates and returns an ArrayList of all the products whose
	 * name, somewhat matches the search word.
	 * 
	 * @param name
	 * @return A list of products found
	 */
	public ArrayList<Product> getProducts(String name)
	{
		ArrayList<Product> specifiedProducts = new ArrayList<Product>();
		name = name.toLowerCase();
		for (Product a : products)
		{
			if (a.getName().contains(name))
			{
				specifiedProducts.add(a);
			}
		}
		Serialization.getInstance().serializeClass("modelLayer.ProductContainer");
		return specifiedProducts;
	}

	/**
	 * Made as a test method for the ProductContainerTest test class, the create
	 * offer and the create order use cases.
	 * 
	 * @param product a product object to be added to the product container
	 * @return a boolean value of the success
	 */
	public boolean addProduct(Product product)
	{
		for (Product p : products)
		{
			if (product.getBarcode().equals(p.getBarcode()))
			{
				return false;
			}
		}
		products.add(product);
		Serialization.getInstance().serializeClass("modelLayer.ProductContainer");
		return true;
	}

	/**
	 * Deleting a product based on the product provided.
	 * 
	 * @param product
	 * @return a boolean value if deleting the product was successful.
	 */
	public boolean deleteProduct(Product product)
	{
		Iterator<Product> it = products.iterator();
		while (it.hasNext())
		{
			Product p = it.next();
			if (p.getBarcode().equals(product.getBarcode()))
			{
				return products.remove(p);
			}
		}
		return false;
	}

	/**
	 * A method that overrides the instance with the object retrieved from
	 * deserialization.
	 * 
	 * @param ois	new ProductCOntainer object ot be used
	 * @throws IOException	based if the input object is reabable
	 * @throws ClassNotFoundException if it cannot find the singleton class
	 */
	private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException
	{
		ois.defaultReadObject();
		uniqueInstance = this;
	}

	/**
	 * Used for serialization to return the instance of the singleton class
	 * 
	 * @return returns a productCOntainer instance that can be serialized
	 */
	private Object readResolve()
	{
		return uniqueInstance;
	}
	
	/**
	 * Is used in the controlLayer to retrieve
	 * the entire ArrayList of products saved
	 * in the container.
	 */
	public ArrayList<Product> getProductsArrayList()
	{
		return products;
	}
}
