package com.hatem.drone.task.repository;

import com.hatem.drone.task.model.DroneRide;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DroneRideRepo extends MongoRepository<DroneRide,String> {
}
