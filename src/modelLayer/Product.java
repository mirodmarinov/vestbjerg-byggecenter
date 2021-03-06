package modelLayer;

import java.io.Serializable;

/**
 * The class Product is a part of the System developed for Vestbjerg
 * Byggecenter. It defines all the traits and knowledge we need to store on each
 * individual product.
 * It implement Serializable in order to be able to save it to a file.
 * 
 */

public class Product implements Serializable
{
	private static final long serialVersionUID = 1L;//is used to identify the state of the Object
	private int threshold; // the minimum of products, once crossed, restock is initiated             
	private int quantity;
	private int discount;
	private float purchasePrice;
	private float salesPrice;
	private String barcode; // unique number for each type of product
	private String name;
	private String description;
	private String group;
	private String location;

	public Product(int threshold, int quantity,  int discount, float purchasePrice, float salesPrice, String barcode, String name, String description,
	                String group, String location)
	{
		this.threshold = threshold;
		this.quantity = quantity;
		this.discount = discount;
		this.purchasePrice = purchasePrice;
		this.salesPrice = salesPrice;
		this.barcode = barcode;
		this.name = name.toLowerCase();
		this.description = description;
		this.group = group;
		this.location = location;
	}

	/**
	 * These are all the getters and setters for the all the fields
	 * 
	 */
	public String getBarcode()
	{
		return barcode;
	}

	public void setBarcode(String barcode)
	{
		this.barcode = barcode;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public int getThreshold()
	{
		return threshold;
	}

	public void setThreshold(int threshold)
	{
		this.threshold = threshold;
	}

	public String getGroup()
	{
		return group;
	}

	public void setGroup(String group)
	{
		this.group = group;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public int getDiscount()
	{
		return discount;
	}
	
	public int getDiscount(int quantity)
	{
		return discount;
	}

	public void setDiscount(int discount)
	{
		this.discount = discount;
	}
	
	public float getPurchasePrice()
	{
		return purchasePrice;
	}

	public void setPurchasePrice(float purchasePrice)
	{
		this.purchasePrice = purchasePrice;
	}

	public float getSalesPrice()
	{
		return salesPrice;
	}

	public void setSalesPrice(float salesPrice)
	{
		this.salesPrice = salesPrice;
	}

	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location = location;
	}

	public void updateThreshold(int threshold)
	{
		this.threshold += threshold;
	}
	
	public void updateQuantity(int quantity)
	{
		this.quantity += quantity;
	}

	/**
	 * Is used to format the way we would display
	 * the product as a String when used in 
	 * the TUI. It shows all fields of the
	 * Product in a comprehensible way.
	 * 
	 */
	@Override
	public String toString()
	{
		return String.format(" (1) Name: %s\n (2) Description: %s\n (3) Group: %s\n (4) Barcode: %s\n (5) Location: %s\n (6) Quantity: %d\n (7) Threshold: %d\n (8) Sales Price: %f\n (9) Purchase Price: %f\n (10) Discount: %d\n",name, description, group, barcode, location, quantity, threshold, salesPrice, purchasePrice, discount);
		
	}
	
	/**
	 * Is used to gather all the fields
	 * in an Array of Strings, where all the
	 * fields are formatted to fit how we want
	 * to showcase everything, such as the 
	 * prices, that are floats. Then the 
	 * Array of Strings is returned.
	 * 
	 */
	public String[] toStrings()
	{
		return new String[] { name, description, group, barcode,
                location, Integer.toString(quantity), Integer.toString(threshold), String.format("%.2f", salesPrice), String.format("%.2f", purchasePrice), Integer.toString(discount)};
	}
	
	/**
	 * Returns specific information
	 * used in the UI formatted and in an
	 * ordered String Array.
	 * This could have been combined with
	 * either of the toString() methods above,
	 * if the methods had been better planned.
	 * 
	 */
	public String[] tableFill()
	{
		String[] data = new String[8];
		data[0] = barcode;
		data[1] = name;
		data[2] = group;
		data[3] = location;
		data[4] = String.format("%.2f", salesPrice);
		data[5] = Integer.toString(quantity);
		data[6] = Integer.toString(discount);
		return data;
	}
}
