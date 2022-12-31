package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;

import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

public class ShowCustomer implements Command {

	private int cusId;
	
	public ShowCustomer(int cusId) {
		this.cusId = cusId;
	}
	
	@Override
	public void execute(FlightBookingSystem flightBookingSystem)throws FlightBookingSystemException{
		 Customer customer = flightBookingSystem.getCustomerByID(cusId);
		 System.out.println(customer.getDetailsLong());
	}
}
