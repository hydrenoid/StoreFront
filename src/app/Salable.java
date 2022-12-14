package app;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

// Jackson Type Info for all Super Classes of a Car
//  Maps the type field to the Super Class types
@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type")
		@JsonSubTypes({ 
		  @Type(value = Armor.class, name = "armor"), 
		  @Type(value = Weapon.class, name = "weapon"),
		  @Type(value = Health.class, name = "health")})
public class Salable implements Comparable<Salable>
{

	private String name;
	private String description;
	private double price;
	private int quantity;
	
	/**
	 * Constructor for base initialization of the Salable.
	 */
	public Salable()
	{
		name = "";
		description = "";
		price = 0;
		quantity = 0;
	}
	
	/**
	 * Constructor with parameters for all the class variables.
	 * 
	 * @param name The name of the Salable.
	 * @param description Summary of what the Salable is.
	 * @param price The price of the Salable per quantity.
	 * @param quantity The number of this item in stock.
	 */
	public Salable(String name, String description, double price, int quantity)
	{
		this.name = name;
		this.description = description;
		this.price = price;
		this.quantity = quantity;
	}
	
	/**
	 * Copy constructor taking in a salable and creating one just like it.
	 * 
	 * @param another The salable to be copied.
	 */
	public Salable(Salable another)
	{
		this.name = another.name;
		this.description = another.description;
		this.price = another.price;
		this.quantity = another.quantity;
	}
	
	/**
	 * Return the name of Salable.
	 * 
	 * @return Return the name of Salable.
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Return description of Salable.
	 * 
	 * @return Return description of Salable.
	 */
	public String getDescription()
	{
		return this.description;
	}
	
	/**
	 * Return price of Salable.
	 * 
	 * @return Return price of Salable.
	 */
	public double getPrice()
	{
		return this.price;
	}
	
	/**
	 * Return quantity of Salable.
	 * 
	 * @return Return quantity of Salable.
	 */
	public int getQuantity()
	{
		return this.quantity;
	}
	
	/**
	 * Add a quantity to an item.
	 * 
	 * @param quantity Number to add to quantity.
	 */
	public void addQuantity(int quantity)
	{
		this.quantity += quantity;
	}
	
	/**
	 * Remove quantity from the Salable.
	 * 
	 * @param quantity The number to remove from quantity.
	 */
	public void removeQuantity(int quantity)
	{
		this.quantity -= quantity;
	}
	
	/**
	 * Take in a number and replace the quantity with that number.
	 * 
	 * @param num The amount to replace the quantity with.
	 */
	public void setQuantity(int num)
	{
		this.quantity = num;
	}

	@Override
	/**
	 * Take in a Salable and compare it with the current one by name, and if they are the same, then by price.
	 * 
	 * @param o This is the Salable to compare to.
	 * @return Return 0 if they are the same, 1 if it is greater than, and -1 if it is less than.
	 */
	public int compareTo(Salable o)
	{
		// if they are not the same return the difference, if they are then check the price
		if (this.name.compareTo(o.getName()) != 0)
		{
			return this.name.compareTo(o.getName());
		}
		else if ((this.price - o.getPrice()) != 0)
		{
			if (this.price > o.getPrice())
			{
				return 1;
			}
			else
			{
				return -1;
			}
		}
		else
		{
			return 0;
		}
		
	}
}
