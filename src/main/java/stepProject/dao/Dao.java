package stepProject.dao;


import stepProject.model.entity.BookingEntity;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

   void saveAll(BookingEntity bookingEntity);
   List<BookingEntity> getAll();
   Optional<BookingEntity> getById(int id);
   boolean deleteById(int id);
}
