package stepProject.service;



import stepProject.model.entity.BookingEntity;

import java.util.List;
import java.util.Optional;

public class FlightService implements Service{

    @Override
    public void saveAll(BookingEntity bookingEntity) {

    }

    @Override
    public List<BookingEntity> getAll() {
        return List.of();
    }

    @Override
    public Optional<BookingEntity> getById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }
}
