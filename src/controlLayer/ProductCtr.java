package controlLayer;

import modelLayer.*;

import java.util.*;


/**
 * This class is a part of the System developed for Vestbjerg Byggecenter. It
 * acts a controller for all of the product objects.
 */

public class ProductCtr
{
	private ArrayList<Product> products;

	public ProductCtr()
	{
	}

	/**
	 * The method returns an ArrayList of products by calling the matching
	 * method in the Container and passing the String parameter name.
	 */
	public ArrayList<Product> getProducts(String name)
	{
		return ProductContainer.getInstance().getProducts(name);
	}

	/**
	 * This method creates a product, and passes
	 * all necessary information into it.
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
	 * @param name used to find all products that contain this string
	 *            
	 * @return An ArrayList of Strings arrays with their fields as values
	 */
	public ArrayList<String[]> getProductInfo(String name)
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
	 * Finds a product by its place on the generated ArrayList
	 * 
	 * @param placeOnList index in the products ArrayList         
	 * @return returns an array of all its field values
	 */
	public String[] selectProduct(int placeOnList)
	{
		return products.get(placeOnList).toStrings();
	}

	/**
	 * It updates a product's parameter based on its place on the list of the product ArrayList
	 * If the input is invalid, it returns false, otherwise returns true.
	 * 
	 * @param placeOnList
	 * @param index
	 * @param value
	 * @return boolean
	 */
	public boolean updateParameter(int placeOnList, int index, String value)
	{
		Product p = products.get(placeOnList);
		if (index >= 5)
		{
			if (!tryCatch(value))
			{
				return false;
			}
		}
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
				p.setSalesPrice(Float.valueOf(value));
				break;
			case 8:
				p.setPurchasePrice(Float.valueOf(value));
				break;
			case 9:
				p.setDiscount(Integer.valueOf(value));
				break;
		}
		return true;
	}
	
	/**
	 * Deletes a product based on it's place in a list.
	 * 
	 * @param placeInList
	 */
	public boolean deleteProduct(int placeInList)
	{
		return ProductContainer.getInstance().deleteProduct(products.get(placeInList));
	}

	/**
	 * Retrieves info about a product
	 * based on it's place in a list.
	 * And returns it as a String
	 * without brackets,
	 * so it does not look like
	 * they are choices.
	 * 
	 * @param placeInList
	 */
	public String getInfo(int placeInList)
	{
		return products.get(placeInList).toString().replaceAll("\\([^()]*\\)", "");
	}

	/**
	 * Retrieves info about a product
	 * based on it's place in a list.
	 * And returns it as a String
	 * 
	 * @param placeInList
	 */
	public String getParameters(int placeInList)
	{
		return products.get(placeInList).toString();

	}
	
	/**
	 * Checks the parameter validation for the updateParameter method.
	 * Whenever the value is a number returns true otherwise it returns false
	 * 
	 * @param value 
	 * @return
	 */
	private boolean tryCatch(String value)
	{
		try
		{
			Float.parseFloat(value);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	/**
	 * Returns the found elements to the Products panel
	 * 
	 * @param name of the product
	 * @return
	 */
	public ArrayList<String[]> searchField(String name)
	{
		ArrayList<Product> products = ProductContainer.getInstance().getProducts(name);
		ArrayList<String[]> data = new ArrayList<>();
		for (Product e : products)
		{
			this.products.add(e);
			data.add(e.tableFill());
		}
		return data;
	}
	
	/**
	 * Returns the newest up to 50 elements from the Products container.
	 * Those datas fill up the products table in the UI.
	 * 
	 * @param index of the page
	 * @return the data of the products
	 */
	public ArrayList<String[]> getProductsInfo(int index)
	{
		int productAmount = 0;
		ArrayList<String[]> returnValue = new ArrayList<>();
		ArrayList<Product> products = ProductContainer.getInstance().getProductsArrayList();	
		if (products.size() == 0)
		{
			return returnValue;
		}
		if ((int)(Math.floor(products.size() / 50))+1 > index)
		{
			this.products = (ArrayList<Product>) products.subList((index-1)*50, (index-1)*50+50);
			productAmount = 50;
		}
		else if ((int)(Math.floor(products.size() / 50))+1 == index)
		{
			this.products = new ArrayList<Product>(products.subList((index-1)*50, (index-1)*50+(int)products.size()%50));
			productAmount = (int)(products.size()%50);
		}
		else
		{
			return returnValue;
		}
		for (int e = (index-1)*50; e < (index-1)*50+productAmount; e++)
		{
			returnValue.add(products.get(e).tableFill());	
		}
		
		return returnValue;
	}
	
	/**
	 * Gets only product by the barcode. Used in EditCustomerDialog UI. 
	 * Return all the information about the product.
	 * 
	 * @param barcode
	 * @return
	 */
	public String[] getProductInfoByBarcode(String barcode)
	{
		ArrayList<Product> products = ProductContainer.getInstance().getProductsArrayList();
		String[] productInfo = null;
		for (Product product : products)
		{
			if (product.getBarcode().equals(barcode))
			{
				productInfo = product.toStrings();
				break;
			}
		}
		return productInfo;
	}
	
	/**
	 * This method returns a Product Object form the Product Container
	 * based on the products barcode.
	 * @param barcode
	 * @return
	 */
	public Product getProduct(String barcode)
	{
		ArrayList<Product> products = ProductContainer.getInstance().getProductsArrayList();
		for (Product product : products)
		{
			if (product.getBarcode().equals(barcode))
			{
				return product;
			}
		}
		return null;
	}
	
	/**
	 * This method is used to do error handling in the GUI
	 * we check if the inputs are correct, for the given
	 * type.
	 */
	public boolean checkValues(String name,String value,boolean isString)
	{
		if (isString)
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
				float input = Float.parseFloat(value);
				if(input < 0)
				{
					return false;
				}
				return true;
			}
			catch(Exception e)
			{
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Updates the location by retrieving
	 * the product based on barcode
	 * and then inputing the new location.
	 */
	public void updateLocation(String barcode, String location)
	{
		Product p = getProduct(barcode);
		p.setLocation(location);
	}
}
