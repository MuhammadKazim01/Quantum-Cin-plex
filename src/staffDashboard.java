
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JComboBox;
import javax.swing.table.TableColumn;

class BorderColorTableCellRenderer extends DefaultTableCellRenderer {

    private static final Color BORDER_COLOR = Color.WHITE; // Set your desired border color

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        // Set a custom border with the desired color
        ((JComponent) renderer).setBorder(new LineBorder(BORDER_COLOR));

        return renderer;
    }
}

class CustomRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);
        Font font = new Font("Times New Roman", Font.BOLD, 12);
        label.setFont(font);
        return label;
    }
}

public class staffDashboard extends javax.swing.JFrame {

    int cancelcheck;
    String name;
    static String id;

    /**
     * Creates new form adminDashboard
     */
    private static void fillDateComboBox(JComboBox<String> comboBox) {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < 7; i++) {
            comboBox.addItem(dateFormat.format(calendar.getTime()));
            // Move to the next date
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    public staffDashboard() {
        initComponents();
        adminidLabel.setText("STAFF ID: " + id);
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);
        addButton.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        customerIDField1.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        submitButton1.setVisible(false);
        customerIDField.setVisible(false);
        movieIDField.setVisible(false);
        priceField.setVisible(false);
        statusField.setVisible(false);
        showtimeIDField.setVisible(false);
        seatNoField.setVisible(false);
        submitButton.setVisible(false);
        showtimeTable.setRowHeight(40);
        movieTable.setRowHeight(40);
        movieTable.setVisible(false);
        sellButton.setVisible(false);
        cancelticketButton.setVisible(false);
        movieScrollPane.setVisible(false);
        showtimeTable.setVisible(false);
        showtimeScrollPane.setVisible(false);
        moviescreeningLabel.setVisible(false);
        moviescreeningLabel1.setVisible(false);
        dateLabel.setVisible(false);
        for (int i = 0; i < showtimeTable.getColumnCount(); i++) {
            TableColumn column = showtimeTable.getColumnModel().getColumn(i);
            column.setHeaderRenderer(new CustomRenderer());
        }
        showtimeScrollPane.setBorder(new LineBorder(Color.BLACK));
        movieScrollPane.setBorder(new LineBorder(Color.BLACK));
        JViewport viewport = showtimeScrollPane.getViewport();
        viewport.setBackground(Color.BLACK);
        for (int i = 0; i < movieTable.getColumnCount(); i++) {
            TableColumn column = movieTable.getColumnModel().getColumn(i);
            column.setHeaderRenderer(new CustomRenderer());
        }
        JViewport viewport1 = movieScrollPane.getViewport();
        viewport1.setBackground(Color.BLACK);
    }

    public staffDashboard(String id) {
        this.id = id;
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
        rightsidePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        adminIdPanel = new javax.swing.JPanel();
        adminidLabel = new javax.swing.JLabel();
        moviePanel = new javax.swing.JPanel();
        movieLabel = new javax.swing.JLabel();
        movieiconLabel = new javax.swing.JLabel();
        showtimePanel = new javax.swing.JPanel();
        showtimeLabel = new javax.swing.JLabel();
        showtimeiconLabel = new javax.swing.JLabel();
        ticketPanel = new javax.swing.JPanel();
        ticketLabel = new javax.swing.JLabel();
        ticketiconLabel = new javax.swing.JLabel();
        customerinfoPanel = new javax.swing.JPanel();
        customerinfoLabel = new javax.swing.JLabel();
        customerinfoiconLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        leftsidePanel = new javax.swing.JPanel();
        moviemainPanel = new javax.swing.JPanel();
        moviescreeningLabel = new javax.swing.JLabel();
        movieScrollPane = new javax.swing.JScrollPane();
        movieTable = new javax.swing.JTable();
        showtimemainPanel = new javax.swing.JPanel();
        showtimeScrollPane = new javax.swing.JScrollPane();
        showtimeTable = new javax.swing.JTable();
        showtimeComboBox = new javax.swing.JComboBox<>();
        moviescreeningLabel1 = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        ticketmainPanel = new javax.swing.JPanel();
        sellButton = new javax.swing.JButton();
        cancelticketButton = new javax.swing.JButton();
        customerIDField = new javax.swing.JTextField();
        priceField = new javax.swing.JTextField();
        showtimeIDField = new javax.swing.JTextField();
        movieIDField = new javax.swing.JTextField();
        statusField = new javax.swing.JTextField();
        seatNoField = new javax.swing.JTextField();
        submitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        customermainPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        submitButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        fnameField = new javax.swing.JTextField();
        lnameField = new javax.swing.JTextField();
        emailField = new javax.swing.JTextField();
        contactField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        customerIDField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        rightsidePanel.setBackground(new java.awt.Color(0, 0, 0));

        titleLabel.setBackground(new java.awt.Color(255, 255, 255));
        titleLabel.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("QUANTUM CINEPLEX");

        adminIdPanel.setBackground(new java.awt.Color(255, 0, 0));

        adminidLabel.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        adminidLabel.setForeground(new java.awt.Color(255, 255, 255));
        adminidLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adminidLabel.setText("STAFF ID");

        javax.swing.GroupLayout adminIdPanelLayout = new javax.swing.GroupLayout(adminIdPanel);
        adminIdPanel.setLayout(adminIdPanelLayout);
        adminIdPanelLayout.setHorizontalGroup(
            adminIdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 183, Short.MAX_VALUE)
            .addGroup(adminIdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(adminidLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
        );
        adminIdPanelLayout.setVerticalGroup(
            adminIdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 36, Short.MAX_VALUE)
            .addGroup(adminIdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(adminidLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
        );

        moviePanel.setBackground(new java.awt.Color(255, 255, 255));
        moviePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moviePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                moviePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                moviePanelMouseExited(evt);
            }
        });

