package be.kapture.entities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

public class ClothingItemTest {
	
	private ClothingItem item, otherItem;
	
	
	@Before
	public void before() {
		item = new ClothingItem(115, Color.BLUE.toString());
		otherItem = new ClothingItem(115, Color.BLUE.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createClothingItemWithNegativeSize() {
		item = new ClothingItem(-7, Color.BLUE.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createClothingItemWithSizeZero() {
		item = new ClothingItem(0, Color.BLUE.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createClothingItemWithColorNull() {
		item = new ClothingItem(115, null);
	}
	
	@Test
	public void ClothingItemEqualsOtherClothingItem() {
		assertEquals(item, otherItem);
		
		otherItem.setSize(120);
		assertNotEquals(item, otherItem);
		item.setSize(120);
		assertEquals(item, otherItem);
		
		otherItem.setColor(Color.RED.toString());
		assertNotEquals(item, otherItem);
		item.setColor(Color.RED.toString());
		assertEquals(item, otherItem);
	}
}