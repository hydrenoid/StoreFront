package test;

import org.junit.Assert;
import org.junit.Test;

import app.Health;

public class HealthTest
{
	@Test
	public void testGetHP()
	{
		Health health = new Health("name", "Description", 1.1, 10, 25, true);
		
		Assert.assertEquals(25, health.getHP());
		
		health = new Health();
		
		Assert.assertEquals(0, health.getHP());
	}
	
	@Test
	public void testCheckPermanance()
	{
		Health health = new Health("name", "Description", 1.1, 10, 25, true);
		
		Assert.assertEquals(true, health.checkPermanance());
		
		health = new Health();
		
		Assert.assertEquals(false, health.checkPermanance());
	}
	
	@Test
	public void testToString()
	{
		Health health = new Health("name", "description", 1.1, 10, 25, true);
		
		Assert.assertEquals("Name: name\nDescription: description\nPrice: 1.1\nQuantity: 10\nHP: 25\nPermanence: true", health.toString());
		
		health = new Health();
		
		Assert.assertEquals("Name: \nDescription: \nPrice: 0.0\nQuantity: 0\nHP: 0\nPermanence: false", health.toString());


	}
}
