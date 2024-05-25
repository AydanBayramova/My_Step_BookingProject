package stepProject.dao.imp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import stepProject.dao.Dao;
import stepProject.exception.BookingException;
import stepProject.exception.FlightException;
import stepProject.model.entity.BookingEntity;
import stepProject.model.entity.FlightEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDaoiInFile implements Dao<FlightEntity> {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String FLIGHT_PATH = RESOURCES_PATH.concat("flights.json");

    private ObjectMapper objectMapper=new ObjectMapper();

    public FlightDaoiInFile(final ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public FlightEntity saveAll(FlightEntity flightEntity) {

        try (FileWriter fileWriter = new FileWriter(FLIGHT_PATH, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            String bookingJson = objectMapper.writeValueAsString(flightEntity);
            printWriter.println(bookingJson);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BookingException("Error saving booking entity.");
        }
        return flightEntity;
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
        Optional<FlightEntity> bookingToDelete = flights.stream()
                .filter(flightEntity -> id.equals(flightEntity.getFlightId()))
                .findFirst();
        if (bookingToDelete.isPresent()) {
            flights.remove(bookingToDelete.get());
            saveAllToFile(flights);
            return true;
        }
        return false;
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
