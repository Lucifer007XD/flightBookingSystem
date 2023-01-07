package bcu.cmp5332.bookingsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;



class FlightTest {
	

	Flight flight = new Flight(1, "FL007", "London", "Leeds", LocalDate.parse("2022-12-24"), 270.0, 125);
	Customer customer = new Customer(1, "Vish ODEDRA", "9876543210", "vish@microsoft.com");
	
	
	//Testing FLIGHT class
	@Test
	public void testGetId() throws Exception {
		assertEquals(1, flight.getId());
	}
	
	@Test
	public void testSetId() throws Exception {
		flight.setId(2);
		assertEquals(2, flight.getId());
	}
	
	@Test
	public void testGetFlightNumber() throws Exception {
		assertEquals("FL007", flight.getFlightNumber());
	}
	
	@Test
	public void testSetFlightNumber() throws Exception {
		flight.setFlightNumber("ABC786");
		assertEquals("ABC786", flight.getFlightNumber());
	}
	
	@Test
	public void testGetOrigin() throws Exception {
		assertEquals("London", flight.getOrigin());
	}
	
	@Test
	public void testSetOrigin() throws Exception {
		flight.setOrigin("Birmingham");
		assertEquals("Birmingham", flight.getOrigin());
	}
	
	@Test
	public void testGetDestination() throws Exception {
		assertEquals("Leeds", flight.getDestination());
	}
	
	@Test
	public void testSetDestination() throws Exception {
		flight.setDestination("Barcelona");
		assertEquals("Barcelona", flight.getDestination());
	}
	
	@Test
	public void testGetDepartureDate() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse("2022-12-24", formatter);
		assertEquals(date, flight.getDepartureDate());
	}
	
	@Test
	public void testSetDepartureDate() throws Exception {
		flight.setDepartureDate(LocalDate.parse("2023-01-03"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.parse("2023-01-03", formatter);
		assertEquals(date, flight.getDepartureDate());
	}
	
	@Test
	public void testGetStatus() throws Exception {
		assertFalse(flight.getStatus());
	}
	
	@Test
	public void testSetStatus() throws Exception {
		flight.setStatus(true);
		assertTrue(flight.getStatus());
	}
	
	@Test
	public void testGetNoOfSeats() throws Exception {
		assertEquals(125, flight.getNoOfSeats());
	}
	
	@Test
	public void testSetNoOfSeats() throws Exception {
		flight.setNoOfSeats(150);
		assertEquals(150, flight.getNoOfSeats());
	}
	
	@Test
	public void testGetPrice() throws Exception {
    	assertEquals(270.0, flight.getPrice(), 1e-6);
	}
	
	@Test
	public void testSetPrice() throws Exception {
		flight.setPrice(300);
		assertEquals(300, flight.getPrice(), 1e-6);
	}
	
	@Test
	public void testGetPassanger() throws Exception {
		List<Customer> c1 = new ArrayList<>();
		flight.addPassenger(customer);
		c1.add(customer);
		assertEquals(c1, flight.getPassengers());
	}
	
	@Test
	public void testGetDetailsShort() throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		String shortDetails = "Flight #" + flight.getId() + " - " + flight.getFlightNumber() + " - " + flight.getOrigin() + " to " + flight.getDestination() + " on " + flight.getDepartureDate().format(formatter)+" - Price: $"+flight.getPrice();
		assertEquals(shortDetails, flight.getDetailsShort());
	}
	
	@Test
	public void testGetDetailsLong() throws Exception {
		int remSeats=flight.getNoOfSeats()-flight.getPassengers().size();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String longDetail = "Flight #"+flight.getId()+"\nFlight No.: "+flight.getFlightNumber()+"\nOrigin: "+flight.getOrigin()+"\nDestination: "+flight.getDestination()+"\nDeparture Date: "+flight.getDepartureDate().format(formatter) +"\nPrice: "+flight.getPrice()+"\n"+"Total No. of Seats: "+flight.getNoOfSeats()+"\n"+"No. of Seats Left: "+remSeats;
		String passengerDetails="";
        for(Customer p:flight.getPassengers()) {
        	passengerDetails=passengerDetails+"\n"+"* Id: "+p.getId()+" - "+p.getName()+" - "+p.getPhone();
        }
        String longDetails = longDetail+"\n"+"------------------------"+"\n"+"Passengers:"+passengerDetails;
		assertEquals(longDetails, flight.getDetailsLong());
	}
	
	@Test
	public void testAddPassenger() throws Exception {
		List<Customer> c1 = new ArrayList<>();
		flight.addPassenger(customer);
		c1.add(customer);
		assertEquals(c1, flight.getPassengers());
	}
	
	@Test
	public void testRemovePassenger() throws Exception {
		List<Customer> c1 = new ArrayList<>();
		flight.addPassenger(customer);
		flight.removePassenger(customer);
		assertEquals(c1, flight.getPassengers());
	}
}
