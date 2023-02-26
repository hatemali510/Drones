package com.hatem.drone.task.service;

import com.hatem.drone.task.enums.State;
import com.hatem.drone.task.model.*;
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
    RideValidation rideValidation;



    public static final String BATTERY_RULE_NAME= "BATTERY_RULE";
    public static final String BATTERY_RULE_FAILED_MESSAGE= "drone can't fly , battery percentage is less than the minimum value .";

    public ResponseEntity<Drone> register(Drone drone){
        return ResponseEntity.ok(droneRepo.save(drone));
    }

    public LoadItemRequest loadItems(String droneId, List<Medication> medicationList){
        LoadItemRequest loadItemRequest=new LoadItemRequest();
        Optional<Drone> optionalDrone=droneRepo.findById(droneId);
        DroneRideRequest droneRideRequest = new DroneRideRequest(optionalDrone, medicationList);
        if (rideValidation.checkDroneState(optionalDrone.get().getId())&&rideValidation.checkDroneBattery(optionalDrone)) {
            loadItemRequest =rideValidation.validateRide(droneRideRequest);
            if (Boolean.FALSE.equals(loadItemRequest.getRideRequestStatus())) {
                return loadItemRequest;
            }
        }else {
            loadItemRequest.setRideRequestStatus(false);
            loadItemRequest.setFailedRule(BATTERY_RULE_NAME);
            loadItemRequest.setFailedRuleMessage(BATTERY_RULE_FAILED_MESSAGE);
        }
        return loadItemRequest;
    }



    /*
    - load all the available drones with status IDLE which ready for the loading process
    - then the drone id will send again in the /load-items/{droneId} API
     */
    public List<Drone> getAvailableDrones(){
        return droneRepo.findByState(State.IDLE.name());
    }
}
