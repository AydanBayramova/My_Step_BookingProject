package stepProject.dao;

import stepProject.model.entity.BookingEntity;

import java.sql.*;
import java.util.*;

public class BookingDaoDB implements Dao<BookingEntity> {

    Connection connection = new DBconnection().getConnection();


    public void saveAll(List<BookingEntity> bookingEntities) {
        String bookingSql = "INSERT INTO bookings(flight_id) VALUES(?)";
        String passengerSql = "INSERT INTO passengers(full_name) VALUES(?)";
        String bookingPassengerSql = "INSERT INTO booking_passengers(booking_id, passenger_id) VALUES(?, ?)";

        try (
                PreparedStatement bookingPs = connection.prepareStatement(bookingSql, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement passengerPs = connection.prepareStatement(passengerSql, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement bookingPassengerPs = connection.prepareStatement(bookingPassengerSql)
        ) {
            connection.setAutoCommit(false);

            for (BookingEntity bookingEntity : bookingEntities) {
                bookingPs.setLong(1, bookingEntity.getFlightNumber());
                bookingPs.executeUpdate();

                ResultSet bookingRs = bookingPs.getGeneratedKeys();
                if (bookingRs.next()) {
                    long bookingId = bookingRs.getLong(1);

                    for (String passengerName : bookingEntity.getPassengerName()) {
                        passengerPs.setString(1, passengerName);
                        passengerPs.executeUpdate();

                        ResultSet passengerRs = passengerPs.getGeneratedKeys();
                        if (passengerRs.next()) {
                            long passengerId = passengerRs.getLong(1);

                            bookingPassengerPs.setLong(1, bookingId);
                            bookingPassengerPs.setLong(2, passengerId);
                            bookingPassengerPs.executeUpdate();
                        }
                    }
                }
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public List<BookingEntity> getAll() {
        Map<Long, BookingEntity> bookingMap = new HashMap<>();
        String sql = "SELECT b.id AS booking_id, b.flight_id, p.full_name " +
                "FROM bookings b " +
                "JOIN booking_passengers bp ON b.id = bp.booking_id " +
                "JOIN passengers p ON bp.passenger_id = p.id";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                long bookingId = rs.getLong("booking_id");
                long flightNumber = rs.getLong("flight_id");
                String passengerName = rs.getString("full_name");

                BookingEntity bookingEntity = bookingMap.get(bookingId);
                if (bookingEntity == null) {
                    bookingEntity = new BookingEntity();
                    bookingEntity.setBookingId(bookingId);
                    bookingEntity.setFlightNumber(flightNumber);
                    bookingEntity.setPassengerName(new ArrayList<>());
                    bookingMap.put(bookingId, bookingEntity);
                }
                bookingEntity.getPassengerName().add(passengerName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(bookingMap.values());
    }

    @Override
    public Optional<BookingEntity> getById(Long id) {
        return Optional.empty();
    }


//    @Override
//    public Optional<BookingEntity> getById(String passengerName) {
//        String sql = "select bookings.id from passengers p " +
//                "join bookings_passengers bp on p.id = bp.passenger_id " +
//                "join bookings b on bp.booking_id=b.id where p.full_name=?";
//        // where full_name = ?
//
//        try(PreparedStatement ps = connection.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery()){
//            ps.setString(1, passengerName);
//            List<Long> listOfBookingIds = new ArrayList<>();
//            while (rs.next()) {
//                listOfBookingIds.add( rs.getLong("id"));
//            }
//
//
//
//
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//
//    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM bookings WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
