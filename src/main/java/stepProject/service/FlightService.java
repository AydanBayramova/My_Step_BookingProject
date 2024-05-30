package stepProject.service;
import stepProject.dao.Dao;
import stepProject.exception.BookingException;
import stepProject.model.dto.FlightDto;
import stepProject.model.entity.FlightEntity;
import java.util.List;
import java.util.Optional;

public class FlightService implements Service {

    private final Dao<FlightEntity> flightEntityDao;

    public FlightService(Dao<FlightEntity> flightEntityDao) {
        this.flightEntityDao = flightEntityDao;
    }

    private FlightEntity maptoFlightEntity(FlightDto flightDto) {
        FlightEntity flightEntity = new FlightEntity();
        flightEntity.setFlightId(flightDto.getFlightId());
        flightEntity.setDepartureDate(flightDto.getDepartureDate());
        flightEntity.setLocations(flightDto.getLocations());
        flightEntity.setDepartureCity(flightDto.getDepartureCity());
        return flightEntity;
    }

    @Override
    public void save(List t) {
        if (t != null) {
            flightEntityDao.saveAll((List<FlightEntity>) t);
        } else {
            System.out.println("errorororor");
        }
    }

    @Override
    public List getAll() {
        if (flightEntityDao.getAll() == null) {
            throw new BookingException("No FlightEntity found");
        } else {
            return flightEntityDao.getAll();
        }
    }


    @Override
    public Optional getById(Long id) {
        if (flightEntityDao.getById(id).isPresent()) {
            return flightEntityDao.getById(id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (flightEntityDao.getById(id).isPresent()) {
            return flightEntityDao.deleteById(id);
        } else {
            return false;
        }
    }

    public void displayFlights(List<FlightEntity> flights) {
        if (flights.isEmpty()) {
            System.out.println("No flights from Kiev in the next 24 hours.");
        } else {
            System.out.println("Flights from Kiev in the next 24 hours:");
            for (FlightEntity flight : flights) {
                System.out.println("Flight ID: " + flight.getFlightId() +
                        ", Destination: " + flight.getLocations() +
                        ", Departure Time: " + flight.getDepartureDate());
            }
        }
    }

}

