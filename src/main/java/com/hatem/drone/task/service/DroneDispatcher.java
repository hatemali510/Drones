package com.hatem.drone.task.service;

import com.hatem.drone.task.dto.DroneDto;
import com.hatem.drone.task.dto.DroneRideRequest;
import com.hatem.drone.task.dto.LoadItemRequest;
import com.hatem.drone.task.dto.Medication;
import com.hatem.drone.task.enums.State;
import com.hatem.drone.task.model.*;
import com.hatem.drone.task.repository.DroneRepo;
import org.modelmapper.ModelMapper;
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



    public static final String DRONE_NOT_AVAILABLE_RULE="NOT_AVAILABLE";
    public static final String DRONE_NOT_AVAILABLE_MESSAGE="drone is not available now";

    public static final String BATTERY_RULE_NAME= "BATTERY_RULE";
    public static final String BATTERY_RULE_FAILED_MESSAGE= "drone can't fly , battery percentage is less than the minimum value .";

    public ResponseEntity<Drone> register(DroneDto droneDto){
        ModelMapper modelMapper=new ModelMapper();
        Drone drone=modelMapper.map(droneDto,Drone.class);
        return ResponseEntity.ok(droneRepo.save(drone));
    }

    public LoadItemRequest loadItems(String droneId, List<Medication> medicationList){
        LoadItemRequest loadItemRequest;
        Optional<Drone> optionalDrone=droneRepo.findById(droneId);
        DroneRideRequest droneRideRequest = new DroneRideRequest(optionalDrone, medicationList);
        if (!rideValidation.isAvailableDrone(optionalDrone.get().getId())){
            return prepareLoadItemRequestResponse(false,DRONE_NOT_AVAILABLE_RULE,DRONE_NOT_AVAILABLE_MESSAGE);
        }
        if (rideValidation.checkDroneBattery(optionalDrone)) {
            loadItemRequest =rideValidation.validateRide(droneRideRequest);
            if (Boolean.FALSE.equals(loadItemRequest.getRideRequestStatus())) {
                return loadItemRequest;
            }
        }else {
            loadItemRequest=prepareLoadItemRequestResponse(false,BATTERY_RULE_NAME,BATTERY_RULE_FAILED_MESSAGE);
        }
        return loadItemRequest;
    }

    private LoadItemRequest prepareLoadItemRequestResponse(Boolean rideStatus,String failedReason,String failedMessage){
        LoadItemRequest loadItemRequest=new LoadItemRequest();
        loadItemRequest.setRideRequestStatus(rideStatus);
        loadItemRequest.setFailedRule(failedReason);
        loadItemRequest.setFailedRuleMessage(failedMessage);
        return loadItemRequest;
    }

    /*
    - load all the available drones with status IDLE which ready for the loading process
    - then the drone id will send again in the /load-items/{droneId} API
     */
    public List<Drone> getAvailableDrones(){
        return droneRepo.findByState(State.IDLE.name());
    }

    public List<Drone> getAllDrones(){
        return droneRepo.findAll();
    }
}
