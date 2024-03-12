import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
public class User extends JFrame implements ActionListener{
JFrame ff;
JLabel bbb,bb,aa,cc,dd;
JPasswordField e;
JTextField a,b,d;
JPasswordField c;
JButton x;
JButton y;
    Connection conn;
    Statement stmt;

User(){ 
ff = new JFrame("ADMIN_PAGE");
ff.setVisible(true);
ff.setSize(400,400);
ff.setLayout(null);
ff.setBackground(Color.BLUE);
bb = new JLabel("USER CREATION");
bb.setVisible(true);
bb.setForeground(Color.BLUE);
bb.setBounds(90,0,250,100);
bb.setFont(new Font("Arial",Font.BOLD,20));
ff.add(bb);
aa = new JLabel("NAME:");
aa.setVisible(true);
aa.setBounds(40,90,100,50);
aa.setFont(new Font("Arial",Font.BOLD,15));
aa.setForeground(Color.red);
ff.add(aa);
bb = new JLabel("USERNAME:");
bb.setVisible(true);
bb.setBounds(40,130,100,50);
bb.setFont(new Font("Arial",Font.BOLD,15));
bb.setForeground(Color.red);
ff.add(bb);
cc = new JLabel("PASSWORD:");
cc.setVisible(true);
cc.setBounds(40,170,100,50);
cc.setFont(new Font("Arial",Font.BOLD,15));
cc.setForeground(Color.red);
ff.add(cc);
dd = new JLabel("USERTYPE:");
dd.setVisible(true);
dd.setBounds(40,210,100,50);
dd.setFont(new Font("Arial",Font.BOLD,15));
dd.setForeground(Color.red);
ff.add(dd);
a = new JTextField();
a.setVisible(true);
a.setBounds(150,143,200,30);
ff.add(a);
b = new JTextField();
b.setVisible(true);
b.setBounds(150,100,200,30);
ff.add(b);
c = new JPasswordField();
c.setVisible(true);
c.setBounds(150,186,200,30);
ff.add(c);
d = new JTextField();
d.setVisible(true);
d.setBounds(150,229,200,30);
ff.add(d);

x=new JButton("ADD");
x.setVisible(true);
x.setBounds(70,270,75,50);
x.addActionListener(this);
x.setForeground(Color.blue);
ff.add(x);
y=new JButton("CANCEL");
y.setVisible(true);
y.addActionListener(this);
y.setBounds(180,270,100,50);
y.setForeground(Color.blue);
ff.add(y);
   try {
    Class.forName("com.mysql.cj.jdbc.Driver");
    
} catch (ClassNotFoundException ex) {
    System.out.println("JDBC driver not found");
}    
}
public void actionPerformed(ActionEvent ee){
    if (ee.getSource()==x){
        try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare","root","Rakesh@108");
    String name = b.getText();
    String username = a.getText();
    String password = c.getText();
    String type = d.getText();
    String sql = "INSERT INTO dataa (name, username, password, utype) "
           + "VALUES ('" + name + "', '" + username + "', '" + password + "', '" + type + "')";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
                }                
     catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
}
}



    
public static void main(String[] args){
User ob = new User();
        };
}

