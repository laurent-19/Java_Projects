package main;

import controllers.ProductsViewController;

import javax.swing.*;

public class StockManager {
    public static void main(String[] args) {
        JFrame presentation = new ProductsViewController();
        presentation.setVisible(true);
    }
}
