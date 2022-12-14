package test;

import org.junit.Assert;
import org.junit.Test;

import app.Salable;


public class SalableTest
{
	@Test
	public void testGetName()
	{
		Salable salable = new Salable("name", "description", 1.1, 10);
		
		Assert.assertEquals("name", salable.getName());
		
		salable = new Salable();
		
		Assert.assertEquals("", salable.getName());
		
	}
	
	@Test
	public void testGetDescription()
	{
		Salable salable = new Salable("name", "description", 1.1, 10);
		
		Assert.assertEquals("description", salable.getDescription());
		
		salable = new Salable();
		
		Assert.assertEquals("", salable.getDescription());
		
	}
	
	@Test
	public void testGetPrice()
	{
		Salable salable = new Salable("name", "description", 1.1, 10);
		
		Assert.assertEquals(1.1, salable.getPrice(), .01);
		
		salable = new Salable();
		
		Assert.assertEquals(0, salable.getPrice(), .01);
		
	}
	
	@Test
	public void testGetQuantity()
	{
		Salable salable = new Salable("name", "description", 1.1, 10);
		
		Assert.assertEquals(10, salable.getQuantity());
		
		salable = new Salable();
		
		Assert.assertEquals(0, salable.getQuantity());
		
	}
	
	@Test
	public void testAddQuantity()
	{
		Salable salable = new Salable("name", "description", 1.1, 10);
		
		salable.addQuantity(10);
		
		Assert.assertEquals(20, salable.getQuantity());
		
		salable.addQuantity(-1);
		
		Assert.assertEquals(19, salable.getQuantity());
		
		
	}
	
	@Test
	public void testRemoveQuantity()
	{
		Salable salable = new Salable("name", "description", 1.1, 10);
		
		salable.removeQuantity(5);
		
		Assert.assertEquals(5, salable.getQuantity());
		
		salable.removeQuantity(-5);
		
		Assert.assertEquals(10, salable.getQuantity());
		
	}
	
	@Test
	public void testSetQuantity()
	{
		Salable salable = new Salable("name", "description", 1.1, 10);
		
		salable.setQuantity(100);
		
		Assert.assertEquals(100, salable.getQuantity());
		
	    salable.setQuantity(-5);
	    
	    Assert.assertEquals(-5, salable.getQuantity());
	}
	
	@Test
	public void testCompareTo()
	{
		Salable first = new Salable("Sword", "Made for slashing", 15.9, 15);
		Salable second = new Salable("Sword", "Made for blocking", 52.5, 5);
		
		Assert.assertEquals(-1, first.compareTo(second));
		Assert.assertEquals(1, second.compareTo(first));
		
		second = new Salable("Sword", "Made for slashing", 15.9, 15);
		
		Assert.assertEquals(0, first.compareTo(second));
		
	}
}
