package uiLayer;

import controlLayer.*;
import modelLayer.*;

/**
 * This class is a part of the System for
 * Vestbjerg Byggecenter. It acts as the TUI 
 * the user will interact with, whenever they manage
 * anything regarding orders, offers and regular sales.
 */

import java.util.Scanner;
import java.util.ArrayList;

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
		boolean running = true;
		
		while(running) 
		{
			System.out.println("Please input customer phone number: ");
			int phone = input.nextInt();
			input.nextLine();
			String customerName = orderCtr.findCustomer(phone);
			System.out.println("Creating offer for  " + customerName + ":");
			
			boolean productsAdded = false;
			while(!productsAdded)
			{
				searchForProduct();
				
				System.out.println("Do you wish to add more products to the offer? Y/N");
				String answer = input.nextLine();
				if(answer.equals("N") || answer.toLowerCase().equals("no"))
				{
					productsAdded = true;
				}
			}
		
			System.out.println("Offer summary:");
			System.out.println("Products: ");
			
			ArrayList<OrderLineItem> allProducts = orderCtr.getOrderProducts();
			for(OrderLineItem o : allProducts)
			{
				Product product = o.getProduct();
				String name = product.getName();
				long price = product.getSalesPrice();
				int quantity = o.getQuantity();
				long totalPrice = quantity * price;
				
				System.out.println(name + "				" + price + "kr.");
				System.out.println("");
				if(quantity > 1)
				{
					System.out.println(quantity +  " x " + price +				totalPrice + "kr.");
				}
				
			}
			
		}
		
	}

	private int writeOrderMenu()
	{
		System.out.println("* Main Menu *");
		System.out.println(" (1) Create Offer");
		System.out.println(" (2) Place Order");
		System.out.println(" (3) ");
		System.out.println(" (0) Back");
		System.out.print("\n Choose: \n");

		while (!input.hasNextInt())
		{
			System.out.println("Input should be a number, try again");
			input.nextLine();
		}

		int choice = input.nextInt();
		return choice;
	}
	
	private void searchForProduct() {
		
		System.out.println("Please input product name:");
		String productName = input.nextLine();
		ArrayList<String[]> products = orderCtr.getProducts(productName);
		
		
			System.out.println("Please select a product by number:");
		
			int i = 1;
			for(String[] product: products)
			{
				System.out.println("(" + i + ") Product name: " + product[0]);
				System.out.println("   Description: " + product[1]);
				i++;
			}
		
			int choice = input.nextInt();
			input.nextLine();
			
			System.out.println("Please input quantity: ");
			int quantity = input.nextInt();
			input.nextLine();
			orderCtr.selectProduct(choice - 1, quantity);
		
			String[] chosenProductInfo = products.get(i-1);
			String chosenProductName = chosenProductInfo[0];
			System.out.println(quantity + " of product " + chosenProductName + " has been added to the offer");	
	}
}
