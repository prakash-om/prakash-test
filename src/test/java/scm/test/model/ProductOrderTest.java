package scm.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class ProductOrderTest {
	
	ProductOrder product;
	Catalog catalog = new Catalog();
	
	String prodId = "A";
	String prodId2 = "B";
	
	@Before
	public void setProductOrder() {
		product = catalog.getProductOrder(prodId, 10);
	}
	
	@Test
	public void productTest() {
		assertEquals(prodId, product.getProduct().getName());
	}
	
	@Test
	public void productQuantityTest() {
		assertEquals(10, product.getProductQuantity());
	}
	
	@Test
	public void productPrice() {
		assertEquals(50, product.getProduct().getPrice());
	}
	
	
	

}
