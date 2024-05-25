package stepProject.dao.imp;

import stepProject.dao.Dao;
import stepProject.model.entity.BookingEntity;
import stepProject.model.entity.FlightEntity;

import java.util.*;

public class FlightInMemory implements Dao<FlightEntity> {

    private static final Set<FlightEntity> FLIGHT_ENTITY_SET = new HashSet<>();

    @Override
    public FlightEntity saveAll(FlightEntity flightEntity) {
        FLIGHT_ENTITY_SET.add(flightEntity);
        return flightEntity;
    }

    @Override
    public List<FlightEntity> getAll() {
        return List.copyOf(FLIGHT_ENTITY_SET);
    }

    @Override
    public Optional<FlightEntity> getById(Long id) {
        return FLIGHT_ENTITY_SET.stream()
                .filter(bookingEntity -> Objects.equals(bookingEntity.getFlightId(), id))
                .findFirst()
                .map(Optional::of)
                .orElse(Optional.empty());
    }

    @Override
    public boolean deleteById(Long id) {
        Iterator<FlightEntity> iterator = FLIGHT_ENTITY_SET.iterator();
        while (iterator.hasNext()) {
            FlightEntity flightEntity = iterator.next();
            if (Objects.equals(flightEntity.getFlightId(), id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
