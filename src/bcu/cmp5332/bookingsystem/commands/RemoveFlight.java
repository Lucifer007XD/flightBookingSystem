package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class RemoveFlight implements Command{
	private int flightId;
	public RemoveFlight(int flightId) {
		this.flightId=flightId;
		
	}
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem)throws FlightBookingSystemException{
		Flight flight=flightBookingSystem.getFlightByID(flightId);
		flight.setStatus(true);
		
		System.out.println("Flight Removed Successsfully");
	}


}
