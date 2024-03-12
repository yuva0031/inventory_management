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
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
 class Newstock extends JFrame implements ActionListener{
    JFrame x;
    JLabel a;
    JLabel b;
    JLabel c;
    JLabel d;
    JLabel e;
    JTextField f;
    JTextField g;
    JTextField h;
    JTextField i;
    JTextField j;
    JButton l;
    JButton m;

    public Newstock() {
        x = new JFrame("SP");
        x.setSize(400, 800);
        x.setLocationRelativeTo(null); // Center the frame on the screen
        x.setLayout(null);
        x.setVisible(true);

        a = new JLabel("PRODUCT ID");
        a.setBounds(50, 100, 100, 25);
        a.setVisible(true);

        b = new JLabel("PRODUCT NAME");
        b.setBounds(50, 200, 100, 25);
        b.setVisible(true);

        c = new JLabel("PRICE");
        c.setBounds(50, 300, 100, 25);
        c.setVisible(true);

        d = new JLabel("QUANTITY");
        d.setBounds(50, 400, 100, 25);
        d.setVisible(true);

        e = new JLabel("LOCATION");
        e.setBounds(50, 500, 100, 25);
        e.setVisible(true);

        f = new JTextField();
        f.setBounds(200, 100, 150, 25);
        f.setVisible(true);

        g = new JTextField();
        g.setBounds(200, 200, 150, 25);
        g.setVisible(true);

        h = new JTextField();
        h.setBounds(200, 300, 150, 25);
        h.setVisible(true);

        i = new JTextField();
        i.setBounds(200, 400, 150, 25);
        i.setVisible(true);

        j = new JTextField();
        j.setBounds(200, 500, 150, 25);
        j.setVisible(true);

        l = new JButton("BACK");
        l.setBounds(50, 600, 100, 25);
        l.setVisible(true);
        l.addActionListener(this);

        m = new JButton("ADD");
        m.setBounds(200, 600, 100, 25);
        m.setVisible(true);
        m.addActionListener(this);

        x.add(a);
        x.add(b);
        x.add(c);
        x.add(d);
        x.add(e);
        x.add(f);
        x.add(g);
        x.add(h);
        x.add(i);
        x.add(j);
        x.add(l);
        x.add(m);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==m){
           try {
            Connection conn;
            Statement stmt;
                conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
                String sql = "INSERT INTO products (prod_name, product_cost, product_id, prod_place, quantity) "
           + "VALUES ('" + g.getText() + "', '" + h.getText() + "', '" + f.getText() + "','" + j.getText() + "','" + i.getText() + "')";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);      
            JOptionPane.showMessageDialog(this, "STOCK ADDED SUCCESSFULLY");
                     }  catch (Exception ee) {
                         JOptionPane.showMessageDialog(this,  ee.getMessage());
           
        }
            
        }
        if (e.getSource() == l){
        
        dispose();
        Stockupdates obj = new Stockupdates();
        
        
                }
        
    }
    

 
}

