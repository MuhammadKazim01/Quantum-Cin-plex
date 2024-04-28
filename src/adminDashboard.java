
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import java.text.SimpleDateFormat;
import java.util.Date;

class CustomHeaderRenderer extends DefaultTableCellRenderer {

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

public class adminDashboard extends javax.swing.JFrame {

    String name;

    public adminDashboard() {
        initComponents();
        Toolkit toolkit = getToolkit();
        Dimension size = toolkit.getScreenSize();
        setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

        //Revenue Panel
        revenueChart();
        ticketChart();

        //Show Movies And Showtimes
        movieButton.setVisible(false);
        showtimeButton.setVisible(false);
        genericScrollPane1.setVisible(false);
        genericTable.setVisible(false);

        for (int i = 0; i < genericTable.getColumnCount(); i++) {
            TableColumn column = genericTable.getColumnModel().getColumn(i);
            column.setHeaderRenderer(new CustomHeaderRenderer());
        }

        JViewport genericViewPort = genericScrollPane1.getViewport();
        genericViewPort.setBackground(Color.BLACK);

        //Movie Panel
        movieAddButton.setVisible(false);
        movieUpdateButton.setVisible(false);
        movieDeleteButton.setVisible(false);
        nameField.setVisible(false);
        movieIDField.setVisible(false);
        genreField.setVisible(false);
        releasedateField.setVisible(false);
        languageField.setVisible(false);
        productioncompanyField.setVisible(false);
        durationField.setVisible(false);
        statusField.setVisible(false);
        genericButton.setVisible(false);

        //Showtime Panel
        addShowtimeButton.setVisible(false);
        updateShowtimeButton.setVisible(false);
        deleteShowtimeButton.setVisible(false);
        showtimeIDField.setVisible(false);
        primaryMovieIDField.setVisible(false);
        primaryHallIDField.setVisible(false);
        startTimeField.setVisible(false);
        endTimeField.setVisible(false);
        dateField.setVisible(false);
        genericButtonShowtime.setVisible(false);

        //Ticket Panel
        ticketSaleScrollPane.setVisible(false);
        ticketSalesTabel.setVisible(false);
        for (int i = 0; i < ticketSalesTabel.getColumnCount(); i++) {
            TableColumn column = ticketSalesTabel.getColumnModel().getColumn(i);
            column.setHeaderRenderer(new CustomHeaderRenderer());
        }

        ticketSalesTabel.getColumnModel().getColumn(0).setPreferredWidth(60);
        ticketSalesTabel.getColumnModel().getColumn(1).setPreferredWidth(150);
        ticketSalesTabel.getColumnModel().getColumn(2).setPreferredWidth(130);
        ticketSalesTabel.getColumnModel().getColumn(4).setPreferredWidth(60);

        JViewport ticketViewPort = ticketSaleScrollPane.getViewport();
        ticketViewPort.setBackground(Color.BLACK);

        //Customer Panel
        CustomerScrollPane.setVisible(false);
        monitorCustomerTable.setVisible(false);
        for (int i = 0; i < monitorCustomerTable.getColumnCount(); i++) {
            TableColumn column = monitorCustomerTable.getColumnModel().getColumn(i);
            column.setHeaderRenderer(new CustomHeaderRenderer());
        }

        monitorCustomerTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        monitorCustomerTable.getColumnModel().getColumn(1).setPreferredWidth(130);
        monitorCustomerTable.getColumnModel().getColumn(4).setPreferredWidth(160);

        JViewport customerViewPort = CustomerScrollPane.getViewport();
        customerViewPort.setBackground(Color.BLACK);
        ratingTable.setVisible(false);
        ratingScrollPane.setVisible(false);

        //Rating Panel
        for (int i = 0; i < ratingTable.getColumnCount(); i++) {
            TableColumn column = ratingTable.getColumnModel().getColumn(i);
            column.setHeaderRenderer(new CustomRenderer());
        }
        ratingScrollPane.setBorder(new LineBorder(Color.BLACK));
        JViewport viewport = ratingScrollPane.getViewport();
        viewport.setBackground(Color.BLACK);
    }

    private void revenueChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dbconnection db = new dbconnection();
        LocalDate currentDate = LocalDate.now();
        LocalDate fiveMonthsBack = currentDate.minusMonths(5);

