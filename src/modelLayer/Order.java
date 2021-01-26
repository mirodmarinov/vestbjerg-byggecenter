package modelLayer;

import java.util.Calendar;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * This class defines orders in the System for
 * Vestbjerg Byggecenter. It defines all the elements
 * of knowledge that is necessary to manage orders.
 * It implement Serializable in order to be able to save it to a file.
 */
public class Order implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int orderNumber; // The unique identifier
	private int discount;
	private float totalPrice;
	private Calendar expirationDate; //and Order object is offer if expirationDate is null, if it isnt, it is and offer
	private Calendar purchaseDate;
	private String status; // The status of the sales process, is it still just an offer or how far along has it come
	private String delivery;
	private Customer customer;
	private ArrayList<OrderLineItem> products;
	
	
	public Order(Customer customer, ArrayList<OrderLineItem> products) 
	{
		this.customer = customer;
		this.products = products;
		expirationDate = null;
		totalPrice = calculateTotal();
	}
	
	/**
 	* The following methods are the getters 
 	* and setters for all the fields.
 	*/
	public String getStatus() 
	{
		return status;
	}

	public boolean setStatus(String status) 
	{
		this.status = status;
		return true;
	}

	public int getDiscount() 
	{
		return discount;
	}

	public void setDiscount(int discount) 
	{
		this.discount = discount;
	}

	public String getDelivery() 
	{
		return delivery;
	}

	public void setDelivery(String delivery) 
	{
		this.delivery = delivery;
	}

	public float getTotalPrice() 
	{
		return totalPrice;
	}

	public void setTotalPrice(float totalPrice) 
	{
		this.totalPrice = totalPrice;
	}

	public Customer getCustomer() 
	{
		return customer;
	}

	public void setCustomer(Customer customer) 
	{
		this.customer = customer;
	}

	public ArrayList<OrderLineItem> getProducts() 
	{
		return products;
	}

	public void setProducts(ArrayList<OrderLineItem> products) 
	{
		this.products = products;
	}

	public int getOrderNumber() 
	{
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) 
	{
		this.orderNumber = orderNumber;
	}

	/**
	 * Converts the purchase date to string
	 * and returns it.
	 */
	public String getPurchaseDate() 
	{
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String date = "...";
		if(purchaseDate != null) {
			date = dateFormat.format(purchaseDate.getTime());
		}
		return date;
	}

	public void setPurchaseDate(Calendar purchaseDate) 
	{
		this.purchaseDate = purchaseDate;
	}
	
	/**
	 * Converts the expiration date to string
	 * and returns it.
	 */
	public String getExpirationDate() 
	{
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		String date = "...";
		if(status.equalsIgnoreCase("Pending")) {
			date = dateFormat.format(expirationDate.getTime());
		}
		return date;
	}

	public void setExpirationDate(Calendar expirationDate) 
	{
		this.expirationDate = expirationDate;
	}
	
	public Calendar calculateExpirationDate() 
	{
		Calendar generateExpirationDate = Calendar.getInstance();
		generateExpirationDate.roll(Calendar.MONTH, true);
		expirationDate = generateExpirationDate; // saves it to the field
		return generateExpirationDate;
	}
	
	public void generatePurchaseDate() 
	{
		purchaseDate = Calendar.getInstance();
	}
	
	/** This method creates an array list of string arrays
	 * containing information about the order,
	 * it's customer, and all of the
	 * orderlineItems in it.
	 */
	public ArrayList<String[]> toStrings()
	{
		ArrayList<String[]> allInfo = new ArrayList<String[]>();
		
		String[] customerInfo = new String[5];
		customerInfo[0] = customer.getName();
		customerInfo[1] = customer.getGroup();
		customerInfo[2] = String.valueOf(customer.getPhone());
		customerInfo[3] = customer.getAddress();
		customerInfo[4] = String.valueOf(customer.getDiscount());
		//Information about the customer
		allInfo.add(customerInfo);
		
		for(OrderLineItem o: products)
		{
			//we need 6 slots but the last one is calculated later in the GUI
			String[] productInfo = new String[5];
			productInfo[0] = o.getProduct().getBarcode();
			productInfo[1] = o.getProduct().getName();
			productInfo[2] = String.valueOf(o.getProduct().getSalesPrice());
			productInfo[3] = String.valueOf(o.getQuantity());
			productInfo[4] = String.valueOf(o.getProduct().getDiscount());
			//Information about all the products
			allInfo.add(productInfo);
		}
		
		String[] orderInfo = new String[6];
		
		orderInfo[0] = String.valueOf(getTotalPrice());
		orderInfo[1] = String.valueOf(getPurchaseDate());
		orderInfo[2] = getStatus();
		orderInfo[3] = String.valueOf(getExpirationDate());
		orderInfo[4] = String.valueOf(getDiscount());
		orderInfo[5] = getDelivery();
		//Information about the order
		allInfo.add(orderInfo);
		
		return allInfo;
	}
	
	/**
	 * Returns the 5 information what the GUI need to display.
	 * 
	 * @return
	 */
	public String[] searchBar()
	{
		String[] data = new String[6];
		
		data[0] = Integer.toString(getOrderNumber());
		data[1] = customer.getName();
		data[2] = getPurchaseDate();
		data[3] = getStatus();
		data[4] = getExpirationDate();
		data[5] = String.format("%.2f", getTotalPrice());
		return data;
	}
	
	public float calculateTotal() {
		int totalWithoutDiscount = 0;
		int totalWithDiscount = 0;
		for (OrderLineItem p : products)
		{
			Product product = p.getProduct();
			int quantity = p.getQuantity();
			totalWithoutDiscount += product.getSalesPrice() * quantity;
			totalWithDiscount += product.getSalesPrice() * ((float)(100 - product.getDiscount(quantity))/100) * quantity;
		}
		if (customer != null)
		{
			totalWithDiscount *= ((float)(100 - customer.getDiscount())/100);
		}
		return (float)totalWithDiscount / (float)totalWithoutDiscount < 0.8 ? (float)(0.8 * totalWithoutDiscount) : totalWithDiscount;
	}
}
