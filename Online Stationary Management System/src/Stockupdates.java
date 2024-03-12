package online.stationary.management.system;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.table.TableRowSorter;
import java.util.Date;
import java.text.SimpleDateFormat;

class Stockupdates implements ActionListener {
     JFrame frame;
    JTable table;
    DefaultTableModel tableModel;
    JTextField searchField;
    JButton searchButton;
    JLabel dateLabel;

     JButton b1;
     JButton b2;
     JButton b3;

    Stockupdates() {
        
        frame = new JFrame("Stock Updates");
        frame.setSize(1600, 800);

        frame.setLayout(new BorderLayout());


        table = new JTable();
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{"Product Name", "Product Cost", "Product ID", "Product Place", "Quantity"});
        table.setModel(tableModel);

        // Create the scroll pane and add the table to it
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Create the search panel
        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchPanel.add(searchField);

        searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchPanel.add(searchButton);

        // Add the search panel to the frame
        frame.add(searchPanel, BorderLayout.NORTH);

        // Create the buttons panel
        JPanel buttonPanel = new JPanel();
        b1 = new JButton("NEW STOCK");
        b1.addActionListener(this);
        buttonPanel.add(b1);

        b2 = new JButton("UPDATE STOCK");
        b2.addActionListener(this);
        buttonPanel.add(b2);

        b3 = new JButton("DELETE STOCK");
        b3.addActionListener(this);
        buttonPanel.add(b3);

        // Add the button panel to the frame
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        try(
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_info", "root", "1234");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {

            while (resultSet.next()) {
                String productName = resultSet.getString("prod_name");
                double productCost = resultSet.getDouble("product_cost");
                int productID = resultSet.getInt("product_id");
                String productPlace = resultSet.getString("prod_place");
                int quantity = resultSet.getInt("quantity");

                tableModel.addRow(new Object[]{productName, productCost, productID, productPlace, quantity});
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
}


    @Override
public void actionPerformed(ActionEvent e) {
    int selectedRow = table.getSelectedRow();
    if (e.getSource() == searchButton) {
        // Search functionality
        String searchText = searchField.getText();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
    } else if (e.getSource() == b1) {
        // Open new stock page
        frame.dispose();
        Newstock obj1 = new Newstock();
    }
        else if (e.getSource() == b2){
        if (selectedRow != -1) {
            String productName = tableModel.getValueAt(selectedRow, 0).toString();
            double productCost = Double.parseDouble(tableModel.getValueAt(selectedRow, 1).toString());
            int productID = Integer.parseInt(tableModel.getValueAt(selectedRow, 2).toString());
            String productPlace = tableModel.getValueAt(selectedRow, 3).toString();
            int quantity = Integer.parseInt(tableModel.getValueAt(selectedRow, 4).toString());

            // Open update stock page passing the selected stock details
            frame.dispose();
            UpdateStock obj2 = new UpdateStock(productName, productCost, productID, productPlace, quantity);
        }
    } else if (e.getSource() == b3) {

        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this stock?",
                    "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String productID = tableModel.getValueAt(selectedRow, 2).toString();

                try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_info", "root", "1234");
                     Statement statement = connection.createStatement()) {

                    String deleteQuery = "DELETE FROM products WHERE product_id = '" + productID + "'";
                    int rowsAffected = statement.executeUpdate(deleteQuery);

                    if (rowsAffected > 0) {
                        tableModel.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(frame, "Stock deleted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error deleting stock.");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a stock to delete.");
        }
    }
}

    }

