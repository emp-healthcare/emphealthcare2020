/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatewayguiswing;

import static gatewayguiswing.ReadFromSensor.x;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author blunt
 */
public class Sensors extends javax.swing.JFrame {

    //Alerts
    boolean sensorConnected = false;

    private static String commande = "";
    private static String historyFile = "";
    private static String historyFileNotSent = "";

    //Sensor Bluetooth Parameter
    private static Timer timer;
    private static OutputStream os = null;
    private static InputStream is = null;
    private static StreamConnection streamConnection = null;
    private static String hc05Url = "";
    private boolean start = false;
    private BufferedWriter writer = null;
    private BufferedWriter writer2 = null;
    int i = 0;

    //Graph variables :
    private static JFreeChart chart;
    private static XYSeries series = new XYSeries("Sensor Readings");
    private static XYSeriesCollection dataset = new XYSeriesCollection(series);
    static int x = 0;

    private boolean record = true;

    public Sensors() {
        initComponents();
        this.setResizable(false);
        Stop.setEnabled(false);

        DefaultListModel model = new DefaultListModel();
        List<String> sensorsNames = sensorsList();
        if (sensorsNames != null) {
            for (int i = 0; i < sensorsNames.size(); i++) {
                String s=sensorsNames.get(i);
                String j=(s.replace("capteur.", "")).replace("@emp.dz", "");
                model.addElement(j);
            }
        }

        sensorsListJ.setModel(model);

        panel3.setVisible(false);
        panel4.setVisible(false);

        //Les configurations :
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
                            record = true;
                        } else {
                            record = false;
                        }
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Configuration.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Verification du connexion des capteurs :
        /*alerte.setText("wait while check sesors state ...");
        Sensor sen;
        for(int i=0;i<sensorsNames.size();i++){
            try {
                sen=searchSensorByName(sensorsNames.get(i));
                
                String url=sen.getHc05UR();
                
                streamConnection = (StreamConnection) Connector.open(url);
            if(streamConnection==null){
                ConectionState.setBackground(Color.GREEN);
                ConectionState1.setText("");
                ConectionState.setText("Sensor Connected");
            }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Sensors.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Sensors.class.getName()).log(Level.SEVERE, null, ex);
                alerte.setText("Sensor not Connected");
            }
        }
         */
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
            Logger.getLogger(Sensors.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sensors.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Sensors.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator3 = new javax.swing.JSeparator();
        panel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        supporrt = new javax.swing.JLabel();
        type = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        startMonitor = new javax.swing.JButton();
        Stop = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        ConectionState = new javax.swing.JLabel();
        ConectionState1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        GraphPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sensorsListJ = new javax.swing.JList<>();
        Home = new javax.swing.JButton();
        History = new javax.swing.JButton();
        Help1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setForeground(new java.awt.Color(204, 255, 204));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 0, 180));

        panel3.setBackground(new java.awt.Color(255, 255, 255));
        panel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 0));
        jLabel5.setText("Type");
        panel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        supporrt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        supporrt.setText("support");
        panel3.add(supporrt, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, -1, -1));

        type.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        type.setText("type");
        panel3.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));
        panel3.add(icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 90, 80));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 0));
        jLabel7.setText("Support");
        panel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        getContentPane().add(panel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 260, 110));

        panel4.setBackground(new java.awt.Color(255, 255, 255));
        panel4.setLayout(null);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 153, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/refresh.png")));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        panel4.add(jButton2);
        jButton2.setBounds(210, 10, 60, 40);

        startMonitor.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        startMonitor.setForeground(new java.awt.Color(0, 153, 51));
        startMonitor.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/start.png")));
        startMonitor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startMonitorMouseClicked(evt);
            }
        });
        panel4.add(startMonitor);
        startMonitor.setBounds(30, 10, 50, 40);

        Stop.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Stop.setForeground(new java.awt.Color(204, 0, 0));
        Stop.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/stop.png")));
        Stop.setToolTipText("");
        Stop.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                StopMouseClicked(evt);
            }
        });
        panel4.add(Stop);
        Stop.setBounds(130, 10, 50, 40);
        panel4.add(jSeparator2);
        jSeparator2.setBounds(-240, 160, 540, 50);

        ConectionState.setForeground(new java.awt.Color(255, 0, 0));
        ConectionState.setText(" ");
        panel4.add(ConectionState);
        ConectionState.setBounds(30, 60, 180, 14);

        ConectionState1.setForeground(new java.awt.Color(255, 0, 0));
        ConectionState1.setText(" ");
        panel4.add(ConectionState1);
        ConectionState1.setBounds(30, 80, 200, 14);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panel4.add(jSeparator1);
        jSeparator1.setBounds(0, 0, 30, 110);

        getContentPane().add(panel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 300, 110));

        GraphPanel.setLayout(null);
        getContentPane().add(GraphPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 560, 300));

        jPanel1.setBackground(new java.awt.Color(0, 153, 0));
        jPanel1.setLayout(null);

        sensorsListJ.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sensorsListJ.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        sensorsListJ.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                sensorsListJValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(sensorsListJ);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 40, 150, 170);

        Home.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Home.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/back.png")));
        Home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HomeMouseClicked(evt);
            }
        });
        jPanel1.add(Home);
        Home.setBounds(20, 230, 120, 30);

        History.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        History.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/history.png")));
        History.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HistoryMouseClicked(evt);
            }
        });
        History.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HistoryActionPerformed(evt);
            }
        });
        jPanel1.add(History);
        History.setBounds(20, 270, 120, 30);

        Help1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Help1.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/help.png")));
        Help1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Help1MouseClicked(evt);
            }
        });
        jPanel1.add(Help1);
        Help1.setBounds(20, 310, 120, 30);

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sensors");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(20, 10, 71, 22);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 170, 410));
        getContentPane().add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 500, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HomeMouseClicked

        // TODO add your handling code here:
        Home h = new Home();
        h.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_HomeMouseClicked

    private void sensorsListJValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_sensorsListJValueChanged

        sensorConnected = false;
        ConectionState.setText("");
        ConectionState1.setText("");
        series.clear();

        try {
            Sensor sensor = searchSensorByName("capteur."+sensorsListJ.getSelectedValue()+"@emp.dz");
           
            if (sensor != null) {

                panel3.setVisible(true);
                panel4.setVisible(true);

                GraphPanel.setSize(600, 400);
                GraphPanel.setLayout(new BorderLayout());
                GraphPanel.add(new ChartPanel(chart), BorderLayout.CENTER);
                chart = ChartFactory.createXYLineChart("Sensor Readings", "Time (seconds)", "Reading", dataset);
                GraphPanel.add(new ChartPanel(chart), BorderLayout.CENTER);
                GraphPanel.repaint();

//                sensorID.setText(sensor.getID());
                supporrt.setText(sensor.getCommunicationSupport());
                type.setText(sensor.getType());
                String urlIcon = "Ressources/Icons/" + sensor.getID() + ".png";
                Icon ic = new ImageIcon(urlIcon);
                icon.setIcon(ic);

                historyFile = "Ressources/History/history" + sensor.getID();
                historyFileNotSent = "Ressources/HistoryNotSent/" + sensor.getID();
                hc05Url = sensor.getHc05UR();
                commande = sensor.getCommande();
                /*        
                alerte.setText("wait while check sesors state ...");
                try {
            // TODO add your handling code here:
            streamConnection = (StreamConnection) Connector.open(hc05Url);
            if(streamConnection==null){
                ConectionState.setBackground(Color.GREEN);
                ConectionState1.setText("");
                ConectionState.setText("Sensor Connected");
            }
            else{
                ConectionState.setBackground(Color.RED);
                ConectionState.setText("Sensor Not Connected");
            }
        } catch (IOException ex) {
            Logger.getLogger(Sensors.class.getName()).log(Level.SEVERE, null, ex);
            ConectionState.setBackground(Color.RED);
            ConectionState.setText("Sensor Not Connected");
            ConectionState1.setText("Check the connection");
        }
                 */

            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sensors.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sensorsListJValueChanged

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        try {
            // TODO add your handling code here:
            streamConnection = (StreamConnection) Connector.open(hc05Url);
            if (streamConnection != null) {
                ConectionState.setBackground(Color.GREEN);
                ConectionState1.setText("");
                ConectionState.setText("Sensor Connected");
                streamConnection.close();
                sensorConnected = true;

            } else {
                ConectionState.setBackground(Color.RED);
                ConectionState.setText("Sensor Not Connected");
                sensorConnected = false;
            }
        } catch (IOException ex) {
            Logger.getLogger(Sensors.class.getName()).log(Level.SEVERE, null, ex);
            ConectionState.setBackground(Color.RED);
            ConectionState.setText("Sensor Not Connected");
            ConectionState1.setText("Check the connection");
            sensorConnected = false;
        }
    }//GEN-LAST:event_jButton2MouseClicked

    private void startMonitorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startMonitorMouseClicked

        if (record == false) {
            start();
        } else {
            startAndRecord();
        }
    }//GEN-LAST:event_startMonitorMouseClicked

    private void start() {
        if (sensorConnected == true) {
            try {
                streamConnection = (StreamConnection) Connector.open(hc05Url);
                if (streamConnection != null) {
                    series.clear();
                    os = streamConnection.openOutputStream();
                    is = streamConnection.openInputStream();
                    os.write(commande.getBytes()); //49 en ASCII pour prendre les mesures de temperatures;50 pour ECG
                    //Commence à recevoir from BT:
                    System.err.println("Receiving ... ");
                    int tempsDEcoute = 360;
                    timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {

                        byte[] b = new byte[200];
                        String result = "";
                        int j = 0;
                        long t0 = System.currentTimeMillis();

                        @Override
                        public void run() {
                            //Buttons
                            sensorsListJ.setEnabled(false);
                            Stop.setEnabled(true);
                            startMonitor.setEnabled(false);
                            Home.setEnabled(false);
                            History.setEnabled(false);
                            // About.setEnabled(false);

                            if (System.currentTimeMillis() - t0 > tempsDEcoute * 1000) {
                                try {
                                    System.out.println("End Of Timer");
                                    timer.cancel();

                                    //Buttons
                                    sensorsListJ.setEnabled(true);
                                    Stop.setEnabled(false);
                                    startMonitor.setEnabled(true);
                                    Home.setEnabled(true);
                                    History.setEnabled(true);
                                    // About.setEnabled(true);

                                    //Close stream
                                    is.close();
                                    os.write("0".getBytes()); //50 en ASCII
                                    os.close();
                                    streamConnection.close();
                                } catch (IOException ex) {
                                    Logger.getLogger(ReadFromSensor.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            } else {

                                try {
                                    is.read(b);
                                    result = new String(b);
                                    System.out.println("receive from BT: " + result);
                                    if (j != 0) {
                                        series.add(x++, Float.valueOf(result));
                                        GraphPanel.repaint();
                                    }
                                    j = j + 1;
                                    //results.add(result);
                                } catch (IOException ex) {
                                    Logger.getLogger(ReadFromSensor.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }
                        }
                    }, 0, 1000);

                }

            } catch (IOException ex) {
                Logger.getLogger(ReadFromSensor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ConectionState.setText("");
            ConectionState1.setText("Check the Connection");
        }
    }

    private void startAndRecord() {

        if (sensorConnected == true) {
            try {

                streamConnection = (StreamConnection) Connector.open(hc05Url);
                if (streamConnection != null) {
                    os = streamConnection.openOutputStream();
                    is = streamConnection.openInputStream();

                    //Envoyer les commandes via bluetooth
                    os.write(commande.getBytes()); //49 en ASCII pour prendre les mesures de temperatures;50 pour ECG

                    DateFormat format = new SimpleDateFormat();
                    Date date = new Date();
                    // String filename = date.toGMTString().subSequence(0, 11).toString().replaceAll(" ", "");
                    Timestamp timestamp = new Timestamp(date.getTime());

                    String filename = (timestamp.toString()).subSequence(0, 10).toString().replaceAll(" ", "");

                    writer = new BufferedWriter(new FileWriter(historyFile + "/" + filename + ".txt", true));
                    writer2 = new BufferedWriter(new FileWriter(historyFileNotSent + "/" + filename + ".txt", true));
                    
                    

                    System.err.println("Receiving ... ");

                    int tempsDEcoute = 360;

                    timer = new Timer();

                    timer.scheduleAtFixedRate(new TimerTask() {

                        byte[] b = new byte[5];
                        String result = "";
                        int j = 0;
                        long t0 = System.currentTimeMillis();

                        @Override
                        public void run() {
                            //Buttons
                            boolean firstMesure=true;//Pour formatter les données de l'ECG
                            
                            sensorsListJ.setEnabled(false);
                            Stop.setEnabled(true);
                            startMonitor.setEnabled(false);
                            Home.setEnabled(false);
                            History.setEnabled(false);
                            //  About.setEnabled(false);
                            if (System.currentTimeMillis() - t0 > tempsDEcoute * 1000) {
                                try {
                                    System.out.println("End Of Timer");
                                    timer.cancel();

                                    //Buttons
                                    sensorsListJ.setEnabled(true);
                                    Stop.setEnabled(false);
                                    startMonitor.setEnabled(true);
                                    Home.setEnabled(true);
                                    History.setEnabled(true);
                                    //   About.setEnabled(true);

                                    //Close stream
                                    is.close();
                                    os.write("0".getBytes()); //50 en ASCII
                                    os.close();
                                    streamConnection.close();

                                    start = false;
                                    writer.close();
                                    writer2.close();

                                } catch (IOException ex) {
                                    Logger.getLogger(ReadFromSensor.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            } else {
                                try {

                                    is.read(b);
                                    result = new String(b);
                                    System.out.println("receive from BT: " + result);
                                    if (j != 0) {

                                        series.add(x++, Float.valueOf(result));
                                        GraphPanel.repaint();

                                        //Enregistrer
                                        //Si temperature : separer par des "\n"
                                        //Si ECG : Separer par des virgule
                                        DateFormat format = new SimpleDateFormat();
                                        Date date = new Date();

                                        if (commande == "1") {// Si c'est temperature
                                            Timestamp timestamp = new Timestamp(date.getTime());
                                            writer.write(timestamp.toString() + "\n");
                                            writer.write(result + "\n");

                                            writer2.write(timestamp.toString() + "\n");
                                            writer2.write(result + "\n");
                                        } else if (commande == "2") {//Si c'est ECG
                                            Timestamp timestamp = new Timestamp(date.getTime());
                                            writer.write(timestamp.toString() + "\n");
                                            writer.write(result + "\n");
                                            
                                            
                                            if(firstMesure==false)
                                                writer2.write(result + " , ");
                                            else{
                                                writer2.write("\n"+timestamp.toString() + "\n");
                                                writer2.write(result + " , ");
                                                firstMesure=false;
                                            }
                                        }
                                    }
                                    j++;
                                    //results.add(result);

                                } catch (IOException ex) {
                                    Logger.getLogger(ReadFromSensor.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
                    }, 0, 1000);

                } else {
                    System.out.println("error connexion");
                }
            } catch (IOException ex) {
                Logger.getLogger(ReadFromSensor.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            ConectionState.setText("");
            ConectionState1.setText("Check the Connection");
        }

    }
    private void StopMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_StopMouseClicked
        // TODO add your handling code here:

        if (os != null && is != null && streamConnection != null) {
            timer.cancel();

            //Buttons
            sensorsListJ.setEnabled(true);
            Stop.setEnabled(false);
            startMonitor.setEnabled(true);
            Home.setEnabled(true);
            History.setEnabled(true);
            //  About.setEnabled(true);

            if (writer != null && writer2 != null) {
                try {
                    writer.close();
                    writer2.close();
                } catch (IOException ex) {
                    Logger.getLogger(Sensors.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            start = false;
            try {
                is.close();
                os.write("0".getBytes()); //50 en ASCII
                os.close();
                streamConnection.close();
            } catch (IOException ex) {
                Logger.getLogger(Sensors.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_StopMouseClicked

    private void HistoryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HistoryMouseClicked
        // TODO add your handling code here:

        this.dispose();
        this.removeAll();
        History h = new History();

        h.setVisible(true);


    }//GEN-LAST:event_HistoryMouseClicked

    private void Help1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Help1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Help1MouseClicked

    private void HistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HistoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_HistoryActionPerformed

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
            Logger.getLogger(Sensors.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
            java.util.logging.Logger.getLogger(Sensors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sensors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sensors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sensors.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                chart = ChartFactory.createXYLineChart("Sensor Readings", "Time (seconds)", "Reading", dataset);
                new Sensors().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ConectionState;
    private javax.swing.JLabel ConectionState1;
    private javax.swing.JPanel GraphPanel;
    private javax.swing.JButton Help1;
    private javax.swing.JButton History;
    private javax.swing.JButton Home;
    private javax.swing.JButton Stop;
    private javax.swing.JLabel icon;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JPanel panel3;
    private javax.swing.JPanel panel4;
    private javax.swing.JList<String> sensorsListJ;
    private javax.swing.JButton startMonitor;
    private javax.swing.JLabel supporrt;
    private javax.swing.JLabel type;
    // End of variables declaration//GEN-END:variables
}
