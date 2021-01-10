package modelLayer;

import java.util.Calendar;
import java.util.Date;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * This class defines orders in the System for
 * Vestbjerg Byggecenter. It defines all the elements
 * of knowledge that is necessary to manage orders.
 * It implement Serializable in order ot be able to save it to a file.
 */
public class Order implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private static int orderNumberGenerator = 0;
	private int orderNumber; // The unique identifier
	private int discount;
	private long totalPrice;
	private Calendar expirationDate; //and Order object is order if expirationDate is null, if it isnt, it is and offer
	private Calendar purchaseDate;
	private String status; // The status of the sales process, is it still just an offer or how far along has it come
	private String delivery;
	private Customer customer;
	private ArrayList<OrderLineItem> products;
	
	
	public Order(Customer customer, ArrayList<OrderLineItem> products) 
	{
		orderNumber = ++orderNumberGenerator;
		this.customer = customer;
		this.products = products;
		expirationDate = null;
	
		//TODO automatically add purchaseDate, status, delivery, totalPrice, automatically generate expirationDate
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

	public long getTotalPrice() 
	{
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) 
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

	//TODO fix the date return
	public String getPurchaseDate() 
	{
		Date date = Calendar.getInstance().getTime(); 
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
		String strDate = dateFormat.format(date);
		return strDate;
	}

	public void setPurchaseDate(Calendar purchaseDate) 
	{
		this.purchaseDate = purchaseDate;
	}
	
	//TODO fix the date return
	public String getExpirationDate() 
	{
		Date date = Calendar.getInstance().getTime(); 
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
		String strDate = dateFormat.format(date);
		return strDate;
	}

	public void setExpirationDate(Calendar expirationDate) 
	{
		this.expirationDate = expirationDate;
	}

	public Calendar calculateExpirationDate() 
	{
		Calendar generateExpirationDate = Calendar.getInstance();
		generateExpirationDate.roll(Calendar.MONTH, true);
		return generateExpirationDate;
	}
	
	public void generatePurchaseDate() 
	{
		purchaseDate = Calendar.getInstance();
	}
	
	/** This method creates an array list of string arrays
	 * 
	 * 
	 * 
	 * @return
	 */
	public ArrayList<String[]> toStrings()
	{
		ArrayList<String[]> allInfo = new ArrayList<String[]>();
		
		String[] customerInfo = new String[4];
		customerInfo[0] = customer.getName();
		customerInfo[1] = customer.getGroup();
		customerInfo[2] = String.valueOf(customer.getPhone());
		customerInfo[3] = customer.getAddress();
		//Information about the customer
		allInfo.add(customerInfo);
		
		for(OrderLineItem o: products)
		{
			//we need 6 slots but the last one is calculated later in the gui
			String[] productInfo = new String[5];
			productInfo[0] = o.getProduct().getBarcode();
			productInfo[1] = o.getProduct().getName();
			productInfo[2] = String.valueOf(o.getProduct().getSalesPrice());
			productInfo[3] = String.valueOf(o.getQuantity());
			productInfo[4] = String.valueOf(o.getProduct().getDiscount());
			//Information about all the products
			allInfo.add(productInfo);
		}
		
		String[] orderInfo = new String[1];
		
		orderInfo[0] = String.valueOf(getTotalPrice());
		//Total price
		allInfo.add(orderInfo);
		
		return allInfo;
	}
	
	public String[] searchBar()
	{
		String[] data = new String[5];
		data[0] = customer.getName();
		data[1] = getPurchaseDate();
		data[2] = getStatus();
		data[3] = getExpirationDate();
		data[4] = Long.toString(getTotalPrice());
		return data;
	}
}
