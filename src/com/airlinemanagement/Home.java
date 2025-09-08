package com.airlinemanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener; // <-- added
import java.io.File;
import java.net.URL;

public class Home extends JFrame implements ActionListener { // <-- added implements ActionListener

    public Home() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Try classpath resource first (packaged JAR)
        URL res = getClass().getResource("/com/airlinemanagement/assets/main.png");
        ImageIcon icon = null;

        if (res != null) {
            icon = new ImageIcon(res);
        } else {
            // Fallback to development path (IDE)
            File f = new File("src/com/airlinemanagement/assets/main.png");
            if (f.exists()) {
                icon = new ImageIcon(f.getAbsolutePath());
            }
        }

        if (icon != null) {
            // scale to screen size and set as content pane
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            Image scaled = icon.getImage().getScaledInstance(screen.width, screen.height, Image.SCALE_SMOOTH);
            JLabel background = new JLabel(new ImageIcon(scaled));
            setContentPane(background);
            background.setLayout(null); // keep absolute positioning if you want to add components later
        } else {
            // Fallback: white background with welcome text
            JPanel fallback = new JPanel(new BorderLayout());
            fallback.setBackground(Color.WHITE);

            JLabel welcome = new JLabel("Harsha's Airline Welcomes You", SwingConstants.CENTER);
            welcome.setFont(new Font("SansSerif", Font.BOLD, 36));
            welcome.setForeground(Color.BLACK);

            fallback.add(welcome, BorderLayout.CENTER);
            setContentPane(fallback);
        }

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu details = new JMenu("Details");
        menuBar.add(details);

        JMenuItem flightDetails = new JMenuItem("Flight Details");
        flightDetails.addActionListener(this);
        details.add(flightDetails);

        JMenuItem customerDetails = new JMenuItem("Customer Details");
        customerDetails.addActionListener(this);
        details.add(customerDetails);

        JMenuItem reservationDetails = new JMenuItem("Reservation Details");
        details.add(reservationDetails);

        JMenuItem bookFlight = new JMenuItem("Book Flight");
        details.add(bookFlight);

        JMenuItem journeyDetails = new JMenuItem("Journey Details");
        details.add(journeyDetails);

        JMenuItem ticketCancellation = new JMenuItem("Ticket Cancellation");
        details.add(ticketCancellation);

        JMenu ticket = new JMenu("Ticket");
        menuBar.add(ticket);

        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        ticket.add(boardingPass);

        // Frame setup
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screen);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae){
        String text = ae.getActionCommand();

        if ("Customer Details".equals(text)) {
            new AddCustomer();
        } else if ("Flight Details".equals(text)) {
            new FlightInfo();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Home::new);
    }
}
