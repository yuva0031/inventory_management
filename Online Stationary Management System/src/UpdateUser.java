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
public class UpdateUser  implements ActionListener {
     JFrame frame;
    JTable table;
    DefaultTableModel tableModel;
    JTextField searchField;
    JButton searchButton;
    JLabel dateLabel;

     JButton b1;
     JButton b2;
     JButton b3;

    UpdateUser(){
        
        frame = new JFrame("Stock Updates");
        frame.setSize(1600, 800);
        frame.setLayout(new BorderLayout());


        table = new JTable();
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new Object[]{ "First Name", "Surname","UserName", "Password", "Email","Phone Number","Address"});
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


        b2 = new JButton("UPDATE USER INFO");
        b2.addActionListener(this);
        buttonPanel.add(b2);


        // Add the button panel to the frame
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_info", "root", "1234");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM dataa")) {

// Inside the constructor of UpdateUser class, after adding rows to the tableModel

while (resultSet.next()) {
    String FirstName = resultSet.getString("first_name");
    String Surname = resultSet.getString("surname");
    String ID = resultSet.getString("id");
    String Password = resultSet.getString("password");
    String Email = resultSet.getString("email");
    String PhoneNo = resultSet.getString("phoneno");
    String Address = resultSet.getString("address");
    tableModel.addRow(new Object[]{FirstName, Surname, ID, Password, Email, PhoneNo, Address});
}

table.setModel(tableModel); // Set the table model to the table

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
    } 
        else if (e.getSource() == b2){
        if (selectedRow != -1) {
            String FirstName = tableModel.getValueAt(selectedRow,0).toString();
                String Surname = tableModel.getValueAt(selectedRow,1).toString();
                String ID = tableModel.getValueAt(selectedRow,2).toString();
                String Password = tableModel.getValueAt(selectedRow,3).toString();
                String Email = tableModel.getValueAt(selectedRow,4).toString();
                String PhoneNo = (tableModel.getValueAt(selectedRow, 5).toString());
                String Address = tableModel.getValueAt(selectedRow,6).toString();
       

            // Open update stock page passing the selected stock details
            frame.dispose();
            UpdateUseri obj2 = new UpdateUseri(FirstName,Surname,ID,Password,Email,PhoneNo,Address);
        }
    } 
}

    }

