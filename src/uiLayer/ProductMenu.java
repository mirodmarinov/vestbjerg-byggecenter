package uiLayer;


import java.util.ArrayList;
import java.util.Scanner;

import controlLayer.*;

public class ProductMenu {
	
	private Scanner input = new Scanner(System.in);
	private ProductCtr productCtr;
	
	public ProductMenu(){
		productCtr = new ProductCtr();
	}
	
	/**
	 * Runs the ProductMenu. Whenever the user enters 0 returns to the main menu.
	 */
	public void start()
	{
		boolean running = true;
		int choice;
		while (running)
		{
			System.out.println("* Product Menu *");
			System.out.println(" (1) Create product");
			System.out.println(" (2) Find product");
			System.out.println(" (3) Update product information");
			System.out.println(" (4) Delete product");
			System.out.println(" (0) Back");
			System.out.print("\n Choose: \n");

			choice = intInput();

			switch (choice)
			{
				case 1:
					createProduct();
					break;
				case 2:
					readProduct();
					break;
				case 3:
					updateProduct();
					break;
				case 4:
					deleteProduct();
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
	
	private void createProduct()
	{
		//TODO check if user communication has a good form
		int threshold,quantity,purchasePrice,salesPrice,discount;
		String barcode,name,description,group,location;
		//********************** Get name **********************\\
		System.out.println("Please enter the name of the product");
		name = input.nextLine();
		while (name.length() < 3)
		{
			System.out.println("The name should we longer than 2 characters! Please try an another one!");
			name = input.nextLine();
		}
		
		//********************** Get description **********************\\
		System.out.println("Please enter the description of the product");
		description = input.nextLine();
		while (description.length() == 0)
		{
			System.out.println("The desciption shouldnt be empty! Please try an another one!");
			description = input.nextLine();
		}
		
		//********************** Get group **********************\\
		System.out.println("Please enter the group of the product");
		group = input.nextLine();
		while (group.length() == 0)
		{
			System.out.println("The group shouldnt be empty! Please try an another one!");
			group = input.nextLine();
		}
		
		//********************** Get barcode **********************\\
		System.out.println("Please enter the barcode of the product");
		barcode = input.nextLine();
		while (barcode.length() == 13)
		{
			System.out.println("The barcode has to be 13 characters! Please try an another one!");
			barcode = input.nextLine();
		}
		
		//********************** Get location **********************\\
		System.out.println("Please enter the location of the product");
		location = input.nextLine();
		while (location.length() == 0)
		{
			System.out.println("The location shouldnt be empty! Please try an another one!");
			location = input.nextLine();
		}
		
		
		//********************** Get quantity **********************\\
		System.out.println("Please enter the quantity of the product");
		quantity = intInput();
		while (quantity == 0)
		{
			System.out.println("The quantity shouldn't zero! Please try an another one!");
			quantity = intInput();
		}
		
		
		//********************** Get threshold **********************\\
		System.out.println("Please enter the threshold of the product");
		threshold = intInput();
		while (threshold == 0)
		{
			System.out.println("The threshold shouldn't zero! Please try an another one!");
			threshold = intInput();
		}		

		
		//********************** Get salesPrice **********************\\
		System.out.println("Please enter the saleprice of the product");
		salesPrice = intInput();
		while (salesPrice == 0)
		{
			System.out.println("The salesPrice shouldn't zero! Please try an another one!");
			salesPrice = intInput();
		}
		
		
		//********************** Get purchasePrice **********************\\
		System.out.println("Please enter the purchaseprice of the product");
		purchasePrice = intInput();
		while (purchasePrice == 0)
		{
			System.out.println("The purchasePrice shouldn't zero! Please try an another one!");
			purchasePrice = intInput();
		}
		
		
		//********************** Get discount **********************\\
		System.out.println("Please enter the discount of the product");
		discount = intInput();
		while (discount < 0 || discount > 100)
		{
			System.out.println("Invalid discount! Please try an another one!");
			discount = intInput();
		}
		//
		
		if (productCtr.createProduct(threshold,quantity,discount,purchasePrice,salesPrice,barcode,name,description,group,location))
		{
			System.out.println("Product creation successful!");
		}
		else
		{
			System.out.println("Product creation error!");
		}
	}
	
	private void readProduct()
	{
		int choice = getProduct();	
		if (choice != -6)
		{
		System.out.println(productCtr.getInfo(choice-1));
		}
	}
	
	/*
	 * Runs the getProduct method, then prints the found product. After the 
	 * user selects a parameter of the product and enters the value of it
	 * it send the values to the controllayer so it can process the
	 * information. If the input is invalid asks for the data again.
	 * The method can be leaved by typing 0.
	 * 
	 */
	private void updateProduct()
	{
		int choice = getProduct();
		if (choice != -6)
		{
		System.out.println(productCtr.getParameters(choice-1));
		
		boolean running = true;
		int element;
		while (running)
		{
			System.out.println("Please choose an element you want to edit");
			element = intInput();
			while (element <= 0 || element > 10)
			{
				System.out.println("Please choose a number from the list!");
				element = intInput();
			}
			String updated = "No";
			while (updated.equals("No"))
			{
				System.out.println("Please enter the new value or type 0 to return to the main menu without saving!");
				String value = input.nextLine();
				if (value.equals("0"))
				{
					return;
				}
				while (value.length() == 0)
				{
					System.out.println("It cannot be empty!");
					value = input.nextLine();
				}
				if (!productCtr.updateParameter(choice-1, element-1, value))
				{
					System.out.println("Invalid value!");
					
				}
				else
				{
					updated = "Yes";
				}
			}
			System.out.println("If you want to edit an another parameter of the product type 'y' or 'yes' otherwise type 'n' or 'no' ");
			String answer = "run";
			while (answer.equals("run"))
			{
				answer = input.nextLine();
				if (answer.trim().equalsIgnoreCase("N") || answer.trim().equalsIgnoreCase("no"))
				{
					running = false;
					answer = "notRun";
					System.out.println("Returning to the menu:");
				}
				
				else if (answer.trim().equalsIgnoreCase("Y") || answer.trim().equalsIgnoreCase("yes"))
				{
					answer = "notRun";
				}
				
				else
				{
					System.out.println("Unknown command!");
				}
			}
		}
		}
		
	}
	
	private void deleteProduct()
	{
		int choice = getProduct();
		if (choice != -6)
		{
			if(productCtr.deleteProduct(choice-1))
			{
				System.out.println("Product successfully deleted!");
			}
			else
			{
				System.out.println("Product delete error!");
			}
		}
	}
	
	/**
	 * Used for asking for Integer input from the scenner.
	 * Whenever the input is not string it asks again.
	 * @return
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
	 * The methods readProduct, updateProduct and deleteProduct uses it.
	 * Used for searching for product and printing the whole returned array.
	 * It also captures the choice of the user, which element they want to display.
	 * @return
	 */
	private int getProduct()
	{
		
		int choice = 0;
		int place = 0;
		String name;
		ArrayList<String[]> data;
		do
		{
		System.out.println("Please enter the name of the product or type 0 to return to the menu:");
		name = input.nextLine();
		if (name.equals("0"))
		{
			return  -6; //if the user inputs 0 it returns to the menu returning -6 so it won't display anyting more
		}
		while (name.length() < 3)
		{
			System.out.println("Please enter a name with has more than 2 characters!");
			name = input.nextLine();
		}
		data = productCtr.getProductInfo(name);
		System.out.println("Please select an product from the list:");

		
		place = 0;
		for (String[] element : data)
		{
			System.out.print(""+(++place)+")");
			System.out.println("Name of the product: " + element[0] );
			System.out.println("Description: " + element[1] );
			
		}
		
		}
		while(data.size() == 0);
		
		
		System.out.println("Choice: ");
		choice = intInput();
		while (choice <= 0 || choice > place)
		{
			System.out.println("Please enter a number between 1 and " + place + "!");
			choice = input.nextInt();
		}
		return choice;
	}
}