        try {
            ResultSet rs = db.st.executeQuery("SELECT SUM(R.EARNING) AS TOTAL_REVENUE, "
                    + "MONTHNAME(M.RELEASE_DATE) AS MONTH "
                    + "FROM MOVIE M "
                    + "JOIN revenue R ON M.MOVIE_ID = R.MOVIE_ID "
                    + "WHERE M.RELEASE_DATE BETWEEN '" + fiveMonthsBack + "' AND '" + currentDate + "' "
                    + "GROUP BY MONTHNAME(M.RELEASE_DATE)");

            while (rs.next()) {
                String monthName = rs.getString("MONTH");
                double totalEarnings = rs.getDouble("TOTAL_REVENUE");
                dataset.addValue(totalEarnings, "Revenue", monthName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "Error fetching data", "Error", JOptionPane.ERROR_MESSAGE);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Monthly Revenue", // Chart title
                "Month", // X-axis label
                "Revenue", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        chart.setBackgroundPaint(Color.BLACK);
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setBackgroundPaint(Color.BLACK);
        categoryPlot.setDomainGridlinePaint(Color.BLACK);

        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color barColor = new Color(204, 0, 51);
        renderer.setSeriesPaint(0, barColor);

        Font titleFont = new Font("Times New Roman", Font.BOLD, 16);
        chart.getTitle().setFont(titleFont);

        Font labelFont = new Font("Times New Roman", Font.BOLD, 14);
        Color fontColor = Color.WHITE; // Set font color to white

        chart.getCategoryPlot().getDomainAxis().setLabelFont(labelFont);
        chart.getCategoryPlot().getRangeAxis().setLabelFont(labelFont);
        chart.getCategoryPlot().getDomainAxis().setTickLabelFont(labelFont);
        chart.getCategoryPlot().getRangeAxis().setTickLabelFont(labelFont);

        // Set the font color to white for both domain and range axes
        chart.getCategoryPlot().getDomainAxis().setLabelPaint(fontColor);
        chart.getCategoryPlot().getRangeAxis().setLabelPaint(fontColor);
        chart.getCategoryPlot().getDomainAxis().setTickLabelPaint(fontColor);
        chart.getCategoryPlot().getRangeAxis().setTickLabelPaint(fontColor);

        ChartPanel barChartPanel = new ChartPanel(chart);

        earningGraphPanel.removeAll();
        earningGraphPanel.add(barChartPanel, BorderLayout.CENTER);
        earningGraphPanel.validate();
    }

    private void ticketChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dbconnection db = new dbconnection();
        try {
            ResultSet rs = db.st.executeQuery("SELECT T.MOVIE_ID as MOVIE_ID, COUNT(*) AS TICKETS_SOLD "
                    + "FROM TICKET T JOIN MOVIE M ON T.MOVIE_ID = M.MOVIE_ID "
                    + "WHERE M.STATUS = 'Active' "
                    + "GROUP BY MOVIE_ID");

            while (rs.next()) {
                String movieName = rs.getString("MOVIE_ID");
                int ticketsSold = rs.getInt("TICKETS_SOLD");
                dataset.addValue(ticketsSold, "Tickets Sold", movieName);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "Error fetching data", "Error", JOptionPane.ERROR_MESSAGE);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Tickets Sold per Movie", // Chart title
                "Movie ID", // X-axis label
                "Tickets Sold", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL,
                false,
                true,
                false
        );

        chart.setBackgroundPaint(Color.BLACK);
        CategoryPlot categoryPlot = chart.getCategoryPlot();
        categoryPlot.setBackgroundPaint(Color.BLACK);
        categoryPlot.setDomainGridlinePaint(Color.BLACK);

        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        Color barColor = new Color(204, 0, 51);
        renderer.setSeriesPaint(0, barColor);

        Font titleFont = new Font("Times New Roman", Font.BOLD, 16);
        chart.getTitle().setFont(titleFont);

        Font labelFont = new Font("Times New Roman", Font.BOLD, 14);
        Color fontColor = Color.WHITE;

        chart.getCategoryPlot().getDomainAxis().setLabelFont(labelFont);
        chart.getCategoryPlot().getRangeAxis().setLabelFont(labelFont);
        chart.getCategoryPlot().getDomainAxis().setTickLabelFont(labelFont);
        chart.getCategoryPlot().getRangeAxis().setTickLabelFont(labelFont);

        // Set the font color to white for both domain and range axes
        chart.getCategoryPlot().getDomainAxis().setLabelPaint(fontColor);
        chart.getCategoryPlot().getRangeAxis().setLabelPaint(fontColor);
        chart.getCategoryPlot().getDomainAxis().setTickLabelPaint(fontColor);
        chart.getCategoryPlot().getRangeAxis().setTickLabelPaint(fontColor);

        ChartPanel barChartPanel = new ChartPanel(chart);

        ticketGraphPanel.removeAll();
        ticketGraphPanel.add(barChartPanel, BorderLayout.CENTER);
        ticketGraphPanel.validate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        rightsidePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        adminIdPanel = new javax.swing.JPanel();
        adminidLabel = new javax.swing.JLabel();
        revenuePanel = new javax.swing.JPanel();
        revenueLabel = new javax.swing.JLabel();
        revenueiconLabel = new javax.swing.JLabel();
        movieShowtimePanel = new javax.swing.JPanel();
        movieshowtimeiconLabel = new javax.swing.JLabel();
        movieshowtimeLabel = new javax.swing.JLabel();
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
        ratingPanel = new javax.swing.JPanel();
        ratingLabel = new javax.swing.JLabel();
        ratingiconLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();
        leftsidePanel = new javax.swing.JPanel();
        revenuemainPanel = new javax.swing.JPanel();
        revenueAnalyticsLabel = new javax.swing.JLabel();
        earningGraphPanel = new javax.swing.JPanel();
        ticketGraphPanel = new javax.swing.JPanel();
        movieShowtimeMainPanel = new javax.swing.JPanel();
        movieShowtimeLabel = new javax.swing.JLabel();
        movieButton = new javax.swing.JButton();
        showtimeButton = new javax.swing.JButton();
        genericScrollPane1 = new javax.swing.JScrollPane();
        genericTable = new javax.swing.JTable();
        moviemainPanel = new javax.swing.JPanel();
        manageMovieCollectionLabel = new javax.swing.JLabel();
        movieIDField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        genreField = new javax.swing.JTextField();
        releasedateField = new javax.swing.JTextField();
        languageField = new javax.swing.JTextField();
        productioncompanyField = new javax.swing.JTextField();
        durationField = new javax.swing.JTextField();
        movieAddButton = new javax.swing.JButton();
        movieUpdateButton = new javax.swing.JButton();
        movieDeleteButton = new javax.swing.JButton();
        genericButton = new javax.swing.JButton();
        statusField = new javax.swing.JTextField();
        showtimemainPanel = new javax.swing.JPanel();
        manageShowtimesLabel = new javax.swing.JLabel();
        addShowtimeButton = new javax.swing.JButton();
        updateShowtimeButton = new javax.swing.JButton();
        deleteShowtimeButton = new javax.swing.JButton();
        showtimeIDField = new javax.swing.JTextField();
        primaryMovieIDField = new javax.swing.JTextField();
        primaryHallIDField = new javax.swing.JTextField();
        startTimeField = new javax.swing.JTextField();
        endTimeField = new javax.swing.JTextField();
        dateField = new javax.swing.JTextField();
        genericButtonShowtime = new javax.swing.JButton();
        ticketmainPanel = new javax.swing.JPanel();
        ticketSalesMonitoringLabel = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        ticketSaleScrollPane = new javax.swing.JScrollPane();
        ticketSalesTabel = new javax.swing.JTable();
        customermainPanel = new javax.swing.JPanel();
        monitorCustomerLabel = new javax.swing.JLabel();
        searchNameField = new javax.swing.JTextField();
        CustomerScrollPane = new javax.swing.JScrollPane();
        monitorCustomerTable = new javax.swing.JTable();
        ratingmainPanel = new javax.swing.JPanel();
        ratingReviewLabel = new javax.swing.JLabel();
        searchMovieNameField = new javax.swing.JTextField();
        ratingScrollPane = new javax.swing.JScrollPane();
        ratingTable = new javax.swing.JTable();

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
        adminidLabel.setText("ADMIN_ID");

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

        revenuePanel.setBackground(new java.awt.Color(255, 255, 255));
        revenuePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                revenuePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                revenuePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                revenuePanelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                revenuePanelMousePressed(evt);
            }
        });

        revenueLabel.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        revenueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        revenueLabel.setText("REVENUE");
        revenueLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                revenueLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                revenueLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                revenueLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                revenueLabelMousePressed(evt);
            }
        });

        revenueiconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-sell.png"))); // NOI18N
        revenueiconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                revenueiconLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                revenueiconLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                revenueiconLabelMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                revenueiconLabelMousePressed(evt);
            }
        });

        javax.swing.GroupLayout revenuePanelLayout = new javax.swing.GroupLayout(revenuePanel);
        revenuePanel.setLayout(revenuePanelLayout);
        revenuePanelLayout.setHorizontalGroup(
            revenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenuePanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(revenueiconLabel)
                .addGap(27, 27, 27)
                .addComponent(revenueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        revenuePanelLayout.setVerticalGroup(
            revenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(revenueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(revenueiconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        movieShowtimePanel.setBackground(new java.awt.Color(255, 255, 255));
        movieShowtimePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                movieShowtimePanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                movieShowtimePanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                movieShowtimePanelMouseExited(evt);
            }
        });

        movieshowtimeiconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-movie.png"))); // NOI18N
        movieshowtimeiconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                movieshowtimeiconLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                movieshowtimeiconLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                movieshowtimeiconLabelMouseExited(evt);
            }
        });

        movieshowtimeLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        movieshowtimeLabel.setText("MOVIE AND SHOWTIME");
        movieshowtimeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                movieshowtimeLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                movieshowtimeLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                movieshowtimeLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout movieShowtimePanelLayout = new javax.swing.GroupLayout(movieShowtimePanel);
        movieShowtimePanel.setLayout(movieShowtimePanelLayout);
        movieShowtimePanelLayout.setHorizontalGroup(
            movieShowtimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movieShowtimePanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(movieshowtimeiconLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(movieshowtimeLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        movieShowtimePanelLayout.setVerticalGroup(
            movieShowtimePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(movieshowtimeiconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(movieshowtimeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        movieLabel.setText("MANAGE MOVIE");
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
                .addGap(18, 18, 18)
                .addComponent(movieLabel)
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
        showtimeLabel.setText("MANAGE SHOWTIME");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(showtimeLabel)
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

        ratingPanel.setBackground(new java.awt.Color(255, 255, 255));
        ratingPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ratingPanelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ratingPanelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ratingPanelMouseExited(evt);
            }
        });

        ratingLabel.setFont(new java.awt.Font("Times New Roman", 1, 16)); // NOI18N
        ratingLabel.setText("RATING & REVIEWS");
        ratingLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ratingLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ratingLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ratingLabelMouseExited(evt);
            }
        });

        ratingiconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icon-rating.png"))); // NOI18N
        ratingiconLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ratingiconLabelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ratingiconLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ratingiconLabelMouseExited(evt);
            }
        });

        javax.swing.GroupLayout ratingPanelLayout = new javax.swing.GroupLayout(ratingPanel);
        ratingPanel.setLayout(ratingPanelLayout);
        ratingPanelLayout.setHorizontalGroup(
            ratingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ratingPanelLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(ratingiconLabel)
                .addGap(18, 18, 18)
                .addComponent(ratingLabel)
                .addGap(83, 83, 83))
        );
        ratingPanelLayout.setVerticalGroup(
            ratingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ratingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ratingiconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addComponent(revenuePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(moviePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(showtimePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ratingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ticketPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(customerinfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(movieShowtimePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(rightsidePanelLayout.createSequentialGroup()
                .addGroup(rightsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rightsidePanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rightsidePanelLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(adminIdPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(rightsidePanelLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        rightsidePanelLayout.setVerticalGroup(
            rightsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightsidePanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(adminIdPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
                .addComponent(revenuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(movieShowtimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(moviePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(showtimePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ticketPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(customerinfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ratingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(logoutButton)
                .addGap(20, 20, 20))
        );

        leftsidePanel.setBackground(new java.awt.Color(0, 0, 0));

        revenuemainPanel.setBackground(new java.awt.Color(0, 0, 0));

        revenueAnalyticsLabel.setBackground(new java.awt.Color(0, 0, 0));
        revenueAnalyticsLabel.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        revenueAnalyticsLabel.setForeground(new java.awt.Color(255, 255, 255));
        revenueAnalyticsLabel.setText("Revenue Analytics");

        earningGraphPanel.setBackground(new java.awt.Color(0, 0, 0));
        earningGraphPanel.setForeground(new java.awt.Color(255, 255, 255));
        earningGraphPanel.setLayout(new java.awt.BorderLayout());

        ticketGraphPanel.setBackground(new java.awt.Color(0, 0, 0));
        ticketGraphPanel.setForeground(new java.awt.Color(255, 255, 255));
        ticketGraphPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout revenuemainPanelLayout = new javax.swing.GroupLayout(revenuemainPanel);
        revenuemainPanel.setLayout(revenuemainPanelLayout);
        revenuemainPanelLayout.setHorizontalGroup(
            revenuemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenuemainPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(earningGraphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ticketGraphPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, revenuemainPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(revenueAnalyticsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(252, 252, 252))
        );
        revenuemainPanelLayout.setVerticalGroup(
            revenuemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenuemainPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(revenueAnalyticsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(revenuemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(earningGraphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                    .addComponent(ticketGraphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        movieShowtimeMainPanel.setBackground(new java.awt.Color(0, 0, 0));

        movieShowtimeLabel.setBackground(new java.awt.Color(0, 0, 0));
        movieShowtimeLabel.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        movieShowtimeLabel.setForeground(new java.awt.Color(255, 255, 255));
        movieShowtimeLabel.setText("MOVIES AND SHOWTIMES");

        movieButton.setBackground(new java.awt.Color(204, 0, 0));
        movieButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        movieButton.setForeground(new java.awt.Color(255, 255, 255));
        movieButton.setText("MOVIES");
        movieButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                movieButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                movieButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                movieButtonMouseExited(evt);
            }
        });
        movieButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieButtonActionPerformed(evt);
            }
        });

        showtimeButton.setBackground(new java.awt.Color(204, 0, 0));
        showtimeButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        showtimeButton.setForeground(new java.awt.Color(255, 255, 255));
        showtimeButton.setText("SHOWTIMES");
        showtimeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                showtimeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                showtimeButtonMouseExited(evt);
            }
        });
        showtimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showtimeButtonActionPerformed(evt);
            }
        });

        genericTable.setBackground(new java.awt.Color(0, 0, 0));
        genericTable.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        genericTable.setForeground(new java.awt.Color(255, 255, 255));
        genericTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "null", "null", "null", "null", "null", "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        genericTable.setRowHeight(30);
        genericTable.getTableHeader().setReorderingAllowed(false);
        genericScrollPane1.setViewportView(genericTable);
        if (genericTable.getColumnModel().getColumnCount() > 0) {
            genericTable.getColumnModel().getColumn(0).setResizable(false);
            genericTable.getColumnModel().getColumn(1).setResizable(false);
            genericTable.getColumnModel().getColumn(2).setResizable(false);
            genericTable.getColumnModel().getColumn(3).setResizable(false);
            genericTable.getColumnModel().getColumn(4).setResizable(false);
            genericTable.getColumnModel().getColumn(5).setResizable(false);
            genericTable.getColumnModel().getColumn(6).setResizable(false);
            genericTable.getColumnModel().getColumn(7).setResizable(false);
        }

        javax.swing.GroupLayout movieShowtimeMainPanelLayout = new javax.swing.GroupLayout(movieShowtimeMainPanel);
        movieShowtimeMainPanel.setLayout(movieShowtimeMainPanelLayout);
        movieShowtimeMainPanelLayout.setHorizontalGroup(
            movieShowtimeMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movieShowtimeMainPanelLayout.createSequentialGroup()
                .addGroup(movieShowtimeMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(movieShowtimeMainPanelLayout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(movieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(showtimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(movieShowtimeMainPanelLayout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(movieShowtimeLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, movieShowtimeMainPanelLayout.createSequentialGroup()
                .addGap(0, 53, Short.MAX_VALUE)
                .addComponent(genericScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 770, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        movieShowtimeMainPanelLayout.setVerticalGroup(
            movieShowtimeMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movieShowtimeMainPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(movieShowtimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(movieShowtimeMainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movieButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showtimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(genericScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        moviemainPanel.setBackground(new java.awt.Color(0, 0, 0));

        manageMovieCollectionLabel.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        manageMovieCollectionLabel.setForeground(new java.awt.Color(255, 255, 255));
        manageMovieCollectionLabel.setText("Manage Movie Collection");

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

        nameField.setForeground(new java.awt.Color(128, 128, 128));
        nameField.setText("Name");
        nameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameFieldFocusLost(evt);
            }
        });
        nameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameFieldActionPerformed(evt);
            }
        });

        genreField.setForeground(new java.awt.Color(128, 128, 128));
        genreField.setText("Genre");
        genreField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                genreFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                genreFieldFocusLost(evt);
            }
        });

        releasedateField.setForeground(new java.awt.Color(128, 128, 128));
        releasedateField.setText("Release Date");
        releasedateField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                releasedateFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                releasedateFieldFocusLost(evt);
            }
        });

        languageField.setForeground(new java.awt.Color(128, 128, 128));
        languageField.setText("Language");
        languageField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                languageFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                languageFieldFocusLost(evt);
            }
        });

        productioncompanyField.setForeground(new java.awt.Color(128, 128, 128));
        productioncompanyField.setText("Production Company");
        productioncompanyField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                productioncompanyFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                productioncompanyFieldFocusLost(evt);
            }
        });

        durationField.setForeground(new java.awt.Color(128, 128, 128));
        durationField.setText("Duration");
        durationField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                durationFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                durationFieldFocusLost(evt);
            }
        });

        movieAddButton.setBackground(new java.awt.Color(204, 0, 0));
        movieAddButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        movieAddButton.setForeground(new java.awt.Color(255, 255, 255));
        movieAddButton.setText("ADD MOVIE");
        movieAddButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                movieAddButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                movieAddButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                movieAddButtonMouseExited(evt);
            }
        });
        movieAddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieAddButtonActionPerformed(evt);
            }
        });

        movieUpdateButton.setBackground(new java.awt.Color(204, 0, 0));
        movieUpdateButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        movieUpdateButton.setForeground(new java.awt.Color(255, 255, 255));
        movieUpdateButton.setText("UPDATE MOVIE");
        movieUpdateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                movieUpdateButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                movieUpdateButtonMouseExited(evt);
            }
        });
        movieUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieUpdateButtonActionPerformed(evt);
            }
        });

        movieDeleteButton.setBackground(new java.awt.Color(204, 0, 0));
        movieDeleteButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        movieDeleteButton.setForeground(new java.awt.Color(255, 255, 255));
        movieDeleteButton.setText("DELETE MOVIE");
        movieDeleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                movieDeleteButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                movieDeleteButtonMouseExited(evt);
            }
        });
        movieDeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movieDeleteButtonActionPerformed(evt);
            }
        });

        genericButton.setBackground(new java.awt.Color(204, 0, 0));
        genericButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        genericButton.setForeground(new java.awt.Color(255, 255, 255));
        genericButton.setText("ADD");
        genericButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                genericButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                genericButtonMouseExited(evt);
            }
        });
        genericButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genericButtonActionPerformed(evt);
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

        javax.swing.GroupLayout moviemainPanelLayout = new javax.swing.GroupLayout(moviemainPanel);
        moviemainPanel.setLayout(moviemainPanelLayout);
        moviemainPanelLayout.setHorizontalGroup(
            moviemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(moviemainPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(manageMovieCollectionLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, moviemainPanelLayout.createSequentialGroup()
                .addContainerGap(195, Short.MAX_VALUE)
                .addGroup(moviemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, moviemainPanelLayout.createSequentialGroup()
                        .addGroup(moviemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(moviemainPanelLayout.createSequentialGroup()
                                .addGap(169, 169, 169)
                                .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(moviemainPanelLayout.createSequentialGroup()
                                .addComponent(releasedateField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(genreField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(moviemainPanelLayout.createSequentialGroup()
                                .addGroup(moviemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(durationField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addGroup(moviemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(productioncompanyField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(languageField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(171, 171, 171))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, moviemainPanelLayout.createSequentialGroup()
                        .addComponent(genericButton, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, moviemainPanelLayout.createSequentialGroup()
                        .addComponent(movieIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(260, 260, 260))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, moviemainPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(movieAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(movieUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(movieDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );
        moviemainPanelLayout.setVerticalGroup(
            moviemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, moviemainPanelLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(manageMovieCollectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addGroup(moviemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(movieAddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movieUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movieDeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addComponent(movieIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(moviemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productioncompanyField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(moviemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(releasedateField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genreField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(moviemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(durationField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(languageField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(statusField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(genericButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        showtimemainPanel.setBackground(new java.awt.Color(0, 0, 0));

        manageShowtimesLabel.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        manageShowtimesLabel.setForeground(new java.awt.Color(255, 255, 255));
        manageShowtimesLabel.setText("MANAGE SHOWTIMES");

        addShowtimeButton.setBackground(new java.awt.Color(204, 0, 0));
        addShowtimeButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        addShowtimeButton.setForeground(new java.awt.Color(255, 255, 255));
        addShowtimeButton.setText("ADD SHOWTIME");
        addShowtimeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addShowtimeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addShowtimeButtonMouseExited(evt);
            }
        });
        addShowtimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addShowtimeButtonActionPerformed(evt);
            }
        });

        updateShowtimeButton.setBackground(new java.awt.Color(204, 0, 0));
        updateShowtimeButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        updateShowtimeButton.setForeground(new java.awt.Color(255, 255, 255));
        updateShowtimeButton.setText("UPDATE SHOWTIME");
        updateShowtimeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                updateShowtimeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                updateShowtimeButtonMouseExited(evt);
            }
        });
        updateShowtimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateShowtimeButtonActionPerformed(evt);
            }
        });

        deleteShowtimeButton.setBackground(new java.awt.Color(204, 0, 0));
        deleteShowtimeButton.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        deleteShowtimeButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteShowtimeButton.setText("DELETE SHOWTIME");
        deleteShowtimeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteShowtimeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteShowtimeButtonMouseExited(evt);
            }
        });
        deleteShowtimeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteShowtimeButtonActionPerformed(evt);
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

        primaryMovieIDField.setForeground(new java.awt.Color(128, 128, 128));
        primaryMovieIDField.setText("Movie ID");
        primaryMovieIDField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                primaryMovieIDFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                primaryMovieIDFieldFocusLost(evt);
            }
        });

        primaryHallIDField.setForeground(new java.awt.Color(128, 128, 128));
        primaryHallIDField.setText("Hall ID");
        primaryHallIDField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                primaryHallIDFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                primaryHallIDFieldFocusLost(evt);
            }
        });

        startTimeField.setForeground(new java.awt.Color(128, 128, 128));
        startTimeField.setText("Start Time");
        startTimeField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                startTimeFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                startTimeFieldFocusLost(evt);
            }
        });

        endTimeField.setForeground(new java.awt.Color(128, 128, 128));
        endTimeField.setText("End Time");
        endTimeField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                endTimeFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                endTimeFieldFocusLost(evt);
            }
        });

        dateField.setForeground(new java.awt.Color(128, 128, 128));
        dateField.setText("Date");
        dateField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dateFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateFieldFocusLost(evt);
            }
        });

        genericButtonShowtime.setBackground(new java.awt.Color(204, 0, 0));
        genericButtonShowtime.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 18)); // NOI18N
        genericButtonShowtime.setForeground(new java.awt.Color(255, 255, 255));
        genericButtonShowtime.setText("ADD");
        genericButtonShowtime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                genericButtonShowtimeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                genericButtonShowtimeMouseExited(evt);
            }
        });
        genericButtonShowtime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genericButtonShowtimeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout showtimemainPanelLayout = new javax.swing.GroupLayout(showtimemainPanel);
        showtimemainPanel.setLayout(showtimemainPanelLayout);
        showtimemainPanelLayout.setHorizontalGroup(
            showtimemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showtimemainPanelLayout.createSequentialGroup()
                .addGroup(showtimemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(showtimemainPanelLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(genericButtonShowtime, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(showtimemainPanelLayout.createSequentialGroup()
                        .addGap(274, 274, 274)
                        .addComponent(showtimeIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(showtimemainPanelLayout.createSequentialGroup()
                        .addGap(316, 316, 316)
                        .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(showtimemainPanelLayout.createSequentialGroup()
                        .addGap(253, 253, 253)
                        .addComponent(manageShowtimesLabel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showtimemainPanelLayout.createSequentialGroup()
                .addGap(0, 86, Short.MAX_VALUE)
                .addGroup(showtimemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showtimemainPanelLayout.createSequentialGroup()
                        .addGroup(showtimemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(startTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(primaryMovieIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(showtimemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(primaryHallIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(endTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(188, 188, 188))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, showtimemainPanelLayout.createSequentialGroup()
                        .addComponent(addShowtimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateShowtimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(deleteShowtimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))))
        );
        showtimemainPanelLayout.setVerticalGroup(
            showtimemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(showtimemainPanelLayout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(manageShowtimesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(showtimemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addShowtimeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(updateShowtimeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteShowtimeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(97, 97, 97)
                .addComponent(showtimeIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(showtimemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(primaryMovieIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(primaryHallIDField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(showtimemainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(startTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(endTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(genericButtonShowtime, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        ticketmainPanel.setBackground(new java.awt.Color(0, 0, 0));

        ticketSalesMonitoringLabel.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        ticketSalesMonitoringLabel.setForeground(new java.awt.Color(255, 255, 255));
        ticketSalesMonitoringLabel.setText("TICKET MONITORING");

        searchField.setForeground(new java.awt.Color(128, 128, 128));
        searchField.setText("Customer Name");
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFieldFocusLost(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchFieldKeyTyped(evt);
            }
        });

        ticketSaleScrollPane.setBackground(new java.awt.Color(0, 0, 0));
        ticketSaleScrollPane.setForeground(new java.awt.Color(255, 255, 255));

        ticketSalesTabel.setBackground(new java.awt.Color(0, 0, 0));
        ticketSalesTabel.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        ticketSalesTabel.setForeground(new java.awt.Color(255, 255, 255));
        ticketSalesTabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TICKET ID", "MOVIE NAME", "CUSTOMER NAME", "TICKET TYPE", "PRICE", "SEAT NO", "STATUS"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ticketSalesTabel.setRowHeight(30);
        ticketSalesTabel.setSelectionBackground(new java.awt.Color(255, 255, 255));
        ticketSalesTabel.getTableHeader().setReorderingAllowed(false);
        ticketSaleScrollPane.setViewportView(ticketSalesTabel);
        if (ticketSalesTabel.getColumnModel().getColumnCount() > 0) {
            ticketSalesTabel.getColumnModel().getColumn(1).setResizable(false);
            ticketSalesTabel.getColumnModel().getColumn(3).setResizable(false);
            ticketSalesTabel.getColumnModel().getColumn(5).setResizable(false);
            ticketSalesTabel.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout ticketmainPanelLayout = new javax.swing.GroupLayout(ticketmainPanel);
        ticketmainPanel.setLayout(ticketmainPanelLayout);
        ticketmainPanelLayout.setHorizontalGroup(
            ticketmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketmainPanelLayout.createSequentialGroup()
                .addGroup(ticketmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ticketmainPanelLayout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ticketmainPanelLayout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(ticketSalesMonitoringLabel))
                    .addGroup(ticketmainPanelLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(ticketSaleScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        ticketmainPanelLayout.setVerticalGroup(
            ticketmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ticketmainPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(ticketSalesMonitoringLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(ticketSaleScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        customermainPanel.setBackground(new java.awt.Color(0, 0, 0));

        monitorCustomerLabel.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        monitorCustomerLabel.setForeground(new java.awt.Color(255, 255, 255));
        monitorCustomerLabel.setText("MONITOR CUSTOMER");

        searchNameField.setForeground(new java.awt.Color(128, 128, 128));
        searchNameField.setText("Search Name");
        searchNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchNameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchNameFieldFocusLost(evt);
            }
        });
        searchNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchNameFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchNameFieldKeyReleased(evt);
            }
        });

        monitorCustomerTable.setBackground(new java.awt.Color(0, 0, 0));
        monitorCustomerTable.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        monitorCustomerTable.setForeground(new java.awt.Color(255, 255, 255));
        monitorCustomerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "CUSTOMER ID", "NAME", "EMAIL", "PASSWORD", "PHONE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        monitorCustomerTable.setRowHeight(30);
        monitorCustomerTable.getTableHeader().setReorderingAllowed(false);
        CustomerScrollPane.setViewportView(monitorCustomerTable);
        if (monitorCustomerTable.getColumnModel().getColumnCount() > 0) {
            monitorCustomerTable.getColumnModel().getColumn(0).setResizable(false);
            monitorCustomerTable.getColumnModel().getColumn(1).setResizable(false);
            monitorCustomerTable.getColumnModel().getColumn(2).setResizable(false);
            monitorCustomerTable.getColumnModel().getColumn(3).setResizable(false);
            monitorCustomerTable.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout customermainPanelLayout = new javax.swing.GroupLayout(customermainPanel);
        customermainPanel.setLayout(customermainPanelLayout);
        customermainPanelLayout.setHorizontalGroup(
            customermainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customermainPanelLayout.createSequentialGroup()
                .addGap(0, 41, Short.MAX_VALUE)
                .addComponent(CustomerScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(customermainPanelLayout.createSequentialGroup()
                .addGroup(customermainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customermainPanelLayout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addComponent(monitorCustomerLabel))
                    .addGroup(customermainPanelLayout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addComponent(searchNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 405, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        customermainPanelLayout.setVerticalGroup(
            customermainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customermainPanelLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(monitorCustomerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(searchNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(CustomerScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        ratingmainPanel.setBackground(new java.awt.Color(0, 0, 0));

        ratingReviewLabel.setFont(new java.awt.Font("Engravers MT", 1, 24)); // NOI18N
        ratingReviewLabel.setForeground(new java.awt.Color(255, 255, 255));
        ratingReviewLabel.setText("RATING & REVIEWS");

        searchMovieNameField.setForeground(new java.awt.Color(128, 128, 128));
        searchMovieNameField.setText("Search Movie ID");
        searchMovieNameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchMovieNameFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchMovieNameFieldFocusLost(evt);
            }
        });
        searchMovieNameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchMovieNameFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchMovieNameFieldKeyReleased(evt);
            }
        });

        ratingScrollPane.setBackground(new java.awt.Color(0, 0, 0));
        ratingScrollPane.setForeground(new java.awt.Color(255, 255, 255));

        ratingTable.setBackground(new java.awt.Color(0, 0, 0));
        ratingTable.setForeground(new java.awt.Color(255, 255, 255));
        ratingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MOVIE ID", "CUSTOMER ID", "REVIEWS", "RATING"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ratingTable.setRowHeight(30);
        ratingScrollPane.setViewportView(ratingTable);

        javax.swing.GroupLayout ratingmainPanelLayout = new javax.swing.GroupLayout(ratingmainPanel);
        ratingmainPanel.setLayout(ratingmainPanelLayout);
        ratingmainPanelLayout.setHorizontalGroup(
            ratingmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ratingmainPanelLayout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addGroup(ratingmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ratingReviewLabel)
                    .addComponent(searchMovieNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ratingmainPanelLayout.createSequentialGroup()
                .addContainerGap(47, Short.MAX_VALUE)
                .addComponent(ratingScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        ratingmainPanelLayout.setVerticalGroup(
            ratingmainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ratingmainPanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(ratingReviewLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(searchMovieNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(ratingScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout leftsidePanelLayout = new javax.swing.GroupLayout(leftsidePanel);
        leftsidePanel.setLayout(leftsidePanelLayout);
        leftsidePanelLayout.setHorizontalGroup(
            leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(revenuemainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(moviemainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(showtimemainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ticketmainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(customermainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ratingmainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(movieShowtimeMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        leftsidePanelLayout.setVerticalGroup(
            leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(revenuemainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(moviemainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(showtimemainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ticketmainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(customermainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(ratingmainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(leftsidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(movieShowtimeMainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        adminLogin obj = new adminLogin();
        obj.setVisible(true);
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void revenuePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenuePanelMouseEntered
        if (name != "revenuePanel") {
            revenuePanel.setBackground(new Color(0xF5F5F5));
            revenuePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_revenuePanelMouseEntered

    private void revenueLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueLabelMouseEntered
        if (name != "revenuePanel") {
            revenuePanel.setBackground(new Color(0xF5F5F5));
            revenuePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_revenueLabelMouseEntered

    private void revenueiconLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueiconLabelMouseEntered
        if (name != "revenuePanel") {
            revenuePanel.setBackground(new Color(0xF5F5F5));
            revenuePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_revenueiconLabelMouseEntered

    private void revenuePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenuePanelMouseExited
        if (name != "revenuePanel") {
            revenuePanel.setBackground(Color.WHITE);
            revenuePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_revenuePanelMouseExited

    private void revenueLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueLabelMouseExited
        if (name != "revenuePanel") {
            revenuePanel.setBackground(Color.WHITE);
            revenuePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_revenueLabelMouseExited

    private void revenueiconLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueiconLabelMouseExited
        if (name != "revenuePanel") {
            revenuePanel.setBackground(Color.WHITE);
            revenuePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_revenueiconLabelMouseExited

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

    private void ratingPanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratingPanelMouseEntered
        if (name != "ratingPanel") {
            ratingPanel.setBackground(new Color(0xF5F5F5));
            ratingPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_ratingPanelMouseEntered

    private void ratingPanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratingPanelMouseExited
        if (name != "ratingPanel") {
            ratingPanel.setBackground(Color.WHITE);
            ratingPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_ratingPanelMouseExited

    private void ratingLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratingLabelMouseEntered
        if (name != "ratingPanel") {
            ratingPanel.setBackground(new Color(0xF5F5F5));
            ratingPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_ratingLabelMouseEntered

    private void ratingLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratingLabelMouseExited
        if (name != "ratingPanel") {
            ratingPanel.setBackground(Color.WHITE);
            ratingPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_ratingLabelMouseExited

    private void ratingiconLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratingiconLabelMouseEntered
        if (name != "ratingPanel") {
            ratingPanel.setBackground(new Color(0xF5F5F5));
            ratingPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_ratingiconLabelMouseEntered

    private void ratingiconLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratingiconLabelMouseExited
        if (name != "ratingPanel") {
            ratingPanel.setBackground(Color.WHITE);
            ratingPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_ratingiconLabelMouseExited

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

    private void revenuePanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenuePanelMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_revenuePanelMousePressed

    private void revenueLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueLabelMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_revenueLabelMousePressed

    private void revenueiconLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueiconLabelMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_revenueiconLabelMousePressed

    private void revenuePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenuePanelMouseClicked

        name = "revenuePanel";

        revenuePanel.setBackground(Color.red);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(true);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(false);
    }//GEN-LAST:event_revenuePanelMouseClicked

    private void revenueLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueLabelMouseClicked

        name = "revenuePanel";

        revenuePanel.setBackground(Color.red);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(true);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(false);
    }//GEN-LAST:event_revenueLabelMouseClicked

    private void revenueiconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueiconLabelMouseClicked

        name = "revenuePanel";

        revenuePanel.setBackground(Color.red);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(true);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(false);
    }//GEN-LAST:event_revenueiconLabelMouseClicked

    private void moviePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moviePanelMouseClicked

        name = "moviePanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.red);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(true);
        movieAddButton.setVisible(true);
        movieUpdateButton.setVisible(true);
        movieDeleteButton.setVisible(true);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(false);
    }//GEN-LAST:event_moviePanelMouseClicked

    private void movieLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieLabelMouseClicked

        name = "moviePanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.red);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(true);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);

        movieAddButton.setVisible(true);
        movieUpdateButton.setVisible(true);
        movieDeleteButton.setVisible(true);
        movieShowtimeMainPanel.setVisible(false);
    }//GEN-LAST:event_movieLabelMouseClicked

    private void movieiconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieiconLabelMouseClicked

        name = "moviePanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.red);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(true);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(false);

        movieAddButton.setVisible(true);
        movieUpdateButton.setVisible(true);
        movieDeleteButton.setVisible(true);
    }//GEN-LAST:event_movieiconLabelMouseClicked

    private void showtimePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showtimePanelMouseClicked

        name = "showtimePanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.red);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(true);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(false);

        addShowtimeButton.setVisible(true);
        updateShowtimeButton.setVisible(true);
        deleteShowtimeButton.setVisible(true);
    }//GEN-LAST:event_showtimePanelMouseClicked

    private void showtimeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showtimeLabelMouseClicked

        name = "showtimePanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.red);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(true);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(false);

        addShowtimeButton.setVisible(true);
        updateShowtimeButton.setVisible(true);
        deleteShowtimeButton.setVisible(true);
    }//GEN-LAST:event_showtimeLabelMouseClicked

    private void showtimeiconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showtimeiconLabelMouseClicked

        name = "showtimePanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.red);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(true);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(false);

        addShowtimeButton.setVisible(true);
        updateShowtimeButton.setVisible(true);
        deleteShowtimeButton.setVisible(true);
    }//GEN-LAST:event_showtimeiconLabelMouseClicked

    private void ticketPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketPanelMouseClicked

        name = "ticketPanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.red);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(true);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(false);

        ticketSaleScrollPane.setVisible(true);
        ticketSalesTabel.setVisible(true);

        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) ticketSalesTabel.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT T.TICKET_ID, M.NAME AS MOVIE_NAME, C.NAME AS CUSTOMER_NAME, T.PRICE, T.TICKET_TYPE, T.SEAT_NO, T.STATUS FROM TICKET T JOIN MOVIE M ON T.MOVIE_ID = M.MOVIE_ID JOIN CUSTOMER C ON T.CUSTOMER_ID = C.CUSTOMER_ID;");
            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "No Record Found");
            } else {
                String tbData1[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), rs.getString(6), rs.getString(7)};
                tblmodel.addRow(tbData1);
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), rs.getString(6), rs.getString(7)};
                    tblmodel.addRow(tbData);
                }
            }
        } catch (SQLException ex) {
            JFrame f = null;
            ex.printStackTrace();
            JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ticketPanelMouseClicked

    private void ticketLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketLabelMouseClicked

        name = "ticketPanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.red);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(true);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(false);

        ticketSaleScrollPane.setVisible(true);
        ticketSalesTabel.setVisible(true);

        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) ticketSalesTabel.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT T.TICKET_ID, M.NAME AS MOVIE_NAME, C.NAME AS CUSTOMER_NAME, T.PRICE, T.TICKET_TYPE, T.SEAT_NO, T.STATUS FROM TICKET T JOIN MOVIE M ON T.MOVIE_ID = M.MOVIE_ID JOIN CUSTOMER C ON T.CUSTOMER_ID = C.CUSTOMER_ID;");
            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "No Record Found");
            } else {
                String tbData1[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), rs.getString(6), rs.getString(7)};
                tblmodel.addRow(tbData1);
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), rs.getString(6), rs.getString(7)};
                    tblmodel.addRow(tbData);
                }
            }
        } catch (SQLException ex) {
            JFrame f = null;
            ex.printStackTrace();
            JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ticketLabelMouseClicked

    private void ticketiconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ticketiconLabelMouseClicked

        name = "ticketPanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.red);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(true);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(false);

        ticketSaleScrollPane.setVisible(true);
        ticketSalesTabel.setVisible(true);

        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) ticketSalesTabel.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT T.TICKET_ID, M.NAME AS MOVIE_NAME, C.NAME AS CUSTOMER_NAME, T.PRICE, T.TICKET_TYPE, T.SEAT_NO, T.STATUS FROM TICKET T JOIN MOVIE M ON T.MOVIE_ID = M.MOVIE_ID JOIN CUSTOMER C ON T.CUSTOMER_ID = C.CUSTOMER_ID;");
            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "No Record Found");
            } else {
                String tbData1[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), rs.getString(6), rs.getString(7)};
                tblmodel.addRow(tbData1);
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), rs.getString(6), rs.getString(7)};
                    tblmodel.addRow(tbData);
                }
            }
        } catch (SQLException ex) {
            JFrame f = null;
            ex.printStackTrace();
            JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ticketiconLabelMouseClicked

    private void customerinfoPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerinfoPanelMouseClicked

        name = "customerinfoPanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.red);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(true);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(false);

        CustomerScrollPane.setVisible(true);
        monitorCustomerTable.setVisible(true);

        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) monitorCustomerTable.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT * FROM CUSTOMER");
            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "No Record Found");
            } else {
                String tbData1[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                tblmodel.addRow(tbData1);
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                    tblmodel.addRow(tbData);
                }
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_customerinfoPanelMouseClicked

    private void customerinfoLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerinfoLabelMouseClicked

        name = "customerinfoPanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.red);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(true);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(false);

        CustomerScrollPane.setVisible(true);
        monitorCustomerTable.setVisible(true);

        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) monitorCustomerTable.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT * FROM CUSTOMER");
            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "No Record Found");
            } else {
                String tbData1[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                tblmodel.addRow(tbData1);
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                    tblmodel.addRow(tbData);
                }
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_customerinfoLabelMouseClicked

    private void customerinfoiconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerinfoiconLabelMouseClicked

        name = "customerinfoPanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.red);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(true);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(false);

        CustomerScrollPane.setVisible(true);
        monitorCustomerTable.setVisible(true);

        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) monitorCustomerTable.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT * FROM CUSTOMER");
            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "No Record Found");
            } else {
                String tbData1[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                tblmodel.addRow(tbData1);
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                    tblmodel.addRow(tbData);
                }
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_customerinfoiconLabelMouseClicked

    private void ratingPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratingPanelMouseClicked

        name = "ratingPanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.red);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(true);
        movieShowtimeMainPanel.setVisible(false);

        ratingTable.setVisible(true);
        ratingScrollPane.setVisible(true);
        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel1 = (DefaultTableModel) ratingTable.getModel();
        try {
            tblmodel1.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT MOVIE_ID, CUSTOMER_ID, REVIEWS, RATING FROM MOVIE_REVIEWS");
            while (rs.next()) {
                String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
                tblmodel1.addRow(tbData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ratingPanelMouseClicked

    private void ratingLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratingLabelMouseClicked

        name = "ratingPanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.red);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(true);
        movieShowtimeMainPanel.setVisible(false);

        ratingTable.setVisible(true);
        ratingScrollPane.setVisible(true);
        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel1 = (DefaultTableModel) ratingTable.getModel();
        try {
            tblmodel1.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT MOVIE_ID, CUSTOMER_ID, REVIEWS, RATING FROM MOVIE_REVIEWS");
            while (rs.next()) {
                String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
                tblmodel1.addRow(tbData);
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ratingLabelMouseClicked

    private void ratingiconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ratingiconLabelMouseClicked

        name = "ratingPanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.red);
        movieShowtimePanel.setBackground(Color.WHITE);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(true);
        movieShowtimeMainPanel.setVisible(false);

        ratingTable.setVisible(true);
        ratingScrollPane.setVisible(true);
        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel1 = (DefaultTableModel) ratingTable.getModel();
        try {
            tblmodel1.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT MOVIE_ID, CUSTOMER_ID, REVIEWS, RATING FROM MOVIE_REVIEWS");
            while (rs.next()) {
                String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
                tblmodel1.addRow(tbData);
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_ratingiconLabelMouseClicked

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

    private void genericButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genericButtonMouseEntered
        genericButton.setForeground(Color.BLACK);
        genericButton.setBackground(Color.WHITE);
        genericButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_genericButtonMouseEntered

    private void genericButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genericButtonMouseExited
        genericButton.setForeground(Color.WHITE);
        genericButton.setBackground(Color.RED);
        genericButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_genericButtonMouseExited

    private void genericButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButtonActionPerformed
        int forUpdation = 0;
        if (genericButton.getText().equals("ADD")) {

            if (movieIDField.getText().trim().isEmpty() || statusField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty() || genreField.getText().trim().isEmpty() || releasedateField.getText().trim().isEmpty() || languageField.getText().trim().isEmpty() || productioncompanyField.getText().trim().isEmpty() || durationField.getText().trim().isEmpty() || movieIDField.getText().equals("Movie ID") || statusField.getText().equals("Status") || nameField.getText().equals("Name") || genreField.getText().equals("Genre") || releasedateField.getText().equals("Release Date") || languageField.getText().equals("Language") || productioncompanyField.getText().equals("Production Company") || durationField.getText().equals("Duration")) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "The Fields are Empty!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                dbconnection db = new dbconnection();
                String sql = "insert into MOVIE (MOVIE_ID,NAME,GENERE,RELEASE_DATE,LANGUAGE,PRODUCTION_COMPANY,DURATION, STATUS) values ('" + movieIDField.getText() + "','" + nameField.getText() + "','" + genreField.getText() + "','" + releasedateField.getText() + "','" + languageField.getText() + "','" + productioncompanyField.getText() + "','" + durationField.getText() + "','" + statusField.getText() + "')";
                try {
                    db.st.executeUpdate(sql);
                    JFrame f = null;
                    JOptionPane.showMessageDialog(f, "Movie Added Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    setPromptText(nameField, "Name");
                    setPromptText(movieIDField, "Movie ID");
                    setPromptText(genreField, "Genre");
                    setPromptText(releasedateField, "Release Date");
                    setPromptText(languageField, "Language");
                    setPromptText(productioncompanyField, "Production Company");
                    setPromptText(durationField, "Duration");
                    setPromptText(statusField, "Status");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Movie Addition Failed");
                    setPromptText(movieIDField, "Movie ID");
                    setPromptText(nameField, "Name");
                    setPromptText(movieIDField, "Movie ID");
                    setPromptText(genreField, "Genre");
                    setPromptText(releasedateField, "Release Date");
                    setPromptText(languageField, "Language");
                    setPromptText(productioncompanyField, "Production Company");
                    setPromptText(durationField, "Duration");
                    setPromptText(statusField, "Status");
                }
            }
        }

        if (genericButton.getText().equals("FIND MOVIE")) {

            if (movieIDField.getText().trim().isEmpty()) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "Movie ID Field is Empty!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                dbconnection db = new dbconnection();
                String movieID = movieIDField.getText();
                String sql = "SELECT * FROM MOVIE WHERE MOVIE_ID = '" + movieID + "'";

                try {
                    ResultSet resultSet = db.st.executeQuery(sql);
                    if (!resultSet.next()) {
                        JOptionPane.showMessageDialog(this, "Movie ID Field is Empty");
                        setPromptText(nameField, "Name");
                        setPromptText(movieIDField, "Movie ID");
                        setPromptText(genreField, "Genre");
                        setPromptText(releasedateField, "Release date");
                        setPromptText(languageField, "Language");
                        setPromptText(productioncompanyField, "Production Company");
                        setPromptText(durationField, "Duration");
                        setPromptText(statusField, "Status");

                        nameField.setEditable(false);
                        genreField.setEditable(false);
                        releasedateField.setEditable(false);
                        languageField.setEditable(false);
                        productioncompanyField.setEditable(false);
                        durationField.setEditable(false);
                        statusField.setEditable(false);
                    } else {
                        String movieName = resultSet.getString("NAME");
                        String genre = resultSet.getString("GENERE");
                        String releaseDate = resultSet.getString("RELEASE_DATE");
                        String language = resultSet.getString("LANGUAGE");
                        String productionCompany = resultSet.getString("PRODUCTION_COMPANY");
                        String duration = resultSet.getString("DURATION");
                        String status = resultSet.getString("STATUS");

                        nameField.setEditable(true);
                        genreField.setEditable(true);
                        releasedateField.setEditable(true);
                        languageField.setEditable(true);
                        productioncompanyField.setEditable(true);
                        durationField.setEditable(true);
                        statusField.setEditable(true);

                        nameField.setForeground(Color.BLACK);
                        genreField.setForeground(Color.BLACK);
                        releasedateField.setForeground(Color.BLACK);
                        languageField.setForeground(Color.BLACK);
                        productioncompanyField.setForeground(Color.BLACK);
                        durationField.setForeground(Color.BLACK);
                        statusField.setForeground(Color.BLACK);

                        nameField.setText(movieName);
                        genreField.setText(genre);
                        releasedateField.setText(releaseDate);
                        languageField.setText(language);
                        productioncompanyField.setText(productionCompany);
                        durationField.setText(duration);
                        statusField.setText(status);
                        movieIDField.setEditable(false);
                        forUpdation = 1;
                        genericButton.setText("UPDATE");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "An error occurred. Check logs for details.");
                }
            }
        }

        if (genericButton.getText().equals("UPDATE") && forUpdation != 1) {

            if (movieIDField.getText().trim().isEmpty() || statusField.getText().trim().isEmpty() || nameField.getText().trim().isEmpty() || genreField.getText().trim().isEmpty() || releasedateField.getText().trim().isEmpty() || languageField.getText().trim().isEmpty() || productioncompanyField.getText().trim().isEmpty() || durationField.getText().trim().isEmpty() || movieIDField.getText().equals("Movie ID") || statusField.getText().equals("Status") || nameField.getText().equals("Name") || genreField.getText().equals("Genre") || releasedateField.getText().equals("Release Date") || languageField.getText().equals("Language") || productioncompanyField.getText().equals("Production Company") || durationField.getText().equals("Duration")) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "The Fields are Empty!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                dbconnection db = new dbconnection();
                String sql = "UPDATE MOVIE SET " + "NAME = '" + nameField.getText() + "', "
                        + "GENERE = '" + genreField.getText() + "', "
                        + "RELEASE_DATE = '" + releasedateField.getText() + "', "
                        + "LANGUAGE = '" + languageField.getText() + "', "
                        + "PRODUCTION_COMPANY = '" + productioncompanyField.getText() + "', "
                        + "DURATION = '" + durationField.getText() + "' "
                        + "STATUS = '" + statusField.getText() + "' "
                        + "WHERE MOVIE_ID = '" + movieIDField.getText() + "'";
                int check;

                try {
                    check = db.st.executeUpdate(sql);
                    JFrame f = null;
                    if (check == 1) {
                        JOptionPane.showMessageDialog(f, "Movie Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        genericButton.setText("FIND MOVIE");
                    } else {
                        JOptionPane.showMessageDialog(f, "Movie Not Updated!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                    }
                    movieIDField.setEditable(true);

                    Color customGreyColor = new Color(128, 128, 128);

                    nameField.setForeground(customGreyColor);
                    genreField.setForeground(customGreyColor);
                    releasedateField.setForeground(customGreyColor);
                    languageField.setForeground(customGreyColor);
                    productioncompanyField.setForeground(customGreyColor);
                    durationField.setForeground(customGreyColor);
                    statusField.setForeground(customGreyColor);

                    setPromptText(nameField, "Name");
                    setPromptText(movieIDField, "Movie ID");
                    setPromptText(genreField, "Genre");
                    setPromptText(releasedateField, "Release date");
                    setPromptText(languageField, "Language");
                    setPromptText(productioncompanyField, "Production Company");
                    setPromptText(durationField, "Duration");
                    setPromptText(statusField, "Status");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Movie Not Found");
                }
            }
        }

        if (genericButton.getText().equals("DELETE")) {

            if (movieIDField.getText().trim().isEmpty()) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "Movie ID Field is Empty!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                dbconnection db = new dbconnection();
                String sql = "DELETE FROM MOVIE WHERE MOVIE_ID = '" + movieIDField.getText() + "'";
                int check;
                System.out.println(sql);
                try {
                    check = db.st.executeUpdate(sql);
                    JFrame f = null;
                    if (check == 1) {
                        JOptionPane.showMessageDialog(f, "Movie Deleted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        setPromptText(movieIDField, "Movie ID");
                    } else {
                        JOptionPane.showMessageDialog(f, "Movie ID Field is Empty!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                        setPromptText(movieIDField, "Movie ID");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "No Movie Found");
                }
            }

        }
    }//GEN-LAST:event_genericButtonActionPerformed

    private void nameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameFieldFocusGained
        if (nameField.getText().equals("Name")) {
            setPromptText(nameField, null);
        }
    }//GEN-LAST:event_nameFieldFocusGained

    private void nameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameFieldFocusLost
        if (nameField.getText().trim().isEmpty()) {
            setPromptText(nameField, "Name");
        }
    }//GEN-LAST:event_nameFieldFocusLost

    private void genreFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_genreFieldFocusGained
        if (genreField.getText().equals("Genre")) {
            setPromptText(genreField, null);
        }
    }//GEN-LAST:event_genreFieldFocusGained

    private void genreFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_genreFieldFocusLost
        if (genreField.getText().trim().isEmpty()) {
            setPromptText(genreField, "Genre");
        }
    }//GEN-LAST:event_genreFieldFocusLost

    private void releasedateFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_releasedateFieldFocusGained
        if (releasedateField.getText().equals("Release Date")) {
            setPromptText(releasedateField, null);
        }
    }//GEN-LAST:event_releasedateFieldFocusGained

    private void releasedateFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_releasedateFieldFocusLost
        if (releasedateField.getText().trim().isEmpty()) {
            setPromptText(releasedateField, "Release Date");
        }
    }//GEN-LAST:event_releasedateFieldFocusLost

    private void languageFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_languageFieldFocusGained
        if (languageField.getText().equals("Language")) {
            setPromptText(languageField, null);
        }
    }//GEN-LAST:event_languageFieldFocusGained

    private void languageFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_languageFieldFocusLost
        if (languageField.getText().trim().isEmpty()) {
            setPromptText(languageField, "Language");
        }
    }//GEN-LAST:event_languageFieldFocusLost

    private void productioncompanyFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_productioncompanyFieldFocusGained
        if (productioncompanyField.getText().equals("Production Company")) {
            setPromptText(productioncompanyField, null);
        }
    }//GEN-LAST:event_productioncompanyFieldFocusGained

    private void productioncompanyFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_productioncompanyFieldFocusLost
        if (productioncompanyField.getText().trim().isEmpty()) {
            setPromptText(productioncompanyField, "Production Company");
        }
    }//GEN-LAST:event_productioncompanyFieldFocusLost

    private void durationFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_durationFieldFocusGained
        if (durationField.getText().equals("Duration")) {
            setPromptText(durationField, null);
        }
    }//GEN-LAST:event_durationFieldFocusGained

    private void durationFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_durationFieldFocusLost
        if (durationField.getText().trim().isEmpty()) {
            setPromptText(durationField, "Duration");
        }
    }//GEN-LAST:event_durationFieldFocusLost

    private void nameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameFieldActionPerformed

    private void movieAddButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieAddButtonMouseEntered
        movieAddButton.setForeground(Color.BLACK);
        movieAddButton.setBackground(Color.WHITE);
        movieAddButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_movieAddButtonMouseEntered

    private void movieAddButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieAddButtonMouseExited
        movieAddButton.setForeground(Color.WHITE);
        movieAddButton.setBackground(Color.RED);
        movieAddButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_movieAddButtonMouseExited

    private void movieUpdateButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieUpdateButtonMouseEntered
        movieUpdateButton.setForeground(Color.BLACK);
        movieUpdateButton.setBackground(Color.WHITE);
        movieUpdateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_movieUpdateButtonMouseEntered

    private void movieUpdateButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieUpdateButtonMouseExited
        movieUpdateButton.setForeground(Color.WHITE);
        movieUpdateButton.setBackground(Color.RED);
        movieUpdateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_movieUpdateButtonMouseExited

    private void movieDeleteButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieDeleteButtonMouseEntered
        movieDeleteButton.setForeground(Color.BLACK);
        movieDeleteButton.setBackground(Color.WHITE);
        movieDeleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_movieDeleteButtonMouseEntered

    private void movieDeleteButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieDeleteButtonMouseExited
        movieDeleteButton.setForeground(Color.WHITE);
        movieDeleteButton.setBackground(Color.RED);
        movieDeleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_movieDeleteButtonMouseExited

    private void movieAddButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieAddButtonMouseClicked

    }//GEN-LAST:event_movieAddButtonMouseClicked

    private void movieAddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieAddButtonActionPerformed
        setPromptText(nameField, "Name");
        setPromptText(movieIDField, "Movie ID");
        setPromptText(genreField, "Genre");
        setPromptText(releasedateField, "Release Date");
        setPromptText(languageField, "Language");
        setPromptText(productioncompanyField, "Production Company");
        setPromptText(durationField, "Duration");
        setPromptText(statusField, "Status");
        nameField.setVisible(true);
        movieIDField.setVisible(true);
        genreField.setVisible(true);
        releasedateField.setVisible(true);
        languageField.setVisible(true);
        productioncompanyField.setVisible(true);
        durationField.setVisible(true);
        statusField.setVisible(true);
        genericButton.setVisible(true);
        genericButton.setText("ADD");
        movieIDField.setEditable(true);
        nameField.setEditable(true);
        genreField.setEditable(true);
        releasedateField.setEditable(true);
        languageField.setEditable(true);
        productioncompanyField.setEditable(true);
        durationField.setEditable(true);
        statusField.setEditable(true);
    }//GEN-LAST:event_movieAddButtonActionPerformed

    private void movieUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieUpdateButtonActionPerformed
        setPromptText(nameField, "Name");
        setPromptText(movieIDField, "Movie ID");
        setPromptText(genreField, "Genre");
        setPromptText(releasedateField, "Release Date");
        setPromptText(languageField, "Language");
        setPromptText(productioncompanyField, "Production Company");
        setPromptText(durationField, "Duration");
        setPromptText(statusField, "Status");
        nameField.setVisible(true);
        movieIDField.setVisible(true);
        genreField.setVisible(true);
        releasedateField.setVisible(true);
        languageField.setVisible(true);
        productioncompanyField.setVisible(true);
        durationField.setVisible(true);
        statusField.setVisible(true);
        genericButton.setVisible(true);
        genericButton.setText("FIND MOVIE");
        nameField.setEditable(false);
        genreField.setEditable(false);
        releasedateField.setEditable(false);
        languageField.setEditable(false);
        productioncompanyField.setEditable(false);
        durationField.setEditable(false);
        statusField.setEditable(false);
    }//GEN-LAST:event_movieUpdateButtonActionPerformed

    private void movieDeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieDeleteButtonActionPerformed
        setPromptText(movieIDField, "Movie ID");
        nameField.setVisible(false);
        movieIDField.setVisible(true);
        genreField.setVisible(false);
        releasedateField.setVisible(false);
        languageField.setVisible(false);
        productioncompanyField.setVisible(false);
        durationField.setVisible(false);
        statusField.setVisible(false);
        genericButton.setVisible(true);
        genericButton.setText("DELETE");
        movieIDField.setEditable(true);
    }//GEN-LAST:event_movieDeleteButtonActionPerformed

    private void genericButtonShowtimeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genericButtonShowtimeMouseEntered
        genericButtonShowtime.setForeground(Color.BLACK);
        genericButtonShowtime.setBackground(Color.WHITE);
        genericButtonShowtime.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_genericButtonShowtimeMouseEntered

    private void genericButtonShowtimeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_genericButtonShowtimeMouseExited
        genericButtonShowtime.setForeground(Color.WHITE);
        genericButtonShowtime.setBackground(Color.RED);
        genericButtonShowtime.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_genericButtonShowtimeMouseExited

    private void addShowtimeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addShowtimeButtonMouseEntered
        addShowtimeButton.setForeground(Color.BLACK);
        addShowtimeButton.setBackground(Color.WHITE);
        addShowtimeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_addShowtimeButtonMouseEntered

    private void addShowtimeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addShowtimeButtonMouseExited
        addShowtimeButton.setForeground(Color.WHITE);
        addShowtimeButton.setBackground(Color.RED);
        addShowtimeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_addShowtimeButtonMouseExited

    private void updateShowtimeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateShowtimeButtonMouseEntered
        updateShowtimeButton.setForeground(Color.BLACK);
        updateShowtimeButton.setBackground(Color.WHITE);
        updateShowtimeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_updateShowtimeButtonMouseEntered

    private void updateShowtimeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateShowtimeButtonMouseExited
        updateShowtimeButton.setForeground(Color.WHITE);
        updateShowtimeButton.setBackground(Color.RED);
        updateShowtimeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_updateShowtimeButtonMouseExited

    private void deleteShowtimeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteShowtimeButtonMouseEntered
        deleteShowtimeButton.setForeground(Color.BLACK);
        deleteShowtimeButton.setBackground(Color.WHITE);
        deleteShowtimeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_deleteShowtimeButtonMouseEntered

    private void deleteShowtimeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteShowtimeButtonMouseExited
        deleteShowtimeButton.setForeground(Color.WHITE);
        deleteShowtimeButton.setBackground(Color.RED);
        deleteShowtimeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_deleteShowtimeButtonMouseExited

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

    private void primaryMovieIDFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_primaryMovieIDFieldFocusGained
        if (primaryMovieIDField.getText().equals("Movie ID")) {
            setPromptText(primaryMovieIDField, null);
        }
    }//GEN-LAST:event_primaryMovieIDFieldFocusGained

    private void primaryMovieIDFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_primaryMovieIDFieldFocusLost
        if (primaryMovieIDField.getText().trim().isEmpty()) {
            setPromptText(primaryMovieIDField, "Movie ID");
        }
    }//GEN-LAST:event_primaryMovieIDFieldFocusLost

    private void primaryHallIDFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_primaryHallIDFieldFocusGained
        if (primaryHallIDField.getText().equals("Hall ID")) {
            setPromptText(primaryHallIDField, null);
        }
    }//GEN-LAST:event_primaryHallIDFieldFocusGained

    private void primaryHallIDFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_primaryHallIDFieldFocusLost
        if (primaryHallIDField.getText().trim().isEmpty()) {
            setPromptText(primaryHallIDField, "Hall ID");
        }
    }//GEN-LAST:event_primaryHallIDFieldFocusLost

    private void startTimeFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_startTimeFieldFocusGained
        if (startTimeField.getText().equals("Start Time")) {
            setPromptText(startTimeField, null);
        }
    }//GEN-LAST:event_startTimeFieldFocusGained

    private void startTimeFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_startTimeFieldFocusLost
        if (startTimeField.getText().trim().isEmpty()) {
            setPromptText(startTimeField, "Start Time");
        }
    }//GEN-LAST:event_startTimeFieldFocusLost

    private void endTimeFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_endTimeFieldFocusGained
        if (endTimeField.getText().equals("End Time")) {
            setPromptText(endTimeField, null);
        }
    }//GEN-LAST:event_endTimeFieldFocusGained

    private void endTimeFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_endTimeFieldFocusLost
        if (endTimeField.getText().trim().isEmpty()) {
            setPromptText(endTimeField, "End Time");
        }
    }//GEN-LAST:event_endTimeFieldFocusLost

    private void addShowtimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addShowtimeButtonActionPerformed
        setPromptText(showtimeIDField, "Showtime ID");
        setPromptText(primaryMovieIDField, "Movie ID");
        setPromptText(primaryHallIDField, "Hall ID");
        setPromptText(startTimeField, "Start Time");
        setPromptText(endTimeField, "End Time");
        setPromptText(dateField, "Date");
        showtimeIDField.setVisible(true);
        primaryMovieIDField.setVisible(true);
        primaryHallIDField.setVisible(true);
        startTimeField.setVisible(true);
        endTimeField.setVisible(true);
        dateField.setVisible(true);
        genericButtonShowtime.setVisible(true);
        genericButtonShowtime.setText("ADD");
        showtimeIDField.setEditable(true);
        primaryMovieIDField.setEditable(true);
        primaryHallIDField.setEditable(true);
        startTimeField.setEditable(true);
        endTimeField.setEditable(true);
        dateField.setEditable(true);
    }//GEN-LAST:event_addShowtimeButtonActionPerformed

    private void updateShowtimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateShowtimeButtonActionPerformed
        setPromptText(showtimeIDField, "Showtime ID");
        setPromptText(primaryMovieIDField, "Movie ID");
        setPromptText(primaryHallIDField, "Hall ID");
        setPromptText(startTimeField, "Start Time");
        setPromptText(endTimeField, "End Time");
        setPromptText(dateField, "Date");
        showtimeIDField.setVisible(true);
        primaryMovieIDField.setVisible(true);
        primaryHallIDField.setVisible(true);
        startTimeField.setVisible(true);
        endTimeField.setVisible(true);
        dateField.setVisible(true);
        genericButtonShowtime.setVisible(true);
        genericButtonShowtime.setText("FIND SHOWTIME");
        primaryMovieIDField.setEditable(false);
        primaryHallIDField.setEditable(false);
        startTimeField.setEditable(false);
        endTimeField.setEditable(false);
        dateField.setEditable(false);
    }//GEN-LAST:event_updateShowtimeButtonActionPerformed

    private void deleteShowtimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteShowtimeButtonActionPerformed
        setPromptText(showtimeIDField, "Showtime ID");
        showtimeIDField.setVisible(true);
        primaryMovieIDField.setVisible(false);
        primaryHallIDField.setVisible(false);
        startTimeField.setVisible(false);
        endTimeField.setVisible(false);
        dateField.setVisible(false);
        genericButtonShowtime.setVisible(true);
        genericButtonShowtime.setText("DELETE");
        showtimeIDField.setEditable(true);
    }//GEN-LAST:event_deleteShowtimeButtonActionPerformed

    private void genericButtonShowtimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genericButtonShowtimeActionPerformed
        int forUpdation = 0;
        if (genericButtonShowtime.getText().equals("ADD")) {

            if (showtimeIDField.getText().trim().isEmpty() || primaryMovieIDField.getText().trim().isEmpty() || primaryHallIDField.getText().trim().isEmpty() || startTimeField.getText().trim().isEmpty() || endTimeField.getText().trim().isEmpty() || dateField.getText().trim().isEmpty() || showtimeIDField.getText().equals("Showtime ID") || primaryMovieIDField.getText().equals("Movie ID") || primaryHallIDField.getText().equals("Hall ID") || startTimeField.getText().equals("Start Time") || endTimeField.getText().equals("End Time") || dateField.getText().equals("Date")) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "Fields are Empty!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                dbconnection db = new dbconnection();
                Date currentDate = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String currentDateString = dateFormat.format(currentDate);
                if (dateField.getText().compareTo(currentDateString) >= 0) {
                    String sql = "insert into SHOWTIME (SHOWTIME_ID,MOVIE_ID,HALL_ID,STARTTIME,ENDTIME, DATE) values ('" + showtimeIDField.getText() + "','" + primaryMovieIDField.getText() + "',' " + primaryHallIDField.getText() + " ','" + startTimeField.getText() + "','" + endTimeField.getText() + "','" + dateField.getText() + "'";
                    try {
                        db.st.executeUpdate(sql);
                        JFrame f = null;
                        JOptionPane.showMessageDialog(f, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        setPromptText(showtimeIDField, "Showtime ID");
                        setPromptText(primaryMovieIDField, "Movie ID");
                        setPromptText(primaryHallIDField, "Hall ID");
                        setPromptText(startTimeField, "Start Time");
                        setPromptText(endTimeField, "End Time");
                        setPromptText(dateField, "Date");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(this, "Try Again");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Date Entered Wrong");
                }
            }
        }

        if (genericButtonShowtime.getText().equals("FIND SHOWTIME")) {

            if (showtimeIDField.getText().trim().isEmpty()) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "Showtime ID Field is Empty!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                dbconnection db = new dbconnection();
                String showtimeID = showtimeIDField.getText();
                String sql = "SELECT * FROM SHOWTIME WHERE SHOWTIME_ID = '" + showtimeID + "'";

                try {
                    ResultSet resultSet = db.st.executeQuery(sql);
                    if (!resultSet.next()) {
                        JOptionPane.showMessageDialog(this, "Showtime Not Found");
                        setPromptText(showtimeIDField, "Showtime ID");
                        setPromptText(primaryMovieIDField, "Movie ID");
                        setPromptText(primaryHallIDField, "Hall ID");
                        setPromptText(startTimeField, "Start Time");
                        setPromptText(endTimeField, "End Time");
                        setPromptText(dateField, "Date");

                        primaryMovieIDField.setEditable(false);
                        primaryHallIDField.setEditable(false);
                        startTimeField.setEditable(false);
                        endTimeField.setEditable(false);
                        dateField.setEditable(false);
                    } else {
                        String movieID = resultSet.getString("MOVIE_ID");
                        String hallID = resultSet.getString("HALL_ID");
                        String startTime = resultSet.getString("STARTTIME");
                        String endTime = resultSet.getString("ENDTIME");
                        String date = resultSet.getString("DATE");

                        primaryMovieIDField.setEditable(true);
                        primaryHallIDField.setEditable(true);
                        startTimeField.setEditable(true);
                        endTimeField.setEditable(true);
                        dateField.setEditable(true);

                        primaryMovieIDField.setForeground(Color.BLACK);
                        primaryHallIDField.setForeground(Color.BLACK);
                        startTimeField.setForeground(Color.BLACK);
                        endTimeField.setForeground(Color.BLACK);
                        dateField.setForeground(Color.BLACK);

                        primaryMovieIDField.setText(movieID);
                        primaryHallIDField.setText(hallID);
                        startTimeField.setText(startTime);
                        endTimeField.setText(endTime);
                        dateField.setText(date);
                        showtimeIDField.setEditable(false);
                        forUpdation = 1;
                        genericButtonShowtime.setText("UPDATE");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "An error occurred. Check logs for details.");
                }
            }
        }

        if (genericButtonShowtime.getText().equals("UPDATE") && forUpdation != 1) {

            if (showtimeIDField.getText().trim().isEmpty() || primaryMovieIDField.getText().trim().isEmpty() || primaryHallIDField.getText().trim().isEmpty() || startTimeField.getText().trim().isEmpty() || endTimeField.getText().trim().isEmpty() || dateField.getText().trim().isEmpty() || showtimeIDField.getText().equals("Showtime ID") || primaryMovieIDField.getText().equals("Movie ID") || primaryHallIDField.getText().equals("Hall ID") || startTimeField.getText().equals("Start Time") || endTimeField.getText().equals("End Time") || dateField.getText().equals("Date")) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "Fields are Empty!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                dbconnection db = new dbconnection();
                String sql = "UPDATE SHOWTIME SET " + "MOVIE_ID = '" + primaryMovieIDField.getText() + "', "
                        + "HALL_ID = '" + primaryHallIDField.getText() + "', "
                        + "STARTTIME = '" + startTimeField.getText() + "', "
                        + "ENDTIME = '" + endTimeField.getText() + "', "
                        + "DATE = '" + dateField.getText() + "' "
                        + "WHERE SHOWTIME_ID = '" + showtimeIDField.getText() + "'";
                int check;

                try {
                    check = db.st.executeUpdate(sql);
                    JFrame f = null;
                    if (check == 1) {
                        JOptionPane.showMessageDialog(f, "Showtime Updated Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        genericButtonShowtime.setText("FIND MOVIE");
                    } else {
                        JOptionPane.showMessageDialog(f, "Showtime Not Updated!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                    }
                    showtimeIDField.setEditable(true);

                    Color customGreyColor = new Color(128, 128, 128);

                    primaryMovieIDField.setForeground(customGreyColor);
                    primaryHallIDField.setForeground(customGreyColor);
                    startTimeField.setForeground(customGreyColor);
                    endTimeField.setForeground(customGreyColor);
                    dateField.setForeground(customGreyColor);

                    setPromptText(showtimeIDField, "Showtime ID");
                    setPromptText(primaryMovieIDField, "Movie ID");
                    setPromptText(primaryHallIDField, "Hall ID");
                    setPromptText(startTimeField, "Start Time");
                    setPromptText(endTimeField, "End Time");
                    setPromptText(dateField, "Date");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "An error occurred. Check logs for details.");
                }
            }
        }

        if (genericButtonShowtime.getText().equals("DELETE")) {
            if (showtimeIDField.getText().trim().isEmpty()) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "Showtime ID Field is Empty!", "Alert", JOptionPane.WARNING_MESSAGE);
            } else {
                dbconnection db = new dbconnection();
                String sql = "DELETE FROM SHOWTIME WHERE SHOWTIME_ID = '" + showtimeIDField.getText() + "'";
                int check;
                try {
                    check = db.st.executeUpdate(sql);
                    JFrame f = null;
                    if (check == 1) {
                        JOptionPane.showMessageDialog(f, "Showtime Deleted Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        setPromptText(movieIDField, "Showtime ID");
                    } else {
                        JOptionPane.showMessageDialog(f, "Record Not Found!", "Not Found", JOptionPane.INFORMATION_MESSAGE);
                        setPromptText(movieIDField, "Showtime ID");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Try Again");
                }
            }
        }
    }//GEN-LAST:event_genericButtonShowtimeActionPerformed

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

    private void dateFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateFieldFocusGained
        if (dateField.getText().equals("Date")) {
            setPromptText(dateField, null);
        }
    }//GEN-LAST:event_dateFieldFocusGained

    private void dateFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateFieldFocusLost
        if (dateField.getText().trim().isEmpty()) {
            setPromptText(dateField, "Date");
        }
    }//GEN-LAST:event_dateFieldFocusLost

    private void searchFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusGained
        if (searchField.getText().equals("Customer Name")) {
            setPromptText(searchField, null);
        }
    }//GEN-LAST:event_searchFieldFocusGained

    private void searchFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusLost
        if (searchField.getText().trim().isEmpty()) {
            setPromptText(searchField, "Customer Name");
        }
    }//GEN-LAST:event_searchFieldFocusLost

    private void movieShowtimePanelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieShowtimePanelMouseEntered
        if (name != "movieShowtimePanel") {
            movieShowtimePanel.setBackground(new Color(0xF5F5F5));
            movieShowtimePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_movieShowtimePanelMouseEntered

    private void movieShowtimePanelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieShowtimePanelMouseExited
        if (name != "movieShowtimePanel") {
            movieShowtimePanel.setBackground(Color.WHITE);
            movieShowtimePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_movieShowtimePanelMouseExited

    private void movieShowtimePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieShowtimePanelMouseClicked
        name = "movieShowtimePanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.RED);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(true);

        movieButton.setVisible(true);
        showtimeButton.setVisible(true);

        genericScrollPane1.setVisible(true);
        genericTable.setVisible(true);
    }//GEN-LAST:event_movieShowtimePanelMouseClicked

    private void movieshowtimeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieshowtimeLabelMouseEntered
        if (name != "movieShowtimePanel") {
            movieShowtimePanel.setBackground(new Color(0xF5F5F5));
            movieShowtimePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_movieshowtimeLabelMouseEntered

    private void movieshowtimeLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieshowtimeLabelMouseExited
        if (name != "movieShowtimePanel") {
            movieShowtimePanel.setBackground(Color.WHITE);
            movieShowtimePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_movieshowtimeLabelMouseExited

    private void movieshowtimeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieshowtimeLabelMouseClicked
        name = "movieShowtimePanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.RED);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(true);

        movieButton.setVisible(true);
        showtimeButton.setVisible(true);

        genericScrollPane1.setVisible(true);
        genericTable.setVisible(true);
    }//GEN-LAST:event_movieshowtimeLabelMouseClicked

    private void movieshowtimeiconLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieshowtimeiconLabelMouseEntered
        if (name != "movieShowtimePanel") {
            movieShowtimePanel.setBackground(new Color(0xF5F5F5));
            movieShowtimePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_movieshowtimeiconLabelMouseEntered

    private void movieshowtimeiconLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieshowtimeiconLabelMouseExited
        if (name != "movieShowtimePanel") {
            movieShowtimePanel.setBackground(Color.WHITE);
            movieShowtimePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }//GEN-LAST:event_movieshowtimeiconLabelMouseExited

    private void movieshowtimeiconLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieshowtimeiconLabelMouseClicked
        name = "movieShowtimePanel";

        revenuePanel.setBackground(Color.WHITE);
        moviePanel.setBackground(Color.WHITE);
        showtimePanel.setBackground(Color.WHITE);
        ticketPanel.setBackground(Color.WHITE);
        customerinfoPanel.setBackground(Color.WHITE);
        ratingPanel.setBackground(Color.WHITE);
        movieShowtimePanel.setBackground(Color.RED);

        revenuemainPanel.setVisible(false);
        moviemainPanel.setVisible(false);
        showtimemainPanel.setVisible(false);
        ticketmainPanel.setVisible(false);
        customermainPanel.setVisible(false);
        ratingmainPanel.setVisible(false);
        movieShowtimeMainPanel.setVisible(true);

        movieButton.setVisible(true);
        showtimeButton.setVisible(true);

        genericScrollPane1.setVisible(true);
        genericTable.setVisible(true);
    }//GEN-LAST:event_movieshowtimeiconLabelMouseClicked

    private void movieButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieButtonMouseEntered
        movieButton.setForeground(Color.BLACK);
        movieButton.setBackground(Color.WHITE);
        movieButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_movieButtonMouseEntered

    private void movieButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieButtonMouseExited
        movieButton.setForeground(Color.WHITE);
        movieButton.setBackground(Color.RED);
        movieButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_movieButtonMouseExited

    private void showtimeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showtimeButtonMouseEntered
        showtimeButton.setForeground(Color.BLACK);
        showtimeButton.setBackground(Color.WHITE);
        showtimeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_showtimeButtonMouseEntered

    private void showtimeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showtimeButtonMouseExited
        showtimeButton.setForeground(Color.WHITE);
        showtimeButton.setBackground(Color.RED);
        showtimeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_showtimeButtonMouseExited

    private void movieButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movieButtonActionPerformed

        genericTable.getColumnModel().getColumn(0).setHeaderValue("MOVIE ID");
        genericTable.getColumnModel().getColumn(1).setHeaderValue("NAME");
        genericTable.getColumnModel().getColumn(2).setHeaderValue("GENRE");
        genericTable.getColumnModel().getColumn(3).setHeaderValue("REL. DATE");
        genericTable.getColumnModel().getColumn(4).setHeaderValue("LANGUAGE");
        genericTable.getColumnModel().getColumn(5).setHeaderValue("COMPANY");
        genericTable.getColumnModel().getColumn(6).setHeaderValue("DURATION");
        genericTable.getColumnModel().getColumn(7).setHeaderValue("STATUS");

        genericTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        genericTable.getColumnModel().getColumn(5).setPreferredWidth(150);

        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) genericTable.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT * FROM MOVIE");
            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "No Record Found");
            } else {
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)};
                    tblmodel.addRow(tbData);
                }
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_movieButtonActionPerformed

    private void showtimeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showtimeButtonActionPerformed
        genericTable.getColumnModel().getColumn(0).setHeaderValue("SHOW ID");
        genericTable.getColumnModel().getColumn(1).setHeaderValue("MOVIE NAME");
        genericTable.getColumnModel().getColumn(2).setHeaderValue("HALL NO");
        genericTable.getColumnModel().getColumn(3).setHeaderValue("RES. SEATS");
        genericTable.getColumnModel().getColumn(4).setHeaderValue("AVL. SEATS");
        genericTable.getColumnModel().getColumn(5).setHeaderValue("START TIME");
        genericTable.getColumnModel().getColumn(6).setHeaderValue("END TIME");

        genericTable.getColumnModel().getColumn(1).setPreferredWidth(160);

        dbconnection db = new dbconnection();
        DefaultTableModel tblmodel = (DefaultTableModel) genericTable.getModel();
        try {
            tblmodel.setRowCount(0);
            ResultSet rs = db.st.executeQuery("SELECT S.SHOWTIME_ID, M.NAME, S.HALL_ID, S.RESERVED_SEATS, S.AVAILABLE_SEATS, S.STARTTIME, S.ENDTIME FROM SHOWTIME S JOIN MOVIE M ON S.MOVIE_ID = M.MOVIE_ID");
            if (!rs.next()) {
                JOptionPane.showMessageDialog(this, "No Record Found");
            } else {
                while (rs.next()) {
                    String tbData[] = {rs.getString("S.SHOWTIME_ID"), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)};
                    tblmodel.addRow(tbData);
                }
            }
        } catch (SQLException ex) {
            JFrame f = null;
            JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_showtimeButtonActionPerformed

    private void movieButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movieButtonMouseClicked

    }//GEN-LAST:event_movieButtonMouseClicked

    private void searchNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchNameFieldFocusGained
        if (searchNameField.getText().equals("Search Name")) {
            setPromptText(searchNameField, null);
        }
    }//GEN-LAST:event_searchNameFieldFocusGained

    private void searchNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchNameFieldFocusLost
        if (searchNameField.getText().trim().isEmpty()) {
            setPromptText(searchNameField, "Search Name");
        }
    }//GEN-LAST:event_searchNameFieldFocusLost

    private void searchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyPressed
        DefaultTableModel tblmodel = (DefaultTableModel) ticketSalesTabel.getModel();
        if (searchField.getText().trim().isEmpty()) {
            dbconnection db = new dbconnection();
            try {
                tblmodel.setRowCount(0);
                ResultSet rs = db.st.executeQuery("SELECT T.TICKET_ID, M.NAME AS MOVIE_NAME, C.NAME AS CUSTOMER_NAME, T.PRICE, T.TICKET_TYPE, T.SEAT_NO, T.STATUS FROM TICKET T JOIN MOVIE M ON T.MOVIE_ID = M.MOVIE_ID JOIN CUSTOMER C ON T.CUSTOMER_ID = C.CUSTOMER_ID;");
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "No Record Found");
                } else {
                    String tbData1[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), rs.getString(6), rs.getString(7)};
                    tblmodel.addRow(tbData1);
                    while (rs.next()) {
                        String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), rs.getString(6), rs.getString(7)};
                        tblmodel.addRow(tbData);
                    }
                }
            } catch (SQLException ex) {
                JFrame f = null;
                ex.printStackTrace();
                JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            dbconnection db = new dbconnection();
            try {
                tblmodel.setRowCount(0);
                String input = searchField.getText();
                ResultSet rs = db.st.executeQuery("SELECT T.TICKET_ID,M.NAME AS MOVIE_NAME,C.NAME AS CUSTOMER_NAME,T.PRICE,T.TICKET_TYPE,T.SEAT_NO,T.STATUS FROM TICKET T JOIN MOVIE M ON T.MOVIE_ID = M.MOVIE_ID JOIN CUSTOMER C ON T.CUSTOMER_ID = C.CUSTOMER_ID WHERE C.NAME LIKE '" + input + "%'");
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), rs.getString(6), rs.getString(7)};
                    tblmodel.addRow(tbData);
                }
            } catch (SQLException ex) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_searchFieldKeyPressed

    private void searchFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyTyped

    }//GEN-LAST:event_searchFieldKeyTyped

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        DefaultTableModel tblmodel = (DefaultTableModel) ticketSalesTabel.getModel();
        if (searchField.getText().trim().isEmpty()) {
            dbconnection db = new dbconnection();
            try {
                tblmodel.setRowCount(0);
                ResultSet rs = db.st.executeQuery("SELECT T.TICKET_ID, M.NAME AS MOVIE_NAME, C.NAME AS CUSTOMER_NAME, T.PRICE, T.TICKET_TYPE, T.SEAT_NO, T.STATUS FROM TICKET T JOIN MOVIE M ON T.MOVIE_ID = M.MOVIE_ID JOIN CUSTOMER C ON T.CUSTOMER_ID = C.CUSTOMER_ID;");
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "No Record Found");
                } else {
                    String tbData1[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), rs.getString(6), rs.getString(7)};
                    tblmodel.addRow(tbData1);
                    while (rs.next()) {
                        String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), rs.getString(6), rs.getString(7)};
                        tblmodel.addRow(tbData);
                    }
                }
            } catch (SQLException ex) {
                JFrame f = null;
                ex.printStackTrace();
                JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            dbconnection db = new dbconnection();
            try {
                tblmodel.setRowCount(0);
                String input = searchField.getText();
                ResultSet rs = db.st.executeQuery("SELECT T.TICKET_ID,M.NAME AS MOVIE_NAME,C.NAME AS CUSTOMER_NAME,T.PRICE,T.TICKET_TYPE,T.SEAT_NO,T.STATUS FROM TICKET T JOIN MOVIE M ON T.MOVIE_ID = M.MOVIE_ID JOIN CUSTOMER C ON T.CUSTOMER_ID = C.CUSTOMER_ID WHERE C.NAME LIKE '" + input + "%'");
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4), rs.getString(6), rs.getString(7)};
                    tblmodel.addRow(tbData);
                }
            } catch (SQLException ex) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_searchFieldKeyReleased

    private void searchNameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchNameFieldKeyPressed
        DefaultTableModel tblmodel = (DefaultTableModel) monitorCustomerTable.getModel();
        if (searchNameField.getText().trim().isEmpty()) {
            dbconnection db = new dbconnection();
            try {
                tblmodel.setRowCount(0);
                ResultSet rs = db.st.executeQuery("SELECT * FROM CUSTOMER");
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "No Record Found");
                } else {
                    String tbData1[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                    tblmodel.addRow(tbData1);
                    while (rs.next()) {
                        String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                        tblmodel.addRow(tbData);
                    }
                }
            } catch (SQLException ex) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            dbconnection db = new dbconnection();
            try {
                tblmodel.setRowCount(0);
                String input = searchNameField.getText();
                ResultSet rs = db.st.executeQuery("SELECT * FROM CUSTOMER WHERE NAME LIKE '" + input + "%'");
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                    tblmodel.addRow(tbData);
                }
            } catch (SQLException ex) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_searchNameFieldKeyPressed

    private void searchNameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchNameFieldKeyReleased
        DefaultTableModel tblmodel = (DefaultTableModel) monitorCustomerTable.getModel();
        if (searchNameField.getText().trim().isEmpty()) {
            dbconnection db = new dbconnection();
            try {
                tblmodel.setRowCount(0);
                ResultSet rs = db.st.executeQuery("SELECT * FROM CUSTOMER");
                if (!rs.next()) {
                    JOptionPane.showMessageDialog(this, "No Record Found");
                } else {
                    String tbData1[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                    tblmodel.addRow(tbData1);
                    while (rs.next()) {
                        String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                        tblmodel.addRow(tbData);
                    }
                }
            } catch (SQLException ex) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            dbconnection db = new dbconnection();
            try {
                tblmodel.setRowCount(0);
                String input = searchNameField.getText();
                ResultSet rs = db.st.executeQuery("SELECT * FROM CUSTOMER WHERE NAME LIKE '" + input + "%'");
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)};
                    tblmodel.addRow(tbData);
                }
            } catch (SQLException ex) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_searchNameFieldKeyReleased

    private void searchMovieNameFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchMovieNameFieldFocusGained
        if (searchMovieNameField.getText().equals("Search Movie ID")) {
            setPromptText(searchMovieNameField, null);
        }
    }//GEN-LAST:event_searchMovieNameFieldFocusGained

    private void searchMovieNameFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchMovieNameFieldFocusLost
        if (searchMovieNameField.getText().trim().isEmpty()) {
            setPromptText(searchMovieNameField, "Search Movie ID");
        }
    }//GEN-LAST:event_searchMovieNameFieldFocusLost

    private void searchMovieNameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchMovieNameFieldKeyPressed
        DefaultTableModel tblmodel = (DefaultTableModel) ratingTable.getModel();
        if (searchMovieNameField.getText().trim().isEmpty()) {
            dbconnection db = new dbconnection();
            try {
                tblmodel.setRowCount(0);
                ResultSet rs = db.st.executeQuery("SELECT MOVIE_ID, CUSTOMER_ID, REVIEWS, RATING FROM MOVIE_REVIEWS");
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
                    tblmodel.addRow(tbData);
                }
            } catch (SQLException ex) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            dbconnection db = new dbconnection();
            try {
                tblmodel.setRowCount(0);
                String input = searchMovieNameField.getText();
                ResultSet rs = db.st.executeQuery("SELECT MOVIE_ID, CUSTOMER_ID, REVIEWS, RATING FROM MOVIE_REVIEWS WHERE MOVIE_ID LIKE '" + input + "%'");
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
                    tblmodel.addRow(tbData);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_searchMovieNameFieldKeyPressed

    private void searchMovieNameFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchMovieNameFieldKeyReleased
        DefaultTableModel tblmodel = (DefaultTableModel) ratingTable.getModel();
        if (searchMovieNameField.getText().trim().isEmpty()) {
            dbconnection db = new dbconnection();
            try {
                tblmodel.setRowCount(0);
                ResultSet rs = db.st.executeQuery("SELECT MOVIE_ID, CUSTOMER_ID, REVIEWS, RATING FROM MOVIE_REVIEWS");
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
                    tblmodel.addRow(tbData);
                }
            } catch (SQLException ex) {
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            dbconnection db = new dbconnection();
            try {
                tblmodel.setRowCount(0);
                String input = searchMovieNameField.getText();
                ResultSet rs = db.st.executeQuery("SELECT MOVIE_ID, CUSTOMER_ID, REVIEWS, RATING FROM MOVIE_REVIEWS WHERE MOVIE_ID LIKE '" + input + "%'");
                while (rs.next()) {
                    String tbData[] = {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
                    tblmodel.addRow(tbData);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JFrame f = null;
                JOptionPane.showMessageDialog(f, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_searchMovieNameFieldKeyReleased
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
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane CustomerScrollPane;
    private javax.swing.JButton addShowtimeButton;
    private javax.swing.JPanel adminIdPanel;
    private javax.swing.JLabel adminidLabel;
    private javax.swing.JLabel customerinfoLabel;
    private javax.swing.JPanel customerinfoPanel;
    private javax.swing.JLabel customerinfoiconLabel;
    private javax.swing.JPanel customermainPanel;
    private javax.swing.JTextField dateField;
    private javax.swing.JButton deleteShowtimeButton;
    private javax.swing.JTextField durationField;
    private javax.swing.JPanel earningGraphPanel;
    private javax.swing.JTextField endTimeField;
    private javax.swing.JButton genericButton;
    private javax.swing.JButton genericButtonShowtime;
    private javax.swing.JScrollPane genericScrollPane1;
    private javax.swing.JTable genericTable;
    private javax.swing.JTextField genreField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField languageField;
    private javax.swing.JPanel leftsidePanel;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel manageMovieCollectionLabel;
    private javax.swing.JLabel manageShowtimesLabel;
    private javax.swing.JLabel monitorCustomerLabel;
    private javax.swing.JTable monitorCustomerTable;
    private javax.swing.JButton movieAddButton;
    private javax.swing.JButton movieButton;
    private javax.swing.JButton movieDeleteButton;
    private javax.swing.JTextField movieIDField;
    private javax.swing.JLabel movieLabel;
    private javax.swing.JPanel moviePanel;
    private javax.swing.JLabel movieShowtimeLabel;
    private javax.swing.JPanel movieShowtimeMainPanel;
    private javax.swing.JPanel movieShowtimePanel;
    private javax.swing.JButton movieUpdateButton;
    private javax.swing.JLabel movieiconLabel;
    private javax.swing.JPanel moviemainPanel;
    private javax.swing.JLabel movieshowtimeLabel;
    private javax.swing.JLabel movieshowtimeiconLabel;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField primaryHallIDField;
    private javax.swing.JTextField primaryMovieIDField;
    private javax.swing.JTextField productioncompanyField;
    private javax.swing.JLabel ratingLabel;
    private javax.swing.JPanel ratingPanel;
    private javax.swing.JLabel ratingReviewLabel;
    private javax.swing.JScrollPane ratingScrollPane;
    private javax.swing.JTable ratingTable;
    private javax.swing.JLabel ratingiconLabel;
    private javax.swing.JPanel ratingmainPanel;
    private javax.swing.JTextField releasedateField;
    private javax.swing.JLabel revenueAnalyticsLabel;
    private javax.swing.JLabel revenueLabel;
    private javax.swing.JPanel revenuePanel;
    private javax.swing.JLabel revenueiconLabel;
    private javax.swing.JPanel revenuemainPanel;
    private javax.swing.JPanel rightsidePanel;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField searchMovieNameField;
    private javax.swing.JTextField searchNameField;
    private javax.swing.JButton showtimeButton;
    private javax.swing.JTextField showtimeIDField;
    private javax.swing.JLabel showtimeLabel;
    private javax.swing.JPanel showtimePanel;
    private javax.swing.JLabel showtimeiconLabel;
    private javax.swing.JPanel showtimemainPanel;
    private javax.swing.JTextField startTimeField;
    private javax.swing.JTextField statusField;
    private javax.swing.JPanel ticketGraphPanel;
    private javax.swing.JLabel ticketLabel;
    private javax.swing.JPanel ticketPanel;
    private javax.swing.JScrollPane ticketSaleScrollPane;
    private javax.swing.JLabel ticketSalesMonitoringLabel;
    private javax.swing.JTable ticketSalesTabel;
    private javax.swing.JLabel ticketiconLabel;
    private javax.swing.JPanel ticketmainPanel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton updateShowtimeButton;
    // End of variables declaration//GEN-END:variables
}
