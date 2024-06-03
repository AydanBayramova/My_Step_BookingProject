package stepProject.model.dto;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class BookingDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long bookingId;
    private List<String> passengerName;
    private LocalDate bookingDate;
    private Long flightNumber;

    public BookingDto(final Long bookingId, final List<String> passengerName, final LocalDate bookingDate, final Long flightNumber) {
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.bookingDate = bookingDate;
        this.flightNumber = flightNumber;
    }

    public BookingDto() {
    }

    public Long getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(Long flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public List<String> getPassengerName() {
        return this.passengerName;
    }

    public void setPassengerName(final List<String> passengerName) {
        this.passengerName = passengerName;
    }

    public LocalDate getBookingDate() {
        return this.bookingDate;
    }

    public void setBookingDate(final LocalDate bookingDate) {
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

    @Override
    public String toString() {
        return "BookingDto{" +
                "bookingId=" + bookingId +
                ", passengerName=" + passengerName +
                ", bookingDate=" + bookingDate +
                ", flightNumber=" + flightNumber +
                '}';
    }
}
