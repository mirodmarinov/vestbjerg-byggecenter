package uiLayer;


import java.util.Scanner;

import controlLayer.*;

public class ProductMenu {
	
	private Scanner input = new Scanner(System.in);
	private ProductCtr productCtr;
	
	public ProductMenu(){
		productCtr = new ProductCtr();
	}
	
	public void start()
	{
		boolean running = true;

		while (running)
		{
			System.out.println("* Order Menu *");
			System.out.println(" (1) Create product");
			System.out.println(" (2) Find product");
			System.out.println(" (3) Update product information");
			System.out.println(" (4) Delete product");
			System.out.println(" (0) Back");
			System.out.print("\n Choose: \n");

			int choice = intInput();

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
		//TODO write this
	}
	
	private void readProduct()
	{
		//TODO write this
	}
	
	private void updateProduct()
	{
		//TODO write this
	}
	
	private void deleteProduct()
	{
		//TODO write this
	}
	
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
}
