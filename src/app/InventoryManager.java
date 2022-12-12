package app;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;


public class InventoryManager<E extends Salable>
{
	private ArrayList<E> stock;
	
	/**
	 * Basic constructor
	 */
	InventoryManager()
	{
	}
	
	/**
	 * Creates a file service object and goes and reads the inventory json and sets it as the current inventory.
	 * 
	 * @throws IOException If there is a problem opening the file.
	 */
	public void pullInventoryJson() throws IOException
	{
		FileService<Salable> fileService = new FileService<Salable>();
		this.stock = (ArrayList<E>) fileService.readInventoryJson("inventory.json");
	}
	
	/**
	 * Turns the salable ArrayList into a basic array so it can be jsonofied and sent from the server.
	 * 
	 * @return The array of salables of current inventory.
	 */
	public Salable[] getInventory()
	{
		Salable[] temp = new Salable[this.stock.size()];
		for(int i = 0; i < this.stock.size(); i++)
		{
			temp[i] = this.stock.get(i);
		}
		return temp;
	}
	
	/**
	 * Send inventory to json file service to be saved and overwrite old inventory json.
	 * 
	 * @throws IOException When error opening file.
	 */
	public void saveInventory() throws IOException
	{
		FileService<Salable> fileService = new FileService<Salable>();
		fileService.saveToFile("inventory.json", getInventory());
	}
	
	/**
	 * Sort the array using Comparable interface then simply run a for loop to reverse the order.
	 */
	public void sortDecending()
	{
		// sort the array Ascending then run a for loop to reverse the array
		this.stock.sort(null);
		ArrayList<E> revArrayList = new ArrayList<E>();
        for (int i = this.stock.size() - 1; i >= 0; i--) {
 
            // Append the elements in reverse order
            revArrayList.add(this.stock.get(i));
        }
		this.stock = revArrayList;
	}
	
	/**
	 * Use the Comparable interface to sort the stock by name and then by price.
	 */
	public void sortAscending()
	{
		this.stock.sort(null); 
	}
	
	/**
	 * Check if the product is in the list first, if it isn't then add it.
	 * 
	 * @param product Is the product to add to the inventory.
	 */
	public void addProduct(E product)
	{
		if(this.stock == null)
		{
			stock.add(product);
		}
		else if(this.isInStock(product))
		{
			System.out.println(product.getName() + " is already in inventory, try adding quantity");
		}
		else
		{
			stock.add(product);
		}
		
	}
	
	/**
	 * Iterate through the list to see if the object is in the list.
	 * 
	 * @param product Is the item to check if it is in stock.
	 * @return True if it is in the list, False if not.
	 */
	public boolean isInStock(E product)
	{
		for(int i = 0; i < this.stock.size(); i++)
		{
			if (product.getName() == stock.get(i).getName())
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Find the product by the name and remove it from the list.
	 * 
	 * @param product Product to remove from the ArrayList.
	 */
	public void removeProduct(String product)
	{
		for(int i = 0; i < this.stock.size(); i++)
		{
			if (product == this.stock.get(i).getName())
			{
				this.stock.remove(i);
			}
		}
	}
	
	
	/**
	 * Look for the product and if found and the number is a valid quantity, create a temp object depending on what kind of salable product it is
	 * and then set quantities of the temp and product in the list to the specified quantities and return the temp object.
	 * 
	 * @param name Name of the product to take out of ArrayList.
	 * @param num The quantity to reduce the item by.
	 * @return Return a new (copied) version of the salable from the list.
	 * @throws IllegalArgumentException if invalid input is sent to function
	 */
	public E getProduct(String name, int num) throws IllegalArgumentException
	{
		for(int i = 0; i < this.stock.size(); i++)
		{
			if( name.toLowerCase().equals(this.stock.get(i).getName().toLowerCase()) && num <= this.stock.get(i).getQuantity() )
			{
				Salable temp = null;
				if(this.stock.get(i).getClass() == Health.class)
				{
					temp = new Health((Health) this.stock.get(i));
				}
				else if(this.stock.get(i).getClass() == Armor.class)
				{
					temp = new Armor((Armor) this.stock.get(i));
				}
				else if(this.stock.get(i).getClass() == Weapon.class)
				{
					temp = new Weapon((Weapon) this.stock.get(i));
				}
				
				this.stock.get(i).removeQuantity(num);
				temp.setQuantity(num);
				return (E) temp;
			}
			else if( (name.toLowerCase().equals(this.stock.get(i).getName().toLowerCase())) && (num > this.stock.get(i).getQuantity() || num < 1))
			{
				System.out.println("Choose a number that is between 1 and the quantity of item");
				return null;
			}
		}
			System.out.println("This product does not exist. Check spelling.");
			return null;
	}
	
	/**
	 * Take in a product and add the quantity of the object back to the product in the list.
	 * 
	 * @param product Salable to be put back into inventory.
	 */
	public void returnProducts(E product)
	{
		for(int i = 0; i < this.stock.size(); i++)
		{
			if (product.getName().toLowerCase().equals(this.stock.get(i).getName().toLowerCase()) )
			{
				this.stock.get(i).addQuantity(product.getQuantity());
				return;
			}
		}
		System.out.println("Item does not exist.");
	}
	
	/**
	 * Display all items in stock.
	 */
	public void showStock()
	{
		this.sortAscending();
		System.out.println("----------STOCK----------");
		for(int i = 0; i < this.stock.size(); i++)
		{
			System.out.println(this.stock.get(i).toString());
			System.out.println("-----------------------------------------------------");
		}
	}
	
	/**
	 * Take in the list from the cart and add the quantity back to the inventory.
	 * 
	 * @param returned ArrayList of salables that need to be added back into inventory.
	 */
	public void restockReturnedCart(ArrayList<E> returned)
	{
		for (int i = 0; i < returned.size(); i++)
		{
			for (int j = 0; j < this.stock.size(); i++)
			{
				if(returned.get(i).getName() == this.stock.get(j).getName())
				{
					this.stock.get(j).addQuantity(returned.get(i).getQuantity());
				}
			}
		}
	}
	
	/**
	 * Replace the current inventory with a new one.
	 * 
	 * @param inventory Is the inventory to replace the current one.
	 */
	public void setInventory(ArrayList<E> inventory)
	{
		this.stock = inventory;
	}
}
