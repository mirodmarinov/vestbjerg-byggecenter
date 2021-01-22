package controlLayer;

import modelLayer.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * This class is a part of the System developed for Vestbjerg Byggecenter. 
 * It acts as a controller for all of the order objects.
 */

public class OrderCtr
{
	private CustomerCtr customerCtr = new CustomerCtr();
	private ProductCtr productCtr = new ProductCtr();
	private Customer customer;
	private ArrayList<OrderLineItem> orderProducts;
	private ArrayList<Product> foundProducts;

	public OrderCtr()
	{
		orderProducts = new ArrayList<OrderLineItem>();
		foundProducts = new ArrayList<Product>();
	}

	public ArrayList<OrderLineItem> getOrderProducts()
	{
		return orderProducts;
	}

	/**
	 * This method returns the customers name and also stores the customer
	 * Object in the customer field of this class.
	 * Throws and exception if the customer can't be find
	 * 
	 * @param phone
	 * @return String of customer name
	 */
	public String findCustomer(int phone) throws CustomerNotFoundException
	{
		// TODO - check if correct
		Customer customer = customerCtr.getCustomer(phone);
		this.customer = customer;
		if (customer == null)
		{
			throw new CustomerNotFoundException("No customer exists with this phone number: " + phone + "\n Make sure to write the phone number without the country code.");
		}
		return customer.getName();
	}
	
	public String[] getCustomerInfo(String stringPhone)// throws CustomerNotFoundException
	{
		int phone = 0;
		try
		{
			phone = Integer.parseInt(stringPhone);
		}
		catch(Exception e)
		{
			return null;
		}
		
		customer = customerCtr.getCustomer(phone);
		if (customer == null)
		{
			return new String[] {"", ""};
			//throw new CustomerNotFoundException("No customer exists with this phone number: " + phone + "\n Make sure to write the phone number without the country code.");
		}
		
		String[] info = new String[2];
		info[0] = customer.getName();
		info[1] = customer.getGroup();
		
		return info;
	}

	/**
	 * This method calls the getProducts() in the product controller and creates
	 * a list of products that are stored in the foundProducts field. Then an
	 * Array of Strings containing info such as the name an description of the
	 * product is created. This can then be used in the UI layer.
	 * 
	 * @param name
	 * @return ArrayList of String Arrays containing product information
	 */
	public ArrayList<String[]> getProducts(String name)
	{
		foundProducts = productCtr.getProducts(name);
		ArrayList<String[]> allProductsInfo = new ArrayList<String[]>();

		for (Product product : foundProducts)
		{
			String[] productInfo = new String[6];
			productInfo[0] = product.getName();
			productInfo[1] = product.getDescription();
			productInfo[2] = String.valueOf(product.getQuantity());
			productInfo[3] = product.getBarcode();
			productInfo[4] = String.valueOf(product.getDiscount());
			productInfo[5] = String.format("%.2f", product.getSalesPrice());
			allProductsInfo.add(productInfo);
		}
		
		return allProductsInfo;
	}

	/**
	 * This method allows us to have a product selected from the foundProducts
	 * list, and then based on the place in the list add it to the orderProducts
	 * list along with the quantity of the product that is being
	 * ordered/offered. If the product and quantity is successfully added to the
	 * orderProducts list, the method returns true, if not false.
	 * 
	 * @param placeInList
	 * @param quantity
	 * @return true or false
	 */
	public boolean selectProduct(int placeInList, int quantity)
	{
		Product product = foundProducts.get(placeInList);
		/**
		 * Creating a copy of the product so we don't save the reference of the product but an actualy copy
		 * of it inside the OrderLineItem. In that way we can prevent some errors in the future.
		 */
		Product productcopy = new Product(product.getThreshold(), product.getQuantity(), product.getDiscount(0),
				product.getPurchasePrice(), product.getSalesPrice(), product.getBarcode(), product.getName(),
				product.getDescription(), product.getGroup(), product.getLocation());
		OrderLineItem orderLineItem = new OrderLineItem(productcopy, quantity);
		return orderProducts.add(orderLineItem);
	}

