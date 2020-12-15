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
	/**
	 * Runs the OrderMenu. Whenever the user enters 0 returns to the main menu.
	 */
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
					orderCtr = new OrderCtr(); // created locally to reset fields
					createOffer();
					break;
				case 2:
					orderCtr = new OrderCtr();
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
		String customerName = findCustomer();
		System.out.println("Creating offer for  " + customerName + ":");

		searchForProduct(); // creates a list of products and stores it in the OrderCtr

		displaySummary(customerName);

		confirm("offer");
	}

	private void createOrder() 
	{

		String customerName = findCustomer();
		System.out.println("Creating order for " + customerName + ":");

		searchForProduct();

		displaySummary(customerName);

		confirm("order");
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
		input.nextLine();
		return choice;
	}
	
	/**
	 * This method allows us to check whether an input is an int, and whether
	 * that int within the limits of what we need.
	 * 
	 * @param min
	 * @param max
	 * @return int
	 */
	private int intInput(int min, int max)
	{
		int choice = intInput();
		while(choice < min || choice > max)
		{
			System.out.println("Input should be no lower than " + min + " and no higher than " + max + ", try again");
			choice = intInput(); // Try again
		}
		
		return choice;
	}
	
	/**
	 * The method is used to ensure proper
	 * input when asking for strings. We use it
	 * to make sure that in specific
	 * cases users are forced to input
	 * more than 2 characters.
	 * 
	 * @return String
	 */
	private String stringInput()
	{
		String outcome = input.nextLine();
		while(outcome.length() < 3)
		{
			System.out.println("Please input more than 2 characters to search.");
			outcome = input.nextLine();
		}
		
		return outcome;
	}

	/**
	 * This method allows us to search for customers based on their phone numbers,
	 * and return their name as a string, in order to use it inside other methods.
	 * 
	 * @return the found customers name
	 */
	private String findCustomer() 
	{
		String customerName = null;
		boolean phoneCorrect = false;
		int phone = 0;
		while(!phoneCorrect)
		{
			System.out.println("Please input customer's phone number: ");
			phone = intInput();

			if(Integer.toString(phone).length() != 8) 
			{	
				System.out.println("Phone number invalid, make sure not to use white spaces or country code.");
				//skips searching for customer
				continue;
			}
			else 
			{
				phoneCorrect = true;
			}
			boolean customerFound = false;
			while(!customerFound)
			{
				try 
				{
					customerName = orderCtr.findCustomer(phone);
					customerFound = true;
				} catch (Exception e) 
				{
					System.out.println(e.getMessage());
				}
			}
		}
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
		boolean productsAdded = false;
		while(!productsAdded)
		{
			String productName = "";
			ArrayList<String[]> products = new ArrayList<>();
			boolean productsFound = false;
			while(!productsFound)
			{
				System.out.println("Please input product name:");
				productName = stringInput();
				
				products = orderCtr.getProducts(productName);
			
				if(products.size() == 0)
				{
					System.out.println("There is no product that contains this sequence of characters: " + productName);
				}
				else
				{
					productsFound = true;
				}
			}
			
			/******************************* Product Selection *******************************/
			System.out.println("Please select a product by number:");
		
			int i = 1;
			for(String[] product: products)
			{
				System.out.println("(" + i + ") Product name: " + product[0]);
				System.out.println("    Description: " + product[1]);
				System.out.println("    Quantity in stock: " + product[2]);
				i++;
			}
		
			int choice = intInput(1, products.size()); // Choice is registered
			
			/******************************* Quantity selection *******************************/
			System.out.println("Please input quantity: ");
			int quantity = intInput(); // Quantity is registered
		
			while(quantity <= 0 || quantity > 1000) //Handles input error when quantity is negative
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
			if(quantity == 69)
			{
				System.out.println("Nice.");
			}
		
			
			orderCtr.selectProduct(choice - 1, quantity); //Chosen product and quantity is passed to ctr
		
			/******************************* Addition confirmation *******************************/
			String[] chosenProductInfo = products.get(choice-1);
			String chosenProductName = chosenProductInfo[0];
			System.out.println(quantity + " of product " +  "'" + chosenProductName + "'" + " has been added");
		
			System.out.println("Do you wish to add more products? Y/N");
			String answer = input.nextLine();
			if(answer.trim().equalsIgnoreCase("N") || answer.trim().equalsIgnoreCase("no"))
			{
				productsAdded = true;
			}
		}
	}

	/**
	 * This method shows a summary of whatever type of sale (offer or order) is
	 * currently stored in the order controller, by retrieving the list of
	 * orderLineItems and the customer that has been saved. The parameter needed for
	 * the method is the customer name, which should already have been identified,
	 * prior to this method.
	 */
	private void displaySummary(String customerName) 
	{
		System.out.println("\nSummary:");
		System.out.println("Customer: " + customerName);
		System.out.println("Products:\n");

		long priceWithoutDiscount = 0;
		//storing a string value of a price
		String temp;
		
		ArrayList<OrderLineItem> allProducts = orderCtr.getOrderProducts();

		for (OrderLineItem o : allProducts) 
		{
			Product product = o.getProduct();
			String name = product.getName();
			long price = product.getSalesPrice();
			int quantity = o.getQuantity();
			long totalPrice = quantity * price; // Total price of the product based on quantity
			priceWithoutDiscount += totalPrice;
			
			temp = String.valueOf(price);
			String formattedPrice = temp.substring(0, temp.length()-1) + "." +  temp.substring(temp.length() - 2, temp.length());
			
			temp = String.valueOf(totalPrice);
			String formattedTotal = temp.substring(0, temp.length()-1) + "." +  temp.substring(temp.length() - 2, temp.length());
			
			System.out.println(name + "				" + formattedPrice + " kr./stk");
			if (quantity > 1) 
			{
				System.out.println(quantity + " x " + formattedPrice + " kr./stk" + "		" + formattedTotal + " kr.");
			}
			System.out.println("");
		}


		temp = String.valueOf(priceWithoutDiscount);
		String withoutDiscount = temp.substring(0, temp.length()-1) + "." +  temp.substring(temp.length() - 2, temp.length());

		System.out.println("Price before discount: " + withoutDiscount + " kr.");
		

		temp = String.valueOf(orderCtr.calculateTotal());
		String withDiscount = temp.substring(0, temp.length()-1) + "." +  temp.substring(temp.length() - 2, temp.length());
			
		System.out.println("Total: " + withDiscount + " kr.");

	}

	/**
	 * This method is used to confirm an order based on the orderLineItems and
	 * customer placed inside the OrderCtr. This is used to finalize the process of
	 * creating an order or offer, and it sends the confirmation to the OrderCtr
	 * that then creates the Order object and adds it to the OrderContainer
	 */
	private void confirm(String type) 
	{
		System.out.println("\nConfirm " + type + "? Y/N");
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