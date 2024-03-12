package online.stationary.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Admin_Page extends JFrame implements ActionListener {
    JButton aa, bb, cc, dd, k, l, eee, button,bi;
    JLabel imageLabel;
    JLabel a, b, c, d, e, g, h, i, j, u,re;
        Timer timer;
        JPanel f;
    TimerTask task;
    ImageIcon resizedIcon;
    JTextField m, n, o, p, q, r, s, t;
    Connection conn;
    Image resizedImage;
    Statement stmt;
    JFrame ff;
    Date currentDate;
    JLabel dateLabel;
SimpleDateFormat dateFormat;
    ImageIcon originalIcon;
    BufferedImage originalImage;
    Admin_Page() {
        
        ff = new JFrame();
        ff.setSize(1600, 850);
        ff.getContentPane().setLayout(null);
        ff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ff.setLocationRelativeTo(null);
        f = new JPanel(new FlowLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image = new ImageIcon(getClass().getResource("dma.jpg")).getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            }
        };
        f.setLayout(null);
        f.setBounds(0, 0, 1600, 850);
        ff.getContentPane().add(f);
        
        aa = new JButton("ADD USER");
        aa.setVisible(true);
        aa.setBackground(Color.BLUE);
        aa.setForeground(Color.WHITE);
        aa.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
        aa.setBounds(0, 100, 200, 65);
        aa.addActionListener(this);
        f.add(aa);

        f.setBounds(0, 0, 1600, 850);
        ff.getContentPane().add(f);
        u = new JLabel("SERVICES INFO");
        u.setVisible(true);
        u.setBounds(550, 0, 400, 65);
        u.setForeground(Color.GREEN);
        u.setFont(new Font("TimesNewRoman", Font.BOLD, 45));
        f.add(u);
        bb = new JButton("EDIT USER INFO");
        bb.setVisible(true);
        bb.setForeground(Color.WHITE);
        bb.setBackground(Color.BLUE);
        bb.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
        bb.setBounds(0, 165, 200, 65);
        bb.addActionListener(this);
        f.add(bb);
        
        cc = new JButton("DELETE USER");
        cc.setVisible(true);
        cc.setForeground(Color.WHITE);
        cc.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
        cc.setBackground(Color.BLUE);
        cc.setBounds(0, 230, 200, 65);
        cc.addActionListener(this);
        f.add(cc);
        dd = new JButton("BILLING");
        dd.setVisible(true);
        dd.setForeground(Color.WHITE);
        dd.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
        dd.setBackground(Color.BLUE);
        dd.addActionListener(this);
        dd.setBounds(0, 295, 200, 65);
        f.add(dd);
        eee = new JButton("STOCK UPDATES");
        eee.setVisible(true);
        eee.setForeground(Color.WHITE);
        eee.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
        eee.setBackground(Color.BLUE);
        eee.addActionListener(this);
        eee.setBounds(0, 360, 200, 65);
        f.add(eee);
        bi = new JButton("BILLS");
        bi.setVisible(true);
        bi.setForeground(Color.WHITE);
        bi.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
        bi.setBackground(Color.BLUE);
        bi.addActionListener(this);
        bi.setBounds(0, 425, 200, 65);
        f.add(bi);
        a = new JLabel("EMPLOYEE REGISTRATION");
        a.setBounds(650, 0, 300, 65);
        a.setFont(new Font("Arial", Font.BOLD, 50));
        f.add(a);
        a.setVisible(false);
        a.setFont(new Font("Arial", Font.BOLD, 20));
        a.setForeground(Color.GREEN);
        b = new JLabel("FIRST NAME");
        b.setBounds(250, 75, 300, 30);
        f.add(b);
        b.setVisible(false);
        b.setFont(new Font("Arial", Font.BOLD, 15));
        b.setForeground(Color.BLACK);
        m = new JTextField();
        m.setBounds(600, 75, 300, 30);
        f.add(m);
        m.setVisible(false);
        m = new JTextField();
        m.setBounds(600, 75, 300, 30);
        f.add(m);
        m.setVisible(false);
        c = new JLabel("SURNAME");
        c.setBounds(250, 125, 300, 30);
        f.add(c);
        c.setVisible(false);
        c.setFont(new Font("Arial", Font.BOLD, 15));
        c.setForeground(Color.BLACK);
        n = new JTextField();
        n.setBounds(600, 125, 300, 30);
        f.add(n);
        n.setVisible(false);
        d = new JLabel("PHONE NUMBER");
        d.setBounds(250, 175, 300, 30);
        f.add(d);
        d.setVisible(false);
        d.setFont(new Font("Arial", Font.BOLD, 15));
        d.setForeground(Color.BLACK);
         dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateLabel = new JLabel();
        currentDate = new Date();
        dateLabel.setBounds(1400, 110, 150, 100);
        String formattedDate = dateFormat.format(currentDate);
        dateLabel.setText(formattedDate);
        f.add(dateLabel);

        // Initialize timer and task
        timer = new Timer(true); // Set the timer as a daemon thread
        task = new TimerTask() {
            public void run() {
                updateTime();
            }
        };

        // Schedule the task to run every second (1000 milliseconds)
        timer.scheduleAtFixedRate(task, 0, 1000);
        o = new JTextField();
        o.setBounds(600, 175, 300, 30);
        f.add(o);
        o.setVisible(false);
        e = new JLabel("EMAIL ID");
        e.setBounds(250, 225, 300, 30);
        f.add(e);
        e.setVisible(false);
        e.setFont(new Font("Arial", Font.BOLD, 15));
        e.setForeground(Color.BLACK);
        p = new JTextField();
        p.setBounds(600, 225, 300, 30);
        f.add(p);
        p.setVisible(false);
        g = new JLabel("ADDRESS");
        g.setBounds(250, 275, 300, 30);
        f.add(g);
        g.setVisible(false);
        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.setForeground(Color.BLACK);
        q = new JTextField();
        q.setBounds(600, 275, 300, 75);
        f.add(q);
        q.setVisible(false);
        h = new JLabel("USERNAME");
        h.setBounds(250, 375, 300, 30);
        f.add(h);
        h.setVisible(false);
        h.setFont(new Font("Arial", Font.BOLD, 15));
        h.setForeground(Color.BLACK);
        r = new JTextField();
        r.setBounds(600, 375, 300, 30);
        f.add(r);
        r.setVisible(false);
        i = new JLabel("PASSWORD");
        i.setBounds(250, 425, 300, 30);
        f.add(i);
        i.setVisible(false);
        i.setFont(new Font("Arial", Font.BOLD, 15));
        i.setForeground(Color.BLACK);
        re = new JLabel("Password must contain  1-9, a-z, A-Z and  Special Characters");
        re.setBounds(600, 400, 300, 200);
        f.add(re);
        re.setVisible(false);
        re.setFont(new Font("Arial", Font.BOLD, 12));
        re.setForeground(Color.BLACK);        
        s = new JTextField();
        s.setBounds(600, 425, 300, 30);
        f.add(s);
        s.setVisible(false);
        j = new JLabel("CONFIM PASSWORD");
        j.setBounds(250, 525, 300, 30);
        f.add(j);
        j.setVisible(false);
        j.setFont(new Font("Arial", Font.BOLD, 15));
        j.setForeground(Color.BLACK);
        t = new JTextField();
        t.setBounds(600, 525, 300, 30);
        f.add(t);
        t.setVisible(false);
        k = new JButton("REGISTER");
        k.setVisible(false);
        k.addActionListener(this);
        k.setBounds(850, 600, 150, 50);
        k.setFont(new Font("Arial", Font.BOLD, 20));
        k.setForeground(Color.BLACK);
        f.add(k);
        l = new JButton("CANCEL");
        l.setVisible(false);
        l.addActionListener(this);
        l.setBounds(550, 600, 150, 50);
        l.setFont(new Font("Arial", Font.BOLD, 20));
        l.setForeground(Color.BLACK);
        f.add(l);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC driver not found");
        } 
