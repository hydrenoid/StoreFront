package app;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;


public class StoreFront 
{
	private InventoryManager<Salable> manager;
	private Cart<Salable> cart;
	
	/**
	 * Constructor for initializing the manager and the cart.
	 */
	StoreFront()
	{	
		try
		{
			manager = new InventoryManager<Salable>();
			cart = new Cart<Salable>();
		}
		catch(Exception IOException)
		{
			System.out.println("There was a problem creating manager and cart, due to file reading error");
		}

	}
	
	/**
	 * Begin the shopping running many of the main functions of the application.
	 */
	public void beginShopping()
	{
		// Display the name of shop and the welcome message
		System.out.println("-----------SHOP OF THE AGES------------");
		System.out.println("Thank you for coming to our shop, feel free to look around and enjoy the selection, REMEMBER shoplifters get hung on sight!");
		
		// Open a reader for user input
		Scanner read = new Scanner(System.in);
		
		// display inventory then ask user what they want, open loop
		while(true)
		{
			try
			{
				System.out.println("Enter 1 to add to cart, 2 to remove from cart, 3 to complete purchase, 4 to cancel purchase, or 5 to be done");
				int num1 = read.nextInt();
				read.nextLine();
				if(num1 == 1)
				{
					// show available stock from inventory and ask them what they want and how much, if valid, put items into cart
					manager.showStock();
					System.out.println("Enter the name of item you would like to add to cart.");
					String input = read.nextLine();
					
					System.out.println("How many?");
					int num = read.nextInt();
					
					try
					{
						cart.addItem(manager.getProduct(input, num));
					}
					catch(Exception IllegalArgumentException)
					{
						System.out.println("Please enter a valid name and number.\n");
					}
				}
				else if(num1 == 2)
				{
					// show the items in their cart and ask them what they want to get rid of, if valid, take item out of cart
					cart.listItems();
					System.out.println("Enter the name of item you would like to remove from cart.");
					String input = read.nextLine();
					try
					{
						manager.returnProducts(cart.removeItem(input));
					}
					catch(Exception IllegalArgumentException)
					{
						System.out.println("Please enter a valid name of an item in the cart.\n");
					}
				}
				else if(num1 == 3)
				{
					// Show them their items and send message saying completed message
					this.completePurchase();
				}
				else if(num1 == 4)
				{
					// Clear the cart and add all items back to inventory
					this.cancelPurchase();
				}
				else
				{
					break;
				}
			}
			catch(Exception InputMismatchException)
			{
				System.out.println("Enter a valid number or name when prompted.\n");
				read = new Scanner(System.in);
			}
		}
		
		read.close();
	}
	
	/**
	 * Complete the purchase for the customer, clearing the cart.
	 */
	public void completePurchase()
	{
		System.out.println("Thank you for completing your purchase, here is your ordered items:\n");
		cart.listItems();
		cart.clearCart();
	}
	
	/**
	 *  Cancel the purchase by removing all items from cart and putting quantities back into the inventory.
	 */
	public void cancelPurchase()
	{
		System.out.println("Your cart is cleared.");
		manager.restockReturnedCart(cart.clearCart());
	}
}