	/**
	 * This method uses the fields customer and orderProducts to create an order
	 * Object and add it to our collection of orders. It also creates an
	 * expiration date and automatically sets status to pending. This is an
	 * offer because its status is set to pending.
	 * 
	 * @return true or false
	 */
	public boolean createOffer()
	{
		Order offer = new Order(customer, orderProducts);
		offer.calculateExpirationDate();
		offer.setStatus("pending");
		//offer.setTotalPrice((float)calculateTotal());
		offer.setDiscount(customer.getDiscount());

		/*
		 * Here we update the threshold of the product, to make sure that the
		 * quantity in stock matches the possible order size.
		 */
		for (OrderLineItem p : orderProducts)
		{
			p.getProduct().updateThreshold(p.getQuantity());
		}

		return OrderContainer.getInstance().addOrder(offer);
	}

	/**
	 * This method creates an Order, by passing the customer and the list of
	 * products and setting the status of the order to confirmed so indicate
	 * that it is an order.
	 * 
	 * 
	 */
	public boolean createOrder()
	{
		Order order = new Order(customer, orderProducts);
		order.setStatus("confirmed");
		//order.setTotalPrice((float)calculateTotal());
		order.setDiscount(customer.getDiscount());
		order.generatePurchaseDate();
		/*
		 * Here we update the stock, by changing the quantity of the product in
		 * stock based on the quantity of the orderline item.
		 */
		for(OrderLineItem p : orderProducts)
		{
			Product product = productCtr.getProduct(p.getProduct().getBarcode());
			product.updateQuantity(p.getQuantity() * (-1));
		}

		return OrderContainer.getInstance().addOrder(order);
	}

