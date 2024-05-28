package stepProject.console;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import stepProject.dao.imp.BookingDaoInFile;
import stepProject.dao.imp.FlightDaoiInFile;
import stepProject.service.BookingService;
import stepProject.service.FlightService;

public class Main {
    public static void main(String[] args) {

        FlightDaoiInFile flightDao = new FlightDaoiInFile(new ObjectMapper().registerModule(new JavaTimeModule()));
        BookingDaoInFile bookingDaoInFile=new BookingDaoInFile(new ObjectMapper().registerModule(new JavaTimeModule()));

        FlightService flightService = new FlightService(flightDao);
        BookingService bookingService = new BookingService(bookingDaoInFile);

        ConsoleApp consoleApp = new ConsoleApp(flightService, bookingService);
        consoleApp.run();
    }
}
