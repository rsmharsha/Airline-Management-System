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

        JLabel lblusername = new JLabel("Username:");
        lblusername.setBounds(20, 20, 100, 20);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(130, 20, 200, 20);
        tfusername.setToolTipText("Enter your username");
        add(tfusername);

        JLabel lblpassword = new JLabel("Password:");
        lblpassword.setBounds(20, 60, 100, 20);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(130, 60, 200, 20);
        tfpassword.setToolTipText("Enter your password");
        add(tfpassword);

        reset = new JButton("Reset");
        reset.setBounds(40, 120, 120, 25);
        reset.addActionListener(this);
        add(reset);

        submit = new JButton("Login");
        submit.setBounds(190, 120, 120, 25);
        submit.addActionListener(this);
        add(submit);

        close = new JButton("Exit");
        close.setBounds(120, 160, 120, 25);
        close.addActionListener(this);
        add(close);

        setSize(400, 250);
        setLocation(600, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == "submit"){
            
        } else if (ae.getSource() == close) {
            setVisible(false);
        } else if (ae.getSource() == reset) {
            tfusername.setText("");
            tfpassword.setText("");
        }
    }
    public static void main(String[] args){
        new Login();
    }
}
