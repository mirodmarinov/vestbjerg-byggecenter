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
	
	public void start()
	{
		//TODO - update
	}
}
