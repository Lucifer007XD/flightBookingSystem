package bcu.cmp5332.bookingsystem.gui;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bcu.cmp5332.bookingsystem.commands.EditBooking;
import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

public class UpdateBookingWindow extends JFrame implements ActionListener {

    private MainWindow mw;
    private JTextField flightIdText= new JTextField();
    private JTextField bookingIdText = new JTextField();
    

    private JButton updateBtn = new JButton("Add");
    private JButton cancelBtn = new JButton("Cancel");

    public UpdateBookingWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Add a New Flight");

        setSize(400, 300);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridLayout(4, 2));
        topPanel.add(new JLabel("Booking Id : "));
        topPanel.add(bookingIdText);
        topPanel.add(new JLabel("Flight Id : "));
        topPanel.add(flightIdText);
        
       
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(updateBtn);
        bottomPanel.add(cancelBtn);

        updateBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.getContentPane().add(topPanel, BorderLayout.CENTER);
        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
        setLocationRelativeTo(mw);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == updateBtn) {
            updateBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void updateBook() {
        try {
            int flightId = Integer.parseInt(flightIdText.getText());
            int bookingId = Integer.parseInt(bookingIdText.getText());
           
            
            // create and execute the AddFlight Command
            Command updateBooking = new EditBooking(bookingId,flightId);
            updateBooking.execute(mw.getFlightBookingSystem());
            // refresh the view with the list of flights
           
            // hide (close) the AddFlightWindow
            this.setVisible(false);
            JOptionPane.showMessageDialog(null,"Booking Updated","Success",JOptionPane.INFORMATION_MESSAGE);
            mw.displayCustomers();

        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
