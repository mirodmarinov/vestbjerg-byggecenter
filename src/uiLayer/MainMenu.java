package uiLayer;

/**
 * This class is a part of the System for
 * Vestbjerg Byggecenter. It acts as the TUI that
 * the user is first greeted by. It redirects the 
 * user to different TUIs based on what they wish to
 * do when interacting with the system.
 *
 */
import modelLayer.*;
import java.util.Scanner;
import java.lang.reflect.Method;
import java.io.*;

public class MainMenu
{
	private Scanner input = new Scanner(System.in);
	private OrderMenu orderUI = new OrderMenu();
	private ProductMenu productUI = new ProductMenu();
	File CONFIG_HOME; // storing the app data folder

	public MainMenu()
	{
		// works on all systems, finds either the APPDATA folder for temporary
		// files or picks user home folder for linux/mac.
		String home = System.getenv("APPDATA");
		if ((home == null) || home.isEmpty()) {
			home = System.getProperty("user.home");
		}

		// creates a folder called VestbjergWMS with a dot in the front which
		// makes it hidden for basic operating systems.
		CONFIG_HOME = new File(home, ".VestbjergWMS").getAbsoluteFile();
		CONFIG_HOME.mkdirs();

		// some changes
	}

	public static void main(String[] args)
	{
		MainMenu mm = new MainMenu();
		// retrieves any saved data
		mm.deserializeClass("modelLayer.ProductContainer");
		mm.deserializeClass("modelLayer.CustomerContainer");
		mm.deserializeClass("modelLayer.OrderContainer");
		mm.start();
		// stores all singleton containers locally
		mm.serializeClass("modelLayer.ProductContainer");
		mm.serializeClass("modelLayer.CustomerContainer");
		mm.serializeClass("modelLayer.OrderContainer");
	}

	/**
	 * The start method is what we use to redirect the user to the TUI's for
	 * managing specific actions inside the system.
	 */
	public void start()
	{
		boolean running = true;

		while (running)
		{
			int choice = writeMainMenu();

			switch (choice) {
			case 1:
				orderUI.start();
				break;
			case 2:
				// this.populateClasses();
				productUI.start();
				break;
			case 3:
				this.populateClasses();
				break;
			case 0:
				System.out.println("Have a nice day");
				running = false;
				break;
			default:
				System.out.println("An error has happened, choice = " + choice);
				break;
			}
		}
	}

	/**
	 * Writes the main menu that is displayed, and return an int, based on the
	 * choice of the user, this int is then used in the start method, to execute
	 * different options inside the switch.
	 * 
	 */
	private int writeMainMenu() {
		System.out.println("* Main Menu *");
		System.out.println(" (1) Order menu");
		System.out.println(" (2) Product menu");
		System.out.println(" (3) Generate test data");
		System.out.println(" (0) Quit");
		System.out.print("\n Choose: \n");

		while (!input.hasNextInt()) {
			System.out.println("Input should be a number, try again");
			input.nextLine();
		}

		int choice = input.nextInt();
		return choice;
	}

	private void serializeClass(String className)
	{

		/*
		 * we use try with resources here, so we make sure that the ObjectOutputStream
		 * object is closed automatically once the try is finished.
		 */

		try (ObjectOutput oos = new ObjectOutputStream(
				new FileOutputStream(new File(CONFIG_HOME.getPath() + File.separator + className + ".ser")))) {
			Class<?> c = Class.forName(className);
			Method method = c.getDeclaredMethod("getInstance");
			System.out.println("Succeeded" + method.invoke(null));
			oos.writeObject(method.invoke(null));
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param className
	 */
	private void deserializeClass(String className)
	{
		File tmpFile = new File(CONFIG_HOME.getPath() + File.separator + className + ".ser");
		if (tmpFile.exists() && tmpFile.isFile()) {
			try (ObjectInput ois = new ObjectInputStream(new FileInputStream(tmpFile))) {
				ois.readObject();
				ois.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * Used for testing if serializable files work and generally generating test
	 * data, allowing us to check whether the rest of the code works.
	 * 
	 */
	public void populateClasses()
	{
		Customer customer1 = new Customer(12658989, 2, "Bob", "Aalborg 12", "Customer");
		Customer customer2 = new Customer(16559898, 0, "Tobias", "Aarhus 50", "Customer");
		CustomerContainer.getInstance().addCustomer(customer1);
		CustomerContainer.getInstance().addCustomer(customer2);
		Product p1 = new Product(10, 50, 0, 5000, 10000, "123456789", "nails", "Huge nails , fix houses",
				"the nail shelf", "3.12.50");
		Product p2 = new Product(10, 50, 0, 6000, 11000, "123456788", "different nails",
				"Bigger nails , used for fixing different houses", "the nail shelf", "3.12.51");
		Product p3 = new Product(10, 10, 0, 2500, 2000, "123456787", "hammer", "A construction hammer", "Tools",
				"3.12.52");

		ProductContainer.getInstance().addProduct(p1);
		ProductContainer.getInstance().addProduct(p2);
		ProductContainer.getInstance().addProduct(p3);

	}

	/*
	 * private void readCustomer()
	 * {
	 * System.out.println(CustomerContainer.getInstance().getCustomer(126589).
	 * getName());
	 * }
	 */

}
