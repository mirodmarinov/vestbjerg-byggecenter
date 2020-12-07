package modelLayer;

/**
 * The class Product is a part of the System developed for Vestbjerg
 * Byggecenter. It defines all the traits and knowledge we need to store on each
 * individual product.
 * 
 */

public class Product
{
	private int threshold; // the minimum of products, once crossed, restock is
	                       // initiated
	private int quantity;
	private int purchasePrice;
	private int salesPrice;
	private int discount;
	private String barcode; // unique number for each type of product
	private String name;
	private String description;
	private String group;
	private String location;

	public Product(int threshold, int quantity, int purchasePrice, int salesPrice, int discount, String barcode, String name, String description,
	                String group, String location)
	{
		this.threshold = threshold;
		this.quantity = quantity;
		this.purchasePrice = purchasePrice;
		this.salesPrice = salesPrice;
		this.discount = discount;
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

	public int getDiscount()
	{
		return discount;
	}

	public void setDiscount(int discount)
	{
		this.discount = discount;
	}
	
	public int getPurchasePrice()
	{
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice)
	{
		this.purchasePrice = purchasePrice;
	}

	public int getSalesPrice()
	{
		return salesPrice;
	}

	public void setSalesPrice(int salesPrice)
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

	/**
	 * 
	 */
	public void updateThreshold()
	{
		// TODO - do the body
	}


}
