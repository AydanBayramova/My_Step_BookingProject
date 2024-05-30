package stepProject.controller;

import stepProject.exception.BookingException;
import stepProject.model.dto.BookingDto;
import stepProject.model.dto.FlightDto;
import stepProject.model.entity.BookingEntity;
import stepProject.model.entity.FlightEntity;
import stepProject.service.BookingService;
import stepProject.service.FlightService;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingController {

    private final BookingService bookingService;

    private final List<BookingEntity> addedBoogins;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
        this.addedBoogins = new ArrayList<>();
    }

    public void addBookings(BookingDto bookingDto) {
        BookingEntity bookingEntity = mapToEntity(bookingDto);
        addedBoogins.add(bookingEntity);
        bookingService.save(addedBoogins);
    }

    private BookingEntity mapToEntity(BookingDto bookingDto) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setBookingId(bookingDto.getBookingId());
        bookingEntity.setFlightNumber(bookingDto.getFlightNumber());
        bookingEntity.setFlightNumber(bookingDto.getFlightNumber());
        bookingEntity.setPassengerName(bookingDto.getPassengerName());
        return bookingEntity;
    }


    public List<BookingEntity> getAll() {
        if (bookingService.getAll() != null) {
            return bookingService.getAll();
        } else {
            return null;
        }


    }

    public Optional<BookingEntity> getById(Long id) {
        if (id > 0 && bookingService.getById(id).isPresent()) {
            return bookingService.getById(id);
        } else {
            return Optional.empty();
        }
    }

    public boolean deleteById(Long id) {
        if (id > 0) {
            return bookingService.deleteById(id);
        } else {
            return false;
        }
    }


}
