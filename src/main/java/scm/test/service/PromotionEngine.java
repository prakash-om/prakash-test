package scm.test.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import scm.test.model.Cart;
import scm.test.model.PromotionOnProduct;
import scm.test.model.PromotionOnProductByPercentage;
import scm.test.model.PromotionOnProductGroup;
import scm.test.utils.Constant;

public class PromotionEngine {

	List<PromotionOnProduct> promotionOnProduct;
	List<PromotionOnProductGroup> promotionOnProductByGroup;
	List<PromotionOnProductByPercentage> promotionOnProductByPercentage;

	public PromotionEngine() {
		promotionOnProduct = new ArrayList<PromotionOnProduct>();
		promotionOnProductByGroup = new ArrayList<PromotionOnProductGroup>();
		promotionOnProductByPercentage = new ArrayList<PromotionOnProductByPercentage>();
		generatePromotionOnProduct();
		generatePromotionOnProductByGroup();
		generatePromotionOnProductByPercentage();

	}

	private void generatePromotionOnProductByPercentage() {
		// TODO Auto-generated method stub

	}

	private void generatePromotionOnProductByGroup() {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(Constant.PROMOTION_GROUP_FILE_NAME).getFile());
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String st;
		try {
			while ((st = br.readLine()) != null) {
				String[] splitInputs = st.split("[ ,]+");
				PromotionOnProductGroup pOnProductGroup = new PromotionOnProductGroup();
				int i = 0;
				for (i = 0; i < splitInputs.length - 1; i++) {
					pOnProductGroup.getProductIds().add(splitInputs[i]);
				}
				pOnProductGroup.setDiscountedPrice(Integer.parseInt(splitInputs[i].trim()));
				promotionOnProductByGroup.add(pOnProductGroup);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void generatePromotionOnProduct() {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(Constant.PROMOTION_FILE_NAME).getFile());
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		String st;
		try {
			while ((st = br.readLine()) != null) {
				String[] splitInputs = st.split(" ");
				PromotionOnProduct pOnProduct = new PromotionOnProduct();
				pOnProduct.setProductId(splitInputs[0]);
				pOnProduct.setQuantity(Integer.parseInt(splitInputs[1].trim()));
				pOnProduct.setDiscountedPrice(Integer.parseInt(splitInputs[2].trim()));
				promotionOnProduct.add(pOnProduct);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public int applyPromotions(Cart cart) {
		int total = cart.getPrice();
		
		for (PromotionOnProduct pOnProduct : promotionOnProduct) {
			total -= pOnProduct.applyPromotion(cart);
		}

		for (PromotionOnProductGroup pOnProductGrou : promotionOnProductByGroup) {
			total -= pOnProductGrou.applyPromotion(cart);
		}

		return total;
	}
}
