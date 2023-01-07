package bcu.cmp5332.bookingsystem.commands;
import bcu.cmp5332.bookingsystem.model.*;

import bcu.cmp5332.bookingsystem.main.*;
import java.time.LocalDate;

public class AddBooking implements Command{
	private int customerId;
	private int flightId;
	private LocalDate bookingDate;

	
	public AddBooking(int customerId,int flightId,LocalDate bookingDate) {
		this.customerId=customerId;
		this.flightId=flightId;
		this.bookingDate=bookingDate;
		
	}
	@Override
	public void execute(FlightBookingSystem flightBookingSystem)throws FlightBookingSystemException{
		int maxId = 0;
		for (Customer c : flightBookingSystem.getCustomers()) {
		    if (c.getBookings().size() > 0) {
		    	maxId = maxId + c.getBookings().size();
		    }
		}
		Customer customer=flightBookingSystem.getCustomerByID(customerId);
		Flight flight=flightBookingSystem.getFlightByID(flightId);
		Booking booking=new Booking(++maxId,customer,flight,bookingDate);
		
	
		customer.addBooking(booking);
		flight.addPassenger(customer);
		System.out.println("Booking Issued Successsfully");
	}

}
