package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import bcu.cmp5332.bookingsystem.model.*;

public class AddCustomer implements Command {

    private final String name;
    private final String phone;

    public AddCustomer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        int maxId =0;
        if(flightBookingSystem.getCustomer().size()>0) {
        	int lastIndex=flightBookingSystem.getCustomer().size()-1;
        	maxId=flightBookingSystem.getCustomer().get(lastIndex).getId();
        }
        Customer customer=new Customer(++maxId,this.name,this.phone);
        flightBookingSystem.addCustomer(customer);
        System.out.println("Customer #"+customer.getId()+" added.");
        
    }
}
