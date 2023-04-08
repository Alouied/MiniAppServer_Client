import java.net.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Client extends JFrame implements ActionListener{
    TextField theure,tdate;
    JButton b1,b2;
    Client(){
        theure = new TextField(10);
        b1 = new JButton("Heure");
        JPanel p1 = new JPanel();
        p1.add(theure);p1.add(b1);
        tdate = new TextField(10);
        b2 = new JButton("Date");
        JPanel p2 = new JPanel();
        p2.add(tdate);p2.add(b2);
        add(p1,"North");add(p2,"South");
        b1.addActionListener(this);
        b2.addActionListener(this);
        pack();setVisible(true);
    }

    
    public static void main(String[] args){
        Client fen = new Client();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            Socket sc = new Socket(InetAddress.getLocalHost(),2000);
            PrintWriter sout = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()));
            BufferedReader sin = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            JButton bs = (JButton)e.getSource();
            if(bs == b1){
                sout.println("Heure");
                sout.flush();
                theure.setText(sin.readLine());
            }
            else {
                sout.println("Date");
                sout.flush();
                tdate.setText(sin.readLine());
            }
            sin.close();sout.close();
        } catch (IOException ee) {
            // TODO: handle exception
        }    
    }
}
