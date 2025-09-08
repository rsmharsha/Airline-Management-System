package com.airlinemanagement;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.util.*;

public class BookFlight extends JFrame implements ActionListener {

    JTextField tfaadhar;
    JLabel tfname, tfnationality, tfaddress, labelgender, labelfname, labelfcode;
    JButton bookflight, fetchButton, flight;
    Choice source, destination;
    JDateChooser dcdate;

    public BookFlight() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel lblaadhar = new JLabel("Aadhar");
        lblaadhar.setBounds(60, 80, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(220, 80, 150, 25);
        add(tfaadhar);

        fetchButton = new JButton("Fetch User");
        fetchButton.setBackground(Color.BLACK);
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblname);

        tfname = new JLabel();
        tfname.setBounds(220, 130, 250, 25);
        add(tfname);

        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);

        tfnationality = new JLabel();
        tfnationality.setBounds(220, 180, 250, 25);
        add(tfnationality);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);

        tfaddress = new JLabel();
        tfaddress.setBounds(220, 230, 250, 25);
        add(tfaddress);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);

        labelgender = new JLabel();
        labelgender.setBounds(220, 280, 150, 25);
        add(labelgender);

        JLabel lblsource = new JLabel("Source");
        lblsource.setBounds(60, 330, 150, 25);
        lblsource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblsource);

        source = new Choice();
        source.setBounds(220, 330, 150, 25);
        add(source);

        JLabel lbldest = new JLabel("Destination");
        lbldest.setBounds(60, 380, 150, 25);
        lbldest.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldest);

        destination = new Choice();
        destination.setBounds(220, 380, 150, 25);
        add(destination);

        // Populate unique sources and destinations
        try {
            DBConnection c = new DBConnection();
            if (c == null || c.c == null) {
                throw new RuntimeException("DB connection not available");
            }

            String query = "SELECT DISTINCT source, destination FROM flights";
            try (PreparedStatement pst = c.c.prepareStatement(query);
                 ResultSet rs = pst.executeQuery()) {

                Set<String> srcSet = new LinkedHashSet<>();
                Set<String> destSet = new LinkedHashSet<>();
                while (rs.next()) {
                    String s = rs.getString("source");
                    String d = rs.getString("destination");
                    if (s != null) srcSet.add(s);
                    if (d != null) destSet.add(d);
                }
                for (String s : srcSet) source.add(s);
                for (String d : destSet) destination.add(d);
            } finally {
                // close the connection after use
                try { if (c.c != null) c.c.close(); } catch (SQLException ignore) {}
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Warning: Could not load flight locations.\n" + e.getMessage());
        }

        flight = new JButton("Fetch Flights");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(380, 380, 120, 25);
        flight.addActionListener(this);
        add(flight);

        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60, 430, 150, 25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfname);

        labelfname = new JLabel();
        labelfname.setBounds(220, 430, 250, 25);
        add(labelfname);

        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(60, 480, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblfcode);

        labelfcode = new JLabel();
        labelfcode.setBounds(220, 480, 150, 25);
        add(labelfcode);

        JLabel lbldate = new JLabel("Date of Travel");
        lbldate.setBounds(60, 530, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbldate);

        dcdate = new JDateChooser();
        dcdate.setBounds(220, 530, 150, 25);
        add(dcdate);

        // Safe image loading: try classpath, then IDE src path, handle nulls
        ImageIcon i1 = null;
        try {
            // 1) Try classpath resource (packaged JAR)
            URL url = getClass().getResource("/com/airlinemanagement/assets/booking.jpg");
            if (url == null) {
                // 2) Try alternative relative path
                url = getClass().getResource("assets/booking.jpg");
            }
            if (url != null) {
                Image img = ImageIO.read(url);
                if (img != null) {
                    Image scaled = img.getScaledInstance(450, 320, Image.SCALE_SMOOTH);
                    i1 = new ImageIcon(scaled);
                }
            } else {
                // 3) Fallback to development path on filesystem
                File f = new File("src/com/airlinemanagement/assets/booking.jpg");
                if (f.exists()) {
                    i1 = new ImageIcon(f.getAbsolutePath());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (i1 != null) {
            JLabel lblimage = new JLabel(i1);
            lblimage.setBounds(550, 80, 500, 410);
            add(lblimage);
        } else {
            System.err.println("Warning: booking.webp not found on classpath or src path. Skipping image.");
        }

        bookflight = new JButton("Book Flight");
        bookflight.setBackground(Color.BLACK);
        bookflight.setForeground(Color.WHITE);
        bookflight.setBounds(220, 580, 150, 25);
        bookflight.addActionListener(this);
        add(bookflight);

        setSize(1100, 700);
        setLocation(200, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        Object srcObj = ae.getSource();

        if (srcObj == fetchButton) {
            String aadhar = tfaadhar.getText().trim();
            if (aadhar.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter Aadhar");
                return;
            }

            DBConnection conn = null;
            try {
                conn = new DBConnection();
                if (conn == null || conn.c == null) throw new Exception("DB connection not available");

                String query = "SELECT name, nationality, address, gender FROM passenger WHERE aadhar = ?";
                try (PreparedStatement pst = conn.c.prepareStatement(query)) {
                    pst.setString(1, aadhar);
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            tfname.setText(rs.getString("name"));
                            tfnationality.setText(rs.getString("nationality"));
                            tfaddress.setText(rs.getString("address"));
                            labelgender.setText(rs.getString("gender"));
                        } else {
                            JOptionPane.showMessageDialog(null, "No passenger found for given Aadhar");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error fetching passenger:\n" + e.getMessage());
            } finally {
                if (conn != null && conn.c != null) {
                    try { conn.c.close(); } catch (SQLException ignore) {}
                }
            }

        } else if (srcObj == flight) {
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();
            if (src == null || dest == null) {
                JOptionPane.showMessageDialog(this, "Please select source and destination");
                return;
            }

            DBConnection conn = null;
            try {
                conn = new DBConnection();
                if (conn == null || conn.c == null) throw new Exception("DB connection not available");

                String query = "SELECT f_name, f_code FROM flights WHERE source = ? AND destination = ?";
                try (PreparedStatement pst = conn.c.prepareStatement(query)) {
                    pst.setString(1, src);
                    pst.setString(2, dest);
                    try (ResultSet rs = pst.executeQuery()) {
                        if (rs.next()) {
                            labelfname.setText(rs.getString("f_name"));
                            labelfcode.setText(rs.getString("f_code"));
                        } else {
                            JOptionPane.showMessageDialog(null, "No Flights Found");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error fetching flights:\n" + e.getMessage());
            } finally {
                if (conn != null && conn.c != null) {
                    try { conn.c.close(); } catch (SQLException ignore) {}
                }
            }

        } else if (srcObj == bookflight) {
            Random random = new Random();

            String aadhar = tfaadhar.getText().trim();
            String name = tfname.getText();
            String nationality = tfnationality.getText();
            String flight_name = labelfname.getText();
            String flight_code = labelfcode.getText();
            String src = source.getSelectedItem();
            String dst = destination.getSelectedItem();
            String travel_date = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();

            if (aadhar.isEmpty() || name.isEmpty() || flight_code.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fetch passenger and flight before booking.");
                return;
            }

            String pnr = "PNR-" + String.format("%06d", random.nextInt(1_000_000));
            String ticket = "TIC-" + String.format("%04d", random.nextInt(10_000));

            DBConnection conn = null;
            try {
                conn = new DBConnection();
                if (conn == null || conn.c == null) throw new Exception("DB connection not available");

                String query = "INSERT INTO reservation (pnr, ticket, aadhar, name, nationality, flight_name, flight_code, src, dst, travel_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pst = conn.c.prepareStatement(query)) {
                    pst.setString(1, pnr);
                    pst.setString(2, ticket);
                    pst.setString(3, aadhar);
                    pst.setString(4, name);
                    pst.setString(5, nationality);
                    pst.setString(6, flight_name);
                    pst.setString(7, flight_code);
                    pst.setString(8, src);
                    pst.setString(9, dst);
                    pst.setString(10, travel_date);

                    int rows = pst.executeUpdate();
                    if (rows > 0) {
                        JOptionPane.showMessageDialog(null, "Ticket Booked Successfully\nPNR: " + pnr + "\nTicket: " + ticket);
                        setVisible(false);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Booking failed. Try again.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error booking ticket:\n" + e.getMessage());
            } finally {
                if (conn != null && conn.c != null) {
                    try { conn.c.close(); } catch (SQLException ignore) {}
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookFlight::new);
    }
}
