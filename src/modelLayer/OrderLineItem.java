package modelLayer;

import java.io.Serializable;

/**
 * This Class is a part of the Vestbjerg Byggecenter System.
 * It contains a product and quantity, and allow us to easily 
 * construct orders containing products and the amount
 * of the products we want to sell. This helps us calculate
 * bulk buy discounts as well.
 *
 */
public class OrderLineItem implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Product product;
	private int quantity;
	
	
	public OrderLineItem(Product product, int quantity)
	{
		this.product = product;
		this.quantity = quantity;
	}

	public Product getProduct()
	{
		return product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}
	
	/**
	 * This method calculates the discount of the product
	 * based on the quantity it is ordered in,
	 * to allow bulk buy.
	 * 
	 * @return the discount triggered by quantity
	 */
	public int calculateDiscount() 
	{
		//TODO write method
		return 0;	
	}
}
