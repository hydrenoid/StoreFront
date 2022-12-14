package test;

import org.junit.Assert;
import org.junit.Test;

import app.Armor;

public class ArmorTest
{
	@Test
	public void testGetArmorValue()
	{
		Armor armor = new Armor("name", "Description", 1.1, 10, 25, "Bronze");
		
		Assert.assertEquals(25, armor.getArmorValue());
		
		armor = new Armor();
		
		Assert.assertEquals(0, armor.getArmorValue());
	}
	
	@Test
	public void testGetMaterial()
	{
		Armor armor = new Armor("name", "Description", 1.1, 10, 25, "Bronze");
		
		Assert.assertEquals(25, armor.getArmorValue());
		
		armor = new Armor();
		
		Assert.assertEquals(0, armor.getArmorValue());
	}
	
	@Test
	public void testToString()
	{
		Armor armor = new Armor("name", "description", 1.1, 10, 25, "Bronze");
		
		Assert.assertEquals("Name: name\nDescription: description\nPrice: 1.1\nQuantity: 10\nArmorValue: 25\nMaterial: Bronze", armor.toString());
		
		armor = new Armor();
		
		Assert.assertEquals("Name: \nDescription: \nPrice: 0.0\nQuantity: 0\nArmorValue: 0\nMaterial: ", armor.toString());

	}
}
