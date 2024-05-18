package stepProject.controller;

import stepProject.service.BookingService;

public class BookingController {
   private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
}
