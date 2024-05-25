package stepProject.console;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import stepProject.controller.FlightController;
import stepProject.dao.imp.FlightDaoiInFile;
import stepProject.model.dto.FlightDto;
import stepProject.service.FlightService;

import java.time.LocalDate;

public class FlightTestApp {
    public static void main(String[] args) {
        FlightDaoiInFile flightDaoiInFile=new FlightDaoiInFile(new ObjectMapper().registerModule(new JavaTimeModule()));
        FlightService flightService=new FlightService(flightDaoiInFile);
        FlightController flightController=new FlightController(flightService);
//        flightController.addFlight(new FlightDto(12L,"BAKU", LocalDate.of(2024,6,7),6L));
//        flightController.addFlight(new FlightDto(7L,"BAKU", LocalDate.of(2024,5,7),6L));
//        flightController.addFlight(new FlightDto(4L,"BAKU", LocalDate.of(2024,9,7),6L));
//        flightController.addFlight(new FlightDto(7L,"BAKU", LocalDate.of(2024,8,7),6L));
        System.out.println(flightController.getAll());
        System.out.println(flightController.deleteById(2L));
        System.out.println(flightController.getAll());
        System.out.println(flightController.getById(7L));
    }
}
