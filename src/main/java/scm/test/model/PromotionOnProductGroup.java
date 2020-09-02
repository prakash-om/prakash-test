package scm.test.model;

import java.util.ArrayList;
import java.util.List;

public class PromotionOnProductGroup implements Promotion {

	@Override
	public int applyPromotion(Cart cart) {
		int discountAmount = 0;
		int minCount = Integer.MAX_VALUE;
		boolean isEligable = true;
		int actualAmount = 0;
		for (String str : getProductIds()) {

			if (null != cart.getMap().get(str) && str.equals(cart.getMap().get(str).getProduct().getName())) {

				if (null != cart.getMap().get(str) && minCount >= cart.getMap().get(str).getProductQuantity()) {
					minCount = cart.getMap().get(str).getProductQuantity();
				}

				if (null != cart.getMap().get(str)) {
					actualAmount += cart.getMap().get(str).getProduct().getPrice();
				}

			} else {
				isEligable = false;
				return discountAmount;
			}

		}

		if (isEligable && minCount > 0) {
			actualAmount = minCount * actualAmount;
			int promotionAmount = minCount * getDiscountedPrice();
			discountAmount = actualAmount - promotionAmount;
		}
		return discountAmount;
	}

	List<String> productIds;

	public List<String> getProductIds() {
		return productIds;
	}

	public void setProductIds(List<String> productIds) {
		this.productIds = productIds;
	}

	public int getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	int discountedPrice;

	public PromotionOnProductGroup() {
		productIds = new ArrayList<String>();

	}

}
