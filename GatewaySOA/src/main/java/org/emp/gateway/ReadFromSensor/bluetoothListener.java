/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.emp.gateway.ReadFromSensor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.Thread.sleep;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.microedition.io.StreamConnection;

public class bluetoothListener {
     Timer timer;
     int i;
     byte[] b = new byte[5];
     String result="";
    public void funct(int x,StreamConnection streamConnection,InputStream is){
       timer = new Timer();
       i=0;
       
       timer.scheduleAtFixedRate(new TimerTask() {
           long t0 = System.currentTimeMillis();
            
            @Override
            public void run() {
                if (System.currentTimeMillis() - t0 > x * 1000) {
                    timer.cancel();
                    System.out.println("end");  
                    
              }else{                    
                    try {
                        is.read(b);
                    } catch (IOException ex) {
                        Logger.getLogger(bluetoothListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        String received = new String(b);
                        System.out.println("receive from BT: "+received);
                       // out.println(received); //ça c pour ecrire dans le canal wifi établi enntre le tlf et le raspberry
     
                }
            }
           
       }, 0, 1000);
       
    }
    
    
    public List<String> funct2(InputStream is,int tempsDEcoute) throws IOException{
        List<String> results=new ArrayList<>();
        timer = new Timer();
        System.err.println("Receiving ... ");
        timer.scheduleAtFixedRate(new TimerTask() {
            long t0 = System.currentTimeMillis();
            @Override
            public void run() {
                if (System.currentTimeMillis() - t0 > tempsDEcoute * 1000) {
                    timer.cancel();
                }
                else{ 
                    try {
                        is.read(b);
                        result = new String(b);
                       // System.out.println("receive from BT: "+result);
                        results.add(result);
                    } catch (IOException ex) {
                        Logger.getLogger(bluetoothListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }, 0, 1000);
         try {
             sleep(3000);
         } catch (InterruptedException ex) {
             Logger.getLogger(bluetoothListener.class.getName()).log(Level.SEVERE, null, ex);
         }
        return results;
    }
    
    public void readAndWriteInFile(InputStream is,String fileName,int tempsDEcoute ) throws IOException{
        
        
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            BufferedWriter sortie = new BufferedWriter(new FileWriter(fileName, true));
            long t0 = System.currentTimeMillis();
            @Override
            public void run() {
                if (System.currentTimeMillis() - t0 > tempsDEcoute * 1000) {
                     System.err.println("end of mesures timer");
                    try {
                        sortie.close();
                    } catch (IOException ex) {
                        Logger.getLogger(bluetoothListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    timer.cancel();
                }
                else{
                    try {
                        is.read(b);
                        result = new String(b);            
                        DateFormat format = new SimpleDateFormat();
                        Date date = new Date();
                        
                        sortie.write(date.toGMTString().subSequence(0, 11).toString()+"\n");
                        sortie.write(date.getHours()+":"+date.getMinutes()+":"+date.getSeconds()+"\n");
                        sortie.write(result+"\n");
                        sortie.write("----------"+"\n");
                    } catch (IOException ex) {
                        Logger.getLogger(bluetoothListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }, 0, 1000);
         
     
    }
}
