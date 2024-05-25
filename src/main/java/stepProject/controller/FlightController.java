package stepProject.controller;

import stepProject.exception.BookingException;
import stepProject.model.dto.BookingDto;
import stepProject.model.dto.FlightDto;
import stepProject.model.entity.BookingEntity;
import stepProject.model.entity.FlightEntity;
import stepProject.service.FlightService;

import java.util.List;
import java.util.Optional;

public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    public FlightDto addFlight(FlightDto flightDto) {
        if (flightDto.getFlightId() > 0) {
            FlightEntity flightEntity = (FlightEntity) flightService.saveAll(flightDto);
            return mapToDto(flightEntity);
        } else {
            throw new BookingException("Booking id can't be empty");
        }
    }

    private FlightDto mapToDto(FlightEntity flightEntity) {
        FlightDto flightDto = new FlightDto();
        flightDto.setFlightId(flightDto.getFlightId());
        flightDto.setLocations(flightDto.getLocations());
        flightDto.setDepartureDate(flightDto.getDepartureDate());
        flightDto.setFreeSeats(flightDto.getFreeSeats());
        return flightDto;
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
