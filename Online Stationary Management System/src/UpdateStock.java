/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package online.stationary.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

class UpdateStock implements ActionListener {
    JFrame frame;
    JTextField productNameField;
    JTextField productCostField;
     JTextField productIDField;
    JTextField productPlaceField;
    JTextField quantityField;
    JButton updateButton;
String productName;
double productCost;
int productID;
String productPlace;
int quantity;

    UpdateStock(String productName, double productCost, int productID, String productPlace, int quantity) {
        this.productName = productName;
        this.productCost = productCost;
        this.productID = productID;
        this.productPlace = productPlace;
        this.quantity = quantity;

        frame = new JFrame("Update Stock");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(5, 2));

        JLabel productNameLabel = new JLabel("Product Name:");
        productNameField = new JTextField(productName);
        formPanel.add(productNameLabel);
        formPanel.add(productNameField);

        JLabel productCostLabel = new JLabel("Product Cost:");
        productCostField = new JTextField(String.valueOf(productCost));
        formPanel.add(productCostLabel);
        formPanel.add(productCostField);

        JLabel productIDLabel = new JLabel("Product ID:");
        productIDField = new JTextField(String.valueOf(productID));
        formPanel.add(productIDLabel);
        formPanel.add(productIDField);

        JLabel productPlaceLabel = new JLabel("Product Place:");
        productPlaceField = new JTextField(productPlace);
        formPanel.add(productPlaceLabel);
        formPanel.add(productPlaceField);

        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(String.valueOf(quantity));
        formPanel.add(quantityLabel);
        formPanel.add(quantityField);

        updateButton = new JButton("Update");
        updateButton.addActionListener(this);

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(updateButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String updatedProductName = productNameField.getText();
            double updatedProductCost = Double.parseDouble(productCostField.getText());
            int updatedProductID = Integer.parseInt(productIDField.getText());
            String updatedProductPlace = productPlaceField.getText();
            int updatedQuantity = Integer.parseInt(quantityField.getText());

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_info", "root", "1234");
                 PreparedStatement statement = connection.prepareStatement("UPDATE products SET prod_name=?, product_cost=?, prod_place=?, quantity=? WHERE product_id=?")) {

                statement.setString(1, updatedProductName);
                statement.setDouble(2, updatedProductCost);
                statement.setString(3, updatedProductPlace);
                statement.setInt(4, updatedQuantity);
                statement.setInt(5, updatedProductID);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(frame, "Stock updated successfully!");
                    frame.dispose();
                    Stockupdates obj = new Stockupdates();
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to update stock.");
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
