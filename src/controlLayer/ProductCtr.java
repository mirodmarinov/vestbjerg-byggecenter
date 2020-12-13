package controlLayer;

import modelLayer.*;
import java.util.*;

/**
 * This class is a part of the System developed for Vestbjerg Byggecenter. It
 * acts a controller for all of the product objects.
 */

public class ProductCtr
{

	ArrayList<Product> products;

	public ProductCtr()
	{
	}

	/*
	 * The method returns an ArrayList of products by calling the matching
	 * method in the Container and passing the String parameter name.
	 */
	public ArrayList<Product> getProducts(String name)
	{
		return ProductContainer.getInstance().getProducts(name);
	}

	/*
	 * public Product selectProduct(String barcode, int quantity) { //TODO -
	 * update return ProductContainer.getInstance().selectProduct(barcode); }
	 */

	/**
	 * Made as a test method for the create offer and the create order use
	 * cases.
	 * 
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
	public boolean createProduct(int threshold, int quantity, int discount, long purchasePrice, long salesPrice, String barcode, String name, String description,
	                String group, String location)
	{
		Product product = new Product(threshold, quantity, discount, purchasePrice, salesPrice, barcode, name, description, group, location);
		return ProductContainer.getInstance().addProduct(product);

	}

	/**
	 * Finds all products that contain a certain String in their name and
	 * returns their string values
	 * 
	 * @param name
	 *            used to find all products that contain this string
	 * @return An array of Strings with their fields as values
	 */
	public ArrayList<String[]> getProduct(String name)
	{
		ArrayList<String[]> productInfo = new ArrayList<String[]>();
		products = ProductContainer.getInstance().getProducts(name);

		for (Product p : products)
		{
			productInfo.add(p.toStrings());
		}
		return productInfo;
	}

	/**
	 * Finds a product by its place on the generated arraylist
	 * 
	 * @param placeOnList
	 *            index in the products arraylist
	 * @return returns an array of all its field values
	 */
	public String[] selectProduct(int placeOnList)
	{
		return products.get(placeOnList).toStrings();
	}

	/**
	 * It updates a product's parameter based on its place on the list
	 * @param placeOnList
	 * @param index
	 * @param value
	 * @return
	 */
	public boolean updateParameter(int placeOnList, int index, String value)
	{
		Product p = products.get(placeOnList);
		switch (index)
		{
			case 0:
				p.setName(value);
				break;
			case 1:
				p.setDescription(value);
				break;
			case 2:
				p.setGroup(value);
				break;
			case 3:
				p.setBarcode(value);
				break;
			case 4:
				p.setLocation(value);
				break;
			case 5:
				p.setQuantity(Integer.valueOf(value));
				break;
			case 6:
				p.setThreshold(Integer.valueOf(value));
				break;
			case 7:
				p.setSalesPrice(Long.valueOf(value));
				break;
			case 8:
				p.setPurchasePrice(Long.valueOf(value));
				break;
			case 9:
				p.setDiscount(Integer.valueOf(value));
				break;
			default:
				break;
		}
		return true;
	}
	
	public boolean deleteProduct(int placeInList)
	{
		ProductContainer.getInstance().deleteProduct(products.get(placeInList));
		
		return true;
	}
	
	public String getInfo(int placeInList)
	{
		return products.get(placeInList).toString().replaceAll("\\([^()]*\\)", "");
		//return products.get(placeInList).toString();
	}
	
	public String getParameters (int placeInList)
	{
		//return products.get(placeInList).getParameterList();
		return products.get(placeInList).toString();
		
	}
}
