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

public class User_Page extends JFrame implements ActionListener {
    JButton aa, bb, cc, dd, k, l, eee, button,bi;
    JLabel imageLabel;
    JLabel a, b, c, d, e, g, h, i, j, u;
    JPanel f;
    Timer timer;
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

    User_Page() {
        ff = new JFrame("USER LOGIN");
        ff.setSize(1600, 850);
        ff.setLocationRelativeTo(null);
        ff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f = new JPanel() {
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
        
                bi = new JButton("BILLS");
        bi.setVisible(true);
        bi.setForeground(Color.WHITE);
        bi.setFont(new Font("TimesNewRoman", Font.BOLD, 15));
        bi.setBackground(Color.BLUE);
        bi.addActionListener(this);
        bi.setBounds(0, 425, 200, 65);
       f.add(bi);

        u = new JLabel("SERVICES INFO");
        u.setVisible(true);
        u.setBounds(550, 0, 400, 65);
        u.setForeground(Color.GREEN);
        u.setFont(new Font("TimesNewRoman", Font.BOLD, 45));
        f.add(u);

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

        s = new JTextField();
        s.setBounds(600, 425, 300, 30);
        f.add(s);
        s.setVisible(false);

        j = new JLabel("CONFIM PASSWORD");
        j.setBounds(250, 475, 300, 30);
        f.add(j);
        j.setVisible(false);
        j.setFont(new Font("Arial", Font.BOLD, 15));
        j.setForeground(Color.BLACK);

        t = new JTextField();
        t.setBounds(600, 475, 300, 30);
        f.add(t);
        t.setVisible(false);

        k = new JButton("REGISTER");
        k.setVisible(false);
        k.addActionListener(this);
        k.setBounds(850, 550, 150, 50);
        k.setFont(new Font("Arial", Font.BOLD, 20));
        k.setForeground(Color.BLACK);
        f.add(k);

        l = new JButton("CANCEL");
        l.setVisible(false);
        l.addActionListener(this);
        l.setBounds(550, 550, 150, 50);
        l.setFont(new Font("Arial", Font.BOLD, 20));
        l.setForeground(Color.BLACK);
        f.add(l);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("JDBC driver not found");
        }

        try {
            originalImage = ImageIO.read(getClass().getResourceAsStream("kkk.png"));
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
        button.setForeground(Color.BLACK);
        button.setBackground(Color.GRAY);
        f.add(button);

        f = new JPanel() {
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
        ff.setVisible(true);
    }

    void updateTime() {
        // Get current date and format it
        currentDate = new Date();
        String formattedDate = dateFormat.format(currentDate);
        dateLabel.setText(formattedDate);
    }

    @Override
    public void actionPerformed(ActionEvent ee) {
        if (ee.getSource() == dd) {
            Billing ob = new Billing();
        }
        if (ee.getSource() == eee) {
            Stockupdates obb = new Stockupdates();
        }
        if (ee.getSource() == button) {
            JOptionPane.showMessageDialog(this, "LOGGED OUT SUCCESSFULLY");
            ff.dispose();
        }
                if (ee.getSource() == bi) {
            PreviousBills previousBills = new PreviousBills();
        }
    }
}

