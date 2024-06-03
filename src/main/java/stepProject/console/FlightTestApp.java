package stepProject.console;

import stepProject.controller.FlightController;

import stepProject.dao.FlightDaoDB;
import stepProject.dao.imp.FlightDaoInFile;
import stepProject.model.dto.FlightDto;
import stepProject.service.FlightService;

import java.time.LocalDate;

public class FlightTestApp {

    public static void main(String[] args) {

     //   FlightDaoInFile flightDaoInFile = new FlightDaoInFile();
        //  FlightInMemory  flightInMemory = new FlightInMemory();
        FlightDaoDB flightDaoDB = new FlightDaoDB();
        FlightService flightService = new FlightService(flightDaoDB);
        FlightController flightController = new FlightController(flightService);
        flightController.addFlight(new FlightDto(1L, "Baku", LocalDate.of(2024, 4, 2), 1L, "Kiyev"));
        flightController.addFlight(new FlightDto(2L, "Baku3", LocalDate.of(2024, 6, 2), 1L, "Kiyev"));
        flightController.addFlight(new FlightDto(3L, "Baku6", LocalDate.of(2024, 7, 2), 1L, "Kiyev"));
        flightController.addFlight(new FlightDto(4L, "Baku9", LocalDate.of(2024, 8, 2), 1L, "Kiyev"));
 //       System.out.println(flightController.getAll());
  // System.out.println(flightController.deleteById(2L));
   //      System.out.println(flightController.getById(3L));


    }
}
