package com.airlinemanagement;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

import net.proteanit.sql.DbUtils;

public class FlightInfo extends JFrame {

    public FlightInfo() {
        setTitle("Flight Information");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JTable table = new JTable();
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 0, 800, 500);
        add(jsp);

        setSize(800, 500);
        setLocation(400, 200);
        setVisible(true);

        // Load data after UI is visible to avoid blocking long startup in constructor
        SwingUtilities.invokeLater(this::loadDataIntoTable);
    }

    private void loadDataIntoTable() {
        // Use try-with-resources to ensure statements/results are closed.
        DBConnection dbConn;
        try {
            dbConn = new DBConnection(); // If DBConnection throws RuntimeException, it will be caught below
        } catch (RuntimeException re) {
            JOptionPane.showMessageDialog(this,
                    "Database initialization failed:\n" + re.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
            re.printStackTrace();
            return;
        }

        if (dbConn == null || dbConn.c == null) {
            JOptionPane.showMessageDialog(this,
                    "No database connection. Please check your DB settings.",
                    "DB Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String sql = "SELECT * FROM flights";

        try (PreparedStatement pst = dbConn.c.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            // DbUtils is from rs2xml (make sure rs2xml jar is on runtime classpath)
            tableSetModelFromResultSet(rs);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error fetching flight data:\n" + sqle.getMessage(),
                    "SQL Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Unexpected error:\n" + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tableSetModelFromResultSet(ResultSet rs) throws SQLException {
        // Convert ResultSet to TableModel and set on the JTable in the UI.
        // Using DbUtils from rs2xml package:
        JTable table = findTableInContentPane();
        if (table != null) {
            table.setModel(DbUtils.resultSetToTableModel(rs));
            // Optional: adjust column widths or revalidate
            table.revalidate();
        } else {
            throw new SQLException("Table component not found in UI.");
        }
    }

    private JTable findTableInContentPane() {
        // The JScrollPane contains the table as its viewport view.
        for (Component comp : getContentPane().getComponents()) {
            if (comp instanceof JScrollPane) {
                JScrollPane jsp = (JScrollPane) comp;
                Component view = jsp.getViewport().getView();
                if (view instanceof JTable) {
                    return (JTable) view;
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(FlightInfo::new);
    }
}
