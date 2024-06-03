package stepProject.model.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class FlightEntity implements Serializable {

    private  final long serialVersionUID = 1L;

    private  Long flightId;
    private String locations;
    private LocalDate departureDate;
    private  int FREE_SEATS;
    private String departureCity;

    public FlightEntity( String locations,  LocalDate departureDate,  int FREE_SEATS,  String departureCity) {
        this.locations = locations;
        this.departureDate = departureDate;
        this.FREE_SEATS = FREE_SEATS;
        this.departureCity = departureCity;
    }

    public FlightEntity() {
    }

    public String getDepartureCity() {
        return this.departureCity;
    }

    public int getFREE_SEATS() {
        return this.FREE_SEATS;
    }

    public void setFREE_SEATS(final int FREE_SEATS) {
        this.FREE_SEATS = FREE_SEATS;
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
