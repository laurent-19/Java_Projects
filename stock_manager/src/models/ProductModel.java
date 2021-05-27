package models;

import java.util.ArrayList;

public class ProductModel {

    private static final ArrayList<ProductModel> products = new ArrayList<>();
    private String name;
    private int price;
    private int quantity;


    public ProductModel() {
    }

    public static ArrayList<ProductModel> getProducts() {
        return products;
    }

    public ProductModel(String name, int price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        products.add(this);
    }

    public void addProduct(String name, int price, int quantity) {
        new ProductModel(name, price, quantity);
    }

    public static void deleteProduct(String name) {
        products.removeIf(product -> product.name.equals(name));
    }

    public String getAllProducts() {
        StringBuilder allProducts = new StringBuilder();
        for (ProductModel product : products) {
            allProducts.append(product.toString()).append("\n");
        }
        return allProducts.toString();
    }

    public static boolean setQuantity(String name, int quantity) {
        for (ProductModel product : products) {
            if (product.name.equals(name)) {
                product.quantity = quantity;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
