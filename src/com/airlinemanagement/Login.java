package com.airlinemanagement;
import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    public Login(){
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(20, 20, 100, 20);
        add(lblusername);

        JTextField tfusername = new JTextField();
        tfusername.setBounds(130, 20, 200, 20);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(20, 60, 100, 20);
        add(lblpassword);

        JPasswordField tfpassword = new JPasswordField();
        tfpassword.setBounds(130, 20, 200, 20);
        add(tfpassword);

        setSize(400, 250);
        setLocation(600, 250);
        setVisible(true);
    }
    public static void main(String[] args){
        new Login();
    }
}
