package bcu.cmp5332.bookingsystem.main;

import bcu.cmp5332.bookingsystem.commands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CommandParser {
    
    public static Command parse(String line) throws IOException, FlightBookingSystemException {
        try {
        	LocalDate today=LocalDate.now();
            String[] parts = line.split(" ", 3);
            String cmd = parts[0];

            
            if (cmd.equals("addflight")) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Flight Number: ");
                String flighNumber = reader.readLine();
                flighNumber=flighNumber.toUpperCase();
                System.out.print("Origin: ");
                String origin = reader.readLine();
                origin=origin.toLowerCase();
                System.out.print("Destination: ");
                String destination = reader.readLine();
                destination=destination.toLowerCase();

                LocalDate departureDate = parseDateWithAttempts(reader);
                System.out.print("Price: ");
                double price =Double.parseDouble( reader.readLine());
                
                System.out.print("Number of Seats: ");
                int capacity =Integer.parseInt( reader.readLine());
              
                return new AddFlight(flighNumber, origin, destination, departureDate,price,capacity);
            } else if (cmd.equals("addcustomer")) {
            	BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
            	System.out.print("Customer Name: ");
            	String customerName=reader.readLine();
            	customerName=customerName.toUpperCase();
            	System.out.print("Phone Number: ");
            	String customerPhone=reader.readLine();
            	System.out.print("Email(Example@mail.com): ");
            	String customerEmail=reader.readLine();
            	
            	return new AddCustomer(customerName,customerPhone,customerEmail);
            	
            	
                
            } else if (cmd.equals("loadgui")) {
                return new LoadGUI();
            } else if (parts.length == 1) {
                if (line.equals("listflights")) {
                    return new ListFlights();
                } else if (line.equals("listcustomers")) {
                    return new ListCustomers();
                } else if (line.equals("help")) {
                    return new Help();
                }
            } else if (parts.length == 2) {
                int id = Integer.parseInt(parts[1]);

                if (cmd.equals("showflight")) {
                	return new ShowFlight(id);
                } else if (cmd.equals("showcustomer")) {
                	return new ShowCustomer(id);
                }else if(cmd.equals("removeflight")) {
                	return new RemoveFlight(id);
                }else if(cmd.equals("removecustomer")) {
                	return new RemoveCustomer(id);
                }
            } else if (parts.length == 3) {
                

                if (cmd.equals("addbooking")) {
                	int customerId=Integer.parseInt(parts[1]);
                	int flightId=Integer.parseInt(parts[2]);
                	LocalDate bookingDate=LocalDate.now();
                	return new AddBooking(customerId,flightId,bookingDate);
                	
                	
                    
                } else if (cmd.equals("editbooking")) {
                	int bookingId=Integer.parseInt(parts[1]);
                	int flighId=Integer.parseInt(parts[2]);
                	return new EditBooking(bookingId, flighId);
                } else if (cmd.equals("cancelbooking")) {
                	int cusId=Integer.parseInt(parts[1]);
                	int fliId=Integer.parseInt(parts[2]);
                	return new CancelBooking(cusId, fliId); 
                }
            }
        } catch (NumberFormatException ex) {

        }

        throw new FlightBookingSystemException("Invalid command.");
    }
    
    private static LocalDate parseDateWithAttempts(BufferedReader br, int attempts) throws IOException, FlightBookingSystemException {
        if (attempts < 1) {
            throw new IllegalArgumentException("Number of attempts should be higher that 0");
        }
        while (attempts > 0) {
            attempts--;
            System.out.print("Departure Date (\"YYYY-MM-DD\" format): ");
            try {
                LocalDate departureDate = LocalDate.parse(br.readLine());
                return departureDate;
            } catch (DateTimeParseException dtpe) {
                System.out.println("Date must be in YYYY-MM-DD format. " + attempts + " attempts remaining...");
            }
        }
        
        throw new FlightBookingSystemException("Incorrect departure date provided. Cannot create flight.");
    }
    
    private static LocalDate parseDateWithAttempts(BufferedReader br) throws IOException, FlightBookingSystemException {
        return parseDateWithAttempts(br, 3);
    }
}
