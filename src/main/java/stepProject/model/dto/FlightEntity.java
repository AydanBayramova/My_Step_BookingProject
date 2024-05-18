package stepProject.model.dto;

import java.time.LocalDate;
import java.util.Objects;

public class FlightEntity {

    private Long flightId;
    private String locations;
    private LocalDate departureDate;
    private Long freeSeats;

    public FlightEntity(Long flightId, String locations, LocalDate departureDate, Long freeSeats) {
        this.flightId = flightId;
        this.locations = locations;
        this.departureDate = departureDate;
        this.freeSeats = freeSeats;
    }

    public FlightEntity() {
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
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
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
        FlightEntity that = (FlightEntity) o;
        return Objects.equals(flightId, that.flightId) && Objects.equals(locations, that.locations) && Objects.equals(departureDate, that.departureDate) && Objects.equals(freeSeats, that.freeSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, locations, departureDate, freeSeats);
    }
}
