package stepProject.dao;

import stepProject.model.entity.FlightEntity;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDaoDB implements Dao<FlightEntity> {
    public Connection conn = new DBconnection().getConnection();


    @Override
    public void saveAll(List<FlightEntity> flightEntities) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into flights ( origin, destination, departure_time, free_seats) values (?, ?, ?, ?)");

            for (FlightEntity flightEntity : flightEntities) {
                LocalDate departureDate = flightEntity.getDepartureDate();
                LocalDateTime departureDateTime = departureDate.atStartOfDay();
                Timestamp departureTimestamp = Timestamp.valueOf(departureDateTime);


                ps.setString(1, flightEntity.getLocations());
                ps.setString(2, flightEntity.getDepartureCity());
                ps.setTimestamp(3, departureTimestamp);
               ps.setInt(4,flightEntity.getFREE_SEATS());


            }
            ps.execute();

            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<FlightEntity> getAll() {
        List<FlightEntity> flights = new ArrayList<>();
        String sql = "SELECT * FROM flights";
        try(Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                FlightEntity flightEntity = new FlightEntity();
                flightEntity.setFlightId(resultSet.getLong("id"));
                flightEntity.setLocations(resultSet.getString("origin"));
                flightEntity.setDepartureCity(resultSet.getString("destination"));

                Timestamp departureTimestamp = resultSet.getTimestamp("departure_time");
                if (departureTimestamp != null) {
                    LocalDateTime departureDateTime = departureTimestamp.toLocalDateTime();
                    flightEntity.setDepartureDate(departureDateTime.toLocalDate());
                }

                flightEntity.setFREE_SEATS(resultSet.getInt("free_seats"));

                flights.add(flightEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return flights;
    }

    @Override
    public Optional<FlightEntity> getById(Long id) {
        String sql = "SELECT * FROM flights WHERE id = ?";
        try (PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setLong(1, id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                FlightEntity flightEntity = new FlightEntity();
                flightEntity.setFlightId(rs.getLong("id"));
                flightEntity.setLocations(rs.getString("origin"));
                flightEntity.setDepartureCity(rs.getString("destination"));
                Timestamp departureTimestamp = rs.getTimestamp("departure_time");
                if (departureTimestamp != null) {
                    LocalDateTime departureDateTime = departureTimestamp.toLocalDateTime();
                    flightEntity.setDepartureDate(departureDateTime.toLocalDate());
                }
                return Optional.of(flightEntity);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM flights WHERE id = ?";
        try(PreparedStatement preparedStatement= conn.prepareStatement(sql)) {
            preparedStatement.setLong(1,id);
            preparedStatement.execute();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
