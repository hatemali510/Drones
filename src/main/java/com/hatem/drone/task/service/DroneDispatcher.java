package com.hatem.drone.task.service;

import com.hatem.drone.task.enums.State;
import com.hatem.drone.task.model.Drone;
import com.hatem.drone.task.model.DroneRide;
import com.hatem.drone.task.model.Medication;
import com.hatem.drone.task.repository.DroneRepo;
import com.hatem.drone.task.repository.DroneRideRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DroneDispatcher {


    @Autowired
    DroneRepo droneRepo;

    @Autowired
    DroneRideRepo droneRideRepo;
    public ResponseEntity<Drone> register(Drone drone){
        return ResponseEntity.ok(droneRepo.save(drone));
    }

    public void loadItems(String droneId, List<Medication> medicationList){
        Optional<Drone> optionalDrone=droneRepo.findById(droneId);
        if (optionalDrone.isPresent()){
            DroneRide droneRide=new DroneRide();
            droneRide.setDroneId(droneId);
            droneRide.setMedicationList(medicationList);
            droneRideRepo.save(droneRide);
        }
    }


    public List<Drone> getAvailableDrones(){
        return droneRepo.findByState(State.IDLE.name());
    }
}
