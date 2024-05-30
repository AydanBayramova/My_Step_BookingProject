package stepProject.dao.imp;

import stepProject.dao.Dao;
import stepProject.model.entity.BookingEntity;

import java.util.*;

public class BookingDaoInMemory implements Dao<BookingEntity> {

    private static final Set<BookingEntity> BOOKING_ENTITY_SET = new HashSet<>();


    @Override
    public void saveAll(List<BookingEntity> bookingEntities) {
        BOOKING_ENTITY_SET.addAll(bookingEntities);
    }


    @Override
    public List<BookingEntity> getAll() {
        return List.copyOf(BOOKING_ENTITY_SET);
    }


    @Override
    public Optional<BookingEntity> getById(Long id) {
        return BOOKING_ENTITY_SET.stream()
                .filter(bookingEntity -> Objects.equals(bookingEntity.getBookingId(), id))
                .findFirst()
                .map(Optional::of)
                .orElse(Optional.empty());
    }

    @Override
    public boolean deleteById(Long id) {
        Iterator<BookingEntity> iterator = BOOKING_ENTITY_SET.iterator();
        while (iterator.hasNext()) {
            BookingEntity bookingEntity = iterator.next();
            if (Objects.equals(bookingEntity.getBookingId(), id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

}
