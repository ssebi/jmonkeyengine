package com.jme3.post;

public class HDRConfig extends javax.swing.JFrame {

    private float gamma, a, white;
    private int lod;
    private HDRRenderer hdrRender;

    public HDRConfig(HDRRenderer hdrRender) {
        initComponents();
        this.hdrRender = hdrRender;
        sldWhiteStateChanged(null);
        sldGammaStateChanged(null);
        sldLODStateChanged(null);
        jComboBox1ActionPerformed(null);
    }

    public float getA() {
        return a;
    }

    public float getGamma() {
        return gamma;
    }

    public int getLod() {
        return lod;
    }

    public float getWhite() {
        return white;
    }

    private void updateHdr(){
        hdrRender.setWhiteLevel(getWhite());
        hdrRender.setThrottle(getLod());
        hdrRender.setExposure(getA());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBright = new javax.swing.JPanel();
        lblWhite = new javax.swing.JLabel();
        sldWhite = new javax.swing.JSlider();
        pnlGamma = new javax.swing.JPanel();
        lblGamma = new javax.swing.JLabel();
        sldGamma = new javax.swing.JSlider();
        pnlA = new javax.swing.JPanel();
        lblA = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        pnlLOD = new javax.swing.JPanel();
        lblLOD = new javax.swing.JLabel();
        sldLOD = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("HDR Configuration");
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        lblWhite.setText("Maximum Brightness: ");
        pnlBright.add(lblWhite);

        sldWhite.setMaximum(1000);
        sldWhite.setValue(100);
        sldWhite.setPreferredSize(new java.awt.Dimension(100, 23));
        sldWhite.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldWhiteStateChanged(evt);
            }
        });
        pnlBright.add(sldWhite);

        getContentPane().add(pnlBright);

        lblGamma.setText("Output Gamma: ");
        pnlGamma.add(lblGamma);

        sldGamma.setMaximum(500);
        sldGamma.setMinimum(1);
        sldGamma.setValue(100);
        sldGamma.setPreferredSize(new java.awt.Dimension(100, 23));
        sldGamma.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldGammaStateChanged(evt);
            }
        });
        pnlGamma.add(sldGamma);

        getContentPane().add(pnlGamma);

        lblA.setText("Exposure: ");
        pnlA.add(lblA);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Dark", "Dim", "Normal", "Light" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        pnlA.add(jComboBox1);

        getContentPane().add(pnlA);

        lblLOD.setText("Leve of detail: ");
        pnlLOD.add(lblLOD);

        sldLOD.setMaximum(15);
        sldLOD.setMinimum(1);
        sldLOD.setValue(1);
        sldLOD.setPreferredSize(new java.awt.Dimension(100, 23));
        sldLOD.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sldLODStateChanged(evt);
            }
        });
        pnlLOD.add(sldLOD);

        getContentPane().add(pnlLOD);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sldWhiteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldWhiteStateChanged
        white = sldWhite.getValue() / 100f;
        updateHdr();
    }//GEN-LAST:event_sldWhiteStateChanged

    private void sldGammaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldGammaStateChanged
        gamma = sldGamma.getValue() / 100f;
        updateHdr();
    }//GEN-LAST:event_sldGammaStateChanged

    private void sldLODStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sldLODStateChanged
        lod = sldLOD.getValue();
        updateHdr();
    }//GEN-LAST:event_sldLODStateChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        switch (jComboBox1.getSelectedIndex()){
            case 0: a = 0.09f; break;
            case 1: a = 0.18f; break;
            case 2: a = 0.36f; break;
            case 3: a = 0.72f; break;
        }
        updateHdr();
    }//GEN-LAST:event_jComboBox1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel lblA;
    private javax.swing.JLabel lblGamma;
    private javax.swing.JLabel lblLOD;
    private javax.swing.JLabel lblWhite;
    private javax.swing.JPanel pnlA;
    private javax.swing.JPanel pnlBright;
    private javax.swing.JPanel pnlGamma;
    private javax.swing.JPanel pnlLOD;
    private javax.swing.JSlider sldGamma;
    private javax.swing.JSlider sldLOD;
    private javax.swing.JSlider sldWhite;
    // End of variables declaration//GEN-END:variables

}
