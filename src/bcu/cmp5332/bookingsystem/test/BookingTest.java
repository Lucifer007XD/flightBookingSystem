package bcu.cmp5332.bookingsystem.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import bcu.cmp5332.bookingsystem.model.Booking;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.Flight;

class BookingTest {

	Flight flight = new Flight(1, "FL007", "London", "Leeds", LocalDate.parse("2022-12-24"));
	Customer customer = new Customer(1, "Vish ODEDRA", "9876543210", "vish@microsoft.com");
	Booking booking = new Booking(1, customer, flight, LocalDate.parse("2023-01-05"));
	
	@Test
	public void testGetId() throws Exception {
		assertEquals(1, booking.getId());
	}
	
	@Test 
	public void testSetId() throws Exception {
		booking.setId(2);
		assertEquals(2, booking.getId());
	}
	
	@Test
	public void testGetCustomer() throws Exception {
		assertEquals(customer, booking.getCustomer());
	}

	@Test
	public void testGetFlight() throws Exception {
		assertEquals(flight, booking.getFlight());
	}
	
	@Test
	public void testGetBookingDate() throws Exception {
		assertEquals(LocalDate.parse("2023-01-05"), booking.getBookingDate());
	}
	
	@Test
	public void testSetCustomer() throws Exception {
		Customer customer2 = new Customer(2, "Vishalji ODEDRA", "999999", "vishalji@tesla.com");
		booking.setCustomer(customer2);
		assertEquals(customer2, booking.getCustomer());
	}

	@Test
	public void testSetFlight() throws Exception {
		Flight flight = new Flight(2, "XYZ111", "Manchester", "Barcelona", LocalDate.parse("2023-01-04"));
		booking.setFlight(flight);
		assertEquals(flight, booking.getFlight());
	}
	
	@Test
	public void testSetBookingDate() throws Exception {
		LocalDate date = LocalDate.parse("2022-12-31");
		booking.setBookingDate(date);
		assertEquals(LocalDate.parse("2022-12-31"), booking.getBookingDate());
	}
}
