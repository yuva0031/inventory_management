package online.stationary.management.system;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.*;

public class Billing extends JFrame implements ActionListener {
    private JFrame frame;
    private JTable table;
    private JButton addButton, billButton;
    private JPanel panel;
    private Connection conn;
    String Amount;
    private JLabel billNumberLabel;
    private JTextField billNumberTextField;
private JLabel dateLabel;
private JTextField dateTextField;

    private JLabel nameLabel, phoneLabel, emailLabel, addressLabel;
    private JTextField nameTextField, phoneTextField, emailTextField, addressTextField;
     int billNumberCounter = 1;
    Billing() {
        
        frame = new JFrame();
        frame.setSize(1600, 850);

        nameLabel = new JLabel("Customer Name:");
        nameLabel.setBounds(50, 20, 150, 30);

        nameTextField = new JTextField();
        nameTextField.setBounds(200, 20, 200, 30);

        phoneLabel = new JLabel("Phone Number:");
        phoneLabel.setBounds(50, 60, 150, 30);

        phoneTextField = new JTextField();
        phoneTextField.setBounds(200, 60, 200, 30);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 100, 150, 30);

        emailTextField = new JTextField();
        emailTextField.setBounds(200, 100, 200, 30);

        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(50, 140, 150, 30);

        addressTextField = new JTextField();
        addressTextField.setBounds(200, 140, 200, 30);

        billNumberLabel = new JLabel("Bill Number:");
        billNumberLabel.setBounds(50, 180, 150, 30);

        billNumberTextField = new JTextField();
        billNumberTextField.setBounds(200, 180, 200, 30);
        billNumberTextField.setEditable(false);

        dateLabel = new JLabel("Date:");
        dateLabel.setBounds(50, 220, 150, 30);

        dateTextField = new JTextField();
        dateTextField.setBounds(200, 220, 200, 30);
        dateTextField.setEditable(false);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 1600, 850);
        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(phoneLabel);
        panel.add(phoneTextField);
        panel.add(emailLabel);
        panel.add(emailTextField);
        panel.add(addressLabel);
        panel.add(addressTextField);
        panel.add(billNumberLabel);
        panel.add(billNumberTextField);
        panel.add(dateLabel);
        panel.add(dateTextField);

        DefaultTableModel model = new DefaultTableModel();
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 260, 1500, 600);

        addButton = new JButton("ADD");
        billButton = new JButton("BILL IT");
        billButton.addActionListener(this);
        billButton.setBounds(750, 700, 150, 50);

        addButton.addActionListener(this);
        addButton.setBounds(500, 700, 150, 50);

        panel.add(billButton);
        panel.add(addButton);
        panel.add(scrollPane);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
        
               try{
                // Generate the bill number
                String billNumber = generateBillNumber();
                billNumberTextField.setText(billNumber);
                // Rest of the code...
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }

        try {
            conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM billing");
            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                model.addColumn(meta.getColumnName(i));
            }

            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = rs.getObject(i);
                }
                model.addRow(row);
            }

            Object[] rowTotal = new Object[columnCount];
            Object[] rowGST = new Object[columnCount];
            Object[] rowAmount = new Object[columnCount];

            rowTotal[2] = "Total";
            rowGST[2] = "GST";
            rowAmount[2] = "Amount";

            rowTotal[0] = "";
            rowTotal[1] = "";
            rowGST[0] = "";
            rowGST[1] = "";
            rowAmount[0] = "";
            rowAmount[1] = "";

            int total = 0;
            for (int i = 0; i < table.getRowCount(); i++) {
                int value = Integer.parseInt(table.getValueAt(i, table.getColumnModel().getColumnIndex("cost")).toString());
                total += value;
            }

            rowTotal[3] = total;
            rowGST[3] = (int) (0.18 * total) + 1;
            rowAmount[3] = (int) (total + (0.18 * total) + 1);
            Amount =  (rowAmount[3]).toString();
            model.addRow(rowTotal);
            model.addRow(rowGST);
            model.addRow(rowAmount);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error connecting to database: " + e.getMessage());
        }

        // Retrieve the current date and format it
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);
        dateTextField.setText(formattedDate);
    }
 // Class variable to keep track of the bill number

