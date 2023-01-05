package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class EditBooking implements Command {

	private int customerId;
	private int flightId;
	private int newFlightId ;
	
	public EditBooking(int customerId,int flightId, int newFlightId ) {
		this.customerId=customerId;
		this.flightId=flightId;	
		this.newFlightId = newFlightId ;
		
	}
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem)throws FlightBookingSystemException{
		customerId = flightBookingSystem.getCustomerByBookingId(bookingId);
		
		System.out.println("Booking Updated Successsfully");
	}
	
}
