package stepProject.controller;

import stepProject.exception.BookingException;
import stepProject.model.dto.BookingDto;
import stepProject.model.dto.FlightDto;
import stepProject.model.entity.BookingEntity;
import stepProject.model.entity.FlightEntity;
import stepProject.service.FlightService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightController {

    private final FlightService flightService;
    private final List<FlightEntity> addedFlights;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
        this.addedFlights = new ArrayList<>();
    }

    public void addFlight(FlightDto flightDto) {

        FlightEntity flightEntity = mapToEntity(flightDto);

        addedFlights.add(flightEntity);

        flightService.save(addedFlights);
    }


    private FlightEntity mapToEntity(FlightDto flightDto) {
        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setFlightId(flightDto.getFlightId());
        flightEntity.setDepartureDate(flightDto.getDepartureDate());
        flightEntity.setLocations(flightDto.getLocations());
        flightEntity.setDepartureCity(flightDto.getDepartureCity());
        return flightEntity;
    }

    public List<FlightEntity> getAll() {
        if (flightService.getAll() != null) {
            return flightService.getAll();
        } else {
            return null;
        }
    }

    public Optional<FlightEntity> getById(Long id) {
        if (id > 0 && flightService.getById(id).isPresent()) {
            return flightService.getById(id);
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteById(Long id) {
        if (flightService.getById(id).isPresent()) {
            return flightService.deleteById(id);
        } else {
            return false;
        }
    }


}
