package org.example.dao;

import org.example.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findByGarageId(long garageId);

    @Query("select count(*)" +
            " from Car as c" +
            " where c.garage.getId = ?1")
    Long findCarsCountInGarage(long garageId);
}