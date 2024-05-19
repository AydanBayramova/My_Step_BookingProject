package stepProject.model.entity;

import java.time.LocalDate;
import java.util.Objects;

public class BookingEntity {

    private Long bookingId;
    private String passengerName;
    private LocalDate bookingDate;
    private Long flightNumber;

    public BookingEntity(Long bookingId, String passengerName, LocalDate bookingDate, Long flightNumber) {
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.bookingDate = bookingDate;
        this.flightNumber = flightNumber;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public BookingEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingEntity that = (BookingEntity) o;
        return Objects.equals(bookingId, that.bookingId) && Objects.equals(passengerName, that.passengerName) && Objects.equals(bookingDate, that.bookingDate) && Objects.equals(flightNumber, that.flightNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, passengerName, bookingDate, flightNumber);
    }
}