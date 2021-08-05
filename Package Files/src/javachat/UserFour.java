package javachat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class UserFour extends JFrame implements ActionListener, Runnable{
    
    JPanel panel1;
    JTextField text1;
    JButton btn1;
    static JTextArea area1;
    
    BufferedWriter writer;
    BufferedReader reader;
    
    UserFour(){
        
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(10, 80, 100));
        panel1.setBounds(0, 0, 450, 70);
        add(panel1);
        
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("javachat/icons/3.png"));
        Image img2 = img1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel label1 = new JLabel(img3);
        label1.setBounds(10,25,25,25);
        panel1.add(label1);
        
        label1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
        
        
        ImageIcon img4 = new ImageIcon(ClassLoader.getSystemResource("javachat/icons/1.png"));
        Image img5 = img4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon img6 = new ImageIcon(img5);
        JLabel label2 = new JLabel(img6);
        label2.setBounds(45,10,50,50);
        panel1.add(label2);
        
        ImageIcon img7 = new ImageIcon(ClassLoader.getSystemResource("javachat/icons/video.png"));
        Image img8 = img7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon img9 = new ImageIcon(img8);
        JLabel label5 = new JLabel(img9);
        label5.setBounds(315,20,30,30);
        panel1.add(label5);
        
        ImageIcon img10 = new ImageIcon(ClassLoader.getSystemResource("javachat/icons/phone.png"));
        Image img11 = img10.getImage().getScaledInstance(30, 35, Image.SCALE_DEFAULT);
        ImageIcon img12 = new ImageIcon(img11);
        JLabel label6 = new JLabel(img12);
        label6.setBounds(365,18,30,35);
        panel1.add(label6);
        
        ImageIcon img13 = new ImageIcon(ClassLoader.getSystemResource("javachat/icons/3icon.png"));
        Image img14 = img13.getImage().getScaledInstance(12, 28, Image.SCALE_DEFAULT);
        ImageIcon img15 = new ImageIcon(img14);
        JLabel label7 = new JLabel(img15);
        label7.setBounds(410,22,12,25);
        panel1.add(label7);
        
        JLabel label3 = new JLabel("OOP (Tufahel)");
        label3.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        label3.setForeground(Color.white);
        label3.setBounds(110, 17, 100, 20);
        panel1.add(label3);
        
        JLabel label4 = new JLabel("Samun, Abid, Tafsir.....");
        label4.setFont(new Font("SAN_SERIF", Font.PLAIN, 15));
        label4.setForeground(Color.white);
        label4.setBounds(110, 37, 170, 20);
        panel1.add(label4);
        
        area1 = new JTextArea();
        area1.setBounds(5, 75, 440, 560);
        area1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        area1.setEditable(false);
        area1.setLineWrap(true);
        area1.setWrapStyleWord(true);
        add(area1);
        
        
        text1 = new JTextField();
        text1.setBounds(10, 640, 330, 40);
        text1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(text1);
        
        btn1 = new JButton("Send");
        btn1.setBounds(345, 640, 95, 40);
        btn1.setBackground(new Color(10, 80, 100));
        btn1.setForeground(Color.WHITE);
        btn1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        btn1.addActionListener(this);
        add(btn1);
        
        //getContentPane().setBackground(Color.BLUE);
        setLayout(null);
        setSize(450, 700);
        setLocation(1410, 100);
        setUndecorated(true);
        setVisible(true);
        
        try{
            Socket socketClient = new Socket("localhost", 2001);
            writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        }catch(Exception e){}
    }
    
    public void actionPerformed(ActionEvent ae){
//        String out = text1.getText();
//        area1.setText(area1.getText()+ "\n\t\t\t" + out);
//        text1.setText("");
          String str = "Tufahel\n"+text1.getText()+"\n\t\t\t";
          try{
              writer.write(str);
              writer.write("\r\n");
              writer.flush();
          }catch(Exception e){}
          text1.setText("");
    }
    
    public void run(){
        try{
            String msg = "";
            while((msg = reader.readLine()) != null){
                area1.append(msg + "\n");
            }
        }catch(Exception e){}
    }
    
    public static void main(String[] args){
        UserFour four = new UserFour();
        Thread t1 = new Thread(four);
        t1.start();
    }
}
