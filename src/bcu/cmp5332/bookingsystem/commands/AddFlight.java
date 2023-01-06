package bcu.cmp5332.bookingsystem.commands;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;
import java.time.LocalDate;
import java.time.Period;
public class AddFlight implements  Command {

    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final LocalDate departureDate;
    private double price;
    private final int capacity;

    public AddFlight(String flightNumber, String origin, String destination, LocalDate departureDate,double price,int capacity) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.price=price;
        this.capacity=capacity;
    }
    
    @Override
    public void execute(FlightBookingSystem flightBookingSystem) throws FlightBookingSystemException {
        int maxId = 0;
        if (flightBookingSystem.getFlights().size() > 0) {
            int lastIndex = flightBookingSystem.getFlights().size() - 1;
            maxId = flightBookingSystem.getFlights().get(lastIndex).getId();
        }
        LocalDate today=LocalDate.now();
        Period diff=Period.between(today, this.departureDate);
        if(diff.getDays()==3) {
        	this.price=this.price+(0.03*this.price);
        }else if(diff.getDays()==2) {
        	this.price=this.price+(0.05*this.price);
        }else if(diff.getDays()==1) {
        	this.price=this.price+(0.1*this.price);
        }else if(diff.getDays()==0) {
        	this.price=this.price+(0.15*this.price);
        }
        
        Flight flight = new Flight(++maxId, flightNumber, origin, destination, departureDate,price,capacity);
        flightBookingSystem.addFlight(flight);
        System.out.println("Flight #" + flight.getId() + " added.");
    }
}
