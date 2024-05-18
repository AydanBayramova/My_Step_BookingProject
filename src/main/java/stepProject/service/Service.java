package stepProject.service;

import stepProject.entity.BookingEntity;

import java.util.List;
import java.util.Optional;

public interface Service {
    void saveAll(BookingEntity bookingEntity);
    List<BookingEntity> getAll();
    Optional<BookingEntity> getById(int id);
    boolean deleteById(int id);
}
