package scm.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ProductTest {
	
	Product prodcut;
	
	@Before
	public void setProduct() {
		prodcut = new Product();
	}
	
	@Test
	public void setNameTest() {
		prodcut.setName("A");
		assertEquals("A", prodcut.getName());
	}
	
	@Test
	public void setPriceTest() {
		prodcut.setPrice(100);
		assertEquals(100, prodcut.getPrice());
	}
	
	@Test
	public void setNameAndPrice() {
		prodcut.setName("B");
		prodcut.setPrice(60);
		assertEquals("B", prodcut.getName());
		assertEquals(60, prodcut.getPrice());
		
	}

}
