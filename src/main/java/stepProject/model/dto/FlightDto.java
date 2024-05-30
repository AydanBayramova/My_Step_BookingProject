package stepProject.model.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class FlightDto  implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long flightId;
    private String locations;
    private LocalDate departureDate;
    private Long freeSeats;
    private String departureCity;

    public FlightDto(final Long flightId, final String locations, final LocalDate departureDate, final Long freeSeats, final String departureCity) {
        this.flightId = flightId;
        this.locations = locations;
        this.departureDate = departureDate;
        this.freeSeats = freeSeats;
        this.departureCity = departureCity;
    }


    public FlightDto() {
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

    public LocalDate getDepartureDate() {
        return this.departureDate;
    }

    public void setDepartureDate(final LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public Long getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(Long freeSeats) {
        this.freeSeats = freeSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlightDto that = (FlightDto) o;
        return Objects.equals(flightId, that.flightId) && Objects.equals(locations, that.locations) && Objects.equals(departureDate, that.departureDate) && Objects.equals(freeSeats, that.freeSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, locations, departureDate, freeSeats);
    }

    @Override
    public String toString() {
        return "FlightDto{" +
                "flightId=" + flightId +
                ", locations='" + locations + '\'' +
                ", departureDate=" + departureDate +
                ", freeSeats=" + freeSeats +
                ", departureCity='" + departureCity + '\'' +
                '}';
    }
}