        movieLabel.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        movieLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        movieLabel.setText("MOVIE");
        movieLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                movieLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                movieLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                movieLabelMouseExited(evt);
            }
        });

        movieiconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-movie.png"))); // NOI18N
        movieiconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                movieiconLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                movieiconLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                movieiconLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout moviePanelLayout = new javax.swing.GroupLayout(moviePanel);
        moviePanel.setLayout(moviePanelLayout);
        moviePanelLayout.setHorizontalGroup(
            moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(moviePanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(movieiconLabel)
                .addGap(38, 38, 38)
                .addComponent(movieLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        moviePanelLayout.setVerticalGroup(
            moviePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(movieLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(movieiconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        showtimePanel.setBackground(new java.awt.Color(255, 255, 255));
        showtimePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showtimePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showtimePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                showtimePanelMouseExited(evt);
            }
        });

        showtimeLabel.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        showtimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        showtimeLabel.setText("SHOWTIME");
        showtimeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showtimeLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showtimeLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                showtimeLabelMouseExited(evt);
            }
        });

        showtimeiconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-showtime.png"))); // NOI18N
        showtimeiconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showtimeiconLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showtimeiconLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                showtimeiconLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout showtimePanelLayout = new javax.swing.GroupLayout(showtimePanel);
        showtimePanel.setLayout(showtimePanelLayout);
        showtimePanelLayout.setHorizontalGroup(
            showtimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showtimePanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(showtimeiconLabel)
                .addGap(32, 32, 32)
                .addComponent(showtimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        showtimePanelLayout.setVerticalGroup(
            showtimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(showtimeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(showtimeiconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ticketPanel.setBackground(new java.awt.Color(255, 255, 255));
        ticketPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ticketPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ticketPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ticketPanelMouseExited(evt);
            }
        });

        ticketLabel.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        ticketLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ticketLabel.setText("TICKETS");
        ticketLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ticketLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ticketLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ticketLabelMouseExited(evt);
            }
        });

        ticketiconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-ticket.png"))); // NOI18N
        ticketiconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ticketiconLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ticketiconLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ticketiconLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ticketPanelLayout = new javax.swing.GroupLayout(ticketPanel);
        ticketPanel.setLayout(ticketPanelLayout);
        ticketPanelLayout.setHorizontalGroup(
            ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(ticketiconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(ticketLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ticketPanelLayout.setVerticalGroup(
            ticketPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(ticketiconLabel)
            .addComponent(ticketLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        customerinfoPanel.setBackground(new java.awt.Color(255, 255, 255));
        customerinfoPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerinfoPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                customerinfoPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                customerinfoPanelMouseExited(evt);
            }
        });

        customerinfoLabel.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        customerinfoLabel.setText("CUSTOMER");
        customerinfoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerinfoLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                customerinfoLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                customerinfoLabelMouseExited(evt);
            }
        });

        customerinfoiconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-customer.png"))); // NOI18N
        customerinfoiconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerinfoiconLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                customerinfoiconLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                customerinfoiconLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout customerinfoPanelLayout = new javax.swing.GroupLayout(customerinfoPanel);
        customerinfoPanel.setLayout(customerinfoPanelLayout);
        customerinfoPanelLayout.setHorizontalGroup(
            customerinfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerinfoPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(customerinfoiconLabel)
                .addGap(43, 43, 43)
                .addComponent(customerinfoLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        customerinfoPanelLayout.setVerticalGroup(
            customerinfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(customerinfoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(customerinfoiconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        logoutButton.setBackground(new java.awt.Color(204, 0, 0));
        logoutButton.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        logoutButton.setForeground(new java.awt.Color(255, 255, 255));
        logoutButton.setText("LOG OUT");
        logoutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutButtonMouseExited(evt);
            }
        });
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rightsidePanelLayout = new javax.swing.GroupLayout(rightsidePanel);
        rightsidePanel.setLayout(rightsidePanelLayout);
        rightsidePanelLayout.setHorizontalGroup(
            rightsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(moviePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(showtimePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ticketPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(customerinfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(rightsidePanelLayout.createSequentialGroup()
                .addGroup(rightsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rightsidePanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rightsidePanelLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(adminIdPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rightsidePanelLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        rightsidePanelLayout.setVerticalGroup(
            rightsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightsidePanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(adminIdPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113)
                .addComponent(moviePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(showtimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ticketPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(customerinfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addGap(63, 63, 63))
        );

        leftsidePanel.setBackground(new java.awt.Color(0, 0, 0));

        moviemainPanel.setBackground(new java.awt.Color(0, 0, 0));

        moviescreeningLabel.setBackground(new java.awt.Color(0, 0, 0));
        moviescreeningLabel.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        moviescreeningLabel.setForeground(new java.awt.Color(255, 255, 255));
        moviescreeningLabel.setText("MOVIES Screening");

        movieTable.setBackground(new java.awt.Color(0, 0, 0));
        movieTable.setForeground(new java.awt.Color(255, 255, 255));
        movieTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MOVIE ID", "NAME", "GENRE", "RELEASE DATE", "LANGUAGE", "COMPANY", "DUARATION"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        movieTable.setRowHeight(30);
        movieScrollPane.setViewportView(movieTable);

        javax.swing.GroupLayout moviemainPanelLayout = new javax.swing.GroupLayout(moviemainPanel);
        moviemainPanel.setLayout(moviemainPanelLayout);
        moviemainPanelLayout.setHorizontalGroup(
            moviemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(moviemainPanelLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(movieScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(moviemainPanelLayout.createSequentialGroup()
                .addGap(265, 265, 265)
                .addComponent(moviescreeningLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        moviemainPanelLayout.setVerticalGroup(
            moviemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, moviemainPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(moviescreeningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
                .addComponent(movieScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        showtimemainPanel.setBackground(new java.awt.Color(0, 0, 0));

        showtimeTable.setBackground(new java.awt.Color(0, 0, 0));
        showtimeTable.setForeground(new java.awt.Color(255, 255, 255));
        showtimeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SHOWTIME ID", "MOVIE NAME", "HALL NO", "RES SEATS", "AVL SEATS", "START TIME", "END TIME", "DATE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        showtimeTable.setRowHeight(30);
        showtimeTable.getTableHeader().setReorderingAllowed(false);
        showtimeScrollPane.setViewportView(showtimeTable);
        if (showtimeTable.getColumnModel().getColumnCount() > 0) {
            showtimeTable.getColumnModel().getColumn(0).setResizable(false);
            showtimeTable.getColumnModel().getColumn(1).setResizable(false);
            showtimeTable.getColumnModel().getColumn(2).setResizable(false);
            showtimeTable.getColumnModel().getColumn(3).setResizable(false);
            showtimeTable.getColumnModel().getColumn(4).setResizable(false);
            showtimeTable.getColumnModel().getColumn(5).setResizable(false);
            showtimeTable.getColumnModel().getColumn(6).setResizable(false);
            showtimeTable.getColumnModel().getColumn(7).setResizable(false);
        }

        showtimeComboBox.setBackground(new java.awt.Color(0, 0, 0));
        showtimeComboBox.setForeground(new java.awt.Color(255, 255, 255));

        moviescreeningLabel1.setBackground(new java.awt.Color(0, 0, 0));
        moviescreeningLabel1.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        moviescreeningLabel1.setForeground(new java.awt.Color(255, 255, 255));
        moviescreeningLabel1.setText("Screen schedule");

        dateLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        dateLabel.setForeground(new java.awt.Color(255, 255, 255));
        dateLabel.setText("DATE");

        javax.swing.GroupLayout showtimemainPanelLayout = new javax.swing.GroupLayout(showtimemainPanel);
        showtimemainPanel.setLayout(showtimemainPanelLayout);
        showtimemainPanelLayout.setHorizontalGroup(
            showtimemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showtimemainPanelLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(showtimemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showtimemainPanelLayout.createSequentialGroup()
                        .addComponent(showtimeScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 820, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showtimemainPanelLayout.createSequentialGroup()
                        .addGroup(showtimemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(moviescreeningLabel1)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, showtimemainPanelLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(dateLabel)
                                .addGap(36, 36, 36)
                                .addComponent(showtimeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(270, 270, 270))))
        );
        showtimemainPanelLayout.setVerticalGroup(
            showtimemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showtimemainPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(moviescreeningLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addGroup(showtimemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showtimeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(showtimeScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        ticketmainPanel.setBackground(new java.awt.Color(0, 0, 0));
        ticketmainPanel.setForeground(new java.awt.Color(255, 255, 255));

        sellButton.setBackground(new java.awt.Color(204, 0, 0));
        sellButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        sellButton.setForeground(new java.awt.Color(255, 255, 255));
        sellButton.setText("SELL");
        sellButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sellButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sellButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                sellButtonMouseExited(evt);
            }
        });
        sellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellButtonActionPerformed(evt);
            }
        });

        cancelticketButton.setBackground(new java.awt.Color(204, 0, 0));
        cancelticketButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        cancelticketButton.setForeground(new java.awt.Color(255, 255, 255));
        cancelticketButton.setText("CANCEL TICKET");
        cancelticketButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelticketButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cancelticketButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cancelticketButtonMouseExited(evt);
            }
        });
        cancelticketButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelticketButtonActionPerformed(evt);
            }
        });

        customerIDField.setForeground(new java.awt.Color(128, 128, 128));
        customerIDField.setText("Customer ID");
        customerIDField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                customerIDFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                customerIDFieldFocusLost(evt);
            }
        });

        priceField.setForeground(new java.awt.Color(128, 128, 128));
        priceField.setText("Price");
        priceField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                priceFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                priceFieldFocusLost(evt);
            }
        });

        showtimeIDField.setForeground(new java.awt.Color(128, 128, 128));
        showtimeIDField.setText("Showtime ID");
        showtimeIDField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                showtimeIDFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                showtimeIDFieldFocusLost(evt);
            }
        });

        movieIDField.setForeground(new java.awt.Color(128, 128, 128));
        movieIDField.setText("Movie ID");
        movieIDField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                movieIDFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                movieIDFieldFocusLost(evt);
            }
        });
        movieIDField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieIDFieldActionPerformed(evt);
            }
        });

        statusField.setForeground(new java.awt.Color(128, 128, 128));
        statusField.setText("Status");
        statusField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                statusFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                statusFieldFocusLost(evt);
            }
        });

        seatNoField.setForeground(new java.awt.Color(128, 128, 128));
        seatNoField.setText("Seat No");
        seatNoField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                seatNoFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                seatNoFieldFocusLost(evt);
            }
        });
        seatNoField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seatNoFieldActionPerformed(evt);
            }
        });

        submitButton.setBackground(new java.awt.Color(204, 0, 0));
        submitButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        submitButton.setForeground(new java.awt.Color(255, 255, 255));
        submitButton.setText("submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("MANAGE TICKETS");

        javax.swing.GroupLayout ticketmainPanelLayout = new javax.swing.GroupLayout(ticketmainPanel);
        ticketmainPanel.setLayout(ticketmainPanelLayout);
        ticketmainPanelLayout.setHorizontalGroup(
            ticketmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketmainPanelLayout.createSequentialGroup()
                .addContainerGap(119, Short.MAX_VALUE)
                .addGroup(ticketmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ticketmainPanelLayout.createSequentialGroup()
                        .addComponent(sellButton, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(cancelticketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ticketmainPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(282, 282, 282))))
            .addGroup(ticketmainPanelLayout.createSequentialGroup()
                .addGroup(ticketmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ticketmainPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ticketmainPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(ticketmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(showtimeIDField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(priceField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(customerIDField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(111, 111, 111)
                        .addGroup(ticketmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(movieIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(seatNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ticketmainPanelLayout.setVerticalGroup(
            ticketmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketmainPanelLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(ticketmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sellButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelticketButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(ticketmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movieIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(ticketmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(ticketmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showtimeIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seatNoField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81)
                .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(125, Short.MAX_VALUE))
        );

        customermainPanel.setBackground(new java.awt.Color(0, 0, 0));

        addButton.setBackground(new java.awt.Color(204, 0, 0));
        addButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setText("ADD");
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addButtonMouseExited(evt);
            }
        });
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        editButton.setBackground(new java.awt.Color(204, 0, 0));
        editButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setText("EDIT");
        editButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editButtonMouseExited(evt);
            }
        });
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(204, 0, 0));
        deleteButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteButton.setText("DELETE");
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteButtonMouseExited(evt);
            }
        });
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        submitButton1.setBackground(new java.awt.Color(204, 0, 0));
        submitButton1.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        submitButton1.setForeground(new java.awt.Color(255, 255, 255));
        submitButton1.setText("add");
        submitButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Engravers MT", 1, 26)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("managing customers");

        fnameField.setForeground(new java.awt.Color(204, 204, 204));
        fnameField.setText("First Name");
        fnameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fnameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fnameFieldFocusLost(evt);
            }
        });

        lnameField.setForeground(new java.awt.Color(204, 204, 204));
        lnameField.setText("Last Name");
        lnameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lnameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lnameFieldFocusLost(evt);
            }
        });

        emailField.setForeground(new java.awt.Color(204, 204, 204));
        emailField.setText("Email Address");
        emailField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFieldFocusLost(evt);
            }
        });

        contactField.setForeground(new java.awt.Color(204, 204, 204));
        contactField.setText("Contact Number");
        contactField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                contactFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                contactFieldFocusLost(evt);
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

        customerIDField1.setForeground(new java.awt.Color(204, 204, 204));
        customerIDField1.setText("Customer ID");
        customerIDField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                customerIDField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                customerIDField1FocusLost(evt);
            }
        });

        javax.swing.GroupLayout customermainPanelLayout = new javax.swing.GroupLayout(customermainPanel);
        customermainPanel.setLayout(customermainPanelLayout);
        customermainPanelLayout.setHorizontalGroup(
            customermainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customermainPanelLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customermainPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(207, 207, 207))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customermainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(customermainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customermainPanelLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(customermainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(passwordField)
                            .addComponent(emailField)
                            .addComponent(contactField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(customermainPanelLayout.createSequentialGroup()
                        .addComponent(fnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(106, 106, 106)
                        .addComponent(lnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(customermainPanelLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(customerIDField1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customermainPanelLayout.createSequentialGroup()
                        .addComponent(submitButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addGap(183, 183, 183))
        );
        customermainPanelLayout.setVerticalGroup(
            customermainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customermainPanelLayout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jLabel2)
                .addGap(51, 51, 51)
                .addGroup(customermainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(customerIDField1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(customermainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lnameField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(contactField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addComponent(submitButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
        );

        javax.swing.GroupLayout leftsidePanelLayout = new javax.swing.GroupLayout(leftsidePanel);
        leftsidePanel.setLayout(leftsidePanelLayout);
        leftsidePanelLayout.setHorizontalGroup(
            leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 860, Short.MAX_VALUE)
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(moviemainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(showtimemainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ticketmainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(customermainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        leftsidePanelLayout.setVerticalGroup(
            leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(moviemainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(showtimemainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ticketmainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(customermainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(rightsidePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leftsidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(rightsidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(leftsidePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        dispose();
        staffLoginPage obj = new staffLoginPage();
        obj.setVisible(true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void moviePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moviePanelMouseEntered
        if (name != "moviePanel") {
            moviePanel.setBackground(new Color(0xF5F5F5));
            moviePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_moviePanelMouseEntered

    private void moviePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moviePanelMouseExited
        if (name != "moviePanel") {
            moviePanel.setBackground(Color.WHITE);
            moviePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_moviePanelMouseExited

    private void movieLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieLabelMouseEntered
        if (name != "moviePanel") {
            moviePanel.setBackground(new Color(0xF5F5F5));
            moviePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_movieLabelMouseEntered

    private void movieLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieLabelMouseExited
        if (name != "moviePanel") {
            moviePanel.setBackground(Color.WHITE);
            moviePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_movieLabelMouseExited

    private void movieiconLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieiconLabelMouseEntered
        if (name != "moviePanel") {
            moviePanel.setBackground(new Color(0xF5F5F5));
            moviePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_movieiconLabelMouseEntered

    private void movieiconLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieiconLabelMouseExited
        if (name != "moviePanel") {
            moviePanel.setBackground(Color.WHITE);
            moviePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_movieiconLabelMouseExited

    private void showtimePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showtimePanelMouseEntered
        if (name != "showtimePanel") {
            showtimePanel.setBackground(new Color(0xF5F5F5));
            showtimePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_showtimePanelMouseEntered

    private void showtimePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showtimePanelMouseExited
        if (name != "showtimePanel") {
            showtimePanel.setBackground(Color.WHITE);
            showtimePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_showtimePanelMouseExited

    private void showtimeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showtimeLabelMouseEntered
        if (name != "showtimePanel") {
            showtimePanel.setBackground(new Color(0xF5F5F5));
            showtimePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_showtimeLabelMouseEntered

    private void showtimeLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showtimeLabelMouseExited
        if (name != "showtimePanel") {
            showtimePanel.setBackground(Color.WHITE);
            showtimePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_showtimeLabelMouseExited

    private void showtimeiconLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showtimeiconLabelMouseEntered
        if (name != "showtimePanel") {
            showtimePanel.setBackground(new Color(0xF5F5F5));
            showtimePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_showtimeiconLabelMouseEntered

    private void showtimeiconLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showtimeiconLabelMouseExited
        if (name != "showtimePanel") {
            showtimePanel.setBackground(Color.WHITE);
            showtimePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_showtimeiconLabelMouseExited

    private void ticketPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketPanelMouseEntered
        if (name != "ticketPanel") {
            ticketPanel.setBackground(new Color(0xF5F5F5));
            ticketPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_ticketPanelMouseEntered

    private void ticketPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketPanelMouseExited
        if (name != "ticketPanel") {
            ticketPanel.setBackground(Color.WHITE);
            ticketPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_ticketPanelMouseExited

    private void ticketLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketLabelMouseEntered
        if (name != "ticketPanel") {
            ticketPanel.setBackground(new Color(0xF5F5F5));
            ticketPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_ticketLabelMouseEntered

    private void ticketLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketLabelMouseExited
        if (name != "ticketPanel") {
            ticketPanel.setBackground(Color.WHITE);
            ticketPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_ticketLabelMouseExited

    private void ticketiconLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketiconLabelMouseEntered
        if (name != "ticketPanel") {
            ticketPanel.setBackground(new Color(0xF5F5F5));
            ticketPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_ticketiconLabelMouseEntered

    private void ticketiconLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketiconLabelMouseExited
        if (name != "ticketPanel") {
            ticketPanel.setBackground(Color.WHITE);
            ticketPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_ticketiconLabelMouseExited

    private void customerinfoPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerinfoPanelMouseEntered
        if (name != "customerinfoPanel") {
            customerinfoPanel.setBackground(new Color(0xF5F5F5));
            customerinfoPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_customerinfoPanelMouseEntered

    private void customerinfoPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerinfoPanelMouseExited
        if (name != "customerinfoPanel") {
            customerinfoPanel.setBackground(Color.WHITE);
            customerinfoPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_customerinfoPanelMouseExited

    private void customerinfoLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerinfoLabelMouseEntered
        if (name != "customerinfoPanel") {
            customerinfoPanel.setBackground(new Color(0xF5F5F5));
            customerinfoPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_customerinfoLabelMouseEntered

    private void customerinfoLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerinfoLabelMouseExited
        if (name != "customerinfoPanel") {
            customerinfoPanel.setBackground(Color.WHITE);
            customerinfoPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_customerinfoLabelMouseExited

    private void customerinfoiconLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerinfoiconLabelMouseEntered
        if (name != "customerinfoPanel") {
            customerinfoPanel.setBackground(new Color(0xF5F5F5));
            customerinfoPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_customerinfoiconLabelMouseEntered

    private void customerinfoiconLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerinfoiconLabelMouseExited
        if (name != "customerinfoPanel") {
            customerinfoPanel.setBackground(Color.WHITE);
            customerinfoPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_customerinfoiconLabelMouseExited

    private void logoutButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutButtonMouseEntered
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_logoutButtonMouseEntered

    private void logoutButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutButtonMouseExited
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setBackground(Color.RED);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_logoutButtonMouseExited

    private void moviePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moviePanelMouseClicked

        name = "moviePanel";
        addButton.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        moviePanel.setBackground(Color.red);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        customerIDField1.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        submitButton1.setVisible(false);
        moviemainPanel.setVisible(true);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        sellButton.setVisible(false);
        cancelticketButton.setVisible(false);
        moviescreeningLabel.setVisible(true);
        movieTable.setVisible(true);
        movieScrollPane.setVisible(true);
        showtimeTable.setVisible(false);
        showtimeScrollPane.setVisible(false);

        movieTable.getColumnModel().getColumn(1).setPreferredWidth(150);

        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) movieTable.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT MOVIE_ID, NAME, GENERE, RELEASE_DATE, LANGUAGE, PRODUCTION_COMPANY, DURATION FROM MOVIE");
            while (rs.next()) {
                String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
                tblmodel.addRow(tbData);
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_moviePanelMouseClicked

    private void movieLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieLabelMouseClicked

        name = "moviePanel";
        addButton.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        customerIDField1.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        submitButton1.setVisible(false);
        moviescreeningLabel.setVisible(true);
        movieTable.setVisible(true);
        movieScrollPane.setVisible(true);
        showtimeTable.setVisible(false);
        showtimeScrollPane.setVisible(false);
        sellButton.setVisible(false);
        cancelticketButton.setVisible(false);
        moviePanel.setBackground(Color.red);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);

        moviemainPanel.setVisible(true);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) movieTable.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT MOVIE_ID, NAME, GENERE, RELEASE_DATE, LANGUAGE, PRODUCTION_COMPANY, DURATION FROM MOVIE");
            while (rs.next()) {
                String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
                tblmodel.addRow(tbData);
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_movieLabelMouseClicked

    private void movieiconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieiconLabelMouseClicked

        name = "moviePanel";
        addButton.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        customerIDField1.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        submitButton1.setVisible(false);
        moviescreeningLabel.setVisible(true);
        movieTable.setVisible(true);
        movieScrollPane.setVisible(true);
        showtimeTable.setVisible(false);
        showtimeScrollPane.setVisible(false);
        sellButton.setVisible(false);
        cancelticketButton.setVisible(false);
        moviePanel.setBackground(Color.red);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);

        moviemainPanel.setVisible(true);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) movieTable.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT MOVIE_ID, NAME, GENERE, RELEASE_DATE, LANGUAGE, PRODUCTION_COMPANY, DURATION FROM MOVIE");
            while (rs.next()) {
                String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
                tblmodel.addRow(tbData);
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_movieiconLabelMouseClicked

    private void showtimePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showtimePanelMouseClicked

        name = "showtimePanel";

        addButton.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        customerIDField1.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        submitButton1.setVisible(false);
        movieTable.setVisible(false);
        movieScrollPane.setVisible(false);
        moviescreeningLabel1.setVisible(true);
        dateLabel.setVisible(true);
        showtimeTable.setVisible(true);
        showtimeScrollPane.setVisible(true);
        sellButton.setVisible(false);
        cancelticketButton.setVisible(false);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.RED);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        showtimeComboBox.setBackground(Color.BLACK);
        showtimeComboBox.setForeground(Color.WHITE);
        fillDateComboBox(showtimeComboBox);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(true);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);

        // Add an ActionListener to the showtimeComboBox
        showtimeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected date from the combo box
                String selectedDate = (String) showtimeComboBox.getSelectedItem();

                // Run the query based on the selected date
                dbconnection db = new dbconnection();
                DefaultTableModel tblmodel = (DefaultTableModel) showtimeTable.getModel();
                try {
                    tblmodel.setRowCount(0);
                    ResultSet rs = db.st.executeQuery("SELECT S.SHOWTIME_ID, M.NAME, S.HALL_ID, S.RESERVED_SEATS, S.AVAILABLE_SEATS, S.STARTTIME, S.ENDTIME, S.DATE FROM SHOWTIME S JOIN MOVIE M ON S.MOVIE_ID = M.MOVIE_ID AND S.DATE = '" + selectedDate + "'");
                    while (rs.next()) {
                        String tbData[] = {rs.getString("S.SHOWTIME_ID"), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)};
                        tblmodel.addRow(tbData);
                        showtimeTable.setAutoResizeMode(showtimeTable.AUTO_RESIZE_ALL_COLUMNS);
                    }
                } catch (SQLException ex) {
                    JFrame f = null;
                    JOptionPane.showMessageDialog(f, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }//GEN-LAST:event_showtimePanelMouseClicked

    private void showtimeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showtimeLabelMouseClicked

        name = "showtimePanel";
        showtimeComboBox.setBackground(Color.BLACK);
        showtimeComboBox.setForeground(Color.WHITE);
        fillDateComboBox(showtimeComboBox);
        addButton.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        customerIDField1.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        submitButton1.setVisible(false);
        movieTable.setVisible(false);
        movieScrollPane.setVisible(false);
        moviescreeningLabel1.setVisible(true);
        dateLabel.setVisible(true);
        showtimeTable.setVisible(true);
        showtimeScrollPane.setVisible(true);
        sellButton.setVisible(false);
        cancelticketButton.setVisible(false);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.red);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);

        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(true);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) showtimeTable.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT S.SHOWTIME_ID, M.NAME, S.HALL_ID, S.RESERVED_SEATS, S.AVAILABLE_SEATS, S.STARTTIME, S.ENDTIME FROM SHOWTIME S JOIN MOVIE M ON S.MOVIE_ID = M.MOVIE_ID");
            while (rs.next()) {
                String tbData[] = {rs.getString("S.SHOWTIME_ID"), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
                tblmodel.addRow(tbData);
                showtimeTable.setAutoResizeMode(showtimeTable.AUTO_RESIZE_ALL_COLUMNS);
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_showtimeLabelMouseClicked

    private void showtimeiconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showtimeiconLabelMouseClicked

        name = "showtimePanel";
        showtimeComboBox.setBackground(Color.BLACK);
        showtimeComboBox.setForeground(Color.WHITE);
        fillDateComboBox(showtimeComboBox);
        addButton.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        customerIDField1.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        submitButton1.setVisible(false);
        movieTable.setVisible(false);
        movieScrollPane.setVisible(false);
        moviescreeningLabel1.setVisible(true);
        dateLabel.setVisible(true);
        showtimeTable.setVisible(true);
        showtimeScrollPane.setVisible(true);
        sellButton.setVisible(false);
        cancelticketButton.setVisible(false);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.red);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);

        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(true);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) showtimeTable.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT S.SHOWTIME_ID, M.NAME, S.HALL_ID, S.RESERVED_SEATS, S.AVAILABLE_SEATS, S.STARTTIME, S.ENDTIME FROM SHOWTIME S JOIN MOVIE M ON S.MOVIE_ID = M.MOVIE_ID");
            while (rs.next()) {
                String tbData[] = {rs.getString("S.SHOWTIME_ID"), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
                tblmodel.addRow(tbData);
                showtimeTable.setAutoResizeMode(showtimeTable.AUTO_RESIZE_ALL_COLUMNS);
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_showtimeiconLabelMouseClicked

    private void ticketPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketPanelMouseClicked

        name = "ticketPanel";
        addButton.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        customerIDField1.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        submitButton1.setVisible(false);
        movieTable.setVisible(false);
        movieScrollPane.setVisible(false);
        showtimeTable.setVisible(false);
        showtimeScrollPane.setVisible(false);
        sellButton.setVisible(true);
        cancelticketButton.setVisible(true);

        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.red);
        customerinfoPanel.setBackground(Color.WHITE);

        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(true);
        customermainPanel.setVisible(false);
        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) showtimeTable.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT S.SHOWTIME_ID, M.NAME, S.HALL_ID, S.RESERVED_SEATS, S.AVAILABLE_SEATS, S.STARTTIME, S.ENDTIME FROM SHOWTIME S JOIN MOVIE M ON S.MOVIE_ID = M.MOVIE_ID");
            while (rs.next()) {
                String tbData[] = {rs.getString("S.SHOWTIME_ID"), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
                tblmodel.addRow(tbData);
                showtimeTable.setAutoResizeMode(showtimeTable.AUTO_RESIZE_ALL_COLUMNS);
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_ticketPanelMouseClicked

    private void ticketLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketLabelMouseClicked

        name = "ticketPanel";
        addButton.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        customerIDField1.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        submitButton1.setVisible(false);
        movieTable.setVisible(false);
        movieScrollPane.setVisible(false);
        showtimeTable.setVisible(false);
        showtimeScrollPane.setVisible(false);
        sellButton.setVisible(true);
        cancelticketButton.setVisible(true);

        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.red);
        customerinfoPanel.setBackground(Color.WHITE);

        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(true);
        customermainPanel.setVisible(false);
        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) showtimeTable.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT S.SHOWTIME_ID, M.NAME, S.HALL_ID, S.RESERVED_SEATS, S.AVAILABLE_SEATS, S.STARTTIME, S.ENDTIME FROM SHOWTIME S JOIN MOVIE M ON S.MOVIE_ID = M.MOVIE_ID");
            while (rs.next()) {
                String tbData[] = {rs.getString("S.SHOWTIME_ID"), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
                tblmodel.addRow(tbData);
                showtimeTable.setAutoResizeMode(showtimeTable.AUTO_RESIZE_ALL_COLUMNS);
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_ticketLabelMouseClicked

    private void ticketiconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketiconLabelMouseClicked

        name = "ticketPanel";
        addButton.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        customerIDField1.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        submitButton1.setVisible(false);
        movieTable.setVisible(false);
        movieScrollPane.setVisible(false);
        showtimeTable.setVisible(false);
        showtimeScrollPane.setVisible(false);
        sellButton.setVisible(true);
        cancelticketButton.setVisible(true);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.red);
        customerinfoPanel.setBackground(Color.WHITE);

        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(true);
        customermainPanel.setVisible(false);
        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) showtimeTable.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT S.SHOWTIME_ID, M.NAME, S.HALL_ID, S.RESERVED_SEATS, S.AVAILABLE_SEATS, S.STARTTIME, S.ENDTIME FROM SHOWTIME S JOIN MOVIE M ON S.MOVIE_ID = M.MOVIE_ID");
            while (rs.next()) {
                String tbData[] = {rs.getString("S.SHOWTIME_ID"), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
                tblmodel.addRow(tbData);
                showtimeTable.setAutoResizeMode(showtimeTable.AUTO_RESIZE_ALL_COLUMNS);
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_ticketiconLabelMouseClicked

    private void customerinfoPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerinfoPanelMouseClicked

        name = "customerinfoPanel";
        addButton.setVisible(true);
        editButton.setVisible(true);
        deleteButton.setVisible(true);
        customerIDField1.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        submitButton1.setVisible(false);

        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.red);

        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(true);

    }//GEN-LAST:event_customerinfoPanelMouseClicked

    private void customerinfoLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerinfoLabelMouseClicked

        name = "customerinfoPanel";

        addButton.setVisible(true);
        editButton.setVisible(true);
        deleteButton.setVisible(true);
        customerIDField1.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        submitButton1.setVisible(false);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.red);

        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(true);

    }//GEN-LAST:event_customerinfoLabelMouseClicked

    private void customerinfoiconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerinfoiconLabelMouseClicked

        name = "customerinfoPanel";

        addButton.setVisible(true);
        editButton.setVisible(true);
        deleteButton.setVisible(true);
        customerIDField1.setVisible(false);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        submitButton1.setVisible(false);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.red);

        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(true);

    }//GEN-LAST:event_customerinfoiconLabelMouseClicked

    private void movieIDFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieIDFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_movieIDFieldActionPerformed

    private void sellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellButtonActionPerformed
        ActionListener[] listeners = submitButton.getActionListeners();
        if (listeners == null) {
            return;
        }
        for (ActionListener listener : listeners) {
            submitButton.removeActionListener(listener);
        }
        customerIDField.setText("Customer ID");
        priceField.setVisible(true);
        statusField.setVisible(true);
        showtimeIDField.setVisible(true);
        seatNoField.setVisible(true);
        cancelcheck = 0;
        submitButton.addActionListener((ActionEvent e) -> {
            if (customerIDField.getText().trim().isEmpty() || movieIDField.getText().trim().isEmpty() || priceField.getText().trim().isEmpty() || statusField.getText().trim().isEmpty() || showtimeIDField.getText().trim().isEmpty() || seatNoField.getText().trim().isEmpty() || customerIDField.getText().equals("Customer ID") || movieIDField.getText().equals("Movie ID") || priceField.getText().equals("Price") || statusField.getText().equals("Status") || showtimeIDField.getText().equals("Ticket Type") || seatNoField.getText().equals("Seat No")) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "The Fields are Empty!", "Alert", JOptionPane.ERROR_MESSAGE);
                setPromptText(customerIDField, "Customer ID");
                setPromptText(movieIDField, "Movie ID");
                setPromptText(priceField, "Price");
                setPromptText(statusField, "Status");
                setPromptText(showtimeIDField, "Showtime ID");
                setPromptText(seatNoField, "Seat No");
            } else {
                dbconnection db = new dbconnection();
                String sql = "INSERT INTO TICKET (CUSTOMER_ID,MOVIE_ID,PRICE,STATUS,SHOWTIME_ID,SEAT_NO) VALUES ('" + customerIDField.getText() + "','" + movieIDField.getText() + "','" + priceField.getText() + "','" + statusField.getText() + "','" + showtimeIDField.getText() + "','" + seatNoField.getText() + "')";
                String sql1 = "UPDATE SHOWTIME SET RESERVED_SEATS =  RESERVED_SEATS + 1,AVAILABLE_SEATS = AVAILABLE_SEATS -1 WHERE MOVIE_ID = '" + movieIDField.getText() + "' ";
                String sql2 = "UPDATE REVENUE SET EARNING = EARNING + '" + priceField.getText() + "' WHERE MOVIE_ID = '" + movieIDField.getText() + "'";
                try {
                    db.st.executeUpdate(sql);
                    db.st.executeUpdate(sql1);
                    db.st.executeUpdate(sql2);

                    System.out.println(sql2);
                    JFrame f = null;
                    JOptionPane.showMessageDialog(f, "Ticket Booked!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    setPromptText(customerIDField, "Customer ID");
                    setPromptText(movieIDField, "Movie ID");
                    setPromptText(priceField, "Price");
                    setPromptText(statusField, "Status");
                    setPromptText(showtimeIDField, "Showtime ID");
                    setPromptText(seatNoField, "Seat No");
                } catch (SQLException ex) {
                    JFrame f = null;
                    JOptionPane.showMessageDialog(f, "Ticket Booking Failed", "Error", JOptionPane.ERROR_MESSAGE);
                    setPromptText(customerIDField, "Customer ID");
                    setPromptText(movieIDField, "Movie ID");
                    setPromptText(priceField, "Price");
                    setPromptText(statusField, "Status");
                    setPromptText(showtimeIDField, "Showtime ID");
                    setPromptText(seatNoField, "Seat No");
                }
            }
        });
    }//GEN-LAST:event_sellButtonActionPerformed

    private void cancelticketButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelticketButtonActionPerformed
        ActionListener[] listeners = submitButton.getActionListeners();
        if (listeners == null) {
            return;
        }
        for (ActionListener listener : listeners) {
            submitButton.removeActionListener(listener);
        }
        customerIDField.setText("Ticket ID");
        priceField.setVisible(false);
        statusField.setVisible(false);
        showtimeIDField.setVisible(false);
        seatNoField.setVisible(false);
        cancelcheck = 1;
        submitButton.addActionListener((ActionEvent e) -> {
            if (customerIDField.getText().trim().isEmpty() || movieIDField.getText().trim().isEmpty() || customerIDField.getText().equals("Ticket ID") || movieIDField.getText().equals("Movie ID")) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "Fields are Empty!", "Alert", JOptionPane.ERROR_MESSAGE);
                setPromptText(customerIDField, "Ticket ID");
                setPromptText(movieIDField, "Movie ID");
            } else {
                dbconnection db = new dbconnection();
                boolean check = false;
                try {
                    String query = "DELETE FROM TICKET WHERE TICKET_ID = '" + customerIDField.getText() + "'";
                    db.st.executeUpdate(query);
                    JFrame f = null;
                    JOptionPane.showMessageDialog(f, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    setPromptText(customerIDField, "Ticket ID");
                    setPromptText(movieIDField, "Movie ID");
                } catch (SQLException ex) {
                    JFrame f = null;
                    JOptionPane.showMessageDialog(f, "No record found", "Error", JOptionPane.ERROR_MESSAGE);
                    setPromptText(customerIDField, "Ticket ID");
                    setPromptText(movieIDField, "Movie ID");
                }
            }
        });
    }//GEN-LAST:event_cancelticketButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed

    }//GEN-LAST:event_submitButtonActionPerformed

    private void customerIDFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_customerIDFieldFocusGained
        if (customerIDField.getText().equals("Customer ID") || customerIDField.getText().equals("Ticket ID")) {
            setPromptText(customerIDField, null);
        }
    }//GEN-LAST:event_customerIDFieldFocusGained

    private void customerIDFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_customerIDFieldFocusLost
        if (customerIDField.getText().trim().isEmpty() && cancelcheck == 0) {
            setPromptText(customerIDField, "Customer ID");
        }
        if (customerIDField.getText().trim().isEmpty() && cancelcheck == 1) {
            setPromptText(customerIDField, "Ticket ID");
        }
    }//GEN-LAST:event_customerIDFieldFocusLost

    private void movieIDFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_movieIDFieldFocusGained
        if (movieIDField.getText().equals("Movie ID")) {
            setPromptText(movieIDField, null);
        }
    }//GEN-LAST:event_movieIDFieldFocusGained

    private void movieIDFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_movieIDFieldFocusLost
        if (movieIDField.getText().trim().isEmpty()) {
            setPromptText(movieIDField, "Movie ID");
        }
    }//GEN-LAST:event_movieIDFieldFocusLost

    private void priceFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_priceFieldFocusGained
        if (priceField.getText().equals("Price")) {
            setPromptText(priceField, null);
        }
    }//GEN-LAST:event_priceFieldFocusGained

    private void priceFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_priceFieldFocusLost
        if (priceField.getText().trim().isEmpty()) {
            setPromptText(priceField, "Price");
        }
    }//GEN-LAST:event_priceFieldFocusLost

    private void statusFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_statusFieldFocusGained
        if (statusField.getText().equals("Status")) {
            setPromptText(statusField, null);
        }
    }//GEN-LAST:event_statusFieldFocusGained

    private void statusFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_statusFieldFocusLost
        if (statusField.getText().trim().isEmpty()) {
            setPromptText(statusField, "Status");
        }
    }//GEN-LAST:event_statusFieldFocusLost

    private void showtimeIDFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_showtimeIDFieldFocusGained
        if (showtimeIDField.getText().equals("Showtime ID")) {
            setPromptText(showtimeIDField, null);
        }
    }//GEN-LAST:event_showtimeIDFieldFocusGained

    private void showtimeIDFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_showtimeIDFieldFocusLost
        if (showtimeIDField.getText().trim().isEmpty()) {
            setPromptText(showtimeIDField, "Showtime ID");
        }
    }//GEN-LAST:event_showtimeIDFieldFocusLost

    private void seatNoFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_seatNoFieldFocusGained
        if (seatNoField.getText().equals("Seat No")) {
            setPromptText(seatNoField, null);
        }
    }//GEN-LAST:event_seatNoFieldFocusGained

    private void seatNoFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_seatNoFieldFocusLost
        if (seatNoField.getText().trim().isEmpty()) {
            setPromptText(seatNoField, "Seat No");
        }
    }//GEN-LAST:event_seatNoFieldFocusLost

    private void seatNoFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seatNoFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_seatNoFieldActionPerformed

    private void sellButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sellButtonMouseClicked
        customerIDField.setVisible(true);
        movieIDField.setVisible(true);
        priceField.setVisible(true);
        statusField.setVisible(true);
        showtimeIDField.setVisible(true);
        seatNoField.setVisible(true);
        submitButton.setVisible(true);
    }//GEN-LAST:event_sellButtonMouseClicked

    private void cancelticketButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelticketButtonMouseClicked
        customerIDField.setVisible(true);
        movieIDField.setVisible(true);
        submitButton.setVisible(true);
        priceField.setVisible(false);
        statusField.setVisible(false);
        showtimeIDField.setVisible(false);
        seatNoField.setVisible(false);
    }//GEN-LAST:event_cancelticketButtonMouseClicked

    private void fnameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fnameFieldFocusGained
        if (fnameField.getText().equals("First Name")) {
            setPromptText(fnameField, null);
        }
    }//GEN-LAST:event_fnameFieldFocusGained

    private void fnameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fnameFieldFocusLost
        if (fnameField.getText().trim().isEmpty()) {
            setPromptText(fnameField, "First Name");
        }
    }//GEN-LAST:event_fnameFieldFocusLost

    private void lnameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lnameFieldFocusGained
        if (lnameField.getText().equals("Last Name")) {
            setPromptText(lnameField, null);
        }
    }//GEN-LAST:event_lnameFieldFocusGained

    private void lnameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lnameFieldFocusLost
        if (lnameField.getText().trim().isEmpty()) {
            setPromptText(lnameField, "Last Name");
        }
    }//GEN-LAST:event_lnameFieldFocusLost

    private void emailFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusGained
        if (emailField.getText().equals("Email Address")) {
            setPromptText(emailField, null);
        }
    }//GEN-LAST:event_emailFieldFocusGained

    private void emailFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFieldFocusLost
        if (emailField.getText().trim().isEmpty()) {
            setPromptText(emailField, "Email Address");
        }
    }//GEN-LAST:event_emailFieldFocusLost

    private void contactFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactFieldFocusGained
        if (contactField.getText().equals("Contact Number")) {
            setPromptText(contactField, null);
        }
    }//GEN-LAST:event_contactFieldFocusGained

    private void contactFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_contactFieldFocusLost
        if (contactField.getText().trim().isEmpty()) {
            setPromptText(contactField, "Contact Number");
        }
    }//GEN-LAST:event_contactFieldFocusLost

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        submitButton1.setText("ADD");
        ActionListener[] listeners = submitButton1.getActionListeners();
        if (listeners == null) {
            return;
        }
        for (ActionListener listener : listeners) {
            submitButton1.removeActionListener(listener);
        }
        System.out.println("hellp");
        customerIDField1.setVisible(false);
        fnameField.setVisible(true);
        lnameField.setVisible(true);
        emailField.setVisible(true);
        passwordField.setVisible(true);
        contactField.setVisible(true);
        submitButton1.setVisible(true);
        submitButton1.addActionListener((ActionEvent e) -> {
            ActionListener[] listeners1 = submitButton1.getActionListeners();
            if (listeners1 == null) {
                return;
            }
            for (ActionListener listener1 : listeners1) {
                submitButton1.removeActionListener(listener1);
            }
            if (lnameField.getText().trim().isEmpty() || fnameField.getText().trim().isEmpty() || contactField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty() || fnameField.getText().equals("First Name") || lnameField.getText().equals("Last Name") || emailField.getText().equals("Email Address") || passwordField.getText().equals("Password") || contactField.getText().equals("Contact Number")) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "Fields are Empty!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                System.out.println("hhh");
                boolean hasUppercase = false;
                boolean hasSpecialChar = false;

                for (char ch : passwordField.getText().toCharArray()) {
                    if (Character.isUpperCase(ch)) {
                        hasUppercase = true;
                    }
                    if ("!@#$%^&*()".contains(String.valueOf(ch))) {
                        hasSpecialChar = true;
                    }
                }
                if (passwordField.getText().length() == 8 && (hasUppercase || hasSpecialChar)) {
                    dbconnection db = new dbconnection();
                    String name = fnameField.getText() + " " + lnameField.getText();
                    if (submitButton1.getText().equals("ADD")) {
                        String sql = "insert into customer (NAME,EMAIL,PASSWORD,PHONE_NO) values ('" + name + "','" + emailField.getText() + "','" + passwordField.getText() + "','" + contactField.getText() + "')";
                        try {
                            db.st.executeUpdate(sql);
                            JFrame f = null;
                            JOptionPane.showMessageDialog(f, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            dispose();
                            loginPage obj = new loginPage();
                            obj.setVisible(true);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(this, "Try Again");
                            Logger.getLogger(signupPage.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else {
                    JFrame f = null;
                    JOptionPane.showMessageDialog(f, "Password must contain 8 digits and a Special Character or a UpperCase Letter!", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }//GEN-LAST:event_addButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        customerIDField1.setText(null);
        ActionListener[] listeners = submitButton1.getActionListeners();
        if (listeners == null) {
            return;
        }
        for (ActionListener listener : listeners) {
            submitButton1.removeActionListener(listener);
        }
        submitButton1.setText("Find Customer");
        submitButton1.setVisible(true);
        customerIDField1.setVisible(true);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        dbconnection db = new dbconnection();
        submitButton1.addActionListener((ActionEvent e) -> {
            ActionListener[] listeners1 = submitButton1.getActionListeners();
            if (listeners1 == null) {
                return;
            }
            for (ActionListener listener1 : listeners1) {
                submitButton1.removeActionListener(listener1);
            }
            try {
                boolean check = false;
                ResultSet rs1 = db.st.executeQuery("SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = '" + customerIDField1.getText() + "'");
                while (rs1.next()) {
                    check = true;
                }

                if (check) {
                    customerIDField1.setEditable(false);
                    fnameField.setVisible(true);
                    lnameField.setVisible(true);
                    emailField.setVisible(true);
                    passwordField.setVisible(true);
                    contactField.setVisible(true);
                    submitButton1.setText("UPDATE");
                    ActionListener[] actionListeners = submitButton1.getActionListeners();
                    for (ActionListener listener : actionListeners) {
                        submitButton1.removeActionListener(listener);
                    }

                    submitButton1.addActionListener((ActionEvent f) -> {
                        if (lnameField.getText().trim().isEmpty() || fnameField.getText().trim().isEmpty() || contactField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty() || passwordField.getText().trim().isEmpty() || fnameField.getText().equals("First Name") || lnameField.getText().equals("Last Name") || emailField.getText().equals("Email Address") || passwordField.getText().equals("Password") || contactField.getText().equals("Contact Number")) {
                            JFrame f1 = null;
                            JOptionPane.showMessageDialog(f1, "Fields are Empty!", "Alert", JOptionPane.WARNING_MESSAGE);
                        } else {
                            boolean hasUppercase = false;
                            boolean hasSpecialChar = false;

                            for (char ch : passwordField.getText().toCharArray()) {
                                if (Character.isUpperCase(ch)) {
                                    hasUppercase = true;
                                }
                                if ("!@#$%^&*()".contains(String.valueOf(ch))) {
                                    hasSpecialChar = true;
                                }
                            }

                            if (passwordField.getText().length() == 8 && (hasUppercase || hasSpecialChar)) {
                                if (submitButton1.getText().equals("UPDATE")) {
                                    try {
                                        String name = fnameField.getText() + " " + lnameField.getText();
                                        String sql = "UPDATE CUSTOMER SET "
                                                + "NAME = '" + name + "', "
                                                + "EMAIL = '" + emailField.getText() + "', "
                                                + "PASSWORD = '" + passwordField.getText() + "', "
                                                + "PHONE_NO = '" + contactField.getText() + "' "
                                                + "WHERE CUSTOMER_ID = '" + customerIDField1.getText() + "'";
                                        db.st.executeUpdate(sql);
                                        JFrame f2 = null;
                                        JOptionPane.showMessageDialog(f2, "SUCCESS", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    }
                                }
                            } else {
                                JFrame f3 = null;
                                JOptionPane.showMessageDialog(f3, "Password must contain 8 digits and a Special Character or an Uppercase Letter!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    });

                } else {
                    JFrame f = null;
                    JOptionPane.showMessageDialog(f, "Record Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }//GEN-LAST:event_editButtonActionPerformed

    private void submitButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButton1ActionPerformed

    }//GEN-LAST:event_submitButton1ActionPerformed

    private void customerIDField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_customerIDField1FocusGained
        if (customerIDField1.getText().equals("Customer ID")) {
            setPromptText(customerIDField1, null);
        }
    }//GEN-LAST:event_customerIDField1FocusGained

    private void customerIDField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_customerIDField1FocusLost
        if (customerIDField1.getText().trim().isEmpty()) {
            setPromptText(customerIDField1, "Customer ID");
        }
    }//GEN-LAST:event_customerIDField1FocusLost

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        customerIDField1.setText(null);
        customerIDField1.setEditable(true);
        ActionListener[] listeners = submitButton1.getActionListeners();
        if (listeners == null) {
            return;
        }
        for (ActionListener listener : listeners) {
            submitButton1.removeActionListener(listener);
        }
        customerIDField1.setVisible(true);
        submitButton1.setVisible(true);
        fnameField.setVisible(false);
        lnameField.setVisible(false);
        emailField.setVisible(false);
        passwordField.setVisible(false);
        contactField.setVisible(false);
        submitButton1.setText("SUBMIT");
        dbconnection db = new dbconnection();
        submitButton1.addActionListener((ActionEvent e) -> {
            ActionListener[] listeners1 = submitButton1.getActionListeners();
            if (listeners1 == null) {
                return;
            }
            for (ActionListener listener1 : listeners1) {
                submitButton1.removeActionListener(listener1);
            }
            try {
                boolean check = false;
                ResultSet rs1 = db.st.executeQuery("SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = '" + customerIDField1.getText() + "'");
                while (rs1.next()) {
                    check = true;
                }
                if (check) {
                    String sql = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = '" + customerIDField1.getText() + "'";
                    db.st.executeUpdate(sql);
                    JFrame f = null;
                    JOptionPane.showMessageDialog(f, "SUCCESS", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JFrame f = null;
                    JOptionPane.showMessageDialog(f, "Record Not Found", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void sellButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sellButtonMouseEntered
        sellButton.setForeground(Color.BLACK);
        sellButton.setBackground(Color.WHITE);
        sellButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_sellButtonMouseEntered

    private void sellButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sellButtonMouseExited
        sellButton.setForeground(Color.WHITE);
        sellButton.setBackground(Color.RED);
        sellButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_sellButtonMouseExited

    private void cancelticketButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelticketButtonMouseEntered
        cancelticketButton.setForeground(Color.BLACK);
        cancelticketButton.setBackground(Color.WHITE);
        cancelticketButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_cancelticketButtonMouseEntered

    private void cancelticketButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelticketButtonMouseExited
        cancelticketButton.setForeground(Color.WHITE);
        cancelticketButton.setBackground(Color.RED);
        cancelticketButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_cancelticketButtonMouseExited

    private void addButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseEntered
        addButton.setForeground(Color.BLACK);
        addButton.setBackground(Color.WHITE);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_addButtonMouseEntered

    private void addButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseExited
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.RED);
        addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_addButtonMouseExited

    private void editButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseEntered
        editButton.setForeground(Color.BLACK);
        editButton.setBackground(Color.WHITE);
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_editButtonMouseEntered

    private void editButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editButtonMouseExited
        editButton.setForeground(Color.WHITE);
        editButton.setBackground(Color.RED);
        editButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_editButtonMouseExited

    private void deleteButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseEntered
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setBackground(Color.WHITE);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_deleteButtonMouseEntered

    private void deleteButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseExited
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setBackground(Color.RED);
        deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_deleteButtonMouseExited

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
            java.util.logging.Logger.getLogger(staffDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(staffDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(staffDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(staffDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new staffDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel adminIdPanel;
    private javax.swing.JLabel adminidLabel;
    private javax.swing.JButton cancelticketButton;
    private javax.swing.JTextField contactField;
    private javax.swing.JTextField customerIDField;
    private javax.swing.JTextField customerIDField1;
    private javax.swing.JLabel customerinfoLabel;
    private javax.swing.JPanel customerinfoPanel;
    private javax.swing.JLabel customerinfoiconLabel;
    private javax.swing.JPanel customermainPanel;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JTextField emailField;
    private javax.swing.JTextField fnameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel leftsidePanel;
    private javax.swing.JTextField lnameField;
    private javax.swing.JButton logoutButton;
    private javax.swing.JTextField movieIDField;
    private javax.swing.JLabel movieLabel;
    private javax.swing.JPanel moviePanel;
    private javax.swing.JScrollPane movieScrollPane;
    private javax.swing.JTable movieTable;
    private javax.swing.JLabel movieiconLabel;
    private javax.swing.JPanel moviemainPanel;
    private javax.swing.JLabel moviescreeningLabel;
    private javax.swing.JLabel moviescreeningLabel1;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField priceField;
    private javax.swing.JPanel rightsidePanel;
    private javax.swing.JTextField seatNoField;
    private javax.swing.JButton sellButton;
    private javax.swing.JComboBox<String> showtimeComboBox;
    private javax.swing.JTextField showtimeIDField;
    private javax.swing.JLabel showtimeLabel;
    private javax.swing.JPanel showtimePanel;
    private javax.swing.JScrollPane showtimeScrollPane;
    private javax.swing.JTable showtimeTable;
    private javax.swing.JLabel showtimeiconLabel;
    private javax.swing.JPanel showtimemainPanel;
    private javax.swing.JTextField statusField;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton submitButton1;
    private javax.swing.JLabel ticketLabel;
    private javax.swing.JPanel ticketPanel;
    private javax.swing.JLabel ticketiconLabel;
    private javax.swing.JPanel ticketmainPanel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables

    private static void setPromptText(JTextField textField, String prompt) {
        textField.setForeground(prompt == null ? Color.BLACK : Color.GRAY);
        textField.setText(prompt);
    }
}
