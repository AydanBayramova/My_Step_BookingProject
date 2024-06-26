package stepProject.service;
import stepProject.dao.Dao;
import stepProject.exception.BookingException;
import stepProject.model.dto.BookingDto;
import stepProject.model.dto.FlightDto;
import stepProject.model.entity.BookingEntity;
import stepProject.model.entity.FlightEntity;
import java.util.List;
import java.util.Optional;


public class BookingService implements Service {

    private final Dao<BookingEntity> bookingEntityDao;

    public BookingService(Dao<BookingEntity> bookingEntityDao) {
        this.bookingEntityDao = bookingEntityDao;
    }

    private BookingEntity maptoBookingEntity(BookingDto bookingDto) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setBookingId(bookingDto.getBookingId());
        bookingEntity.setBookingDate(bookingDto.getBookingDate());
        bookingEntity.setPassengerName(bookingDto.getPassengerName());
        bookingEntity.setFlightNumber(bookingDto.getFlightNumber());
        return bookingEntity;
    }


    @Override
    public void save(List t) {
        if (t != null) {
            bookingEntityDao.saveAll((List<BookingEntity>) t);
        } else {
            System.out.println("errorororor");
        }
    }

    @Override
    public List<BookingEntity> getAll() {
        if (bookingEntityDao.getAll() == null) {
            throw new BookingException("No BookingEntity found");
        } else {
            return bookingEntityDao.getAll();
        }
    }

    @Override
    public Optional<BookingEntity> getById(Long id) {
        if (bookingEntityDao.getById(id).isPresent()) {
            return bookingEntityDao.getById(id);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteById(Long id) {
        if (bookingEntityDao.getById(id).isPresent()) {
            return bookingEntityDao.deleteById(id);
        } else {
            return false;
        }

    }
}
