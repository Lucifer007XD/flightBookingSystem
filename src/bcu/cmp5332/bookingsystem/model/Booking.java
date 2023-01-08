package bcu.cmp5332.bookingsystem.model;

import java.time.LocalDate;

public class Booking {
    private int bookingId;
    private Customer customer;
    private Flight flight;
    private LocalDate bookingDate;
    

    public Booking(int bookingId,Customer customer, Flight flight, LocalDate bookingDate) {
        // TODO: implementation here
        this.bookingId=bookingId;
    	this.customer=customer;
    	this.flight=flight;
    	this.bookingDate=bookingDate;
        
    }
    public void setId(int id){
        this.bookingId=id;
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
    public int getId(){
        return this.bookingId;
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
