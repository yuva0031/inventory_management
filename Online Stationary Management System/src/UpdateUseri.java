package online.stationary.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class UpdateUseri implements ActionListener {
    JFrame frame;
    JLabel titleLabel;
    JLabel firstNameLabel;
    JTextField firstNameField;
    JLabel surnameLabel;
    JTextField surnameField;
    JLabel idLabel;
    JTextField idField;
    JLabel passwordLabel;
    JPasswordField passwordField;
    JLabel emailLabel;
    JTextField emailField;
    JLabel phoneLabel;
    JTextField phoneField;
    JLabel addressLabel;
    JTextField addressField;
    JButton updateButton;
    JButton delete;

    String firstName;
    String surname;
    String id;
    String password;
    String email;
    String phoneNo;
    String address;

    UpdateUseri(String firstName, String surname, String id, String password, String email, String phoneNo, String address) {
        this.firstName = firstName;
        this.surname = surname;
        this.id = id;
        this.password = password;
        this.email = email;
        this.phoneNo = phoneNo;
        this.address = address;

        frame = new JFrame("Update User Information");
        frame.setSize(1000, 800);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleLabel = new JLabel("Update User Information");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        frame.add(titleLabel);

        firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField(20);
        firstNameField.setText(firstName);
        frame.add(firstNameLabel);
        frame.add(firstNameField);

        surnameLabel = new JLabel("Surname:");
        surnameField = new JTextField(20);
        surnameField.setText(surname);
        frame.add(surnameLabel);
        frame.add(surnameField);

        idLabel = new JLabel("ID:");
        idField = new JTextField(20);
        idField.setText(id);
        frame.add(idLabel);
        frame.add(idField);

        passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(20);
        passwordField.setText(password);
        frame.add(passwordLabel);
        frame.add(passwordField);

        emailLabel = new JLabel("Email:");
        emailField = new JTextField(20);
        emailField.setText(email);
        frame.add(emailLabel);
        frame.add(emailField);

        phoneLabel = new JLabel("Phone Number:");
        phoneField = new JTextField(20);
        phoneField.setText(String.valueOf(phoneNo));
        frame.add(phoneLabel);
        frame.add(phoneField);

        addressLabel = new JLabel("Address:");
        addressField = new JTextField(20);
        addressField.setText(address);
        frame.add(addressLabel);
        frame.add(addressField);

        updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        frame.add(updateButton);
        delete=new JButton("CANCEL");
        delete.addActionListener(this);
        frame.add(delete);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String updatedFirstName = firstNameField.getText();
            String updatedSurname = surnameField.getText();
            String updatedId = idField.getText();
            String updatedPassword = String.valueOf(passwordField.getPassword());
            String updatedEmail = emailField.getText();
            String updatedPhoneNo = (phoneField.getText());
            String updatedAddress = addressField.getText();

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_info", "root", "1234");
                 Statement statement = connection.createStatement()) {

                String updateQuery = "UPDATE dataa SET first_name='" + updatedFirstName + "', surname='" + updatedSurname + "', " +
                        "id='" + updatedId + "', password='" + updatedPassword + "', email='" + updatedEmail + "', " +
                        "phoneno=" + updatedPhoneNo + ", address='" + updatedAddress + "' WHERE id='" + id + "'";
                statement.executeUpdate(updateQuery);

                JOptionPane.showMessageDialog(frame, "User information updated successfully!");
                frame.dispose();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Failed to update user information.");
            }
        }
        else{
            frame.dispose();
        }
    }
}
