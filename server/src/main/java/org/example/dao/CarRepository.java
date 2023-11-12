package org.example.dao;

import org.example.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByGarageId(long garageId);

    @Query(value =
            "select count(*)" +
            " from Car c" +
            " where c.garage.getId = :garageId",
            nativeQuery = true)
    Long findCarsCountInGarage(long garageId);
}