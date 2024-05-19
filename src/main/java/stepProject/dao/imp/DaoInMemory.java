package stepProject.dao.imp;

import stepProject.dao.Dao;
import stepProject.model.entity.BookingEntity;

import java.util.*;

public class DaoInMemory implements Dao<BookingEntity> {

    private static final Set<BookingEntity> BOOKING_ENTITY_SET = new HashSet<>();

    @Override
    public BookingEntity saveAll(BookingEntity bookingEntity) {
        System.out.println(BOOKING_ENTITY_SET);
        BOOKING_ENTITY_SET.add(bookingEntity);
        System.out.println(BOOKING_ENTITY_SET);
        return bookingEntity;
    }

    @Override
    public List<BookingEntity> getAll() {
        return List.copyOf(BOOKING_ENTITY_SET);
    }


    @Override
    public Optional<BookingEntity> getById(int id) {
        return BOOKING_ENTITY_SET.stream()
                .filter(bookingEntity -> bookingEntity.getBookingId() == id)
                .findFirst()
                .map(Optional::of)
                .orElse(Optional.empty());
    }

    @Override
    public boolean deleteById(int id) {
        return BOOKING_ENTITY_SET.stream()
                .toList().removeIf(bookingEntity -> bookingEntity.getBookingId() == id);

    }
}
