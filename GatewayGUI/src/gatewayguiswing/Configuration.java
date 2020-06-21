/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatewayguiswing;

import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.plaf.jpbc.util.io.Base64;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import org.emp.clpkc.CLPKC;

/**
 *
 * @author blunt
 */
public class Configuration extends javax.swing.JFrame {

    ButtonGroup CommunicationSupportGrp;
    private CLPKC noeud;

    public Configuration() {
        initComponents();
        
        
        String urlIcon="Ressources/profile/profile.png";
        Icon ic=new ImageIcon(urlIcon);             
         icon.setIcon(ic);
        
        noeud = new CLPKC();
        this.setResizable(false);
        //List of sensors
        cb.setToolTipText("Saved sensors");
        cb.removeAllItems();

        List<String> sensorsNames = sensorsList();
        if (sensorsNames != null) {
            for (int i = 0; i < sensorsNames.size(); i++) {
                 String s=sensorsNames.get(i);
                String j=(s.replace("capteur.", "")).replace("@emp.dz", "");
               // model.addElement(j);
                cb.addItem(j);
            }
        }

        CommunicationSupportGrp = new ButtonGroup();
        CommunicationSupportGrp.add(BT);
        CommunicationSupportGrp.add(Wifi);
        CommunicationSupportGrp.add(Zigbee);

        sensorDataPane.setVisible(false);
        updateSensor.setVisible(false);

        //Chercher monitoring Parameters :
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader("Ressources/MonitoringParams.txt"));
            String line;
            int i = 0;
            while ((line = in.readLine()) != null) {
                i++;
                switch (i) {
                    case 1:
                        if (line.equals("1")) {
                            Recod.setSelected(true);
                        } else {
                            Recod.setSelected(false);
                        }
                        break;
                    case 2:
                        if (line.equals("1")) {
                            graph.setSelected(true);
                        } else {
                            graph.setSelected(false);
                        }
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private List<String> sensorsList() {

        BufferedReader in = null;
        try {
            //From File
            in = new BufferedReader(new FileReader("Ressources/sensorsList.txt"));
            String line;
            List<String> s = new ArrayList<>();
            while ((line = in.readLine()) != null) {
                s.add(line);
            }
            in.close();
            return s;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    private Sensor searchSensorByName(String SensorName) throws FileNotFoundException {

        Sensor s = new Sensor();
        s.setName(SensorName);

        try {
            //Get Data du capteur :
            //Ouvertur du fichier :
            BufferedReader in = new BufferedReader(new FileReader("Ressources/SensorsData/" + SensorName + ".txt"));
            String line;
            int i = 0;
            while ((line = in.readLine()) != null) {
                i++;
                switch (i) {
                    case 2:
                        s.setCommande(line);
                        break;
                    case 3:
                        s.setType(line);
                        break;
                    case 4:
                        s.setCommunicationSupport(line);
                        break;
                    case 5:
                        if (s.getCommunicationSupport().equals("Bluetooth")) {

                            s.setHc05UR(line);

                            s.setBtspp((String) line.subSequence(8, 22));
                            if (((String) line.subSequence(36, 41)).equals("true")) {
                                s.setAuthenticate(true);
                            } else {
                                s.setAuthenticate(false);
                            }
                            if (((String) line.subSequence(50, 55)).equals("true")) {
                                s.setEncrypt(true);
                            } else {
                                s.setEncrypt(false);
                            }

                            if (((String) line.subSequence(63, 68)).equals("true")) {
                                s.setMaster(true);
                            } else {
                                s.setMaster(false);
                            }

                        }
                        break;
                    case 6:
                        s.setID(line);
                        break;
                }
            }

            return s;

        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private void viewBtParameters() {
        support.setText("Bluetooth parameters :");
        btspp.setVisible(true);
        btsppNum.setVisible(true);
        authenticate.setVisible(true);
        encrypt.setVisible(true);
        master.setVisible(true);
    }

    private void hideBtParameters() {
        support.setText("");
        btspp.setVisible(false);
        btsppNum.setVisible(false);
        authenticate.setVisible(false);
        encrypt.setVisible(false);
        master.setVisible(false);
    }

    private void viewSelectedSensorData(Sensor s) {
        sensorDataPane.setVisible(true);
        sensorDataPane.setEnabled(false);

        cancel.setVisible(false);
        save.setVisible(false);
        key.setText("");
        kgcFinalField.setText("");
        KGCEMr.setText("");
        KGCPatient.setText("");

        // sensorsDataLabel.setText("Selected sensor's data :");
        sensorNameField.setText(s.getName());
        TypeSensor.setText(s.getType());
        // sensorID.setText(s.getID());
        support.setText(s.getCommunicationSupport() + " parameters");

        if (s.getCommunicationSupport().equals("Bluetooth")) {
            BT.setSelected(true);
            Wifi.setSelected(false);
            Zigbee.setSelected(false);

            viewBtParameters();
            btsppNum.setText(s.getBtspp());
            authenticate.setSelected(s.isAuthenticate());
            master.setSelected(s.isMaster());
            encrypt.setSelected(s.isEncrypt());
        } else {
            BT.setSelected(false);
            Wifi.setSelected(true);
            Zigbee.setSelected(false);
            hideBtParameters();
        }

        try {
            //Les Credits CLPKC
            BufferedReader in = new BufferedReader(new FileReader("Ressources/ParametresKGCFinaux/" + s.getName() + "KGCFinal.txt"));
            situation.setText("KGC final   exists");
            kgcFinalField.setText(("Ressources/ParametresKGCFinaux/" + s.getName() + "KGCFinal.txt"));

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(ConfigController2.class.getName()).log(Level.SEVERE, null, ex);
            situation.setText("KGC final does not exist");
        }

        //La clé Symetrique
        try {
            BufferedReader in = new BufferedReader(new FileReader("Ressources/SymetricKeys/" + s.getName() + "Key.txt"));
            String line;
            System.out.println("Syemetric Key exists");
            String keyS = "";
            while ((line = in.readLine()) != null) {
                System.err.println(line);
                System.err.println("1");
                key.setText(line);
                keyS = line;
            }
            key.setText(keyS);
        } catch (FileNotFoundException ex) {
            //  Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Symetric Key File does not exist");
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Disconnect = new javax.swing.JButton();
        sensorDataPane = new javax.swing.JPanel();
        sensorListPane = new javax.swing.JPanel();
        icon = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cb = new javax.swing.JComboBox<>();
        updateSensor = new javax.swing.JButton();
        addNewSensor = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        Recod = new javax.swing.JCheckBox();
        graph = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        sensorNameField = new javax.swing.JTextField();
        TypeSensor = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        BT = new javax.swing.JRadioButton();
        Wifi = new javax.swing.JRadioButton();
        Zigbee = new javax.swing.JRadioButton();
        SupportPane = new javax.swing.JPanel();
        support = new javax.swing.JLabel();
        btspp = new javax.swing.JLabel();
        btsppNum = new javax.swing.JTextField();
        authenticate = new javax.swing.JCheckBox();
        encrypt = new javax.swing.JCheckBox();
        master = new javax.swing.JCheckBox();
        jSeparator6 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        key = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        EMRAddress = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        support1 = new javax.swing.JLabel();
        situation = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        KGCPatient = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        KGCEMr = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        kgcFinalField = new javax.swing.JTextField();
        support2 = new javax.swing.JLabel();
        cancel = new javax.swing.JButton();
        save = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Disconnect.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Disconnect.setText("Disconnect");
        Disconnect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DisconnectMouseClicked(evt);
            }
        });
        jPanel1.add(Disconnect, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 10, -1, -1));

        sensorDataPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(sensorDataPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, -1, 450));

        sensorListPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        sensorListPane.add(icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 80, 102));
        sensorListPane.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 161, 13));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 255));
        jLabel2.setText("Sensors");
        sensorListPane.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        cb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbItemStateChanged(evt);
            }
        });
        cb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbActionPerformed(evt);
            }
        });
        sensorListPane.add(cb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 175, -1));

        updateSensor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        updateSensor.setText("Update");
        updateSensor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateSensorMouseClicked(evt);
            }
        });
        sensorListPane.add(updateSensor, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, -1, -1));

        addNewSensor.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addNewSensor.setText("Add New");
        addNewSensor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addNewSensorMouseClicked(evt);
            }
        });
        sensorListPane.add(addNewSensor, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 255));
        jLabel3.setText("Monitoring Parameters");
        sensorListPane.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 260, -1, -1));
        sensorListPane.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 161, 13));

        Recod.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Recod.setText("Enable Recording");
        sensorListPane.add(Recod, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        graph.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        graph.setText("Show graphe");
        sensorListPane.add(graph, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, -1, -1));
        sensorListPane.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 161, 13));

        jPanel1.add(sensorListPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 210, 380));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 420));

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jPanel2.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 255));
        jLabel4.setText("Sensors ID");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 30, 100, 17);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 255));
        jLabel6.setText("Type");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(10, 70, 60, 17);

        sensorNameField.setText("Sensor name");
        jPanel2.add(sensorNameField);
        sensorNameField.setBounds(130, 20, 238, 30);

        TypeSensor.setText("Type");
        jPanel2.add(TypeSensor);
        TypeSensor.setBounds(130, 60, 238, 30);
        jPanel2.add(jSeparator5);
        jSeparator5.setBounds(0, 100, 422, 11);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 255));
        jLabel5.setText("Communication support");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(10, 140, 210, 17);

        BT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        BT.setText("Bluetooth");
        BT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                BTItemStateChanged(evt);
            }
        });
        jPanel2.add(BT);
        BT.setBounds(230, 120, 110, 23);

        Wifi.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Wifi.setText("Wifi");
        Wifi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WifiActionPerformed(evt);
            }
        });
        jPanel2.add(Wifi);
        Wifi.setBounds(230, 140, 80, 23);

        Zigbee.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Zigbee.setText("Zigbee");
        jPanel2.add(Zigbee);
        Zigbee.setBounds(230, 160, 90, 23);

        SupportPane.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        support.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        support.setForeground(new java.awt.Color(0, 51, 255));
        support.setText("Support");
        SupportPane.add(support, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        btspp.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btspp.setText("BTSPP");
        SupportPane.add(btspp, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, -1, -1));
        SupportPane.add(btsppNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 270, 30));

        authenticate.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        authenticate.setText("Authenticate");
        SupportPane.add(authenticate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, -1, -1));

        encrypt.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        encrypt.setText("Encrypt");
        SupportPane.add(encrypt, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        master.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        master.setText("Master");
        SupportPane.add(master, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));
        SupportPane.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 390, 30));

        jPanel2.add(SupportPane);
        SupportPane.setBounds(0, 210, 380, 170);

        jTabbedPane1.addTab("General", jPanel2);

        jPanel3.setLayout(null);

        key.setText(" ");
        jPanel3.add(key);
        key.setBounds(10, 40, 430, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("EMR Address");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(10, 100, 110, 15);
        jPanel3.add(EMRAddress);
        EMRAddress.setBounds(130, 90, 190, 30);

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton4.setText("Generate");
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jPanel3.add(jButton4);
        jButton4.setBounds(330, 90, 110, 30);

        support1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        support1.setForeground(new java.awt.Color(0, 51, 255));
        support1.setText("CLPKC Credits");
        jPanel3.add(support1);
        support1.setBounds(10, 130, 130, 17);

        situation.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        situation.setForeground(new java.awt.Color(204, 0, 51));
        situation.setText(" ");
        jPanel3.add(situation);
        situation.setBounds(150, 140, 270, 20);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("KGC Patient");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(10, 160, 100, 15);

        KGCPatient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KGCPatientMouseClicked(evt);
            }
        });
        jPanel3.add(KGCPatient);
        KGCPatient.setBounds(130, 150, 310, 30);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Aggreagte");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2);
        jButton2.setBounds(210, 270, 130, 30);

        KGCEMr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KGCEMrMouseClicked(evt);
            }
        });
        jPanel3.add(KGCEMr);
        KGCEMr.setBounds(130, 220, 310, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("KGC EMR");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(10, 230, 100, 15);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("KGC Final");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(10, 320, 80, 15);

        kgcFinalField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kgcFinalFieldMouseClicked(evt);
            }
        });
        jPanel3.add(kgcFinalField);
        kgcFinalField.setBounds(130, 310, 310, 30);

        support2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        support2.setForeground(new java.awt.Color(0, 51, 255));
        support2.setText("Encryption Key");
        jPanel3.add(support2);
        support2.setBounds(10, 10, 160, 17);

        jTabbedPane1.addTab("CL-PKC", jPanel3);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 520, 370));

        cancel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cancel.setText("Cancel");
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
        });
        getContentPane().add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, -1, -1));

        save.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        save.setText("Save");
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveMouseClicked(evt);
            }
        });
        getContentPane().add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 390, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addNewSensorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addNewSensorMouseClicked
        // TODO add your handling code here:
        sensorDataPane.setVisible(true);
        sensorDataPane.setEnabled(true);
        cancel.setVisible(true);
        save.setVisible(true);

        sensorListPane.setVisible(false);
        save.setText("Save");
    }//GEN-LAST:event_addNewSensorMouseClicked

    private void updateSensorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateSensorMouseClicked
        // TODO add your handling code here:
        sensorDataPane.setEnabled(true);
        cancel.setVisible(true);
        save.setVisible(true);

        sensorListPane.setVisible(false);
        save.setText("Save modefication");
    }//GEN-LAST:event_updateSensorMouseClicked

    private void cbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbActionPerformed

    private void cbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbItemStateChanged

        String SensorName = (String) cb.getSelectedItem();

        if (SensorName != null) {
            Sensor s = null;
            try {
                s = searchSensorByName("capteur."+SensorName+"@emp.dz");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (s != null) {
                viewSelectedSensorData(s);
                updateSensor.setVisible(true);
            }
        }
    }//GEN-LAST:event_cbItemStateChanged

    private void DisconnectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DisconnectMouseClicked
        PrintWriter writer = null;
        try {
            // TODO add your handling code here:
            //Saving parameters :
            writer = new PrintWriter("Ressources/MonitoringParams.txt", "UTF-8");
            if (Recod.isSelected()) {
                writer.println("1");
            } else {
                writer.println("0");
            }
            if (graph.isSelected()) {
                writer.println("1");
            }
            writer.println("0");
            writer.close();

            Home home = new Home();
            home.setVisible(true);
            this.dispose();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            writer.close();
        }
    }//GEN-LAST:event_DisconnectMouseClicked

    private void saveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMouseClicked
        // TODO add your handling code here:
        String m = save.getText();
        Sensor s = new Sensor();

        int i = CommunicationSupport();
        if (i == 1) {
            s.setCommunicationSupport("Bluetooth");
            s.setAuthenticate(authenticate.isEnabled());
            s.setBtspp(btsppNum.getText());
            s.setEncrypt(encrypt.isEnabled());
            s.setMaster(master.isEnabled());
        } else if (i == 2) {
            s.setCommunicationSupport("Wifi");
        } else {
            s.setCommunicationSupport("Zigbee");
        }

        s.setName(sensorNameField.getText());

        if (m.equals("Save")) {

            saveNewSensor(s);
        } else {
            updateSensorByName(s);
        }
    }//GEN-LAST:event_saveMouseClicked

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        // TODO add your handling code here:
        sensorDataPane.setVisible(false);
        sensorListPane.setVisible(true);
        //SensorSelectionModified();
    }//GEN-LAST:event_cancelMouseClicked

    private void kgcFinalFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kgcFinalFieldMouseClicked
        // TODO add your handling code here:
        JFileChooser j = new JFileChooser("Ressources");
        int r = j.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            // set the label to the path of the selected file
            kgcFinalField.setText(j.getSelectedFile().getAbsolutePath());
        } // if the user cancelled the operation
        else {
            System.out.println("the user cancelled the operation");
        }
    }//GEN-LAST:event_kgcFinalFieldMouseClicked

    private void KGCEMrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KGCEMrMouseClicked
        // TODO add your handling code here:
        JFileChooser j = new JFileChooser("Ressources");
        int r = j.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            // set the label to the path of the selected file
            KGCEMr.setText(j.getSelectedFile().getAbsolutePath());
        } // if the user cancelled the operation
        else {
            System.out.println("the user cancelled the operation");
        }
    }//GEN-LAST:event_KGCEMrMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        // TODO add your handling code here:
        Aggregate();
    }//GEN-LAST:event_jButton2MouseClicked

    private void KGCPatientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KGCPatientMouseClicked
        // TODO add your handling code here:
        JFileChooser j = new JFileChooser("Ressources");
        int r = j.showSaveDialog(null);
        if (r == JFileChooser.APPROVE_OPTION) {
            // set the label to the path of the selected file
            KGCPatient.setText(j.getSelectedFile().getAbsolutePath());
        } else {
            System.out.println("the user cancelled the operation");
        }
    }//GEN-LAST:event_KGCPatientMouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        // TODO add your handling code here:
        boolean c = showParams();
        if (c) {
            String t = EMRAddress.getText();
            if (!t.equals("")) {
                Generate();
            } else {
                situation.setText("Insert EMR Address");
            }
        } else {
            situation.setText("KGCFinal does'nt exist");
        }
    }//GEN-LAST:event_jButton4MouseClicked

    private void WifiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WifiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_WifiActionPerformed

    private void BTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_BTItemStateChanged
        // TODO add your handling code here:
        //CommunicationSuportChange();
    }//GEN-LAST:event_BTItemStateChanged

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void saveNewSensor(Sensor s) {
        sensorDataPane.setVisible(false);
        sensorListPane.setVisible(true);
    }

    private void updateSensorByName(Sensor s) {
        sensorDataPane.setVisible(false);
        sensorListPane.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Configuration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Configuration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Configuration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Configuration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Configuration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton BT;
    private javax.swing.JButton Disconnect;
    private javax.swing.JTextField EMRAddress;
    private javax.swing.JTextField KGCEMr;
    private javax.swing.JTextField KGCPatient;
    private javax.swing.JCheckBox Recod;
    private javax.swing.JPanel SupportPane;
    private javax.swing.JTextField TypeSensor;
    private javax.swing.JRadioButton Wifi;
    private javax.swing.JRadioButton Zigbee;
    private javax.swing.JButton addNewSensor;
    private javax.swing.JCheckBox authenticate;
    private javax.swing.JLabel btspp;
    private javax.swing.JTextField btsppNum;
    private javax.swing.JButton cancel;
    private javax.swing.JComboBox<String> cb;
    private javax.swing.JCheckBox encrypt;
    private javax.swing.JCheckBox graph;
    private javax.swing.JLabel icon;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField key;
    private javax.swing.JTextField kgcFinalField;
    private javax.swing.JCheckBox master;
    private javax.swing.JButton save;
    private javax.swing.JPanel sensorDataPane;
    private javax.swing.JPanel sensorListPane;
    private javax.swing.JTextField sensorNameField;
    private javax.swing.JLabel situation;
    private javax.swing.JLabel support;
    private javax.swing.JLabel support1;
    private javax.swing.JLabel support2;
    private javax.swing.JButton updateSensor;
    // End of variables declaration//GEN-END:variables

    private int CommunicationSupport() { //retourner le choix de l'utilisateur chifferer/dechiffrer
        /*if (CommunicationSupportGrp.getSelection().equals(BT)) {
            return 1;
        } else if (CommunicationSupportGrp.getSelection().equals(Wifi)) {
            return 2;
        } else {
            return 3;
        }
         */
        return 3;
    }

    private void CommunicationSuportChange() {
        SupportPane.setVisible(true);
        int support = CommunicationSupport();
        System.err.println(support);
        if (support == 1) {
            viewBtParameters();
        } else if (support == 2) {
            hideBtParameters();
        } else {
            hideBtParameters();
        }
    }

    private void Generate() {
        // TODO add your handling code here:

        System.out.println("Génération de la clé Symetrique");
        String serverAdd = EMRAddress.getText();
        noeud.setADRESSE_SERVEUR(serverAdd);
        try {
            noeud.generationCle();
            situation.setText("Kei generated");
            key.setText(Base64.encodeBytes(noeud.K.toBytes()));

            System.out.println(("Clé symétrique: " + noeud.K + "\n"));
            System.out.println(Base64.encodeBytes(noeud.K.toBytes()));
            //Enregistrement du clé ICI : ("Ressources/SymetricKeys/" + sensorNameField.getText() + "Key.txt")
            noeud.getID();
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean showParams() {
        String sensorName = sensorNameField.getText();

        try {
            BufferedReader in = new BufferedReader(new FileReader("Ressources/ParametresKGCFinaux/" + sensorName + "KGCFinal.txt"));

            //Lecture des parametres :            
            String line;
            int i = 0;
            while ((line = in.readLine()) != null) {
                i++;
                switch (i) {
                    case 1: //ID
                        noeud.init(line);
                        noeud.setID(line);
                        System.out.println(line);
                        break;
                    case 2: //PKGC
                        Element PKGC = noeud.pairing.getG1().newRandomElement();
                        PKGC.setFromBytes(Base64.decode(line));
                        noeud.PKGC = PKGC.duplicate();
                        System.out.println(Base64.encodeBytes(PKGC.toBytes()));
                        break;
                    case 3: //PPubKGC
                        Element PPubKGC = noeud.pairing.getG1().newRandomElement();
                        PPubKGC.setFromBytes(Base64.decode(line));
                        System.out.println(Base64.encodeBytes(PPubKGC.toBytes()));
                        noeud.PPubKGC = PPubKGC.duplicate();
                        break;
                    case 4:
                        Element Q = noeud.pairing.getG1().newRandomElement();
                        Q.setFromBytes(Base64.decode(line));
                        System.out.println(Base64.encodeBytes(Q.toBytes()));
                        noeud.Q = Q.duplicate();
                        break;
                    case 5:
                        Element D = noeud.pairing.getG1().newRandomElement();
                        D.setFromBytes(Base64.decode(line));
                        System.out.println(Base64.encodeBytes(D.toBytes()));
                        noeud.setD(D.duplicate());
                        break;
                    case 6:
                        Element X = noeud.pairing.getZr().newRandomElement();
                        X.setFromBytes(Base64.decode(line));
                        System.out.println(Base64.encodeBytes(X.toBytes()));
                        noeud.setX(X.duplicate());
                        break;
                    case 7:
                        Element P = noeud.pairing.getG1().newRandomElement();
                        P.setFromBytes(Base64.decode(line));
                        System.out.println(Base64.encodeBytes((noeud.PKGC.duplicate().powZn(noeud.getX().duplicate())).toBytes()));
                        noeud.P = P.duplicate();
                        break;
                    case 10:
                        Element SP = noeud.pairing.getG1().newRandomElement();
                        SP.setFromBytes(Base64.decode(line));
                        System.out.println(Base64.encodeBytes(SP.toBytes()));
                        noeud.setSPrime(SP.duplicate());
                        break;

                }
            }

            situation.setText("Step1..Finished");
            return true;

        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private void Aggregate() {

        if (!KGCPatient.getText().equals("") && !KGCEMr.getText().equals("")) {
            try {
                CLPKC noeud1 = new CLPKC();
                CLPKC noeud2 = new CLPKC();
                //Lecture du KGC patient
                try {
                    BufferedReader in = new BufferedReader(new FileReader(KGCPatient.getText()));
                    String line;
                    int i = 0;

                    while ((line = in.readLine()) != null) {
                        i++;
                        switch (i) {
                            case 1: //ID
                                noeud1.init(line);
                                noeud1.setID(line);
                                System.out.println("line : " + line);
                                break;
                            case 2: //PKGC
                                Element PKGC = noeud1.pairing.getG1().newRandomElement();
                                PKGC.setFromBytes(Base64.decode(line));
                                System.out.println("PKGC : " + PKGC);
                                noeud1.PKGC = PKGC;
                                break;
                            case 3: //PPubKGC
                                Element PPubKGC = noeud1.pairing.getG1().newRandomElement();
                                PPubKGC.setFromBytes(Base64.decode(line));
                                System.out.println("PPubKGC : " + PPubKGC);
                                noeud1.PPubKGC = PPubKGC;
                                break;

                            case 4: //Q
                                Element Q = noeud1.pairing.getG1().newRandomElement();
                                Q.setFromBytes(Base64.decode(line));
                                System.out.println("Q : " + Q);
                                noeud1.Q = Q;
                                break;
                            case 5://D
                                Element D = noeud1.pairing.getG1().newRandomElement();
                                D.setFromBytes(Base64.decode(line));
                                System.out.println("D : " + D);
                                noeud1.setD(D);
                                break;
                            case 6://X
                                Element X = noeud1.pairing.getZr().newRandomElement();
                                X.setFromBytes(Base64.decode(line));
                                System.out.println("X : " + X);
                                noeud1.setX(X);
                                break;
                            case 7://p
                                Element P = noeud1.pairing.getG1().newRandomElement();
                                P.setFromBytes(Base64.decode(line));
                                System.out.println("P : " + P);
                                noeud1.P = P;
                                break;
                        }
                    }
                    System.err.println("sucess Patient");

                } catch (FileNotFoundException ex) {
                    System.err.println("File PatientKGC not found");
                } catch (IOException ex) {
                    Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
                }

                //Lecture du KGC EMR
                try {
                    //Lecture du KGC patient
                    BufferedReader in = new BufferedReader(new FileReader(KGCEMr.getText()));
                    String line;
                    int i = 0;

                    while ((line = in.readLine()) != null) {
                        i++;
                        switch (i) {
                            case 1: //ID
                                noeud2.init(line);
                                noeud2.setID(line);
                                break;
                            case 2: //PKGC
                                Element PKGC = noeud2.pairing.getG1().newRandomElement();
                                PKGC.setFromBytes(Base64.decode(line));
                                noeud2.PKGC = PKGC;
                                break;
                            case 3: //PPubKGC
                                Element PPubKGC = noeud2.pairing.getG1().newRandomElement();
                                PPubKGC.setFromBytes(Base64.decode(line));
                                noeud2.PPubKGC = PPubKGC;
                                break;

                            case 4:
                                Element Q = noeud2.pairing.getG1().newRandomElement();
                                Q.setFromBytes(Base64.decode(line));
                                noeud2.Q = Q;
                                break;
                            case 5:
                                Element D = noeud2.pairing.getG1().newRandomElement();
                                D.setFromBytes(Base64.decode(line));
                                noeud2.setD(D);
                                break;
                            case 6:
                                Element X = noeud2.pairing.getZr().newRandomElement();
                                X.setFromBytes(Base64.decode(line));
                                noeud2.setX(X);
                                break;
                            case 7:
                                Element P = noeud2.pairing.getG1().newRandomElement();
                                P.setFromBytes(Base64.decode(line));
                                noeud2.P = P;
                                break;
                        }
                    }

                    System.err.println("sucess EMR");
                } catch (FileNotFoundException ex) {
                    System.out.println("File EMRKGC not found");
                } catch (IOException ex) {
                    Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
                }

                System.out.println("Commencer l'agrégation");
                CLPKC noeud3 = new CLPKC();
                noeud3.setID(noeud1.getID());
                noeud3.init(noeud1.getID());
                noeud3.PKGC = noeud1.PKGC;
                noeud3.PPubKGC = noeud1.PPubKGC.duplicate().add(noeud2.PPubKGC.duplicate());
                noeud3.Q = noeud1.Q;
                noeud3.setD(noeud1.getD().duplicate().add(noeud2.getD().duplicate()));
                noeud3.setX(noeud1.getX().duplicate().add(noeud2.getX().duplicate()));
                noeud3.P = (noeud3.PKGC.duplicate().powZn(noeud3.getX().duplicate()));
                noeud3.SetSecretKey(noeud3.pairing);
                noeud3.setSA(noeud3.getD().duplicate().powZn(noeud3.getX().duplicate()));
                noeud3.P2003[0] = noeud3.P.duplicate();
                noeud3.P2003[1] = noeud3.PPubKGC.duplicate().powZn(noeud3.getX().duplicate());

                String sensorName = sensorNameField.getText();
                PrintWriter writer2 = new PrintWriter("Ressources/ParametresKGCFinaux/" + sensorName + "KGCFinal.txt", "UTF-8");

                writer2.println(noeud3.getID());  //1
                writer2.println(Base64.encodeBytes(noeud3.PKGC.toBytes()));//2
                writer2.println(Base64.encodeBytes(noeud3.PPubKGC.toBytes()));    //3        
                writer2.println(Base64.encodeBytes(noeud3.Q.toBytes()));          //4
                writer2.println(Base64.encodeBytes(noeud3.getD().toBytes()));      //5       
                writer2.println(Base64.encodeBytes(noeud3.getX().toBytes()));//6

                writer2.println(Base64.encodeBytes(noeud3.P.toBytes()));//7
                writer2.println("+");//8
                writer2.println("+");//9
                writer2.println(Base64.encodeBytes(noeud3.getSPrime().toBytes()));//10
                writer2.println(Base64.encodeBytes(noeud3.getSA().toBytes()));
                writer2.println(Base64.encodeBytes(noeud3.P2003[0].toBytes()));
                writer2.println(Base64.encodeBytes(noeud3.P2003[1].toBytes()));
                writer2.close();

                System.err.println("End");
                
                situation.setText("Aggregation finished");
                kgcFinalField.setText("Ressources/ParametresKGCFinaux/" + sensorName + "KGCFinal.txt");

            } catch (IOException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            situation.setText("KGC field is empty");
        }
    }

}
