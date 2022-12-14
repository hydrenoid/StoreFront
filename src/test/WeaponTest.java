package test;

import org.junit.Assert;
import org.junit.Test;

import app.Weapon;

public class WeaponTest
{
	@Test
	public void testGetDamage()
	{
		Weapon weapon = new Weapon("name", "Description", 1.1, 10, 25, "Bronze");
		
		Assert.assertEquals(25, weapon.getDamage());
		
		weapon = new Weapon();
		
		Assert.assertEquals(0, weapon.getDamage());
	}
	
	@Test
	public void testGetMaterial()
	{
		Weapon weapon = new Weapon("name", "Description", 1.1, 10, 25, "Bronze");
		
		Assert.assertEquals("Bronze", weapon.getMaterial());
		
		weapon = new Weapon();
		
		Assert.assertEquals("", weapon.getMaterial());
	}
	
	@Test
	public void testToString()
	{
		Weapon weapon = new Weapon("name", "description", 1.1, 10, 25, "Bronze");
		
		Assert.assertEquals("Name: name\nDescription: description\nPrice: 1.1\nQuantity: 10\nDamage: 25\nMaterial: Bronze", weapon.toString());
		
		weapon = new Weapon();
		
		Assert.assertEquals("Name: \nDescription: \nPrice: 0.0\nQuantity: 0\nDamage: 0\nMaterial: ", weapon.toString());
	}
}
