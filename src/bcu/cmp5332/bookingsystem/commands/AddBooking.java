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
		Customer customer=flightBookingSystem.getCustomerByID(customerId);
		Flight flight=flightBookingSystem.getFlightByID(flightId);
		Booking booking=new Booking(customer,flight,bookingDate);
		customer.addBooking(booking);
		flight.addPassenger(customer);
		System.out.println("Booking Issued Successsfully");
	}
	
	
	
	

}
