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
	private OrderCtr orderCtr;

	public OrderMenu()
	{
	}

	public void start() 
	{
		boolean running = true;

		while (running) 
		{
			System.out.println("* Order Menu *");
			System.out.println(" (1) Create Offer");
			System.out.println(" (2) Place Order");
			System.out.println(" (3) ");
			System.out.println(" (0) Back");
			System.out.print("\n Choose: \n");

			int choice = intInput();

			switch (choice) 
			{
				case 1:
					createOffer();
					break;
				case 2:
					createOrder();
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
		orderCtr = new OrderCtr(); //Creating the orderCtr, in that way we wipe all the data in the controller
		
		String customerName = "";
		customerName = findCustomer();
		System.out.println("Creating order for  " + customerName + ":");

		if(searchForProduct(true))
		{

		displaySummary(customerName);

		confirm("offer");
		}
	}

	public void createOrder() 
	{
		orderCtr = new OrderCtr();
		
		String customerName = ""; //Creating the orderCtr, in that way we wipe all the data in the controller
		customerName = findCustomer();
		System.out.println("Creating order for  " + customerName + ":");

		if(searchForProduct(true))
		{

		displaySummary(customerName);

		confirm("order");
		}
		
	}

	/**
	 * This method returns the int we use to handle user input errors when we are
	 * searching for ints, and they input Strings instead.
	 * 
	 * @return int
	 */
	private int intInput() 
	{
		while (!input.hasNextInt()) 
		{
			System.out.println("Input should be a number, try again");
			input.nextLine();
		}

		int choice = input.nextInt();
		return choice;
	}

	/**
	 * This method allows us to search for customers based on their phone numbers,
	 * and return their name as a string, in order to use it inside other methods.
	 * 
	 * @return the found customers name
	 */
	private String findCustomer() 
	{
		String customerName = "";
		int phone;
		do 
		{
			
			do 
			{
				System.out.println("Please input customer's phone number or write 0 to return to the menu.");
				phone = intInput();
				if (phone == 0)
					{
						break; //breaks the while loop and returns to the order menu.
					}
				if(Integer.toString(phone).length() != 8) 
					{
						System.out.println("Phone number invalid, make sure not to use spaces or country code.");
					}
			}
			while (customerName.equals(""));
			
			
			if (!customerName.equals(""))
			{
					System.out.println("Bent");
				try 
					{
						return orderCtr.findCustomer(phone);
					} 
				catch (Exception e) 
					{
						System.out.println(e.getMessage());
					}
				
			}
		}
		while(phone != 0);
		
	
		return "";
	}	

	/**
	 * This method allows us to search for products based
	 * on their names and add them to the list
	 * of orderProducts inside the order controller.
	 * 
	 */
	private boolean searchForProduct(boolean isOrder) 
	{			
		boolean productsAdded = false;
		String productName;
		ArrayList<String[]> products = new ArrayList<>();
		while(!productsAdded)
		{
			do
				{
				System.out.println("Please input product name or type 0 to return to the main menu");
				productName = input.nextLine();
				if (productName.equals("0"))
				{
					break;
				}
				
				products = orderCtr.getProducts(productName);
			
				if(products.size() == 0)
				{
					System.out.println("Product(s) not found");
				}		
				}
			while (products.size() == 0);
			
			if (products.size() == 0)
			{
				break; //return to the main menu
			}
			
			//********************** Selecting the product from the list ***********************\\
			System.out.println("Please select a product by number:");
		
			int i = 1;
			for(String[] product: products)
			{
				System.out.println("(" + i + ") Product name: " + product[0]);
				System.out.println("   Description: " + product[1]);
				System.out.println("   Quantity: " + product[2]);
				i++;
			}
		
			int choice = intInput(); // Choice is registered
		
			while(choice <= 0 && choice > i-1) //Handles input error when choice is invalid or negative
			{
				System.out.println("Please input one of the possible choices: ");
				choice = intInput();
			}
			
			//********************** Input quantity **********************\\
			System.out.println("Please input quantity: ");
			int quantity = intInput(); // Quantity is registered
			
			String orderNotPassed = "Passed";


			while(quantity <= 0 || quantity > 100000) //Handles input error when quantity is negative
			{
				if(quantity <= 0)
				{
					System.out.println("Please input positive quantity: ");
					quantity = intInput();
				}
				else
				{
					System.out.println("Really? Are you trying to buy " + quantity + " products?");
					System.out.println("Stop trying to break our code and input serious quantity: ");
					quantity = intInput();
				}
			}
			if (isOrder) //if true, it checks if the quantity reaches the stock quantity or not
			{
					
				while (Integer.valueOf(products.get(choice)[2]) < choice)
				{
					System.out.println("The quantity more than the quantity on the stock!\n"
							+ "Please input an another quantity or type 0 to return the menu: ");
					quantity = intInput();
					if (quantity == 0)
					{
						orderNotPassed = "NotPassed";
						break;
							
					}
				}
			}
			if(quantity == 69)
			{
				System.out.println("Nice.");
			}
			
			//********************** Saving the product **********************\\
			if (orderNotPassed.equals("Passed"))
			{
				
			
				orderCtr.selectProduct(choice - 1, quantity); //Chosen product and quantity is passed to ctr
			
				String[] chosenProductInfo = products.get(i-1);
				String chosenProductName = chosenProductInfo[0];
				System.out.println(quantity + " of product " + chosenProductName + " has been added");
				
				//********************** Check if the the user wants to add more product **********************\\
				String leaveTheWhileLoop = "Stay";
				while(leaveTheWhileLoop.equals("Stay"))
				{
					System.out.println("Do you wish to add more products? Y/N");
					String answer = input.nextLine();
					if(answer.trim().equalsIgnoreCase("N") || answer.trim().equalsIgnoreCase("no"))
					{
						productsAdded = true;
						leaveTheWhileLoop = "DontStay";
					}
					else if (answer.trim().equalsIgnoreCase("Y") || answer.trim().equalsIgnoreCase("yes"))
					{
						leaveTheWhileLoop = "DontStay";
					}
					else 
					{
						System.out.println("Invalid command! Please enter one of the options!");
					}
				
				}
			}
			
			
		}
		return true;
	}

	/**
	 * This method shows a summary of whatever type of sale (orfer or order) is
	 * currently stored in the order controller, by retrieving the list of
	 * orderLineItems and the customer that has been saved. The parameter needed for
	 * the method is the customer name, which should already have been identified,
	 * prior to this method.
	 */
	private void displaySummary(String customerName) 
	{
		System.out.println("Summary:");
		System.out.println("Customer: " + customerName);
		System.out.println("Products:");
		
		long priceWithoutDiscount = 0;
		ArrayList<OrderLineItem> allProducts = orderCtr.getOrderProducts();

		for (OrderLineItem o : allProducts) 
		{
			Product product = o.getProduct();
			String name = product.getName();
			long price = product.getSalesPrice();
			int quantity = o.getQuantity();
			long totalPrice = quantity * price; // Total price of the product based on quantity
			priceWithoutDiscount += totalPrice;

			System.out.println(name + "				" + price + "kr.");
			if (quantity > 1) 
			{
				System.out.println(quantity + " x " + price + totalPrice + "kr.");
			}
			System.out.println("");
		}

		System.out.println("Price before discount " + priceWithoutDiscount + "kr.");
		System.out.println("Total " + orderCtr.calculateTotal() + "kr.");
	}

	/**
	 * This method is used to confirm an order based on the orderLineItems and
	 * customer placed inside the OrderCtr. This is used to finalise the process of
	 * creating an order or offer, and it sends the confirmation to the OrderCtr
	 * that then creates the Order object and adds it to the OrderContainer
	 */
	private void confirm(String type) 
	{
		System.out.println("\n Confirm " + type + "? Y/N");
		String answer = input.nextLine();
		if (answer.trim().equalsIgnoreCase("Y") || answer.trim().equalsIgnoreCase("yes")) 
		{
			if (type.equals("offer")) 
			{
				orderCtr.createOffer();
			} 
			else 
			{
				orderCtr.createOrder();
			}

			System.out.println("The " + type + " has succesfully been created, you will now be returned to the Order Menu.");
		} 
		else 
		{
			System.out.println("The " + type + " was rejected, you will now be returned to the Order Menu.");
		}
	}
}
