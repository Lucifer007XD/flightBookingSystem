package bcu.cmp5332.bookingsystem.commands;

import java.time.LocalDate;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class RemoveCustomer implements Command{
	
	private int customerId;
	
	public RemoveCustomer(int customerId) {
		this.customerId=customerId;
	
		
	}

	@Override
	public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
		// TODO Auto-generated method stub
		Customer customer = flightBookingSystem.getCustomerByID(customerId);
		customer.setStatus(true);
		System.out.println("Customer removed Successsfully.");
	}
}
