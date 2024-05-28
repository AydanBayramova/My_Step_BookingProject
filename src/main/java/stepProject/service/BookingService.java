package stepProject.service;

import stepProject.dao.Dao;
import stepProject.exception.BookingException;
import stepProject.model.dto.BookingDto;
import stepProject.model.entity.BookingEntity;

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
    public List saveAll(Object o) {
        BookingDto bookingDto = (BookingDto) o;
        if (bookingDto != null) {
            BookingEntity bookingEntity = maptoBookingEntity(bookingDto);
            return (List) bookingEntityDao.saveAll(bookingEntity);
        } else {
            return null;
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
