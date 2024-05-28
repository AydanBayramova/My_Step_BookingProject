package stepProject.controller;

import stepProject.exception.BookingException;
import stepProject.model.dto.BookingDto;
import stepProject.model.entity.BookingEntity;
import stepProject.service.BookingService;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;

public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookingDto addBooking(BookingDto bookingDto) {
        if (bookingDto.getBookingId() > 0) {
            BookingEntity bookingEntity = (BookingEntity) bookingService.saveAll(bookingDto);
            return mapToDto(bookingEntity);
        } else {
            throw new BookingException("Booking id can't be empty");
        }
    }

    private BookingDto mapToDto(BookingEntity bookingEntity) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setBookingId(bookingEntity.getBookingId());
        bookingDto.setBookingDate(bookingEntity.getBookingDate());
        bookingDto.setPassengerName(bookingEntity.getPassengerName());
        bookingDto.setFlightNumber(bookingEntity.getFlightNumber());
        return bookingDto;
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
        if (bookingService.getById(id).isPresent()) {
            return bookingService.deleteById(id);
        } else {
            return false;
        }
    }


}
