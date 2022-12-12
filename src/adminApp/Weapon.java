package adminApp;

public class Weapon extends Salable {

	private int damage;
	private String material;
	
	/**
	 * Constructor for a basic initialization of class variables.
	 */
	public Weapon()
	{
		super();
		damage = 0;
		material = "";
	}
	
	/**
	 * Constructor to create Weapon with variables.
	 * 
	 * @param name Name of the weapon.
	 * @param description Summary of what weapon it is.
	 * @param price Price of weapon per quantity.
	 * @param quantity Number of the weapon available.
	 * @param damage How much damage the weapon does.
	 * @param material What material the weapon is made out of.
	 */
	Weapon(String name, String description, double price, int quantity, int damage, String material) {
		super(name, description, price, quantity);
		this.damage = damage;
		this.material = material;
	}
	
	/**
	 * Copy constructor for the weapon.
	 * 
	 * @param another The weapon to be copied from.
	 */
	public Weapon(Weapon another)
	{
		super(another);
		this.damage = another.damage;
		this.material = another.material;
	}
	
	/**
	 * Get the damage of the weapon.
	 * 
	 * @return The damage of the weapon.
	 */
	public int getDamage()
	{
		return this.damage;
	}
	
	/**
	 * Get the material of the weapon.
	 * 
	 * @return Material of the weapon.
	 */
	public String getMaterial()
	{
		return this.material;
	}
	
	/**
	 * Override the toString operator to make it easier to display.
	 */
	public String toString()
	{
		String display = "Name: " + this.getName() + "\nDescription: " + this.getDescription() + "\nPrice: " + this.getPrice() + "\nQuantity: " + this.getQuantity() + "\nDamage: " + this.damage + "\nMaterial: " + this.material;
		return display;
	}
	

}
