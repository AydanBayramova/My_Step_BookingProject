import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import stepProject.controller.BookingController;
import stepProject.dao.BookingDaoDB;
import stepProject.dao.imp.BookingDaoInFile;
import stepProject.model.dto.BookingDto;
import stepProject.service.BookingService;


import java.time.LocalDate;
import java.util.Collections;

public class BookingTestApp {

    public static void main(String[] args) {

      //  BookingDaoInFile bookingDaoInFile = new BookingDaoInFile();
        BookingDaoDB bookingDaoDB = new BookingDaoDB();
        BookingService bookingService = new BookingService(bookingDaoDB);
        BookingController bookingController = new BookingController(bookingService);
        bookingController.addBookings(new BookingDto(1L, Collections.singletonList("AYDAN BAYRAMOVA"), LocalDate.of(2024, 5, 29), 4L));
        bookingController.addBookings(new BookingDto(2L, Collections.singletonList("ISMAIL HUSEYNOV"), LocalDate.of(2024, 5, 29), 5L));
        bookingController.addBookings(new BookingDto(3L, Collections.singletonList("TOM WATSON"), LocalDate.of(2024, 5, 29), 6L));
        bookingController.addBookings(new BookingDto(4L, Collections.singletonList("ALICE JOHN"), LocalDate.of(2024, 5, 29), 6L));
        bookingController.addBookings(new BookingDto(5L, Collections.singletonList("BOB JOM"), LocalDate.of(2024, 5, 29), 8L));
        bookingController.addBookings(new BookingDto(3L, Collections.singletonList("SHERLOCH HOLMES"), LocalDate.of(2024, 5, 29), 9L));
        bookingController.addBookings(new BookingDto(7L, Collections.singletonList("ANJELINA JONY"), LocalDate.of(2024, 5, 29), 14L));
        bookingController.addBookings(new BookingDto(8L, Collections.singletonList("ALEX BOB"), LocalDate.of(2024, 5, 29), 11L));
        bookingController.addBookings(new BookingDto(9L, Collections.singletonList("ALICE JOHN"), LocalDate.of(2024, 5, 29), 32L));
//        bookingController.addBookings(new BookingDto(4L, "AYDAN BAYRAMOVA", LocalDate.of(2024, 5, 29), 5L));
     //  System.out.println(bookingController.getAll());
//       System.out.println(bookingController.deleteById(8L));
         System.out.println(bookingController.getById(3L));
    }
}
