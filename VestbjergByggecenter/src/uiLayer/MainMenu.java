package uiLayer;

/**
 * This class is a part of the System for
 * Vestbjerg Byggecenter. It acts as the TUI that
 * the user is first greeted by. It redirects the 
 * user to different TUIs based on what they wish to
 * do when interacting with the system.
 *
 */
import modelLayer.ProductContainer;
import modelLayer.CustomerContainer;
import modelLayer.OrderContainer;
import java.util.Scanner;
import java.io.*;

public class MainMenu 
{
	private Scanner input = new Scanner(System.in);
	private OrderMenu orderUI = new OrderMenu();
	
	File CONFIG_HOME; //storing the app data folder

	public MainMenu()
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
	
	/**
	 * The start method is what we use
	 * to redirect the user to the TUI's for managing
	 * specific actions inside the system.
	 */
	public void start() 
	{
        boolean running = true;
        
        while (running) 
		{
        	int choice = writeMainMenu();
        	
            switch (choice) 
			{
                case 1:
                	orderUI.start();
                	break;
                case 2:
                	System.out.println("Not yet possible");
                	break;
                case 3:
                	System.out.println("Not yet possible");
                	break;
                case 0:
                	System.out.println("Have a nice day");
                	running = false;
                	break;
                default:
                  System.out.println("An error has happened, choice = "+ choice);
                  break;
            }
        }
    }

	/**
	 * Writes the main menu that is displayed, and return an int,
	 * based on the choice of the user, this int
	 * is then used in the start method, to execute different options
	 * inside the switch.
	 * 
	 */
    private int writeMainMenu() 
	{
        System.out.println("* Main Menu *");
        System.out.println(" (1) Order menu");
        System.out.println(" (2) ");
        System.out.println(" (3) ");
        System.out.println(" (0) Quit");
        System.out.print("\n Choose: \n");
        
        while (!input.hasNextInt()) 
		{
            System.out.println("Input should be a number, try again");
            input.nextLine();
        }

        int choice = input.nextInt();
        return choice;
    }


	public void serializeClass(String className)
	{

		/*
		 *  we use try with resources here, so we make sure that the
		 *  ObjectOutputStream object is closed automatically once the try is
		 *  finished.
		 */
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(CONFIG_HOME.getPath() + File.separator + className + ".ser"))))
		{
			oos.writeObject(ProductContainer.getInstance());
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}

	}
	
	/**
	 * 
	 * @param className
	 */
	public void deserializeClass(String className)
	{
		
		try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(new File(CONFIG_HOME.getPath() + File.separator + className + ".ser"))))
		{
			oos.readObject();
		}
		catch (Exception e)
		{
		}

	}

}
