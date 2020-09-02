package scm.test.model;

public class PromotionOnProduct implements Promotion{

	@Override
	public int applyPromotion(Cart cart) {
		int discountAmount = 0; 
		if (cart.getMap().containsKey(getProductId())) {
			int quatity = cart.getMap().get(getProductId()).getProductQuantity();

			int noOfPromotions = quatity / getQuantity();

			if (noOfPromotions > 0) {
				int productPrice = cart.getMap().get(getProductId()).getProduct().getPrice();
				int actualAmount = noOfPromotions * getQuantity() * productPrice;
				int promotionAmount = noOfPromotions * getDiscountedPrice();

				discountAmount = actualAmount - promotionAmount;

			}
		}
		return discountAmount;
	}
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getDiscountedPrice() {
		return discountedPrice;
	}
	public void setDiscountedPrice(int discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	String productId;
	int quantity;
	int discountedPrice;
		
}
