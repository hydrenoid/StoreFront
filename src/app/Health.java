package app;

public class Health extends Salable{
	
	private int hp;
	private boolean isPermanent;
	
	/**
	 * Constructor for basic initialization.
	 */
	public Health()
	{
		super();
		hp = 0;
		isPermanent = false;
	}
	
	/**
	 * Create health from variables.
	 * 
	 * @param name Name of the health.
	 * @param description Summary of what the health does.
	 * @param price How much health costs per quantity.
	 * @param quantity How many health's are for sale.
	 * @param hp Amount of health given.
	 * @param isPermanent Whether the health is permanent or not.
	 */
	public Health(String name, String description, double price, int quantity, int hp, boolean isPermanent) {
		super(name, description, price, quantity);
		this.hp = hp;
		this.isPermanent = isPermanent;
	}
	
	/**
	 * Copy constructor to get a new Health object.
	 * 
	 * @param another Health to copy from.
	 */
	public Health(Health another)
	{
		super(another);
		this.hp = another.hp;
		this.isPermanent = another.isPermanent;
	}
	
	/**
	 * Get the hp of the health.
	 * 
	 * @return This healths hp.
	 */
	public int getHP()
	{
		return this.hp;
	}
	
	/**
	 * Get whether it is permanent or not.
	 * 
	 * @return This healths permanent status.
	 */
	public boolean checkPermanance()
	{
		return this.isPermanent;
	}
	
	/**
	 * Override the toString operator to ease printing.
	 */
	public String toString()
	{
		String display = "Name: " + this.getName() + "\nDescription: " + this.getDescription() + "\nPrice: " + this.getPrice() + "\nQuantity: " + this.getQuantity() + "\nHP: " + this.hp + "\nPermanence: " + this.isPermanent;
		return display;
	}
	
	

}
