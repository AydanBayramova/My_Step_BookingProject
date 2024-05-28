package stepProject.dao;


import stepProject.model.entity.BookingEntity;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

   List<T> saveAll(T t);
   List<T> getAll();
   Optional<T> getById(Long id);
   boolean deleteById(Long id);
}
