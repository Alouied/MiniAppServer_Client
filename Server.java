import java.net.*;
import java.util.Calendar;
import java.util.Date;
import java.io.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;


public class Server {
    public void traiter () throws IOException{
        ServerSocket ss = new ServerSocket(2000);
        while(true){
            Socket sc = ss.accept();
            BufferedReader sin = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            String msg = sin.readLine();
            String ch ="";
            if(msg.equals("Heure")){
                Calendar c = Calendar.getInstance();
                Date d = c.getTime();
                int h = (int) d.getHours();
                int m = d.getMinutes();
                int s = d.getSeconds();
                ch = h + ":" + m + ":" + s;
            }else {
                Calendar c = Calendar.getInstance();
                Date d = c.getTime();
                int j = (int) d.getDay();
                int mois = d.getMonth();
                int y = d.getYear();
                ch = d.getMonth() + "/" + d.getDay() + "/" + d.getYear();
            }
            PrintWriter sout = new PrintWriter(new OutputStreamWriter(sc.getOutputStream()));
            sout.println(ch);
            sout.flush();
            sc.close();
        }
    }
    
    public static void main (String [] args) {
        Server s = new Server ();
        try {
            s.traiter();
        } catch (Exception e) {
            System.out.println ("Err");
        }
    }
}