try {
    originalImage = ImageIO.read(getClass().getResourceAsStream("Pho.jpg"));
} catch (IOException e) {
    e.printStackTrace();
    return;
}
resizedImage = originalImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
resizedIcon = new ImageIcon(resizedImage);
imageLabel = new JLabel(resizedIcon);
imageLabel.setBounds(1400, 25, resizedIcon.getIconWidth(), resizedIcon.getIconHeight());
f.add(imageLabel);
    button = new JButton("LOG OUT");
        button.setVisible(true);
        button.addActionListener(this);
        button.setBounds(1400, 125, 100, 30);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setForeground(Color.black);
        button.setBackground(Color.white);
        f.add(button);
        
        ff.setVisible(true);
    }
            void updateTime() {
        // Get current date and format it
        currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);
        dateLabel.setText(formattedDate);
    }
     /*       boolean isValidLogin(String password, String confirmPass) {
        // Perform your validation logic here
        // For demonstration purposes, let's assume the correct username is "balaji" and password is "Balajich44_"
        return password.equals("Balajich44_") && confirmPass.equals(password);
    }*/
    @Override
    public void actionPerformed(ActionEvent ee) {
        if (ee.getSource() == aa) {
            u.setVisible(false);
            a.setVisible(true);
            b.setVisible(true);
            c.setVisible(true);
            d.setVisible(true);
            e.setVisible(true);
            g.setVisible(true);
            h.setVisible(true);
            i.setVisible(true);
            j.setVisible(true);
            k.setVisible(true);
            l.setVisible(true);
            m.setVisible(true);
            n.setVisible(true);
            o.setVisible(true);
            p.setVisible(true);
            q.setVisible(true);
            r.setVisible(true);
            s.setVisible(true);
            t.setVisible(true);
            re.setVisible(true);
        }

        if (ee.getSource() == k) {
    if (!s.getText().equals(t.getText())) {
        JOptionPane.showMessageDialog(this, "Password doesn't match");
    } else if (m.getText().isEmpty() || n.getText().isEmpty() || o.getText().isEmpty() || p.getText().isEmpty() || q.getText().isEmpty() || r.getText().isEmpty() || s.getText().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Fields cannot be empty");
    } else if (s.getText().length() < 8) {
        JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long");
    } else if (s.getText().equals(t.getText())) {
        boolean containsUppercase = false;
        boolean containsDigit = false;
        boolean containsSpecialChar = false;

        for (char c : s.getText().toCharArray()) {
            if (Character.isUpperCase(c)) {
                containsUppercase = true;
            } else if (Character.isDigit(c)) {
                containsDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                containsSpecialChar = true;
            }

            if (containsUppercase && containsDigit && containsSpecialChar) {
                break;
            }
        }

        if (containsUppercase && containsDigit && containsSpecialChar) {
                        try {
                conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
                String firstname = m.getText();
                String lastname = n.getText();
                String phoneno = o.getText();
                String emailid = p.getText();
                String address = q.getText();
                String username = r.getText();
                String password = s.getText();
                String sql = "INSERT INTO dataa (id, password, first_name, surname, email, phoneno, address) "
                        + "VALUES ('" + username + "', '" + password + "', '" + firstname + "', '" + lastname + "', '"
                        + emailid + "', '" + phoneno + "', '" + address + "')";
                stmt = conn.createStatement();
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(this, "USER REGISTERED SUCCESSFULLY");
                ff.dispose();
                Admin_Page obj = new Admin_Page();

            } catch (Exception se) {
                JOptionPane.showMessageDialog(this, "ERROR IN REGISTERING " + se.getMessage());
            }
        }
             else {
                JOptionPane.showMessageDialog(this, "The password is not strong enough. It should contain at least one uppercase letter, one digit, and one special character");
            }
        } else {
            JOptionPane.showMessageDialog(this, "The password is not strong enough. It should contain at least one uppercase letter, one digit, and one special character");
        }
    }         
        if (ee.getSource() == dd) {
            try{Billing ob = new Billing();
            System.out.println("BIlling");
            }catch(Exception eee ){
            System.out.println(eee.getMessage());
            }
        }
        if (ee.getSource() == eee) {
            Stockupdates obb = new Stockupdates();
        }
        if (ee.getSource() == bb) {
            UpdateUser obb1 = new UpdateUser();
 
        }
        if (ee.getSource() == cc) {
            DeleteUser ob2 = new DeleteUser();
        }    
        if (ee.getSource() == button) {
            JOptionPane.showMessageDialog(this, "LOGGED OUT SUCCESSFULLY");
            ff.dispose();}
        if (ee.getSource() == bi) {
            PreviousBills previousBills = new PreviousBills();
        }
        
    }
}


   

