package stepProject.dao;

import stepProject.model.entity.BookingEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDaoDB implements Dao<BookingEntity> {

    Connection connection = new DBconnection().getConnection();

    @Override
    public void saveAll(List<BookingEntity> bookingEntities) {
        String sql = "insert into bookings(flight_id) values(?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            for (BookingEntity bookingEntity : bookingEntities) {
                ps.setLong(1, bookingEntity.getFlightNumber());

            }
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BookingEntity> getAll() {
        List<BookingEntity> bookingEntities=new ArrayList<>();
        String sql="select *from bookings";
        try(  Statement statement=connection.createStatement()) {
         ResultSet rs=statement.executeQuery(sql);
         while(rs.next()){
             BookingEntity bookingEntity=new BookingEntity();
             bookingEntity.setFlightNumber(rs.getLong("flight_id"));
             bookingEntities.add(bookingEntity);
             return bookingEntities;
         }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return List.of();
    }

    @Override
    public Optional<BookingEntity> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
