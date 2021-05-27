package controllers;

import models.ProductModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductsViewController extends JFrame {

    ProductModel productModel = new ProductModel();

    JLabel lProductName;
    JLabel lProductPrice;
    JLabel lProductQuantity;

    JTextField tProductName;
    JTextField tProductPrice;
    JTextField tProductQuantity;

    JButton bAddProduct;
    JButton bDeleteProduct;
    JButton bViewProducts;
    JButton bUpdateQuantity;

    JTextArea textArea;


    public ProductsViewController() {

        setTitle("Stock management application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        setSize(700, 600);
    }

    public void init() {

        setLayout(null);
        int width = 150;
        int height = 30;

        lProductName = new JLabel("Product Name:");
        lProductName.setBounds(20, 50, width, height);

        lProductPrice = new JLabel("Product Price:");
        lProductPrice.setBounds(20, 90, width, height);

        lProductQuantity = new JLabel("Product Quantity ");
        lProductQuantity.setBounds(20, 130, width, height);

        tProductName = new JTextField();
        tProductName.setBounds(270, 50, width, height);

        tProductPrice = new JTextField();
        tProductPrice.setBounds(270, 90, width, height);

        tProductQuantity = new JTextField();
        tProductQuantity.setBounds(270, 130, width, height);


        bAddProduct = new JButton("Add");
        bAddProduct.setBounds(90, 200, width / 2, height);

        bDeleteProduct = new JButton("Delete");
        bDeleteProduct.setBounds(170, 200, width / 2, height);

        bViewProducts = new JButton("View All");
        bViewProducts.setBounds(250, 200, 100, height);

        bUpdateQuantity = new JButton("Update");
        bUpdateQuantity.setBounds(360, 200, width / 2, height);

        textArea = new JTextArea();
        textArea.setBounds(60, 240, 500, 300);

        bAddProduct.addActionListener(new ImplementAddButton());
        bDeleteProduct.addActionListener(new ImplementDeleteButton());
        bViewProducts.addActionListener(new ImplementViewButton());
        bUpdateQuantity.addActionListener(new ImplementUpdateButton());

        add(lProductName);
        add(lProductPrice);
        add(lProductQuantity);
        add(tProductName);
        add(tProductPrice);
        add(tProductQuantity);
        add(bAddProduct);
        add(bDeleteProduct);
        add(bViewProducts);
        add(bUpdateQuantity);
        add(textArea);
    }

    class ImplementDeleteButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (!tProductName.getText().equals("")) {
                ProductModel.deleteProduct(tProductName.getText());
                textArea.setText("Product Deleted!");
            } else {
                textArea.setText("Failed to delete! \nIncorrect name for product!");
            }
        }
    }

    class ImplementViewButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (ProductModel.getProducts().size() == 0) {
                textArea.setText("There are no products stored!");
            } else {
                textArea.setText("The products are:\n" + productModel.getAllProducts());
            }
        }
    }

    class ImplementUpdateButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            if ((!tProductName.getText().equals("")) && (!tProductQuantity.getText().equals(""))) {
                if (ProductModel.setQuantity(tProductName.getText(), Integer.parseInt(tProductQuantity.getText()))) {
                    textArea.setText("Product updated!");
                }
            } else {
                textArea.setText("Failed to update! \nThe quantity not specified or product does not exists! ");
            }
        }
    }

    class ImplementAddButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if ((!tProductName.getText().equals("")) && (!tProductPrice.getText().equals("")) && (!tProductQuantity.getText().equals(""))) {
                productModel.addProduct(
                        tProductName.getText(),
                        Integer.parseInt(tProductPrice.getText()),
                        Integer.parseInt(tProductQuantity.getText()));
                textArea.setText("Product Added!");
            } else {
                textArea.setText("Failed to add! \nCheck if all inputs are filled correctly!");
            }
        }
    }

}
