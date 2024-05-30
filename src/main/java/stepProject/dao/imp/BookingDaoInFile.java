package stepProject.dao.imp;

import stepProject.dao.Dao;
import stepProject.model.entity.BookingEntity;
import stepProject.model.entity.FlightEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDaoInFile implements Dao<BookingEntity> {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String BOOKINGS_PATH = RESOURCES_PATH.concat("bookings.ser");

    @Override
    public void saveAll(List<BookingEntity> bookingEntities) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(BOOKINGS_PATH);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(bookingEntities);
            System.out.println("ok written");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<BookingEntity> getAll() {
        try (FileInputStream fileInputStream = new FileInputStream(BOOKINGS_PATH);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Object o = objectInputStream.readObject();
            return (List<BookingEntity>) o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("exception in getAll method flights");
            return null;
        }
    }

    @Override
    public Optional<BookingEntity> getById(Long id) {
        List<BookingEntity> bookingEntityList = getAll();
        for (BookingEntity bookingEntity : bookingEntityList) {
            if (bookingEntity.getBookingId().equals(id)) {
                return Optional.of(bookingEntity);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        List<BookingEntity> bookingEntityList = getAll();
        for (BookingEntity bookingEntity : bookingEntityList) {
            if (bookingEntity.getBookingId().equals(id)) {
                bookingEntityList.remove(bookingEntity);
                saveAll(bookingEntityList);
                return true;
            }
        }
        return false;
    }

}

