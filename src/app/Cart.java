package app;
import java.util.ArrayList;

public class Cart<E extends Salable>
{
	
	private ArrayList<E> products;
	
	/**
	 * Constructor for initializing the ArrayList.
	 */
	Cart()
	{
		this.products = new ArrayList<E>();
	}
	
	/**
	 * Iterate through the products and display items.
	 */
	public void listItems()
	{
		System.out.println("---------CART ITEMS---------");
		if(this.products != null)
		{
			for (int i = 0; i < products.size(); i++)
			{
				System.out.println(this.products.get(i).toString());
				System.out.println("-----------------------------------------------------");
			}
		}
	}
	
	/**
	 * Iterate through list adding the price up and then return the total price.
	 * 
	 * @return Total price of all products * quantities in cart.
	 */
	public float getTotalPrice()
	{
		float tp = 0;
		for (int i = 0; i < products.size(); i++)
		{
			tp += (this.products.get(i).getPrice() * this.products.get(i).getQuantity());
		}
		return tp;
	}
	
	/**
	 * Take an item in and add it to the list if it is not already in it.
	 * 
	 * @param product Salable to be added into the cart.
	 */
	public void addItem(E product)
	{
		if(product == null)
		{
			return;
		}
		for (int i = 0; i < products.size(); i++)
		{
			if(product.getName().equals(this.products.get(i).getName()))
			{
				this.products.get(i).addQuantity(product.getQuantity());
				return;
			}
		}
		this.products.add(product);
	}
	
	/**
	 * Find a product by its name and remove the product if it exists.
	 * 
	 * @param product Item to be removed from the carts products.
	 * @return The product that was removed.
	 * @throws IllegalArgumentException if the entered word does not match one in the cart.
	 */
	public E removeItem(String product) throws IllegalArgumentException
	{
		for (int i = 0; i < products.size(); i++)
		{
			if(product.toLowerCase().equals(this.products.get(i).getName().toLowerCase()))
			{
				E temp = this.products.get(i);
				this.products.remove(i);
				return temp;
			}
		}
		
		System.out.println("Item does not exist. (CART)");
		return null;
	}
	
	/**
	 * Clear the list of products and return the contents of the cart to add back to inventory.
	 * 
	 * @return The ArrayList of Salables being removed.
	 */
	public ArrayList<E> clearCart()
	{
		ArrayList<E> temp = this.products;
		this.products.clear();
		return temp;
		
	}

}
