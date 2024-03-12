package online.stationary.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class User_Login_Page extends JFrame implements ActionListener{
    JFrame ff;
    JLabel bbb, bb, aa;
    JPasswordField e;
    JPanel imagePanel;
    JTextField c;
    JButton x, y;
User_Login_Page(){ 
ff = new JFrame("USER_PAGE");
ff.setVisible(true);
ff.setSize(1600,850);
 ff.setLocationRelativeTo(null);
//ff.getContentPane().setBackground(Color.GRAY);
bb = new JLabel("USER LOGIN");
bb.setVisible(true);
bb.setForeground(Color.BLUE);
bb.setBounds(650,50,250,100);
bb.setFont(new Font("Arial",Font.BOLD,30));
ff.add(bb);
        
aa = new JLabel("USERNAME:");
aa.setVisible(true);
aa.setBounds(550,200,200,100);
aa.setFont(new Font("Arial",Font.BOLD,20));
aa.setForeground(Color.BLACK);
ff.add(aa);
bbb = new JLabel("PASSWORD:");
bbb.setVisible(true);
bbb.setBounds(550,325,200,100);
bbb.setFont(new Font("Arial",Font.BOLD,20));
bbb.setForeground(Color.BLACK);
ff.add(bbb);
c = new JTextField();
c.setVisible(true);
c.setBounds(700,225,200,50);
ff.add(c);
e = new JPasswordField();
e.setForeground(Color.BLACK);
e.setVisible(true);
e.setBounds(700,350,200,50);
ff.add(e);
x=new JButton("LOGIN");
x.setVisible(true);
x.setBounds(750,450,150,50);
x.addActionListener(this);
x.setForeground(Color.BLACK);
    ff.add(x);
    y=new JButton("CANCEL");
    y.setVisible(true);
    y.addActionListener(this);
    y.setBounds(550,450,150,50);
    y.setForeground(Color.BLACK);
    ff.add(y);
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
        ff.getContentPane().add(imagePanel);
    
    
}

    @Override
    public void actionPerformed(ActionEvent ee){
        if (ee.getSource()==x){
            String username = c.getText();
            String password = String.valueOf(e.getPassword());

            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM dataa WHERE id=? AND password=?");
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    User_Page obj = new User_Page();
                } else {
                    JOptionPane.showMessageDialog(this, "INVALID CREDENTIALS");
                }
                conn.close();   
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        if (ee.getSource()==y){
            ff.dispose();
        }
    }
}