package scm.test.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import scm.test.model.Cart;
import scm.test.model.Catalog;
import scm.test.model.ProductOrder;

public class PromotionEngineTest {
	
	PromotionEngine pEngine;
	
	
	Cart cart;
	Catalog catalog = new Catalog();
	String itemA = "A";
	String itemB = "B";
	String itemC = "C";
	String itemD = "D";
	
	@Before
	public void setUp() {
		pEngine = new PromotionEngine();
		cart = new Cart();
		
	}
	
	@Test
	public void applyPromotionTestThree() {
		ProductOrder po = catalog.getProductOrder(itemA, 3);
		ProductOrder p1 = catalog.getProductOrder(itemB, 5);
		ProductOrder p2 = catalog.getProductOrder(itemC, 1);
		ProductOrder p3 = catalog.getProductOrder(itemD, 1);
		
		cart.add(itemA, po);
		cart.add(itemB, p1);
		cart.add(itemC, p2);
		cart.add(itemD, p3);
		cart.calCulateTotal();
		assertEquals(280, pEngine.applyPromotions(cart));
	}
	
	@Test
	public void applyPromotionTestTwo() {
		ProductOrder po = catalog.getProductOrder(itemA, 5);
		ProductOrder p1 = catalog.getProductOrder(itemB, 5);
		ProductOrder p2 = catalog.getProductOrder(itemC, 1);
		
		
		cart.add(itemA, po);
		cart.add(itemB, p1);
		cart.add(itemC, p2);
		cart.calCulateTotal();
		assertEquals(370, pEngine.applyPromotions(cart));
	}
	
	@Test
	public void applyPromotionTestOne() {
		ProductOrder po = catalog.getProductOrder(itemA, 1);
		ProductOrder p1 = catalog.getProductOrder(itemB, 1);
		ProductOrder p2 = catalog.getProductOrder(itemC, 1);
		cart.add(itemA, po);
		cart.add(itemB, p1);
		cart.add(itemC, p2);
		cart.calCulateTotal();
		assertEquals(100, pEngine.applyPromotions(cart));
	}
}
