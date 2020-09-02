package scm.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CartTest {

	Cart cart;
	Catalog catalog = new Catalog();;
	String itemA = "A";
	String itemB = "B";
	String itemC = "C";

	@Before
	public void setCart() {
		cart = new Cart();
	}

	@Test
	public void checkHashMap() {
		assertTrue(cart.map != null);
	}

	@Test
	public void initailSizeofMap() {
		assertTrue(cart.map.size() == 0);
	}

	@Test
	public void addtoCartCheckQuantity() {
		ProductOrder po = catalog.getProductOrder(itemA, 10);
		cart.add(itemA, po);
		assertEquals(10, cart.map.get(itemA).productQuantity);
	}

	@Test
	public void addtoCartCheckItemId() {
		ProductOrder po = catalog.getProductOrder(itemB, 50);
		cart.add(itemA, po);
		assertEquals(itemB, po.product.name);
	}

	@Test
	public void addtoCartCheckDuplicate() {
		ProductOrder po = catalog.getProductOrder(itemC, 50);
		ProductOrder po1 = catalog.getProductOrder(itemC, 40);
		cart.add(itemC, po);
		cart.add(itemC, po1);
		assertEquals(90, cart.map.get(itemC).productQuantity);

	}

	@Test
	public void getTotalCheck() {
		ProductOrder po = catalog.getProductOrder(itemA, 2);
		ProductOrder p1 = catalog.getProductOrder(itemB, 2);
		ProductOrder p2 = catalog.getProductOrder(itemC, 2);

		cart.add(itemA, po);
		cart.add(itemB, p1);
		cart.add(itemC, p2);
		cart.calCulateTotal();
		assertEquals(200, cart.getPrice());

	}

}
