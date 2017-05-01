/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.okmich.m2m.backoffice.dashboard.views;

import com.okmich.m2m.backoffice.dashboard.db.CacheService;
import com.okmich.m2m.backoffice.dashboard.model.Sensor;
import com.okmich.m2m.backoffice.dashboard.views.tablemodel.UniqueKeyTableModel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ABME340
 */
public class SensorPanel extends javax.swing.JPanel implements UIView<Sensor> {

    private final UniqueKeyTableModel connectedSensorTableModel;
    private final UniqueKeyTableModel disconnectedSensorTableModel;

    private final static DateFormat DF = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    /**
     * Creates new form FeedStatusPanel
     *
     * @param cacheService
     */
    public SensorPanel(CacheService cacheService) {
        disconnectedSensorTableModel = new UniqueKeyTableModel(new String[]{"Sensor", "Address", "Last feed"});
        connectedSensorTableModel = new UniqueKeyTableModel(new String[]{"Sensor", "Address", "Last feed"});
        initComponents();

        refreshData(cacheService.getSensors());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        connectedPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        disconnectedPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setPreferredSize(new java.awt.Dimension(350, 855));
        setLayout(new java.awt.GridLayout(2, 0, 5, 5));

        connectedPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Connected Sensor"));
        connectedPanel.setLayout(new java.awt.GridLayout(1, 0));

        jTable1.setModel(connectedSensorTableModel);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTable1);

        connectedPanel.add(jScrollPane1);

        add(connectedPanel);

        disconnectedPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Sensor with Lost Connection"));
        disconnectedPanel.setLayout(new java.awt.GridLayout(1, 0));

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTable2.setModel(disconnectedSensorTableModel);
        jTable2.setColumnSelectionAllowed(true);
        jTable2.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable2);

        disconnectedPanel.add(jScrollPane2);

        add(disconnectedPanel);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel connectedPanel;
    private javax.swing.JPanel disconnectedPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void refreshData(List<Sensor> tList) {
        for (Sensor sensor : tList) {
            refreshData(sensor);
        }
    }

    @Override
    public void refreshData(Sensor t) {
        loadTableModels(t);
    }

    private void loadTableModels(Sensor sensor) {
        String dateTime = DF.format(new Date(sensor.getTimestamp()));
        if (Sensor.STATUS_INACTIVE.equals(sensor.getStatus())) {
            disconnectedSensorTableModel.add(sensor.getDevId(), sensor);
            connectedSensorTableModel.remove(sensor.getDevId());
        } else {
            connectedSensorTableModel.add(sensor.getDevId(), sensor);
            disconnectedSensorTableModel.remove(sensor.getDevId());
        }
    }
}
