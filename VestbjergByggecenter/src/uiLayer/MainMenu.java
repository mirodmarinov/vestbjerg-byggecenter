package uiLayer;

/**
 * This class is a part of the System for
 * Vestbjerg Byggecenter. It acts as the TUI that
 * the user is first greeted by. It redirects the 
 * user to different TUIs based on what they wish to
 * do when interacting with the system.
 *
 */

import java.util.Scanner;

public class MainMenu 
{
	private Scanner input = new Scanner(System.in);
	private OrderMenu orderUI = new OrderMenu();

	public MainMenu()
	{
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
}
