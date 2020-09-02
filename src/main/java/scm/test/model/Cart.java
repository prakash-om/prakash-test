package scm.test.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	Map<String, ProductOrder> map;
	int price;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getDiscontedPrice() {
		return discontedPrice;
	}

	public void setDiscontedPrice(int discontedPrice) {
		this.discontedPrice = discontedPrice;
	}

	int discontedPrice;

	public Map<String, ProductOrder> getMap() {
		return map;
	}

	public Cart() {
		map = new HashMap<String, ProductOrder>();
		price = 0;
		discontedPrice = 0;
	}

	public void add(String itemId, ProductOrder productOrder) {
		if (map.containsKey(itemId)) {
			ProductOrder pOrder = map.get(itemId);
			pOrder.setProductQuantity(pOrder.getProductQuantity() + productOrder.getProductQuantity());
			;
			map.put(itemId, pOrder);
		} else {
			map.put(itemId, productOrder);
		}

	}

	public void calCulateTotal() {

		int total = 0;
		for (Map.Entry<String, ProductOrder> entry : map.entrySet()) {
			total += entry.getValue().getProduct().getPrice() * entry.getValue().getProductQuantity();
		}

		price = total;
	}

}
