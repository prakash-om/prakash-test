package scm.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class PromotionOnProductTest {
	
	PromotionOnProduct pOnProduct ;
	
	Cart cart;
	Catalog catalog = new Catalog();
	String itemA = "A";
	String itemB = "B";
	String itemC = "C";

	
	@Before
	public void setUpPromotionOnProduct() {
		pOnProduct= new PromotionOnProduct();
		pOnProduct.setProductId("A");
		pOnProduct.setQuantity(3);
		pOnProduct.setDiscountedPrice(130);
		cart = new Cart();
	}
	
	@Test
	public void tryToApplyPromotionButNotEliagble() {
		ProductOrder po = catalog.getProductOrder(itemA, 2);
		cart.add(itemA, po);
		cart.calCulateTotal();
		int totalPriceAfterDiscount = cart.getPrice() - pOnProduct.applyPromotion(cart);
		assertEquals(100, totalPriceAfterDiscount);
	}
	
	@Test
	public void applyPromotion() {
		ProductOrder po = catalog.getProductOrder(itemA, 5);
		cart.add(itemA, po);
		cart.calCulateTotal();
		int totalPriceAfterDiscount = cart.getPrice() - pOnProduct.applyPromotion(cart);
		assertEquals(230, totalPriceAfterDiscount);
	}

}