	/**
	 * This method allows us to calculate the price for the offer/order.
	 * 
	 * @return total cost of order/offer
	 */
	public float calculateTotal()
	{
		int totalWithoutDiscount = 0;
		int totalWithDiscount = 0;
		for (OrderLineItem p : orderProducts)
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
	
	/**
	 * This method is used for generating invoice. We save the whole invoice inside a single string
	 * and we return that to the upper layer.
	 * 
	 * @param orderNumber
	 * @return returns a String of the invoice to the menu
	 */
	public String generateInvoice(int orderNumber)
	{
		Order order = OrderContainer.getInstance().generateInvoice(orderNumber);
		String invoice, productInfo = "";
		ArrayList<OrderLineItem> products = order.getProducts();
		// Adding all the products into the productInfo String so it's easier to combine with the whole invoice.
		for (OrderLineItem e : products) 
		{
			productInfo += "\n "+ e.getQuantity()+"x " +
		e.getProduct().getName().substring(0,1).toUpperCase() + e.getProduct().getName().substring(1) + ": " +
		(e.getProduct().getSalesPrice()*e.getQuantity()) +" DKK - " + e.getProduct().getDiscount(0) + "%" + ": "
		+ (int)(e.getProduct().getSalesPrice() * ((float)(100-e.getProduct().getDiscount(0))/100))*e.getQuantity();
		}
		
		//Saving every data into the invoice String
		invoice = "\t  Vestbjerg Byggecenter" + "\n\t    Imaginepark 1536"  + "\n\t*************************" +
				"\n\t   Phone: 52 96 52 63" + "\n\t*************************" +
				"\n\nName: "+order.getCustomer().getName() + "\nDate: " + order.getPurchaseDate() +
				"\nProducts: "+productInfo + "\n\nPrice before discount: " + (float)(order.getTotalPrice() / ((float)(100-order.getDiscount())/100)) + 
				"\nDiscount: "+ order.getDiscount() + " %" +
				"\n\nTotal: " + order.getTotalPrice(); 
		
		return invoice;
	}
	
	/**This method find an offer by its number and returns its details for gui purposes
	 * the customer and orderProducts are again assigned to their fields to make sure any changes are saved
	 * 
	 * @param orderNumber
	 * @return returns strings for gui purposes
	 */
	public ArrayList<String[]> findOrder(int orderNumber)
	{
		Order order = OrderContainer.getInstance().findOrder(orderNumber);
		if (order == null)
		{
			return null;
		}
		customer = order.getCustomer();
		orderProducts = order.getProducts();
		
		return order.toStrings();
	}
	
	/** This method changes the status of the offer to "confirmed" making it an order
	 * 
	 * @param orderNumber
	 * @return returns success
	 */
	public boolean confirmOffer(int orderNumber)
	{
		return OrderContainer.getInstance().confirmOffer(orderNumber);
	}
	
	
	/**
	 * This method is for the fillTable method in the OrderPanel. Returns x amount of order's data
	 * so we can write it to the table in the UI layer.
	 * @param index 
	 * 
	 * @param orderAmount
	 * @return
	 */
	

	public ArrayList<String[]> getOrders(int index)
	{
		int orderAmount = 0;
		ArrayList<String[]> returnValue = new ArrayList<>();
		ArrayList<Order> orders = OrderContainer.getInstance().getOrders();
		
		if (orders.size() == 0)
		{
			return returnValue;
		}
		if ((int)(Math.floor(orders.size() / 50))+1 > index)
		{
			orderAmount = 50;
		}
		else if ((int)(Math.floor(orders.size() / 50))+1 == index)
		{
			orderAmount = (int)(orders.size()%50);
		}
		else
		{
			return returnValue;
		}
		for(int e = (index-1) * 50 + orderAmount - 1; e >= (index - 1) * 50; e--)
		{
			returnValue.add(orders.get(e).searchBar());
		}
		
		Collections.reverse(orders);
		Collections.reverse(returnValue);
		
		return returnValue;
	}
	
	/**
	 * This method is used only for the searchField in the GUI. We had to implement a new
	 * method because the findOffer method doesn't return every necessary information.
	 * 
	 * @param id
	 * @return If order is found returns the data from it, otherwise returns null
	 */
	public ArrayList<String[]> searchBar(String id)
	{
		ArrayList<Order> orders = OrderContainer.getInstance().getOrders();
		ArrayList<String[]> data = new ArrayList<>();

		if (orders == null)
		{
			return data;
		}
		
		for (Order order : orders)
		{
			if(Integer.toString(order.getOrderNumber()).contains(id))
			{
				data.add(order.searchBar());
			}
		}
		return data;
	}
	
	public ArrayList<String[]> fillTable(int index, String productName)
	{
		int productAmount = 0;
		ArrayList<String[]> returnValue = new ArrayList<>();
		ArrayList<String[]> products = getProducts(productName);	
		if (products.size() == 0)
		{
			return returnValue;
		}
		if ((int)(Math.floor(products.size() / 50))+1 > index)
		{
			productAmount = 50;
		}
		else if ((int)(Math.floor(products.size() / 50))+1 == index)
		{
			productAmount = (int)(products.size()%50);
		}
		else
		{
			return returnValue;
		}
		
		for (int e = (index-1)*50; e < (index-1)*50+productAmount; e++)
		{
			returnValue.add(products.get(e));	
		}
		
		return returnValue;
	}
	
	/**
	 * We remove a product from our arraylist of products
	 * chosen for the order.
	 * @param barcode
	 */
	public void removeProductFromList(String barcode) {
		Iterator<OrderLineItem> it = orderProducts.iterator();
		while(it.hasNext()) {
			OrderLineItem orderLineItem = it.next();
			Product productToRemove = orderLineItem.getProduct();
			if(productToRemove.getBarcode().equals(barcode))
			{
				it.remove();
				break;
			}
		}
	}
	
	/**
	 * Deletes the customer
	 * 
	 */
	public void clearCustomer()
	{
		customer = null;
	}
	
	/**
	 * This method checks the quantity on the stock. Returns the elements with less amount of than the desired input 
	 * 
	 * @return
	 */
	public ArrayList<String> checkAmounts()
	{
		ArrayList<String> errors = new ArrayList<>();
		for (OrderLineItem order : orderProducts)
		{
			if (order.getProduct().getQuantity() < order.getQuantity())
			{
				errors.add(order.getProduct().getName());
			}
		}
		return errors;
	}
	
	/**
	 * This method search is the order by barcode and update it's quantity within the orderLineItem.
	 * Used for updating the quantity throught the createOrderPanel
	 * 
	 * @param barcode
	 * @param quantity
	 */
	
	public void changeOrderQuantity(String barcode, String quantity)
	{
		for (OrderLineItem order : orderProducts)
		{
			if (order.getProduct().getBarcode().equals(barcode))
			{
				order.setQuantity(Integer.parseInt(quantity));
			}
		}
	}
}
