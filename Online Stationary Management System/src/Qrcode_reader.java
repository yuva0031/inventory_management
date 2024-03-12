/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package online.stationary.management.system;
import java.awt.*;
import javax.swing.*;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
public class Qrcode_reader {

Qrcode_reader(){
Webcam aa = Webcam.getDefault();
aa.setViewSize(new Dimension (320,240));

WebcamPanel b = new WebcamPanel(aa);
b.setMirrored(false);

JFrame f = new JFrame();
f.add(b);
f.pack();
f.setLocationRelativeTo(null);
f.setVisible(true);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
do{
try{
BufferedImage image  = aa.getImage();
LuminanceSource source = new BufferedImageLuminanceSource(image);
BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
Result result = new MultiFormatReader().decode(bitmap);
if (result.getText()!=null){
JOptionPane.showMessageDialog(null, result.getText(), "INFORMATION", JOptionPane.INFORMATION_MESSAGE);
int index = 0;
int i = 0;
ArrayList words = new ArrayList();   
for(i = 0; i < (result.getText()).length(); i++) {
      
      if((result.getText()).charAt(i) == ' ') {  
        
        // If space found then consider it as word and add it to ArrayList
        words.add((result.getText()).substring(index, i));
        index = i + 1;
      }
    }
    words.add((result.getText()).substring(index)); // Adding last word
    try {
            Connection conn;
            Statement stmt;
                conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user_info", "root", "1234");
                String sql = "INSERT INTO billing (prod_id, prod_name, cost) "
           + "VALUES ('" + words.get(0) + "', '" + words.get(1) + "', '" + words.get(2) + "')";
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);                 
                     } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } }

f.setVisible(false);
f.dispose();
aa.close();
break;
}
catch (Exception e){
}

}while(true);
}

}