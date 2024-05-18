package stepProject.service;

import stepProject.dao.Dao;
import stepProject.model.entity.BookingEntity;

import java.util.List;
import java.util.Optional;


public class BookingService implements Service {
  private final Dao dao;

    public BookingService(Dao dao) {
        this.dao = dao;
    }


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
