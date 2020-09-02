package scm.test.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import scm.test.utils.Constant;

public class Catalog {

	Map<String, Product> map;

	public Catalog() {
		map = new HashMap<String, Product>();
		loadData();

	}

	public ProductOrder getProductOrder(String productId, int quantity) {
		ProductOrder productOrder = new ProductOrder();
		productOrder.setProduct(map.get(productId));
		productOrder.setProductQuantity(quantity);
		return productOrder;
	}

	private void loadData() {

		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(Constant.CATALOG_FILE_NAME).getFile());
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
				Product product = new Product();
				product.setName(splitInputs[0]);
				product.setPrice(Integer.parseInt(splitInputs[1].trim()));
				map.put(splitInputs[0], product);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
