package scm.test;

import java.util.Map;
import java.util.Scanner;

import scm.test.model.Cart;
import scm.test.model.Catalog;
import scm.test.model.ProductOrder;
import scm.test.service.PromotionEngine;

public class App {

	Catalog catalog;
	PromotionEngine promotionEngine;

	public void init() {
		loadCatalog();
		loadPromotionEnginee();
	}

	private void loadPromotionEnginee() {
		promotionEngine = new PromotionEngine();
		System.out.println("Promotion Enginee has been loaded sucessfully");
	}

	private void loadCatalog() {
		catalog = new Catalog();
		System.out.println("Product Catalog has been loaded sucessfully");
	}

	public App() {
		init();
	}

	public static void main(String[] args) {

		App app = new App();
		app.start();

	}

	private void start() {

		Scanner inputScanner = new Scanner(System.in);
		Cart cart = new Cart();
		System.out.println();
		System.out.println("Please select the options");
		while (true) {

			System.out.println("1 Product Purchase");
			System.out.println("2 Place Order");
			System.out.println("3 Exit");

			switch (inputScanner.nextInt()) {
			case 1:
				System.out.println("Enter the Product (SKU) ID and Quantity like (A 5)");
				String productId = inputScanner.next();
				int quantity = Integer.parseInt(inputScanner.nextLine().trim());
				cart.add(productId, catalog.getProductOrder(productId, quantity));
				break;
			case 2:
				cart.calCulateTotal();
				System.out.println("Product purchase by you");
				for (Map.Entry<String, ProductOrder> entry : cart.getMap().entrySet()) {
					System.out.println(entry.getValue().getProduct().getName() + " " +entry.getValue().getProductQuantity());
					
				}
				System.out.println("============================================================");
				System.out.println("Total Amount Before Discount : "+cart.getPrice());
				System.out.println("Total Amount After Discount  : "+ promotionEngine.applyPromotions(cart));
				System.out.println("============================================================");
				System.out.println();
				System.out.println("Please select the options");
				cart = new Cart();
				break;
			case 3:
				System.exit(0);

			}

		}

	}
}
