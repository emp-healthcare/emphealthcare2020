/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatewayguiswing;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;

/**
 *
 * @author blunt
 */
public class Sensor {
   
    private String name;
    private String CommunicationSupport;
    private String ID;
    private String type;
    String commande;
    String hc05URL="";

    public String getHc05UR() {
        return hc05URL;
    }

    public void setHc05UR(String hc05UR) {
        this.hc05URL = hc05UR;
    }

    public String getCommande() {
        return commande;
    }

    public void setCommande(String commande) {
        this.commande = commande;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    
    // BT information :
    String btspp;
    boolean authenticate,encrypt,master;

    //Wifi Information
    
    //Zigbee Information

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommunicationSupport() {
        return CommunicationSupport;
    }

    public void setCommunicationSupport(String CommunicationSupport) {
        this.CommunicationSupport = CommunicationSupport;
    }

    public String getBtspp() {
        return btspp;
    }

    public void setBtspp(String btspp) {
        this.btspp = btspp;
    }

    public boolean isAuthenticate() {
        return authenticate;
    }

    public void setAuthenticate(boolean authenticate) {
        this.authenticate = authenticate;
    }

    public boolean isEncrypt() {
        return encrypt;
    }

    public void setEncrypt(boolean encrypt) {
        this.encrypt = encrypt;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }
    
    
    
   

}
