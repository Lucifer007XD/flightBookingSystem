package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class BookingDataManager implements DataManager {
    
    public final String RESOURCE = "./resources/data/bookings.txt";

    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        // TODO: implementation here
    	try (Scanner sc1 = new Scanner(new File(RESOURCE))){
    		int line_id = 1;
    		while (sc1.hasNextLine()) {
    			String line = sc1.nextLine();
    			String[] proper = line.split(SEPARATOR, -1);
    			try {
    				int  bookingID = Integer.parseInt(proper[0]);
    				int  cusID = Integer.parseInt(proper[1]);
    				int flightId = Integer.parseInt(proper[2]);
    				Flight f1 = fbs.getFlightByID(flightId);
    				Customer c1 =fbs.getCustomerByID(cusID);
    				LocalDate date = LocalDate.parse(proper[3]);
    				Booking b1 = new Booking(bookingID, c1, f1, date);
    				c1.addBooking(b1);
					f1.addPassenger(c1);
    			} catch (NumberFormatException ex) {
    				throw new FlightBookingSystemException("Unable to parse customer id " + proper[0] + " on line " + line_id
                            + "\nError: " + ex);
        		}
    			line_id++;
    		}
    	} 
    }

    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        // TODO: implementation here
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))){
    		for (Customer c1: fbs.getCustomer()) {
    			for(Booking b1: c1.getBookings()){
                    Customer customer=b1.getCustomer();
                    Flight flight=b1.getFlight();
                    out.print(b1.getId() + SEPARATOR);
                    out.print(customer.getId() + SEPARATOR);
                    out.print(flight.getId()+SEPARATOR);
                    out.print(b1.getBookingDate()+SEPARATOR);
    			    out.println();
				}
    		}
    	}
    }
}

