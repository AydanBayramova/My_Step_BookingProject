package stepProject.dao;

import stepProject.model.entity.BookingEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
                bookingRs.next();
                long bookingId = bookingRs.getLong(1);


                passengerPs.setString(1, bookingEntity.getPassengerName());
                passengerPs.executeUpdate();


                ResultSet passengerRs = passengerPs.getGeneratedKeys();
                passengerRs.next();
                long passengerId = passengerRs.getLong(1);


                bookingPassengerPs.setLong(1, bookingId);
                bookingPassengerPs.setLong(2, passengerId);
                bookingPassengerPs.executeUpdate();
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
        List<BookingEntity> bookingEntities = new ArrayList<>();
        String sql = "SELECT b.id, b.flight_id, p.full_name " +
                "FROM bookings b " +
                "JOIN booking_passengers bp ON b.id = bp.booking_id " +
                "JOIN passengers p ON bp.passenger_id = p.id";
        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                BookingEntity bookingEntity = new BookingEntity();
                bookingEntity.setBookingId(rs.getLong("id"));
                bookingEntity.setFlightNumber(rs.getLong("flight_id"));
                bookingEntity.setPassengerName(rs.getString("full_name"));
                bookingEntities.add(bookingEntity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookingEntities;
    }


    @Override
    public Optional<BookingEntity> getById(Long id) {
        String bookingsql = "SELECT * FROM bookings WHERE id=?";
        String passengerSql = "SELECT * FROM passengers WHERE id=?";
        try (PreparedStatement booking_preparedStatement = connection.prepareStatement(bookingsql);
             PreparedStatement passenger_preparedStatement = connection.prepareStatement(passengerSql)) {
            passenger_preparedStatement.setLong(1, id);
            ResultSet rsPassenger = passenger_preparedStatement.executeQuery();
            booking_preparedStatement.setLong(1, id);
            ResultSet rsBooking = booking_preparedStatement.executeQuery();
            if (rsPassenger.next() && rsBooking.next()) {
                BookingEntity bookingEntity = new BookingEntity();
                bookingEntity.setBookingId(rsPassenger.getLong("id"));
                bookingEntity.setFlightNumber(rsBooking.getLong("flight_id"));
                bookingEntity.setPassengerName(rsPassenger.getString("full_name"));
                return Optional.of(bookingEntity);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }

        return Optional.empty();
    }

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
