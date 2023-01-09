package bcu.cmp5332.bookingsystem.gui;

import bcu.cmp5332.bookingsystem.data.FlightBookingSystemData;
import bcu.cmp5332.bookingsystem.model.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;
import javax.swing.table.TableColumn;


public class MainWindow extends JFrame implements ActionListener {

    private JMenuBar menuBar;
    
    private JMenu adminMenu;
    private JMenu flightsMenu;
    private JMenu bookingsMenu;
    private JMenu customersMenu;

    private JMenuItem adminExit;

    private JMenuItem flightsView;
    private JMenuItem flightsAdd;
    private JMenuItem flightsDel;
    
    private JMenuItem bookingsIssue;
    private JMenuItem bookingsUpdate;
    private JMenuItem bookingsCancel;

    private JMenuItem custView;
    private JMenuItem custAdd;
    private JMenuItem custDel;
    private JButton showPassengersBtn = new JButton("Display Passengers");
    private JButton showBookingsBtn = new JButton("Display Bookings");

    private FlightBookingSystem fbs;

    public MainWindow(FlightBookingSystem fbs) {

        initialize();
        this.fbs = fbs;
    }
    
    public FlightBookingSystem getFlightBookingSystem() {
        return fbs;
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        setTitle("Flight Booking Management System");

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //adding adminMenu menu and menu items
        adminMenu = new JMenu("Admin");
        menuBar.add(adminMenu);

        adminExit = new JMenuItem("Exit");
        adminMenu.add(adminExit);
        adminExit.addActionListener(this);

        // adding Flights menu and menu items
        flightsMenu = new JMenu("Flights");
        menuBar.add(flightsMenu);

        flightsView = new JMenuItem("View");
        flightsAdd = new JMenuItem("Add");
        flightsDel = new JMenuItem("Delete");
        flightsMenu.add(flightsView);
        flightsMenu.add(flightsAdd);
        flightsMenu.add(flightsDel);
        // adding action listener for Flights menu items
        for (int i = 0; i < flightsMenu.getItemCount(); i++) {
            flightsMenu.getItem(i).addActionListener(this);
        }
        // adding Bookings menu and menu items
        bookingsMenu = new JMenu("Bookings");
        menuBar.add(bookingsMenu);
        bookingsIssue = new JMenuItem("Issue");
        bookingsUpdate = new JMenuItem("Update");
        bookingsCancel = new JMenuItem("Cancel");
        bookingsMenu.add(bookingsIssue);
        bookingsMenu.add(bookingsUpdate);
        bookingsMenu.add(bookingsCancel);
        
        bookingsIssue.addActionListener(this);
        bookingsUpdate.addActionListener(this);
        bookingsCancel.addActionListener(this);
        // adding action listener for Bookings menu items
        for (int i = 0; i < bookingsMenu.getItemCount(); i++) {
            bookingsMenu.getItem(i).addActionListener(this);
        }

        // adding Customers menu and menu items
        customersMenu = new JMenu("Customers");
        menuBar.add(customersMenu);

        custView = new JMenuItem("View");
        custAdd = new JMenuItem("Add");
        custDel = new JMenuItem("Delete");

        customersMenu.add(custView);
        customersMenu.add(custAdd);
        customersMenu.add(custDel);
        // adding action listener for Customers menu items
        custView.addActionListener(this);
        custAdd.addActionListener(this);
        custDel.addActionListener(this);

        setSize(800, 500);

        setVisible(true);
        setAutoRequestFocus(true);
        toFront();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
/* Uncomment the following line to not terminate the console app when the window is closed */
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);        

    }	

