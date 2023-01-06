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
    private double price;
    private int capacity;
    private boolean hidden=false;

    private final Set<Customer> passengers;

    public Flight(int id, String flightNumber, String origin, String destination, LocalDate departureDate, double price,int capacity) {
        this.id = id;
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
       
        this.capacity=capacity;
        
        passengers = new HashSet<>();
    }
    public boolean getStatus() {
    	return this.hidden;
    }
    public  void setStatus(boolean b) {
    	this.hidden=b;
    	
    }
    public int getNoOfSeats(){
    	return this.capacity;
    }
    public void setNoOfSeats(int cap) {
    	this.capacity=cap;
    }
    public double getPrice() {
    	int leftSeats=this.capacity-passengers.size();
    	if(leftSeats<=(0.05*this.capacity)) {
    		this.price=this.price+(0.1*this.price);
    	}else if(leftSeats<=(0.1*this.capacity)) {
    		this.price=this.price+(0.06*this.price);
    	}else if(leftSeats<=(0.2*this.capacity)) {
    		this.price=this.price+(0.02*this.price);
    		}
    	return this.price;
    }
    public void setPrice(double price) {
    	this.price=price;
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
                + destination + " on " + departureDate.format(dtf)+" - Price: $"+this.getPrice();
    }
   
    public String getDetailsLong() {
    	int leftSeats=this.capacity-this.passengers.size();
    	
        String flightDetails="Flight #"+this.id+"\n"+
    "Flight No.: "+this.flightNumber+"\n"+
        		"Origin: "+this.origin+"\n"+
    "Destination: "+this.destination+"\n"+
        		"Departure Date: "+this.departureDate+"\n"+
    "Price: "+this.getPrice()+"\n"+
        		"Total No. of Seats: "+this.capacity+"\n"+
    "No. of Seats Left: "+leftSeats;
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
        if(passengers.size()>=this.capacity) {
        	 throw new FlightBookingSystemException("Seats Not Available Flight is Full");
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
