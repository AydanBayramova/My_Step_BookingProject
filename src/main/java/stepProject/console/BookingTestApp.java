package stepProject.console;

import stepProject.controller.BookingController;
import stepProject.dao.imp.DaoInMemory;
import stepProject.model.dto.BookingDto;
import stepProject.service.BookingService;

import java.time.LocalDate;

public class BookingTestApp {
    public static void main(String[] args) {

        DaoInMemory daoInMemory = new DaoInMemory();
        BookingService bookingService = new BookingService(daoInMemory);
        BookingController bookingController = new BookingController(bookingService);
        bookingController.addBooking(new BookingDto(2L, "Aydan", LocalDate.of(2024, 12, 20), 1L));
        bookingController.addBooking(new BookingDto(4L, "Aydan1", LocalDate.of(2024, 2, 20), 1L));
        bookingController.addBooking(new BookingDto(5L, "Aydan2", LocalDate.of(2024, 1, 20), 1L));
        bookingController.addBooking(new BookingDto(6L, "Aydan3", LocalDate.of(2024, 12, 8), 1L));
        System.out.println("-------" + bookingController.getAll());
        System.out.println(bookingController.deleteById(4L));
        System.out.println(bookingController.getById(5L));
    }
}
