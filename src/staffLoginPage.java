
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
/**
 *
 * @author kazim
 */
public class staffLoginPage extends javax.swing.JFrame {

    /**
     * Creates new form staffLoginPage
     */
    public staffLoginPage() {
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
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
        picLabel = new javax.swing.JLabel();
        quantumcineplexLabel = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        welcomeLabel = new javax.swing.JLabel();
        detailsLabel = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        signinButton = new javax.swing.JButton();
        showpasswordButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        picLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/staffPage.jpg"))); // NOI18N

        quantumcineplexLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        quantumcineplexLabel.setForeground(new java.awt.Color(255, 255, 255));
        quantumcineplexLabel.setText("QUANTUM CINEPLEX");

        closeButton.setBackground(new java.awt.Color(0, 0, 0));
        closeButton.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        closeButton.setForeground(new java.awt.Color(255, 255, 255));
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

        welcomeLabel.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        welcomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        welcomeLabel.setText("WELCOME BACK");

        detailsLabel.setFont(new java.awt.Font("Yu Gothic UI Semibold", 1, 16)); // NOI18N
        detailsLabel.setForeground(new java.awt.Color(255, 255, 255));
        detailsLabel.setText("Enter your Details");

        emailField.setForeground(new java.awt.Color(204, 204, 204));
        emailField.setText("Email");
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFieldFocusLost(evt);
            }
        });

        passwordField.setText("Password");
        passwordField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                passwordFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                passwordFieldFocusLost(evt);
            }
        });
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });

        signinButton.setBackground(new java.awt.Color(204, 0, 0));
        signinButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        signinButton.setForeground(new java.awt.Color(255, 255, 255));
        signinButton.setText("Sign in");
        signinButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signinButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                signinButtonMouseExited(evt);
            }
        });
        signinButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                signinButtonActionPerformed(evt);
            }
        });

        showpasswordButton.setBackground(new java.awt.Color(0, 0, 0));
        showpasswordButton.setFont(new java.awt.Font("Times New Roman", 1, 11)); // NOI18N
        showpasswordButton.setForeground(new java.awt.Color(255, 255, 255));
        showpasswordButton.setText("SHOW");
        showpasswordButton.setBorder(null);
        showpasswordButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showpasswordButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                showpasswordButtonMouseExited(evt);
            }
        });
        showpasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showpasswordButtonActionPerformed(evt);
            }
        });

        backButton.setBackground(new java.awt.Color(0, 0, 0));
        backButton.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setText("H");
        backButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backButtonMouseExited(evt);
            }
        });
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(quantumcineplexLabel)
                        .addGap(178, 178, 178))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(welcomeLabel)
                        .addGap(116, 116, 116))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(signinButton, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(93, 93, 93))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(passwordField)
                            .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showpasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(detailsLabel)
                        .addGap(179, 179, 179))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(picLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantumcineplexLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(detailsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(showpasswordButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordField, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(62, 62, 62)
                .addComponent(signinButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void closeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseEntered
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(Color.RED);
        closeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_closeButtonMouseEntered

    private void closeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeButtonMouseExited
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(Color.BLACK);
        closeButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_closeButtonMouseExited

    private void signinButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signinButtonMouseEntered
        signinButton.setForeground(Color.BLACK);
        signinButton.setBackground(Color.WHITE);
        signinButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_signinButtonMouseEntered

    private void signinButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signinButtonMouseExited
        signinButton.setForeground(Color.WHITE);
        signinButton.setBackground(Color.RED);
        signinButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_signinButtonMouseExited

    private void emailFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusGained
        if (emailField.getText().equals("Email")) {
            setPromptText(emailField, null);
        }
    }//GEN-LAST:event_emailFieldFocusGained

    private void emailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusLost
        if (emailField.getText().trim().isEmpty()) {
            setPromptText(emailField, "Email");
        }
    }//GEN-LAST:event_emailFieldFocusLost

    private void passwordFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusGained
        if (passwordField.getText().equals("Password")) {
            setPromptText(passwordField, null);
        }
    }//GEN-LAST:event_passwordFieldFocusGained

    private void passwordFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_passwordFieldFocusLost
        if (passwordField.getText().trim().isEmpty()) {
            setPromptText(passwordField, "Password");
        }
    }//GEN-LAST:event_passwordFieldFocusLost

    private void showpasswordButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showpasswordButtonMouseEntered
        showpasswordButton.setForeground(Color.BLACK);
        showpasswordButton.setBackground(Color.WHITE);
        showpasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_showpasswordButtonMouseEntered

    private void showpasswordButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showpasswordButtonMouseExited
        showpasswordButton.setForeground(Color.WHITE);
        showpasswordButton.setBackground(Color.BLACK);
        showpasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_showpasswordButtonMouseExited

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordFieldActionPerformed

    }//GEN-LAST:event_passwordFieldActionPerformed

    private void showpasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showpasswordButtonActionPerformed
        showpasswordButton.addActionListener((ActionEvent e) -> {
            if (passwordField.getEchoChar() == 0) {
                passwordField.setEchoChar('*');
                showpasswordButton.setText("SHOW");
            } else {
                passwordField.setEchoChar((char) 0);
                showpasswordButton.setText("HIDE");
            }
        });
    }//GEN-LAST:event_showpasswordButtonActionPerformed

    private void signinButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_signinButtonActionPerformed
        if (emailField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty() || emailField.getText().equals("Email") || passwordField.getText().equals("Password")) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "The Fields are Empty!", "Alert", JOptionPane.WARNING_MESSAGE);
        } else {
            String id;
            dbconnection db = new dbconnection();
            try {
                ResultSet resultset = db.st.executeQuery("select STAFF_ID,EMAIL,PASSWORD from STAFF where EMAIL='" + emailField.getText() + "'and PASSWORD='" + passwordField.getText() + "'");
                boolean check = false;
                while (resultset.next()) {
                    check = true;
                    id = String.valueOf(resultset.getInt("STAFF_ID"));

                    staffDashboard obj = new staffDashboard(id);
                }
                if (check == true) {
                    dispose();
                    staffDashboard obj = new staffDashboard();
                    obj.setVisible(true);
                } else {
                    ResultSet checkUserResultSet = db.st.executeQuery("SELECT 1 FROM STAFF WHERE EMAIL='" + emailField.getText() + "'");
                    if (checkUserResultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Username or Password Incorrect");
                    } else {
                        JOptionPane.showMessageDialog(null, "No User Found");
                    }
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "No User Found");
            }
        }
    }//GEN-LAST:event_signinButtonActionPerformed

    private void backButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseEntered
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.RED);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_backButtonMouseEntered

    private void backButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backButtonMouseExited
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_backButtonMouseExited

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        dispose();
        Quantum_Cinéplex obj = new Quantum_Cinéplex();
        obj.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private static void setPromptText(JTextField textField, String prompt) {
        textField.setForeground(prompt == null ? Color.BLACK : Color.GRAY);
        textField.setText(prompt);
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
            java.util.logging.Logger.getLogger(staffLoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(staffLoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(staffLoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(staffLoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new staffLoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel detailsLabel;
    private javax.swing.JTextField emailField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel picLabel;
    private javax.swing.JLabel quantumcineplexLabel;
    private javax.swing.JButton showpasswordButton;
    private javax.swing.JButton signinButton;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
