package uiLayer;

import controlLayer.*;

/**
 * This class is a part of the System for
 * Vestbjerg Byggecenter. It acts as the TUI 
 * the user will interact with, whenever they manage
 * anything regarding orders, offers and regular sales.
 */

import java.util.Scanner;

public class OrderMenu
{
	private Scanner input = new Scanner(System.in);
	private OrderCtr orderCtr = new OrderCtr();

	public OrderMenu()
	{
	}

	public void start()
	{
		boolean running = true;

		while (running)
		{
			int choice = writeOrderMenu();

			switch (choice)
			{
				case 1:
					createOffer();
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
					System.out.println("An error has happened, choice = " + choice);
					break;
			}
		}
	}

	private void createOffer()
	{
		
	}

	private int writeOrderMenu()
	{
		System.out.println("* Main Menu *");
		System.out.println(" (1) Order menu");
		System.out.println(" (2) ");
		System.out.println(" (3) ");
		System.out.println(" (0) Quit");
		System.out.print("\n Choose:");

		while (!input.hasNextInt())
		{
			System.out.println("Input should be a number, try again");
			input.nextLine();
		}

		int choice = input.nextInt();
		return choice;
	}
}
