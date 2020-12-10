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
		//TODO discuss whether the ctr is created here
		boolean running = true;
		
		while(running) 
		{
			String customerName = findCustomer();
			System.out.println("Creating offer for  " + customerName + ":");
			
			boolean productsAdded = false;
			while(!productsAdded)
			{
				searchForProduct();
				
				System.out.println("Do you wish to add more products to the offer? Y/N");
				String answer = input.nextLine();
				if(answer.trim().equalsIgnoreCase("N") || answer.trim().equalsIgnoreCase("no"))
				{
					productsAdded = true;
				}
			}
		
			System.out.println("Offer summary:");
			System.out.println("Customer: " + customerName);
			System.out.println("Products: ");
			
			
			long priceWithoutDiscount = 0;
			ArrayList<OrderLineItem> allProducts = orderCtr.getOrderProducts();
			
			for(OrderLineItem o : allProducts)
			{
				Product product = o.getProduct();
				String name = product.getName();
				long price = product.getSalesPrice();
				int quantity = o.getQuantity();
				long totalPrice = quantity * price; //Total price of the product based on quantity
				priceWithoutDiscount += totalPrice;
				
				System.out.println(name + "				" + price + "kr.");
				if(quantity > 1)
				{
					System.out.println(quantity +  " x " + price +				totalPrice + "kr.");
				}
				System.out.println("");
				
			}
			
			System.out.println("Price before discount " + priceWithoutDiscount + "kr.");
			System.out.println("Total " + orderCtr.calculateTotal() + "kr.");
			
			System.out.println("\n Confirm offer? Y/N");
			String answer = input.nextLine(); 
			if(answer.trim().equalsIgnoreCase("Y") || answer.trim().equalsIgnoreCase("yes"))
			{
				orderCtr.createOffer();
				System.out.println("Offer has succesfully been created, you will now be returned to the Order Menu.");
				running = false;
			}
			else
			{
				//TODO figure out if other things should happen if the offer is rejected.
				System.out.println("Offer rejected, you will now be returned to the Order Menu.");
				running = false;
			}
		}
	}

	/**
	 * This method returns the int we use to choose
	 * where the user is going in the OrderMenu.
	 * It also prints and handles choices in general.
	 * @return
	 */
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
	
	/**
	 * This method allows us to search for customers
	 * based on their phone numbers,
	 * and return their name as a string, in order to use
	 * it inside other methods.
	 * 
	 * @return the found customers name
	 */
	private String findCustomer() 
	{
		
		System.out.println("Please input customer phone number: ");
		int phone = input.nextInt();
		input.nextLine();
		String customerName = orderCtr.findCustomer(phone);
		return customerName;
	}
	/**
	 * This method allows us to search for products based
	 * on their names and add them to the list
	 * of orderProducts inside the order controller.
	 * 
	 */
	private void searchForProduct() 
	{
		
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
