package bcu.cmp5332.bookingsystem.data;

import bcu.cmp5332.bookingsystem.main.FlightBookingSystemException;
import bcu.cmp5332.bookingsystem.model.Customer;
import bcu.cmp5332.bookingsystem.model.FlightBookingSystem;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CustomerDataManager implements DataManager {

    private final String RESOURCE = "./resources/data/customers.txt";
    
    @Override
    public void loadData(FlightBookingSystem fbs) throws IOException, FlightBookingSystemException {
        // TODO: implementation here
        try (Scanner sc = new Scanner(new File(RESOURCE))){
    		int line_idx = 1;
    		while (sc.hasNextLine()) {
    			String line = sc.nextLine();
    			String[] properties1 = line.split(SEPARATOR, -1);
    			try {
    				int id = Integer.parseInt(properties1[0]);
    				String customerName = properties1[1];
    				String customerNumber = properties1[2];
    				String customerEmail = properties1[3];
    				Customer c1 = new Customer(id, customerName, customerNumber, customerEmail);
    				fbs.addCustomer(c1);
    			} catch (NumberFormatException ex) {
    				throw new FlightBookingSystemException("Unable to parse book id " + properties1[0] + " on line " + line_idx
                            + "\nError: " + ex);
    			}
    			line_idx++;
    		}
    	}
    }

    @Override
    public void storeData(FlightBookingSystem fbs) throws IOException {
        // TODO: implementation here
        try (PrintWriter out = new PrintWriter(new FileWriter(RESOURCE))){
    		for (Customer c1 : fbs.getCustomers()) {
    			out.print(c1.getId() + SEPARATOR);
    			out.print(c1.getName() + SEPARATOR);
    			out.print(c1.getPhone() + SEPARATOR);
    			out.print(c1.getEmail() + SEPARATOR);
    			out.println();
    		}
    	}
    }
}

