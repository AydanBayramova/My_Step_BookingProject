package stepProject.model.dto;

import java.time.LocalDate;
import java.util.Objects;

public class BookingDto {

    private Long bookingId;
    private String passengerName;
    private LocalDate bookingDate;

    public BookingDto(Long bookingId, String passengerName, LocalDate bookingDate) {
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.bookingDate = bookingDate;
    }

    public BookingDto() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookingDto that = (BookingDto) o;
        return Objects.equals(bookingId, that.bookingId) && Objects.equals(passengerName, that.passengerName) && Objects.equals(bookingDate, that.bookingDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, passengerName, bookingDate);
    }
}
