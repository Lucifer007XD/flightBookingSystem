package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Customer {
    
    private int id;
    private String name;
    private String phone;
    private String email; 
    private final List<Booking> bookings = new ArrayList<>();
    
    // TODO: implement constructor here
    public Customer(int id,String name,String phone,String email) {
    	this.id=id;
    	this.name=name;
    	this.phone=phone;
    	this.email=email;
    }
    
    public void setName(String nm) {
    	this.name=nm;
    }
    public void setPhone(String phn) {
    	this.phone=phn;
    }
    
    public void setEmail(String em) {
    	this.email=em;
    }
    
    public int getId() {
    	return this.id;
    }
    public String getName() {
    	return this.name;
    }
    public String getPhone() {
    	return this.phone;
    }
    public String getEmail() {
    	return this.email;
    }
    public List<Booking> getBookings() {
    	return Collections.unmodifiableList(bookings);
    }
    public String getDetailsShort() {
    	return "Customer #"+this.id+" - Name: "+this.name+"- Phone: "+this.phone+"- Email: "+this.email;
    }
    public String getDetailsLong() {
    	String personalDetails="Customer #"+this.id+"\n"+"Name: "+this.name+"\n"+"Phone: "+this.phone+"\n"+"Email: "+this.email;
    	String bookingDetails="";
    	int numOfBookings=0;
    	for (Booking x:bookings) {
    		bookingDetails=bookingDetails+"\n"+"* Booking date: "+x.getBookingDate()+" for Flight #"+x.getFlight().getId()+" - "+x.getFlight().getFlightNumber()+" - "+x.getFlight().getOrigin()+" to "+x.getFlight().getDestination()+" on "+x.getFlight().getDepartureDate();
    		numOfBookings+=1;
    	
    	}
    	return personalDetails+"\n"+"------------------------"+"\n"+"Bookings:\n"+bookingDetails+"\n"+numOfBookings+" booking(s)";
    	
    	
    }
   
    
    // TODO: implementation of Getter and Setter methods
    
    public void addBooking(Booking booking)throws FlightBookingSystemException {
        for (Booking bk:bookings) {
        	if(bk==booking) {
        		throw new FlightBookingSystemException("Booking Exists");
        		
        	}
        }
        bookings.add(booking);
    	
    }
    public void cancelBookingForFlight(Flight flight)throws FlightBookingSystemException {
    	int n=0;
    	for(int i=0;i<bookings.size();i++) {
    		if (bookings.get(i).getFlight()==flight) {
    			bookings.remove(i);
    			n+=1;
   
    		}
    	}
    	if (n<=0) {
    		throw new FlightBookingSystemException("flight Not Found");
    	}
    
    }
}
