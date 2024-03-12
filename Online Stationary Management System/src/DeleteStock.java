package online.stationary.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DeleteStock {
    JFrame f;
    JTable table;    
    DefaultTableModel tableModel;
    DeleteStock(){
         f = new JFrame("Stock Updates");
        f.setSize(1600, 800);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new BorderLayout());
        table = new JTable();
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Product Name", "Product Cost", "Product ID", "Product Place", "Quantity"});
        table.setModel(tableModel);
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM products");

            while (resultSet.next()) {
                String productName = resultSet.getString("prod_name");
                double productCost = resultSet.getDouble("product_cost");
                int productID = resultSet.getInt("product_id");
                String productPlace = resultSet.getString("prod_place");
                int quantity = resultSet.getInt("quantity");

                tableModel.addRow(new Object[]{productName, productCost, productID, productPlace,quantity});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        f.setVisible(true);

    }
}
