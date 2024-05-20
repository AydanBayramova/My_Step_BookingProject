package stepProject.service;
import stepProject.model.dto.BookingDto;
import stepProject.model.entity.BookingEntity;
import java.util.Optional;
import java.util.List;


public interface Service<T> {



    BookingEntity saveAll(BookingDto o);

    List<BookingEntity> getAll();
    Optional<BookingEntity> getById(Long id);
    boolean deleteById(Long id);
}
