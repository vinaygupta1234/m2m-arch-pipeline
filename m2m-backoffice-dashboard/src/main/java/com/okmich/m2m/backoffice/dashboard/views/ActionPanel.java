/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.okmich.m2m.backoffice.dashboard.views;

import com.okmich.m2m.backoffice.dashboard.views.tablemodel.DefaultTableModel;
import java.util.List;

/**
 *
 * @author m.enudi
 */
public class ActionPanel extends javax.swing.JPanel implements UIView<String[]> {

    private final DefaultTableModel tableModel;

    /**
     * Creates new form FeedStatusPanel
     */
    public ActionPanel() {
        tableModel = new DefaultTableModel(new String[]{"Sensor", "Action", "...", "Time"});
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        triggerActionTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder("Automated Action Log"));
        setPreferredSize(new java.awt.Dimension(350, 424));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        triggerActionTable.setModel(tableModel);
        triggerActionTable.setEnabled(false);
        jScrollPane1.setViewportView(triggerActionTable);

        add(jScrollPane1);

        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tableModel.clear();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable triggerActionTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void refreshData(List<String[]> tList) {
        for (String[] data : tList) {
            this.tableModel.add(data);
        }
    }

    @Override
    public void refreshData(String[] datat) {
        this.tableModel.add(datat);
    }
}
