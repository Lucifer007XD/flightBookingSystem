package bcu.cmp5332.bookingsystem.commands;


import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class CancelBooking implements Command {
	private int customerId;
	private int flightId;
	private LocalDate today=LocalDate.now();
	
	public CancelBooking(int customerId,int flightId) {
		this.customerId=customerId;
		this.flightId=flightId;
		
	}
	@Override
	public void execute(FlightBookingSystem flightBookingSystem)throws FlightBookingSystemException{
		Customer customer=flightBookingSystem.getCustomerByID(customerId);
		Flight flight=flightBookingSystem.getFlightByID(flightId);
		if (flight.getDepartureDate().isAfter(today)||flight.getDepartureDate().isEqual(today)) {
		flight.removePassenger(customer);
		customer.cancelBookingForFlight(flight);
		System.out.println("Booking Cancelled Successsfully");
		}else {
			throw new FlightBookingSystemException("Flight already Departed");
		}
		
	}
	

}
