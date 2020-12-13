package modelLayer;

import java.io.Serializable;

/**
 * The class Product is a part of the System developed for Vestbjerg
 * Byggecenter. It defines all the traits and knowledge we need to store on each
 * individual product.
 * 
 */

public class Product implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int threshold; // the minimum of products, once crossed, restock is
	                       // initiated
	private int quantity;
	private int discount;
	private long purchasePrice;
	private long salesPrice;
	private String barcode; // unique number for each type of product
	private String name;
	private String description;
	private String group;
	private String location;

	public Product(int threshold, int quantity,  int discount, long purchasePrice, long salesPrice, String barcode, String name, String description,
	                String group, String location)
	{
		this.threshold = threshold;
		this.quantity = quantity;
		this.discount = discount;
		this.purchasePrice = purchasePrice;
		this.salesPrice = salesPrice;
		this.barcode = barcode;
		this.name = name;
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

	public int getDiscount(int quantity)
	{
		return quantity > 5 ? discount + 2 : discount;
	}

	public void setDiscount(int discount)
	{
		this.discount = discount;
	}
	
	public long getPurchasePrice()
	{
		return purchasePrice;
	}

	public void setPurchasePrice(long purchasePrice)
	{
		this.purchasePrice = purchasePrice;
	}

	public long getSalesPrice()
	{
		return salesPrice;
	}

	public void setSalesPrice(long salesPrice)
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
	
	@Override
	public String toString()
	{
		return String.format(" (1) Name: %s\n (2) Description: %s\n (3) Group: %s\n (4) Barcode: %s\n (5) Location: %s\n (6) Quantity: %d\n (7) Threshold: %d\n (8) Sales Price: %d\n (9) Purchase Price: %d\n (10) Discount: %d\n",name, description, group, barcode, location, quantity, threshold, salesPrice, purchasePrice, discount);
		//return String.format(" Name: %s\n Description: %s\n Group: %s\n Barcode: %s\n Location: %s\n Quantity: %d\n Threshold: %d\n Sales Price: %d\n Purchase Price: %d\n Discount: %d\n",name, description, group, barcode, location, quantity, threshold, salesPrice, purchasePrice, discount);
	}
	
	
	/*
	public String getParameterList()
	{
		return String.format(" (1) Name: %s\n (2) Description: %s\n (3) Group: %s\n (4) Barcode: %s\n (5) Location: %s\n (6) Quantity: %d\n (7) Threshold: %d\n (8) Sales Price: %d\n (9) Purchase Price: %d\n (10) Discount: %d\n",name, description, group, barcode, location, quantity, threshold, salesPrice, purchasePrice, discount);
	}*/
	
	public String[] toStrings()
	{
		return new String[] { name, description, group, barcode,
                location, Integer.toString(quantity), Integer.toString(threshold), Long.toString(salesPrice), Long.toString(purchasePrice), Integer.toString(discount)};
	}


}
