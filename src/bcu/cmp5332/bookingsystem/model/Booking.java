package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
    

    public Booking(Customer customer, Flight flight, LocalDate bookingDate) {
        // TODO: implementation here
    	this.customer=customer;
    	this.flight=flight;
    	this.bookingDate=bookingDate;
        
    }
    public void setCustomer(Customer cst) {
    	this.customer=cst;
    	
    }
    public void setFlight(Flight flg) {
    	this.flight=flg;
    }
    public void setBookingDate(LocalDate bkdt) {
    	this.bookingDate=bkdt;
    	
    }
    
    public Customer getCustomer() {
    	return this.customer;
    }
    public Flight getFlight() {
    	return this.flight;
    }
    public LocalDate getBookingDate() {
    	return this.bookingDate;
    }
    
        
}
