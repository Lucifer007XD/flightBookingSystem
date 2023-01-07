package bcu.cmp5332.bookingsystem.model;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import java.time.LocalDate;
import java.util.*;

public class FlightBookingSystem {
    
    private final LocalDate systemDate = LocalDate.parse("2020-11-11");
    
    private final Map<Integer, Customer> customers = new TreeMap<>();
    private final Map<Integer, Flight> flights = new TreeMap<>();

    public LocalDate getSystemDate() {
        return systemDate;
    }

    public List<Flight> getFlights() {
        List<Flight> out = new ArrayList<>(flights.values());
        return Collections.unmodifiableList(out);
    }
    public List<Customer> getCustomers(){
    	List<Customer> out=new ArrayList<>(customers.values());
    	return Collections.unmodifiableList(out);
    }

    public Flight getFlightByID(int id) throws FlightBookingSystemException {
        if (!flights.containsKey(id)) {
            throw new FlightBookingSystemException("There is no flight with that ID.");
        }
        return flights.get(id);
    }

    public Customer getCustomerByID(int id) throws FlightBookingSystemException {
        if (!customers.containsKey(id)) {
        	throw new FlightBookingSystemException("There is no Passenger With that ID.");
        }
        return customers.get(id);
    }
    public int getCustomerByBookingId(int bookingId) throws FlightBookingSystemException {
    	List<Customer> customer = new ArrayList<>(customers.values());
    	int custId = 0;
		for (Customer c : customer) {
			for (Booking b : c.getBookings()) {
				if (b.getId() == bookingId) {
					custId = c.getId();
					return custId;
				}
			}
		}
		throw new FlightBookingSystemException("Invalid Booking ID.");
    }

    public void addFlight(Flight flight) throws FlightBookingSystemException {
        if (flights.containsKey(flight.getId())) {
            throw new IllegalArgumentException("Duplicate flight ID.");
        }
        for (Flight existing : flights.values()) {
            if (existing.getFlightNumber().equals(flight.getFlightNumber()) 
                && existing.getDepartureDate().isEqual(flight.getDepartureDate())) {
                throw new FlightBookingSystemException("There is a flight with same "
                        + "number and departure date in the system");
            }
        }
        flights.put(flight.getId(), flight);
    }

    public void addCustomer(Customer customer)throws IllegalArgumentException,FlightBookingSystemException {
        if(customers.containsKey(customer.getId())) {
        	throw new IllegalArgumentException("Duplicate Passenger ID.");
        	
        }
        for(Customer existing: customers.values()) {
        	if(existing.getEmail().equals(customer.getEmail())||existing.getPhone().equals(customer.getPhone())) {
        		throw new FlightBookingSystemException("There is a Passenger with same Email or Phone Number in the System.");
        	}
        }
        customers.put(customer.getId(), customer);
        }
    
        
}
