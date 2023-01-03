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
    				int  cusID = Integer.parseInt(proper[0]);
    				String cusName = proper[1];
    				String cusPhone = proper[2];
    				String cusEmail = proper[3];
    				int flightId = Integer.parseInt(proper[4]);
    				String flightNum = proper[5];
    				String origin = proper[6];
    				String des = proper[7];
    				LocalDate depDate = LocalDate.parse(proper[8]);
    				Flight f1 = new Flight(flightId, flightNum, origin, des, depDate);
    				Customer c1 = new Customer(cusID, cusName, cusPhone, cusEmail);
    				LocalDate date = LocalDate.parse(proper[9]);
    				Booking b1 = new Booking(c1, f1, date);
    				c1.addBooking(b1);
    			} catch (NumberFormatException ex) {
    				throw new FlightBookingSystemException("Unable to parse book id " + proper[0] + " on line " + line_id
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
    			for(Booking b1: c1.getBooking()){
                    Customer customer=b1.getCustomer();
                    Flight flight=b1.getFlight();
                    out.print(customer.getId() + SEPARATOR);
    			    out.print(customer.getName() + SEPARATOR);
    			    out.print(customer.getPhone() + SEPARATOR);
    			    out.print(customer.getEmail() + SEPARATOR);
                    out.print(flight.getId()+SEPARATOR);
                    out.print(flight.getFlightNumber()+SEPARATOR);
                    out.print(flight.getOrigin()+SEPARATOR);
                    out.print(flight.getDestination()+SEPARATOR);
                    out.print(flight.getDepartureDate()+SEPARATOR);
                    out.print(b1.getBookingDate()+SEPARATOR);
    			    out.println();
    		}
    	}
    }
}
