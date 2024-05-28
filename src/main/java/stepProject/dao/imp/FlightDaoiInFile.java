package stepProject.dao.imp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import stepProject.dao.Dao;
import stepProject.exception.BookingException;
import stepProject.exception.FlightException;
import stepProject.model.entity.BookingEntity;
import stepProject.model.entity.FlightEntity;
import java.time.LocalDateTime;
import java.io.*;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightDaoiInFile implements Dao<FlightEntity> {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String FLIGHT_PATH = RESOURCES_PATH.concat("flights.json");

    private ObjectMapper objectMapper=new ObjectMapper();

    public FlightDaoiInFile(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public List<FlightEntity> saveAll(FlightEntity flightEntity) {
        List<FlightEntity> allFlights = getAll();
        allFlights.add(flightEntity);

        String flightsJson;
        try {
            flightsJson = objectMapper.writeValueAsString(allFlights);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BookingException("Error converting flight entities to JSON.");
        }

        try (FileWriter fileWriter = new FileWriter(FLIGHT_PATH, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(flightsJson);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BookingException("Error saving booking entity.");
        }
        return (List<FlightEntity>) flightEntity;
    }

    @Override
    public List<FlightEntity> getAll() {

        List<FlightEntity> flights = new ArrayList<>();
        try (Reader reader = new FileReader(FLIGHT_PATH)) {
            flights = objectMapper.readValue(reader, new TypeReference<List<FlightEntity>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flights;
    }

    @Override
    public Optional<FlightEntity> getById(Long id) {
        List<FlightEntity> flights = getAll();
        return flights.stream()
                .filter(flightEntity -> id.equals(flightEntity.getFlightId()))
                .findFirst();
    }

    @Override
    public boolean deleteById(Long id) {
        List<FlightEntity> flights = getAll();
        Optional<FlightEntity> flightToDelete = flights.stream()
                .filter(flightEntity -> id.equals(flightEntity.getFlightId()))
                .findFirst();
        if (flightToDelete.isPresent()) {
            flights.remove(flightToDelete.get());
            saveAllToFile(flights);
            return true;
        }
        return false;
    }
    public List<FlightEntity> getFlightsFromKievInNext24Hours() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime next24Hours = now.plusHours(24);
          return getAll().stream()
                  .filter(flight->"Kiev".equalsIgnoreCase(flight.getLocations())&&
                          flight.getDepartureDate().isAfter(ChronoLocalDate.from(now))&&
                          flight.getDepartureDate().isBefore(ChronoLocalDate.from(next24Hours)))
                  .collect(Collectors.toList());
    }


    private void saveAllToFile(List<FlightEntity> flights) {
        try (Writer writer = new FileWriter(FLIGHT_PATH)) {
            objectMapper.writeValue(writer, flights);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FlightException("Error saving bookings to file.");
        }
    }
}
