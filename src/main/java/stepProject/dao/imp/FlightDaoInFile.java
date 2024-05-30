package stepProject.dao.imp;
import stepProject.dao.Dao;
import stepProject.model.entity.FlightEntity;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDaoInFile implements Dao<FlightEntity> {

    private static final String RESOURCES_PATH = "src/main/resources/";
    private static final String FLIGTHS_PATH = RESOURCES_PATH.concat("flights.ser");

    @Override
    public void saveAll(List<FlightEntity> flightEntity) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(FLIGTHS_PATH);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(flightEntity);
            System.out.println("ok written");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public List<FlightEntity> getAll() {
        try (FileInputStream fileInputStream = new FileInputStream(FLIGTHS_PATH);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            Object o = objectInputStream.readObject();
            return (List<FlightEntity>) o;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("exception in getAll method flights");
            return null;
        }

    }


    @Override
    public Optional<FlightEntity> getById(Long id) {
        List<FlightEntity> flightEntities = getAll();
        for (FlightEntity flightEntity : flightEntities) {
            if (flightEntity.getFlightId().equals(id)) {
                return Optional.of(flightEntity);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long id) {
        List<FlightEntity> flightEntities = getAll();
        for (FlightEntity flightEntity : flightEntities) {
            if (flightEntity.getFlightId().equals(id)) {
                flightEntities.remove(flightEntity);
                saveAll(flightEntities);
                return true;
            }
        }
        return false;
    }
}
