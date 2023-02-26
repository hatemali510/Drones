package com.hatem.drone.task.repository;

import com.hatem.drone.task.model.Drone;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DroneRepo extends MongoRepository<Drone,String > {
    List<Drone> findByState(String name);
}
