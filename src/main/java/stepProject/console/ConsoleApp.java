package stepProject.console;

import com.fasterxml.jackson.databind.ObjectMapper;
import stepProject.dao.imp.FlightDaoiInFile;
import stepProject.model.City;
import stepProject.model.entity.BookingEntity;
import stepProject.model.entity.FlightEntity;
import stepProject.service.BookingService;
import stepProject.service.FlightService;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleApp {

    private static FlightDaoiInFile flightDao = new FlightDaoiInFile(new ObjectMapper());


    private final FlightService flightService;
    private final BookingService bookingService;

    public ConsoleApp(final FlightService flightService, final BookingService bookingService) {
        this.flightService = flightService;
        this.bookingService = bookingService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Main Menu:");
            System.out.println("1. Show Online Board");
            System.out.println("2. Show Flight Info");
            System.out.println("3. Search and Book a Flight");
            System.out.println("4. Cancel Booking");
            System.out.println("5. My Flights");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showOnlineBoard();
                    break;
                case 2:
                    showFlightInfo();
                    break;
                case 3:
                    searchAndBookFlight();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    showMyFlights();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 6.");
            }
        } while (choice != 6);

        scanner.close();
    }

    private void showOnlineBoard() {
        List<FlightEntity> flights = flightDao.getFlightsFromKievInNext24Hours();
        flightService.displayFlights(flights);
    }

    private void showFlightInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Flight ID: ");

        try {
            Long flightId = scanner.nextLong();
            flightDao.getById(flightId).ifPresentOrElse(
                    flight -> {
                        System.out.println("Flight ID: " + flight.getFlightId());
                        System.out.println("Departure City: " + flight.getDepartureCity());
                        System.out.println("Departure Time: " + flight.getDepartureDate());
                        System.out.println("Arrival City: " + flight.getLocations());
                    },
                    () -> System.out.println("Flight not found.")
            );
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number for Flight ID.");
        }
    }


    private void searchAndBookFlight() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Departure City: ");
        String departureCity = scanner.nextLine();
        System.out.print("Enter Destination City: ");
        String destinationCity = scanner.nextLine();
        System.out.print("Enter your name: ");
        String passengerName = scanner.nextLine();

        List<FlightEntity> flights = flightDao.getAll().stream()
                .filter(flight -> departureCity.equalsIgnoreCase(flight.getDepartureCity()) &&
                        destinationCity.equalsIgnoreCase(flight.getLocations()))
                .toList();

        if (flights.isEmpty()) {
            System.out.println("No flights found.");
        } else {
            System.out.println("Available flights:");
            flights.forEach(flight -> System.out.println("Flight ID: " + flight.getFlightId() +
                    ", Departure Time: " + flight.getDepartureDate() + "LocaionCity:" + flight.getLocations()));
            System.out.print("Enter Flight ID to book: ");
            Long flightId = scanner.nextLong();
            scanner.nextLine();
            Optional<BookingEntity> booking = bookingService.getById(flightId);
            if (booking != null) {
                System.out.println("Booking successful! Booking ID: " + booking);
            } else {
                System.out.println("Booking failed.");
            }
        }
    }

    private void cancelBooking() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Booking ID: ");
        Long bookingId = scanner.nextLong();
        if (bookingService.deleteById(bookingId)) {
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Booking not found.");
        }
    }

    private void showMyFlights() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String passengerName = scanner.nextLine();
        List<BookingEntity> bookings = bookingService.getAll();
        if (bookings.isEmpty()) {
            System.out.println("No bookings found for " + passengerName);
        } else {
            System.out.println("Your bookings:");
            bookings.forEach(booking -> {
                FlightEntity flight = flightDao.getById(booking.getBookingId()).orElse(null);
                if (flight != null) {
                    System.out.println("Booking ID: " + booking.getBookingId() +
                            ", Flight ID: " + flight.getFlightId() +
                            ", Departure City: " + flight.getLocations() +
                            ", Departure Time: " + flight.getDepartureDate());
                }
            });
        }
    }
}

