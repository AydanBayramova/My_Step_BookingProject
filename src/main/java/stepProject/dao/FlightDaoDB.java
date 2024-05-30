package stepProject.dao;

import stepProject.model.entity.FlightEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class FlightDaoDB implements Dao<FlightEntity> {
    public Connection conn =  new DBconnection().getConnection();

//    public void saveAll (FlightEntity flightEntity){
//
//        try {
//            PreparedStatement ps = conn.prepareStatement("insert into flights (id,origin,destination,departure_time,free_seats) values (?,?,?,?,?)");
//
//            LocalDate departureDate = flightEntity.getDepartureDate();
//            LocalDateTime departureDateTime = departureDate.atStartOfDay();
//            Timestamp departureTimestamp = Timestamp.valueOf(departureDateTime);
//
//
//           ps.setLong(1,flightEntity.getFlightId());
//           ps.setString(2,flightEntity.getLocations());
//           ps.setString(3,flightEntity.getDepartureCity());
//            ps.setTimestamp(4, departureTimestamp);
//            ps.setLong(5,FlightEntity.getFreeSeats());
//
//
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//
//
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
            ps.setLong(4, flightEntity.getFreeSeats());



        }
        ps.execute();

        ps.close();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    @Override
    public List<FlightEntity> getAll() {
        return List.of();
    }

    @Override
    public Optional<FlightEntity> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
