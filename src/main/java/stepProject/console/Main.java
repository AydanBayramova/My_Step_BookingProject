package stepProject.console;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import stepProject.dao.imp.BookingDaoInFile;
import stepProject.dao.imp.BookingDaoInMemory;
import stepProject.dao.imp.FlightDaoInFile;
import stepProject.dao.imp.FlightInMemory;
import stepProject.service.BookingService;
import stepProject.service.FlightService;

public class Main {

    public static void main(String[] args) {

        //  FlightInMemory flightInMemory = new FlightInMemory();
        //  BookingDaoInMemory bookingDaoInMemory = new BookingDaoInMemory();
        FlightDaoInFile flightDaoInFile = new FlightDaoInFile();
        BookingDaoInFile bookingDaoInFile = new BookingDaoInFile();
        FlightService flightService = new FlightService(flightDaoInFile);
        BookingService bookingService = new BookingService(bookingDaoInFile);
        ConsoleApp consoleApp = new ConsoleApp(flightService, bookingService);
        consoleApp.run();
    }
}
