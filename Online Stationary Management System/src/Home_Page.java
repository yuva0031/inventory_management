package online.stationary.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home_Page extends JFrame implements ActionListener {
    JFrame f;
    JLabel a;
    JButton b, c;
    JPanel imagePanel;

    Home_Page() {
        f = new JFrame();
        f.setSize(1600, 850);
        f.getContentPane().setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLocationRelativeTo(null); // center the frame on the screen

        // Create the image panel and set its layout to null
        imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon(getClass().getResource("dma.jpg")).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            }
        };
        imagePanel.setLayout(null);
        imagePanel.setBounds(0, 0, 1600, 850);
        f.getContentPane().add(imagePanel);

        a = new JLabel("ECOMMERCE INVENTORY");
        a.setBounds(550, 75, 600, 100);
        Color greenColor = new Color(7,  101, 35);
        a.setForeground(greenColor);
        a.setFont(new Font("Broadway", Font.BOLD, 40));
        imagePanel.add(a);

        b = new JButton("ADMIN PAGE");
        b.addActionListener(this);
        b.setBounds(300, 300, 250, 75);
        b.setFont(new Font("Stencil", Font.BOLD, 30));
        b.setBackground(greenColor);
        b.setForeground(Color.WHITE);
        imagePanel.add(b);

        c = new JButton("USER PAGE");
        c.addActionListener(this);
        c.setBounds(1050, 300, 250, 75);
        c.setFont(new Font("Stencil", Font.BOLD, 30));
        c.setBackground(greenColor);
        c.setForeground(Color.WHITE);
        imagePanel.add(c);

        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b) {
           
            Admin_Login_Page call = new Admin_Login_Page();
        }
        if (e.getSource() == c) {
           
            User_Login_Page call1 = new User_Login_Page();
        }
    }
}
