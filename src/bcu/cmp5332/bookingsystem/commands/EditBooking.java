package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;


import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.*;

public class EditBooking implements Command {
	private int bookingId;
	private int flightId;
	private LocalDate today=LocalDate.now();
	
	public EditBooking(int bookingId,int flightId) {
		this.bookingId=bookingId;
		this.flightId=flightId;	
		
		
	}
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem)throws FlightBookingSystemException{
		int oldFlightId=0;
		int customerId = flightBookingSystem.getCustomerByBookingId(bookingId);
		Customer customer=flightBookingSystem.getCustomerByID(customerId);
		Flight newFlight=flightBookingSystem.getFlightByID(flightId);
		
		if (newFlight.getDepartureDate().isAfter(today)||newFlight.getDepartureDate().isEqual(today)) {
		
		for(Booking b:customer.getBookings()){
			if(b.getId()==bookingId){
				oldFlightId=b.getFlight().getId();
				b.setFlight(newFlight);
				b.setBookingDate(today);
				break;
			}

		}
		Flight oldFlight=flightBookingSystem.getFlightByID(oldFlightId);
		oldFlight.removePassenger(customer);
		
		newFlight.addPassenger(customer);
		
		
		System.out.println("Booking Updated Successsfully");
		}else {
			throw new FlightBookingSystemException("Flight Already Departed");
		}
	}
	
}
