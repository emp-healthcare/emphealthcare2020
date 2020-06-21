/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatewayguiswing;

import static gatewayguiswing.Sensors.x;
import java.awt.BorderLayout;
import java.awt.Panel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class History extends javax.swing.JFrame {

    //Graph variables :
    private static JFreeChart chart;
    private static XYSeries series = new XYSeries("Sensor Readings");
    private static XYSeriesCollection dataset = new XYSeriesCollection(series);
    static int x = 0;

    private static String sensorSelected = "";

    public History() {
        initComponents();
        this.setResizable(false);
        GraphPanel.setSize(600, 400);
        GraphPanel.setLayout(new BorderLayout());
        GraphPanel.add(new ChartPanel(chart), BorderLayout.CENTER);
        chart = ChartFactory.createXYLineChart("Sensor Readings", "Time (seconds)", "Reading", dataset);
        GraphPanel.add(new ChartPanel(chart), BorderLayout.CENTER);
        GraphPanel.repaint();

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

        cb.removeAllItems();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        sensorsListJ = new javax.swing.JList<>();
        GraphPanel = new javax.swing.JPanel();
        type = new javax.swing.JLabel();
        icon = new javax.swing.JLabel();
        cb = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        sensorID = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("Icons/backArro.png")));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

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

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 150, 170));

        javax.swing.GroupLayout GraphPanelLayout = new javax.swing.GroupLayout(GraphPanel);
        GraphPanel.setLayout(GraphPanelLayout);
        GraphPanelLayout.setHorizontalGroup(
            GraphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 580, Short.MAX_VALUE)
        );
        GraphPanelLayout.setVerticalGroup(
            GraphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );

        jPanel1.add(GraphPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 580, 330));

        type.setText(" ");
        jPanel1.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, -1, -1));

        icon.setText(" ");
        jPanel1.add(icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        cb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbItemStateChanged(evt);
            }
        });
        jPanel1.add(cb, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 140, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 0));
        jLabel2.setText("History");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 0));
        jLabel3.setText("Sensors");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        sensorID.setText(" ");
        jPanel1.add(sensorID, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 770, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sensorsListJValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_sensorsListJValueChanged

        series.clear();

        try {
            Sensor sensor = searchSensorByName("capteur."+sensorsListJ.getSelectedValue()+"@emp.dz");
            if (sensor != null) {

                sensorSelected = sensor.getID();
                sensorID.setText(sensor.getID());
                type.setText(sensor.getType());
                String urlIcon = "Ressources/Icons/" + sensor.getID() + ".png";
                Icon ic = new ImageIcon(urlIcon);
                icon.setIcon(ic);

                //Chercher l'historique enregistré :
                List<String> s;

                cb.removeAllItems();
                s = getFolderFilesName("Ressources/History/history" + sensor.getID());
                for (int i = 0; i < s.size(); i++) {
                    cb.addItem(s.get(i));
                }

                // window = new Panel();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Sensors.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sensorsListJValueChanged

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        Sensors s = new Sensors();
        s.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1MouseClicked

    private void cbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbItemStateChanged
        // TODO add your handling code here:
        if (cb.getSelectedItem() != null) {
            List<String> s = getFileValues(cb.getSelectedItem().toString(), sensorSelected);
            if (s != null) {
                for (int i = 0; i < s.size()-1; i=i+2) {
                    series.add(i, Float.valueOf(s.get(i+1)));
                     GraphPanel.repaint();
                }

            }
        }

    }//GEN-LAST:event_cbItemStateChanged

    private List<String> getFileValues(String fileName, String sensorID) {
        List<String> observations = new ArrayList<String>();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader("Ressources/History/" + "history" + sensorID + "/" + fileName + ".txt"));

            String line;

            while ((line = in.readLine()) != null) {

                observations.add(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        }
        return observations;
    }

    private List<String> getFolderFilesName(String folderName) {
        File repertoire = new File(folderName);
        File[] listeFilePath = repertoire.listFiles();
        List<String> s = new ArrayList<String>();
        for (int i = 0, num = 0; i < listeFilePath.length; i++) {
            s.add(listeFilePath[i].getName().replaceAll(".txt", ""));
        }
        return s;
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
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
            return null;
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
            Logger.getLogger(Sensors.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(History.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
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
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(History.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                chart = ChartFactory.createXYLineChart("Sensor Readings", "Time (seconds)", "Reading", dataset);
                new History().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel GraphPanel;
    private javax.swing.JComboBox<String> cb;
    private javax.swing.JLabel icon;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel sensorID;
    private javax.swing.JList<String> sensorsListJ;
    private javax.swing.JLabel type;
    // End of variables declaration//GEN-END:variables
}
