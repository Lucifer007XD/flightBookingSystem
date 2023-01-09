package bcu.cmp5332.bookingsystem.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import bcu.cmp5332.bookingsystem.commands.Command;
import bcu.cmp5332.bookingsystem.commands.CancelBooking;
import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

public class RemoveBookingWindow extends JFrame implements ActionListener{
	private MainWindow mw;
    private JTextField customerIdText = new JTextField();
    private JTextField flightIdText = new JTextField();
   
    private JButton delBtn = new JButton("Delete");
    private JButton cancelBtn = new JButton("Cancel");
    
    public RemoveBookingWindow(MainWindow mw) {
        this.mw = mw;
        initialize();
    }
    public void initialize() {
    	 try {
             UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
         } catch (Exception ex) {

         }

         setTitle("Delete a Existing Booking");

         setSize(350, 220);
         JPanel topPanel = new JPanel();
         topPanel.setLayout(new GridLayout(3, 2));
         topPanel.add(new JLabel("Customer Id : "));
         topPanel.add(customerIdText);
         topPanel.add(new JLabel("Flight Id : "));
         topPanel.add(flightIdText);
         
         JPanel bottomPanel = new JPanel();
         bottomPanel.setLayout(new GridLayout(1, 3));
         bottomPanel.add(new JLabel("     "));
         bottomPanel.add(delBtn);
         bottomPanel.add(cancelBtn);

         delBtn.addActionListener(this);
         cancelBtn.addActionListener(this);

         this.getContentPane().add(topPanel, BorderLayout.CENTER);
         this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
         setLocationRelativeTo(mw);

         setVisible(true);
    	
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delBtn) {
            delBook();
        } else if (ae.getSource() == cancelBtn) {
            this.setVisible(false);
        }

    }

    private void delBook() {
        try {
            int customerId = Integer.parseInt(customerIdText.getText());
            int flightId = Integer.parseInt(flightIdText.getText());
            
            Command removeBooking = new CancelBooking(customerId,flightId);
            removeBooking.execute(mw.getFlightBookingSystem());
            // refresh the view with the list of flights
                   
           
            // hide (close) the AddFlightWindow
            this.setVisible(false);
            JOptionPane.showMessageDialog(null,"Booking Removed","Success",JOptionPane.INFORMATION_MESSAGE);
            mw.displayCustomers();
            
        } catch (FlightBookingSystemException ex) {
            JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
