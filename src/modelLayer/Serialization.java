package modelLayer;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Serialization {
	
	private File CONFIG_HOME; // storing the app data folder link
    private static Serialization uniqueInstance = new Serialization();
    OrderContainer oldOrderC;
	OrderContainer newOrderC;
	
	private Serialization()
	{
		// works on all systems, finds either the APPDATA folder for temporary
		// files or picks user home folder for linux/mac.
		String home = System.getenv("APPDATA");
		if ((home == null) || home.isEmpty())
		{
			home = System.getProperty("user.home");
		}

		// creates a folder called VestbjergWMS with a dot in the front which
		// makes it hidden for basic operating systems.
		CONFIG_HOME = new File(home, ".VestbjergWMS").getAbsoluteFile();
		CONFIG_HOME.mkdirs();

	}
	
    
    public static Serialization getInstance()
    {
        return uniqueInstance;
    }
	

	/**
	 * Serializes (saves to a file) a Singleton container's instance.
	 * @param className the name of the class to be serialized. used as a file name
	 */
	public boolean serializeClass(String className)
	{

		/*
		 * we use try with resources here, so we make sure that the
		 * ObjectOutputStream object is closed automatically once the try is
		 * finished.
		 */

		try (ObjectOutput oos = new ObjectOutputStream(
		                new FileOutputStream(new File(CONFIG_HOME.getPath() + File.separator + className + ".ser"))))
		{
			Class<?> c = Class.forName(className);
			Method method = c.getDeclaredMethod("getInstance");
			oos.writeObject(method.invoke(null));
			oos.flush();
			oos.close();
			return true;
		}
		catch (Exception e)
		{
			return false;
		}

	}

	/**
	 * Deserializes(retrieves from file) a saved Serialized singleton container
	 * @param className the name of the class to be retrieved.
	 */
	public boolean deserializeClass(String className)
	{
		
		boolean returnValue = false;
		File tmpFile = new File(CONFIG_HOME.getPath() + File.separator + className + ".ser");
		if (tmpFile.exists() && tmpFile.isFile())
		{
			try (ObjectInput ois = new ObjectInputStream(new FileInputStream(tmpFile)))
			{
				if (!className.contains("Order"))
				{
					ois.readObject();
					ois.close();
					returnValue = true;
				}
				else
				{
					newOrderC = OrderContainer.getInstance();
					oldOrderC = (OrderContainer) ois.readObject();
					ArrayList<Order> orders = oldOrderC.getOrders();
					for (Order order: orders)
					{
						Customer customer = CustomerContainer.getInstance().getCustomer(order.getCustomer().getPhone());
						Order o = new Order(customer, order.getProducts());
						if (order.getStatus().equals("confirmed"))
						{
							o.setStatus("confirmed");
							o.setDiscount(customer.getDiscount());
							o.generatePurchaseDate();
						}
						else
						{
							o.calculateExpirationDate();
							o.setStatus("pending");
							o.setDiscount(customer.getDiscount());
						}
						newOrderC.addOrder(o);
					}
					OrderContainer.setInstance(newOrderC);
//					OrderContainer c = OrderContainer.getInstance();
//					ProductContainer productC = ProductContainer.getInstance();
//					CustomerContainer customerC = CustomerContainer.getInstance();
//					ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				    ObjectOutputStream oos = new ObjectOutputStream(baos);
//
//
//				    oos.writeObject(newOrderC);
//
//				    oos.flush();
//				    oos.close();
//
//				    InputStream is = new ByteArrayInputStream(baos.toByteArray());
//					ObjectInputStream ois2 = new ObjectInputStream(is);
//					ois2.readObject();
//					ois2.close();
//					OrderContainer orderC = OrderContainer.getInstance();
					System.out.println();
				}
				
			}
			catch (Exception e)
			{
			}
		}
		
		return returnValue;
	}
	
	/**
	 * Populates all containers with dummy data for testing and exploratory purposes. 
	 */
	public static void populateClasses()
	{
		Customer customer1 = new Customer(12658989, 5, "Bob", "Aalborg 12", "Customer");
		Customer customer2 = new Customer(16559898, 0, "Frank", "Aarhus 50", "Customer");
		CustomerContainer.getInstance().addCustomer(customer1);
		CustomerContainer.getInstance().addCustomer(customer2);
		Product p1 = new Product(10, 50, 0, 5000, 1000, "123456789", "nails", "Huge nails , fix houses",
		                "the nail shelf", "3.12.50");
		Product p2 = new Product(10, 50, 15, 6000, 1100, "123456788", "different nails",
		                "Bigger nails , used for fixing different houses", "the nail shelf", "3.12.51");
		Product p3 = new Product(10, 10, 0, 2500, 2000, "123456787", "hammer", "A construction hammer", "Tools",
		                "3.12.52");
		
		OrderLineItem oli = new OrderLineItem(p3, 5);
		OrderLineItem oli2 = new OrderLineItem(p1, 2);
		OrderLineItem oli3 = new OrderLineItem(p2, 4);
		ArrayList<OrderLineItem> oliarray = new ArrayList<>();
		oliarray.add(oli);
		oliarray.add(oli2);
		oliarray.add(oli3);
		for(int i = 0; i < 30; i++) {
			Order order = new Order(customer1, oliarray);
			order.calculateExpirationDate();
			order.setStatus("pending");
			order.setDiscount(5);
			OrderContainer.getInstance().addOrder(order);
			
			Order order2 = new Order(customer2, oliarray);
			order2.generatePurchaseDate();
			order2.setStatus("confirmed");
			OrderContainer.getInstance().addOrder(order2);
			
		}
		
		ProductContainer.getInstance().addProduct(p1);
		ProductContainer.getInstance().addProduct(p2);
		ProductContainer.getInstance().addProduct(p3);

	}
	

}
