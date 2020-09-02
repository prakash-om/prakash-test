package scm.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CatalogTest {

	Catalog catalog = new Catalog();
	String productId = "A";
	int quantity = 10;
	
	@Test
	public void checkHashMap() {
		assertTrue(catalog.map != null);
	}

	@Test
	public void initailSizeofMap() {
		assertTrue(catalog.map.size() > 0);
	}
	
	@Test
	public void getProductOrderTest() {
		ProductOrder po = catalog.getProductOrder(productId, quantity);
		assertEquals(po.product.name, productId);
		assertEquals(quantity, po.productQuantity);
	}
}
