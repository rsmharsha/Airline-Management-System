package com.airlinemanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.URL;

public class AddCustomer extends JFrame implements ActionListener {
    JTextField tfname, tfphone, tfaadhar, tfnationality, tfaddress;
    JRadioButton rbmale, rbfemale;

    public AddCustomer() {
        setTitle("Airline Management - Add Customer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final int FRAME_W = 900;
        final int FRAME_H = 600;
        setSize(FRAME_W, FRAME_H);
        setLocation(300, 150);
        setResizable(false);

        // Base panel (holds everything) — DO NOT replace later
        JPanel base = new JPanel(null);
        base.setBackground(Color.WHITE);
        setContentPane(base);

        // Form area width (left side)
        final int FORM_RIGHT_X = 520;   // x coordinate where right-side image starts
        final int FORM_WIDTH = FORM_RIGHT_X; // form fits within 0..FORM_RIGHT_X-1

        // Right-side image area (positioned at x=FORM_RIGHT_X)
        final int IMG_X = FORM_RIGHT_X + 20; // add small gap
        final int IMG_Y = 80;
        final int IMG_W = 300;
        final int IMG_H = 300;

        // --- Try load image (classpath first, then src dev path) ---
        String resourcePath = "/com/airlinemanagement/assets/details.jpg"; // change filename if needed
        ImageIcon icon = null;

        try {
            URL res = getClass().getResource(resourcePath);
            if (res != null) {
                System.out.println("[AddCustomer] Found image on classpath: " + res);
                icon = new ImageIcon(res);
            } else {
                // Try development path (IDE)
                File f = new File("src" + resourcePath); // e.g. src/com/airlinemanagement/assets/details.jpg
                if (f.exists()) {
                    System.out.println("[AddCustomer] Found image in src path: " + f.getAbsolutePath());
                    icon = new ImageIcon(f.getAbsolutePath());
                } else {
                    System.out.println("[AddCustomer] Image not found. Looked for: " + resourcePath + " and src" + resourcePath);
                }
            }
        } catch (Exception e) {
            System.out.println("[AddCustomer] Exception while looking for image: " + e.getMessage());
            icon = null;
        }

        // If we found an image, create and add the scaled JLabel at the right; otherwise add a fallback panel there.
        if (icon != null) {
            Image scaled = icon.getImage().getScaledInstance(IMG_W, IMG_H, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaled));
            imageLabel.setBounds(IMG_X, IMG_Y, IMG_W, IMG_H);
            imageLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            base.add(imageLabel);
        } else {
            JPanel fallback = new JPanel(new BorderLayout());
            fallback.setBounds(IMG_X, IMG_Y, IMG_W, IMG_H);
            fallback.setBackground(Color.WHITE);
            fallback.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            JLabel welcome = new JLabel("<html><center>Customer<br/>Details</center></html>", SwingConstants.CENTER);
            welcome.setFont(new Font("SansSerif", Font.BOLD, 20));
            welcome.setForeground(Color.DARK_GRAY);
            fallback.add(welcome, BorderLayout.CENTER);

            base.add(fallback);
        }

        // --- Build the form on the left side (use `base` so components are above the right image) ---
        // Heading
        JLabel heading = new JLabel("Add Customer Details");
        heading.setBounds(220, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        base.add(heading);

        // Name
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 80, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        base.add(lblname);

        tfname = new JTextField();
        tfname.setBounds(220, 80, 240, 25); // wider so it doesn't butt up to the image
        base.add(tfname);

        // Nationality
        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 130, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        base.add(lblnationality);

        tfnationality = new JTextField();
        tfnationality.setBounds(220, 130, 240, 25);
        base.add(tfnationality);

        // Aadhar Number
        JLabel lblaadhar = new JLabel("Aadhar Number");
        lblaadhar.setBounds(60, 180, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        base.add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(220, 180, 240, 25);
        base.add(tfaadhar);

        // Address
        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        base.add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(220, 230, 240, 25);
        base.add(tfaddress);

        // Gender
        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        base.add(lblgender);

        ButtonGroup gendergroup = new ButtonGroup();

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(220, 280, 80, 25);
        rbmale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbmale.setActionCommand("Male");
        rbmale.setOpaque(false);
        base.add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(310, 280, 100, 25);
        rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbfemale.setActionCommand("Female");
        rbfemale.setOpaque(false);
        base.add(rbfemale);

        gendergroup.add(rbmale);
        gendergroup.add(rbfemale);

        // Phone
        JLabel lblphone = new JLabel("Phone");
        lblphone.setBounds(60, 330, 150, 25);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        base.add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(220, 330, 240, 25);
        base.add(tfphone);

        // SAVE button (no action yet)
        JButton save = new JButton("SAVE");
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.setBounds(220, 380, 150, 30);
        save.addActionListener(this);
        base.add(save);

        // CANCEL
        JButton cancel = new JButton("CANCEL");
        cancel.setBounds(390, 380, 100, 30);
        cancel.setBackground(Color.LIGHT_GRAY);
        cancel.setForeground(Color.BLACK);
        cancel.addActionListener(e -> dispose());
        base.add(cancel);

        // Finalize
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        String name = tfname.getText();
        String nationality = tfnationality.getText();
        String phone = tfphone.getText();
        String address = tfaddress.getText();
        String aadhar = tfaadhar.getText();
        String gender = null;
        if (rbmale.isSelected()) {
            gender = "Male";
        } else {
            gender = "Female";
        }
        try{
            DBConnection conn = new DBConnection();

            String query = "insert into passenger values('"+name+"', '"+nationality+"', '"+phone+"', '"+address+"', '"+aadhar+"', '"+gender+"')";

            conn.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");

            setVisible(false);
            dispose();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AddCustomer::new);
    }
}
