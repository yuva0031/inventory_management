package online.stationary.management.system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class PreviousBills extends JFrame {
    private JTable table;
    private JPanel panel;

    public PreviousBills() {
        setTitle("Previous Bills");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        getContentPane().add(panel);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadBills();

        setVisible(true);
    }

    private void loadBills() {
        DefaultTableModel model = new DefaultTableModel();
 try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM previous_bills");

        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();

        // Clear existing columns
        model.setColumnCount(0);

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

        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error connecting to database: " + e.getMessage());
    }

    table.setModel(model);
    }
}
