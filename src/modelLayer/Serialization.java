package modelLayer;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Serialization {
	
	private File CONFIG_HOME; // storing the app data folder link
    private static Serialization uniqueInstance = new Serialization();
	
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
				ois.readObject();
				ois.close();
				returnValue = true;
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
		
		
		/**OrderLineItem oli2 = new OrderLineItem(p1, 8);
		ArrayList<OrderLineItem> oliarray2 = new ArrayList<>();
		oliarray2.add(oli2);
		Order order2 = new Order(customer2, oliarray2);
		order2.calculateExpirationDate();
		order2.setStatus("confirmed");
		order2.setTotalPrice(8000);
		order2.setDiscount(0);
		order2.generatePurchaseDate();
		OrderContainer.getInstance().addOrder(order2);*/
		
		OrderLineItem oli = new OrderLineItem(p3, 5);
		ArrayList<OrderLineItem> oliarray = new ArrayList<>();
		oliarray.add(oli);
		for(int i = 0; i < 69; i++) {
			Order order = new Order(customer1, oliarray);
			order.calculateExpirationDate();
			order.setStatus("pending");
			order.setTotalPrice((float) 1000.00);
			order.setDiscount(5);
			
			OrderContainer.getInstance().addOrder(order);
		}
		
		ProductContainer.getInstance().addProduct(p1);
		ProductContainer.getInstance().addProduct(p2);
		ProductContainer.getInstance().addProduct(p3);

	}
	

}
