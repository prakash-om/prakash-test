package scm.test.model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PromotionOnProductGroupTest {
	
	PromotionOnProductGroup promotionOnProductGroup;
	
	Cart cart;
	Catalog catalog = new Catalog();;
	List<String> productLists;
	String itemA = "A";
	String itemB = "B";
	String itemC = "C";
	String itemD = "D";
	
	@Before
	public void setUp() {
		promotionOnProductGroup = new PromotionOnProductGroup();
		productLists = new ArrayList<String>();
		productLists.add(itemC);
		productLists.add(itemD);
		promotionOnProductGroup.setProductIds(productLists);
		promotionOnProductGroup.setDiscountedPrice(30);
		cart = new Cart();
	}
	
	@Test 
	public void applyPromotionSucess() {
		ProductOrder po = catalog.getProductOrder(itemC, 1);
		ProductOrder p1 = catalog.getProductOrder(itemD, 1);
		cart.add(itemC, po);
		cart.add(itemD, p1);
		cart.calCulateTotal();
		int priceAfterDiscount = cart.getPrice() - promotionOnProductGroup.applyPromotion(cart);
		assertEquals(30, priceAfterDiscount);
	}
	
	@Test 
	public void applyPromotionButNotEligable() {
		ProductOrder po = catalog.getProductOrder(itemC, 1);
		cart.add(itemC, po);
		cart.calCulateTotal();
		int priceAfterDiscount = cart.getPrice() - promotionOnProductGroup.applyPromotion(cart);
		assertEquals(20, priceAfterDiscount);
	}

}
