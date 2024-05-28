package stepProject.dao.imp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import stepProject.dao.Dao;
import stepProject.exception.BookingException;
import stepProject.model.entity.BookingEntity;
import stepProject.model.entity.FlightEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDaoInFile implements Dao<BookingEntity> {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String BOOKINGS_PATH = RESOURCES_PATH.concat("bookings.json");

    private ObjectMapper objectMapper;

    public BookingDaoInFile(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    @Override
    public List<BookingEntity> saveAll(BookingEntity bookingEntity) {
        try (FileWriter fileWriter = new FileWriter(BOOKINGS_PATH, true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
             PrintWriter printWriter = new PrintWriter(bufferedWriter)) {
            String bookingJson = objectMapper.writeValueAsString(bookingEntity);
            printWriter.println(bookingJson);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BookingException("Error saving booking entity.");
        }
        return (List<BookingEntity>) bookingEntity;
    }

    @Override
    public List<BookingEntity> getAll() {
        List<BookingEntity> bookings = new ArrayList<>();
        try (Reader reader = new FileReader(BOOKINGS_PATH)) {
            bookings = objectMapper.readValue(reader, new TypeReference<List<BookingEntity>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookings;
    }


    @Override
    public Optional<BookingEntity> getById(Long id) {
        List<BookingEntity> bookings = getAll();
        return bookings.stream()
                .filter(bookingEntity -> id.equals(bookingEntity.getBookingId()))
                .findFirst();
    }

    public boolean deleteById(Long id) {
        List<BookingEntity> bookings = getAll();
        Optional<BookingEntity> bookingToDelete = bookings.stream()
                .filter(bookingEntity -> id.equals(bookingEntity.getBookingId()))
                .findFirst();
        if (bookingToDelete.isPresent()) {
            bookings.remove(bookingToDelete.get());
            saveAllToFile(bookings);
            decreaseFreeSeats();
            return true;
        }
        return false;
    }

    private void decreaseFreeSeats() {
        FlightEntity.decreaseFreeSeats();
    }


    private void saveAllToFile(List<BookingEntity> bookings) {
        try (Writer writer = new FileWriter(BOOKINGS_PATH)) {
            objectMapper.writeValue(writer, bookings);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BookingException("Error saving bookings to file.");
        }
    }
}
