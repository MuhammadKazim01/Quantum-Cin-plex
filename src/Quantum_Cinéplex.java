
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Quantum_Cinéplex extends javax.swing.JFrame {

    public Quantum_Cinéplex() {
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        IconLabel = new javax.swing.JLabel();
        taglineLabel = new javax.swing.JLabel();
        usertypeLabel = new javax.swing.JLabel();
        customerIcon = new javax.swing.JLabel();
        adminIcon = new javax.swing.JLabel();
        adminButton = new javax.swing.JButton();
        CustomerButton = new javax.swing.JButton();
        videocameraIcon = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        staffButton = new javax.swing.JButton();
        adminIcon1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quantum Cinéplex");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setPreferredSize(new java.awt.Dimension(1212, 560));

        IconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Quantum_Cineplex_Logo.jpg"))); // NOI18N

        taglineLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Where Fantasy Meets the Big Screen qq.gif"))); // NOI18N

        usertypeLabel.setFont(new java.awt.Font("Times New Roman", 1, 27)); // NOI18N
        usertypeLabel.setForeground(new java.awt.Color(255, 255, 255));
        usertypeLabel.setText("SELECT USER TYPE");

        customerIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customerIcon.png"))); // NOI18N

        adminIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/AdminIcon.png"))); // NOI18N

        adminButton.setBackground(new java.awt.Color(0, 0, 0));
        adminButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        adminButton.setForeground(new java.awt.Color(255, 255, 255));
        adminButton.setText("ADMINSTRATOR");
        adminButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                adminButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                adminButtonMouseExited(evt);
            }
        });
        adminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminButtonActionPerformed(evt);
            }
        });

        CustomerButton.setBackground(new java.awt.Color(0, 0, 0));
        CustomerButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        CustomerButton.setForeground(new java.awt.Color(255, 255, 255));
        CustomerButton.setText("CUSTOMER");
        CustomerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CustomerButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                CustomerButtonMouseExited(evt);
            }
        });
        CustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerButtonActionPerformed(evt);
            }
        });

        videocameraIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/videoIcon.png"))); // NOI18N
        videocameraIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        closeButton.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        closeButton.setText("X");
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButtonMouseExited(evt);
            }
        });
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        staffButton.setBackground(new java.awt.Color(0, 0, 0));
        staffButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        staffButton.setForeground(new java.awt.Color(255, 255, 255));
        staffButton.setText("STAFF");
        staffButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                staffButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                staffButtonMouseExited(evt);
            }
        });
        staffButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                staffButtonActionPerformed(evt);
            }
        });

        adminIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/staffIcon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(videocameraIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 503, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(taglineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(481, 481, 481))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(IconLabel)
                            .addGap(269, 269, 269)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(adminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(staffButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(CustomerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(281, 281, 281))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(adminIcon)
                        .addGap(153, 153, 153)
                        .addComponent(adminIcon1)
                        .addGap(124, 124, 124)
                        .addComponent(customerIcon)
                        .addGap(329, 329, 329))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeButton, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(usertypeLabel)
                        .addGap(543, 543, 543))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(IconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taglineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(usertypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(customerIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(videocameraIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(adminIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(adminIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CustomerButton)
                            .addComponent(staffButton)
                            .addComponent(adminButton))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1658, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminButtonActionPerformed
        dispose();
        adminLogin obj = new adminLogin();
        obj.setVisible(true);
    }//GEN-LAST:event_adminButtonActionPerformed

    private void CustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CustomerButtonActionPerformed
        dispose();
        signupPage obj = new signupPage();
        obj.setVisible(true);
    }//GEN-LAST:event_CustomerButtonActionPerformed

    private void adminButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminButtonMouseEntered
        adminButton.setForeground(Color.BLACK);
        adminButton.setBackground(Color.WHITE);
        adminButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_adminButtonMouseEntered

    private void adminButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adminButtonMouseExited
        adminButton.setForeground(Color.WHITE);
        adminButton.setBackground(Color.BLACK);
        adminButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_adminButtonMouseExited

    private void CustomerButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerButtonMouseEntered
        CustomerButton.setForeground(Color.BLACK);
        CustomerButton.setBackground(Color.WHITE);
        CustomerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_CustomerButtonMouseEntered

    private void CustomerButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CustomerButtonMouseExited
        CustomerButton.setForeground(Color.WHITE);
        CustomerButton.setBackground(Color.BLACK);
        CustomerButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_CustomerButtonMouseExited

    private void closeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseEntered
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(Color.RED);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_closeButtonMouseEntered

    private void closeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseExited
        closeButton.setForeground(Color.BLACK);
        closeButton.setBackground(Color.WHITE);
        closeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_closeButtonMouseExited

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void staffButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_staffButtonActionPerformed
        dispose();
        staffsignupPage obj = new staffsignupPage();
        obj.setVisible(true);
    }//GEN-LAST:event_staffButtonActionPerformed

    private void staffButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffButtonMouseEntered
        staffButton.setForeground(Color.BLACK);
        staffButton.setBackground(Color.WHITE);
        staffButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_staffButtonMouseEntered

    private void staffButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_staffButtonMouseExited
        staffButton.setForeground(Color.WHITE);
        staffButton.setBackground(Color.BLACK);
        staffButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_staffButtonMouseExited

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
            java.util.logging.Logger.getLogger(Quantum_Cinéplex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Quantum_Cinéplex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Quantum_Cinéplex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Quantum_Cinéplex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Quantum_Cinéplex().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CustomerButton;
    private javax.swing.JLabel IconLabel;
    private javax.swing.JButton adminButton;
    private javax.swing.JLabel adminIcon;
    private javax.swing.JLabel adminIcon1;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel customerIcon;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton staffButton;
    private javax.swing.JLabel taglineLabel;
    private javax.swing.JLabel usertypeLabel;
    private javax.swing.JLabel videocameraIcon;
    // End of variables declaration//GEN-END:variables
}