/* Uncomment the following code to run the GUI version directly from the IDE */
//    public static void main(String[] args) throws IOException, FlightBookingSystemException {
//        FlightBookingSystem fbs = FlightBookingSystemData.load();
//        new MainWindow(fbs);			
//    }



    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == adminExit) {
            try {
                FlightBookingSystemData.store(fbs);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
            System.exit(0);
        } else if (ae.getSource() == flightsView) {
            displayFlights();
            
        } else if (ae.getSource() == flightsAdd) {
            new AddFlightWindow(this);
            
        } else if (ae.getSource() == flightsDel) {
            new RemoveFlightWindow(this);
            
        } else if (ae.getSource() == bookingsIssue) {
            new AddBookingWindow(this);
            
        }else if(ae.getSource()==bookingsUpdate) {
        	new UpdateBookingWindow(this);
        	
        }else if (ae.getSource() == bookingsCancel) {
        	new RemoveBookingWindow(this);
            
        } else if (ae.getSource() == custView) {
            displayCustomers();
            
        } else if (ae.getSource() == custAdd) {
           new AddCustomerWindow(this); 
            
        } else if (ae.getSource() == custDel) {
           new RemoveCustomerWindow(this); 
            
        }else if (ae.getSource()==showPassengersBtn) {
        	new ShowPassengersWindow(this);
        	
        }else if(ae.getSource()==showBookingsBtn) {
        	new ShowBookingWindow(this);
        }
    }

    public void displayFlights() {
        List<Flight> flightsList = fbs.getFlights();
        // headers for the table
        String[] columns = new String[]{"ID","Flight No", "Origin", "Destination", "Departure Date"};
        Object[][] data = new Object[flightsList.size()][6];
   
        for (int i = 0; i < flightsList.size(); i++) {
            Flight flight = flightsList.get(i);
            if(flight.getStatus()==false) {
                data[i][0]=flight.getId();
            	data[i][1] = flight.getFlightNumber();
                data[i][2] = flight.getOrigin();
                data[i][3] = flight.getDestination();
                data[i][4] = flight.getDepartureDate();
          
               
               
                ;
                
               }
            
        }
       
        JTable table = new JTable(data, columns);
        
        
            

        
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(this.showPassengersBtn);

        showPassengersBtn.addActionListener(this);

        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
            this.revalidate();
    }	
    public void displayCustomers() {
        List<Customer> customersList = fbs.getCustomers();
        // headers for the table
        String[] columns = new String[]{"ID","Name", "Phone", "Email","Bookings"};

        Object[][] data = new Object[customersList.size()][6];
        for (int i = 0; i < customersList.size(); i++) {
            Customer customer = customersList.get(i);
           if(customer.getStatus()==false) {
               data[i][0]=customer.getId();
        	   data[i][1] = customer.getName();
               data[i][2] = customer.getPhone();
               data[i][3] = customer.getEmail();
               data[i][4]=showBookingsBtn;
               
               
               String id="";
               id=id+customer.getId();
               showBookingsBtn.setActionCommand(id);
           }
         
        }

        JTable table = new JTable(data, columns);
        this.getContentPane().removeAll();
        this.getContentPane().add(new JScrollPane(table));
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new GridLayout(1, 3));
        bottomPanel.add(new JLabel("     "));
        bottomPanel.add(this.showBookingsBtn);

        showBookingsBtn.addActionListener(this);

        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);


        
        
        this.revalidate();
    }
    public void displayBookings(int id) {
    	List<Customer> customersList=fbs.getCustomers();
    	String[] columns=new String[] {"Booking Id","Flight Number","Booking Date"};
    	Object[][] data=null ;
    	 for (int i = 0; i < customersList.size(); i++) {
             Customer customer = customersList.get(i);
            if(customer.getId()==id) {
         	   List<Booking> bookingList=customer.getBookings();
         	 data =new Object[bookingList.size()][6];
         		for (int j=0;j<bookingList.size();j++) {
         			Booking booking=bookingList.get(j);
         			data[j][0]=booking.getId();
         			data[j][1]=booking.getFlight().getFlightNumber();
         			data[j][2]=booking.getBookingDate();
         			
         		}
         	   
         	   }
          
         }
    	 JTable table = new JTable(data, columns);
         this.getContentPane().removeAll();
         this.getContentPane().add(new JScrollPane(table));
         this.revalidate();
    	 
    	
    	
    }
    public void displayPassengers(int id) {
    	 List<Flight> flightsList = fbs.getFlights();
         // headers for the table
         String[] columns = new String[]{"ID","Name", "Phone", "Email"};

         Object[][] data=null;
         for (int i = 0; i < flightsList.size(); i++) {
             Flight flight = flightsList.get(i);
             if(flight.getId()==id) {
            	 List<Customer> passengersList=flight.getPassengers();
             	 data =new Object[passengersList.size()][6];
             		for (int j=0;j<passengersList.size();j++) {
             			Customer customer=passengersList.get(j);
             			data[j][0]=customer.getId();
             			data[j][1]=customer.getName();
             			data[j][2]=customer.getPhone();
             			data[j][3]=customer.getEmail();
             		}
             		
             			
             }
             
         }

         JTable table = new JTable(data, columns);

         
         this.getContentPane().removeAll();
         this.getContentPane().add(new JScrollPane(table));
         this.revalidate();
    	
    }
    
   
    
}
