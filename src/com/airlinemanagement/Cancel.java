package com.airlinemanagement;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class Cancel extends JFrame implements ActionListener {

    JTextField tfpnr;
    JLabel tfname, cancellationno, lblfcode, lbldateoftravel;
    JButton fetchButton, flight;

    public Cancel() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Random random = new Random();

        JLabel heading = new JLabel("Cancellation");
        heading.setBounds(180, 20, 250, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        add(heading);

        // --- Safe image loading: classpath first, fallback to your dev path ---
        ImageIcon i1 = null;
        try {
            // 1) Try classpath resource
            URL url = getClass().getResource("/com/airlinemanagement/assets/cancel.jpg");
            if (url != null) {
                Image img = ImageIO.read(url);
                if (img != null) {
                    Image scaled = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                    i1 = new ImageIcon(scaled);
                }
            } else {
                // 2) Fallback to your dev filesystem path
                File f = new File("D:\\4 LEARNING WEB\\Airline Management\\Airline Management System\\src\\com\\airlinemanagement\\assets\\cancel.jpg");
                if (f.exists()) {
                    Image img = ImageIO.read(f);
                    if (img != null) {
                        Image scaled = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                        i1 = new ImageIcon(scaled);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        if (i1 != null) {
            JLabel image = new JLabel(i1);
            image.setBounds(470, 120, 250, 250);
            add(image);
        } else {
            System.err.println("Warning: cancel.jpg not found on classpath or filesystem. Skipping image.");
        }
        // --- end image loading ---

        JLabel lblaadhar = new JLabel("PNR Number");
        lblaadhar.setBounds(60, 80, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblaadhar);

        tfpnr = new JTextField();
        tfpnr.setBounds(220, 80, 150, 25);
        add(tfpnr);

        fetchButton = new JButton("Show Details");
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
        tfname.setBounds(220, 130, 150, 25);
        add(tfname);

        JLabel lblnationality = new JLabel("Cancellation No");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblnationality);

        cancellationno = new JLabel("" + random.nextInt(1000000));
        cancellationno.setBounds(220, 180, 150, 25);
        add(cancellationno);

        JLabel lbladdress = new JLabel("Flight Code");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lbladdress);

        lblfcode = new JLabel();
        lblfcode.setBounds(220, 230, 150, 25);
        add(lblfcode);

        JLabel lblgender = new JLabel("Date");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblgender);

        lbldateoftravel = new JLabel();
        lbldateoftravel.setBounds(220, 280, 150, 25);
        add(lbldateoftravel);

        flight = new JButton("Cancel");
        flight.setBackground(Color.BLACK);
        flight.setForeground(Color.WHITE);
        flight.setBounds(220, 330, 120, 25);
        flight.addActionListener(this);
        add(flight);

        setSize(800, 450);
        setLocation(350, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String pnr = tfpnr.getText();

            try {
                DBConnection conn = new DBConnection();
                String query = "select * from reservation where PNR = '"+pnr+"'";
                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    tfname.setText(rs.getString("name"));
                    lblfcode.setText(rs.getString("flight_code"));
                    lbldateoftravel.setText(rs.getString("travel_date"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct PNR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            String passengerName = tfname.getText(); // renamed variable
            String pnr = tfpnr.getText();
            String cancelno = cancellationno.getText();
            String flight_code = lblfcode.getText();
            String travel_date = lbldateoftravel.getText();

            try {
                DBConnection conn = new DBConnection();
                // ✅ specify columns so MySQL knows what to insert
                String query = "INSERT INTO cancel (pnr, name, cancelno, flight_code, travel_date) " +
                        "VALUES ('"+pnr+"', '"+passengerName+"', '"+cancelno+"', '"+flight_code+"', '"+travel_date+"')";
                conn.s.executeUpdate(query);

                conn.s.executeUpdate("DELETE FROM reservation WHERE PNR = '"+pnr+"'");
                JOptionPane.showMessageDialog(null, "Ticket Cancelled");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Cancel();
    }
}
