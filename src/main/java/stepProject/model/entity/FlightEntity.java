package stepProject.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class FlightEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long flightId;
    private String locations;
    private LocalDate departureDate;
    private static long FREE_SEATS = 0;
    private String departureCity;

    public FlightEntity(Long flightId, String locations, LocalDate departureDate,String departureCity) {
        this.flightId = ++FREE_SEATS;
        this.locations = locations;
        this.departureDate = departureDate;
        this.departureCity = departureCity;

    }

    public FlightEntity() {
    }

    public String getDepartureCity() {
        return this.departureCity;
    }

    public void setDepartureCity(final String departureCity) {
        this.departureCity = departureCity;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public static long getFreeSeats() {
        return FREE_SEATS;
    }

    public static void setFreeSeats(long freeSeats) {
        FREE_SEATS = freeSeats;
    }

    public static void decreaseFreeSeats() {
        if (FREE_SEATS > 0) {
            FREE_SEATS--;
        }
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (null == o || this.getClass() != o.getClass()) return false;
        final FlightEntity that = (FlightEntity) o;
        return Objects.equals(this.flightId, that.flightId) && Objects.equals(this.locations, that.locations) && Objects.equals(this.departureDate, that.departureDate);

    }
    @Override
    public int hashCode() {
        return Objects.hash(this.flightId, this.locations, this.departureDate);
    }

    @Override
    public String toString() {
        return "FlightEntity{" +
                "flightId=" + flightId +
                ", locations='" + locations + '\'' +
                ", departureDate=" + departureDate +
                ", departureCity='" + departureCity + '\'' +
                '}';
    }
}
