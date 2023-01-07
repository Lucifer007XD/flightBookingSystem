package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;

import bcu.cmp5332.bookingsystem.model.*;

public class AddCustomer implements Command {

    private final String name;
    private final String phone;
    private final String email;

    public AddCustomer(String name, String phone,String email) {
        this.name = name;
        this.phone = phone;		
        this.email=email;
    }

    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        int maxId =0;
        if(flightBookingSystem.getCustomers().size()>0) {
        	int lastIndex=flightBookingSystem.getCustomers().size()-1;
        	maxId=flightBookingSystem.getCustomers().get(lastIndex).getId();
        }
        Customer customer=new Customer(++maxId,this.name,this.phone,this.email);
        flightBookingSystem.addCustomer(customer);
        System.out.println("Customer #"+customer.getId()+" added.");
        
    }
}