private String generateBillNumber() {
    
    String newBillNumber = "DMA" + String.format("%05d", billNumberCounter); // Format the bill number with leading zeros
    billNumberCounter++;
    return newBillNumber;
}


    public void actionPerformed(ActionEvent ee) {
        if (ee.getSource() == addButton) {
            frame.dispose();
            Qrcode_reader obj = new Qrcode_reader();
            Billing objjj = new Billing();
        }
        if (ee.getSource() == billButton) {
                        try{
                // Generate the bill number
                String billNumber = generateBillNumber();
                billNumberTextField.setText(billNumber);
                // Rest of the code...
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
                        
                    String customerName = nameTextField.getText();
        if (customerName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter customer name.");
            return;
        }

        // Validate phone number
        String phoneNumber = phoneTextField.getText();
        if (phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter phone number.");
            return;
        }

        // Validate address
        String address = addressTextField.getText();
        if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter address.");
            return;
        }

        // Validate email
        String email = emailTextField.getText();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter email.");
            return;
        }
        else{
            try {
                // Create a new PDF document
                Document document = new Document();

                // Set the file name and location
                String filePath = "E:\\Pdfs\\billing.pdf";
                PdfWriter.getInstance(document, new FileOutputStream(filePath));

                // Open the document
                document.open();
                Paragraph ne = new Paragraph("DMART GUDIVADA");
                document.add(ne);
                document.add(new Paragraph("\n"));

                // Create a table for the billing details
                PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
                pdfTable.setWidthPercentage(100);

                for (int i = 0; i < table.getRowCount(); i++) {
                    for (int j = 0; j < table.getColumnCount(); j++) {
                        Object value = table.getValueAt(i, j);
                        pdfTable.addCell(value != null ? value.toString() : "");
                    }
                }
                for (int i = 0; i < table.getColumnCount(); i++) {
                    pdfTable.addCell(table.getColumnName(i));
                }

                document.add(pdfTable);

                // Close the document
                document.close();

                // Update the quantity of products in the database
                PreparedStatement pstmt = conn.prepareStatement("UPDATE products SET Quantity = Quantity - 1 WHERE `prod_name` = ?");
                for (int i = 0; i < table.getRowCount(); i++) {
                    String productName = table.getValueAt(i, table.getColumnModel().getColumnIndex("prod_name")).toString();

                    pstmt.setString(1, productName);
                    pstmt.executeUpdate();
                }
                pstmt.close();

                // Save the billing details as previous bills in the database
                pstmt = conn.prepareStatement("INSERT INTO previous_bills (bill_number, date, customer_name, amount, phoneNumber, email, address) VALUES (?, ?, ?, ?, ?, ?, ?)");
                
                

    String billNumber = billNumberTextField.getText();
    String date = dateTextField.getText();
    String amount = Amount;
    String customerNam = nameTextField.getText();
    String phoneNum = phoneTextField.getText();
    String emai = emailTextField.getText();
    String addres = addressTextField.getText();

    pstmt.setString(1, billNumber);
    pstmt.setString(2, date);
    pstmt.setString(3, customerNam);
    pstmt.setString(4, amount);
    pstmt.setString(5, phoneNum);
    pstmt.setString(6, emai);
    pstmt.setString(7, addres);
    pstmt.executeUpdate();
                pstmt.close();

                // Clear the billing table in the database
                Statement statement = conn.createStatement();
                String sql = "DELETE FROM billing";
                statement.execute(sql);
                statement.close();
                conn.close();
                JOptionPane.showMessageDialog(this, "Bill Generated Successfully!");
                frame.dispose();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
            try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
            Statement stmt = conn.createStatement();
            
            // Alter the table to reset the auto-increment value
            stmt.executeUpdate("ALTER TABLE billing AUTO_INCREMENT = 1");
            
            stmt.close();
            conn.close();
            generateBillNumber();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            

        }
        }
    }

}
