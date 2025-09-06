package com.airlinemanagement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame implements ActionListener {
    JButton reset, submit, close;
    JTextField tfusername;
    JPasswordField tfpassword;

    public Login(){
        setTitle("Airline Management - Login");
        getContentPane().setBackground(Color.white);
        setLayout(null);

        // Username row
        JLabel lblusername = new JLabel("Username :");
        lblusername.setBounds(110, 40, 100, 25);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(180, 40, 150, 25);
        tfusername.setToolTipText("Enter your username");
        add(tfusername);

        // Password row
        JLabel lblpassword = new JLabel("Password :");
        lblpassword.setBounds(110, 80, 100, 25);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(180, 80, 150, 25);
        tfpassword.setToolTipText("Enter your password");
        add(tfpassword);

        // Buttons
        reset = new JButton("Reset");
        reset.setBounds(50, 140, 100, 30);
        reset.addActionListener(this);
        add(reset);

        submit = new JButton("Login");
        submit.setBounds(155, 140, 100, 30);
        submit.addActionListener(this);
        add(submit);

        close = new JButton("Exit");
        close.setBounds(260, 140, 100, 30);
        close.addActionListener(this);
        add(close);

        // Frame setup
        setSize(420, 250);
        setLocation(600, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == submit){
            // Here you can add login validation later
            JOptionPane.showMessageDialog(this, "Login button clicked!");
        } else if (ae.getSource() == close) {
            setVisible(false);
            dispose(); // better cleanup
        } else if (ae.getSource() == reset) {
            tfusername.setText("");
            tfpassword.setText("");
        }
    }

    public static void main(String[] args){
        new Login();
    }
}
