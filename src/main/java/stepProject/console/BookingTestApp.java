import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import stepProject.controller.BookingController;
import stepProject.dao.BookingDaoDB;
import stepProject.dao.imp.BookingDaoInFile;
import stepProject.model.dto.BookingDto;
import stepProject.service.BookingService;


import java.time.LocalDate;

public class BookingTestApp {

    public static void main(String[] args) {

      //  BookingDaoInFile bookingDaoInFile = new BookingDaoInFile();
        BookingDaoDB bookingDaoDB = new BookingDaoDB();
        BookingService bookingService = new BookingService(bookingDaoDB);
        BookingController bookingController = new BookingController(bookingService);
        bookingController.addBookings(new BookingDto(1L, "AYDAN BAYRAMOVA", LocalDate.of(2024, 5, 29), 4L));
        bookingController.addBookings(new BookingDto(2L, "AYDAN BAYRAMOVA", LocalDate.of(2024, 5, 29), 4L));
        bookingController.addBookings(new BookingDto(3L, "AYDAN BAYRAMOVA", LocalDate.of(2024, 5, 29), 4L));
        bookingController.addBookings(new BookingDto(4L, "AYDAN BAYRAMOVA", LocalDate.of(2024, 5, 29), 4L));
        System.out.println(bookingController.getAll());
     //   System.out.println(bookingController.deleteById(4L));
     //   System.out.println(bookingController.getById(1L));
    }
}
