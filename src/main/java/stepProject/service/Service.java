package stepProject.service;
import stepProject.model.dto.BookingDto;
import stepProject.model.entity.BookingEntity;
import java.util.Optional;
import java.util.List;


public interface Service<T> {



   List<T>  saveAll(T o);

    List<T> getAll();
    Optional<T> getById(Long id);
    boolean deleteById(Long id);
}
