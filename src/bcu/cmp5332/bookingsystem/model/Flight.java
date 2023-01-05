package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Flight {
    
    private int id;
    private String flightNumber;
    private String origin;
    private String destination;
    private LocalDate departureDate;

    private final Set<Customer> passengers;

    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        
        passengers = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }
    
    public String getOrigin() {
        return origin;
    }
    
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public List<Customer> getPassengers() {
        return new ArrayList<>(passengers);
    }
	
    public String getDetailsShort() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return "Flight #" + id + " - " + flightNumber + " - " + origin + " to " 
                + destination + " on " + departureDate.format(dtf);
    }

    public String getDetailsLong() {
        String flightDetails="Flight #"+this.id+"\n"+
    "Flight No.: "+this.flightNumber+"\n"+
        		"Origin: "+this.origin+"\n"+
    "Destination: "+this.destination+"\n"+
        		"Departure Date: "+this.departureDate;
        String passengerDetails="";
        for(Customer p:passengers) {
        	passengerDetails=passengerDetails+"\n"+"* Id: "+p.getId()+" - "+p.getName()+" - "+p.getPhone();
        }
        return flightDetails+"\n"+"------------------------"+"\n"+"Passengers:"+passengerDetails;
        		
    }
    
    public void addPassenger(Customer passenger)throws FlightBookingSystemException {
        for(Customer p:passengers) {
        	if(p==passenger) {
        		throw new FlightBookingSystemException("Passenger Already Exists");
        	}
        		
        	
        }
        passengers.add(passenger);
        
    }
    public void removePassenger(Customer passenger)throws FlightBookingSystemException{
    	int n=0;
    	for (Customer p:passengers) {
    		if(p==passenger) {
    			n+=1;
    			passengers.remove(p);
			break;
    		}
    	}
    	if(n<=0) {
    		throw new FlightBookingSystemException("passenger not Found");
    	}
    }
}
