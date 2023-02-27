package com.hatem.drone.task.service;


import com.hatem.drone.task.RideValidationRules.ValidationRuleRunner;
import com.hatem.drone.task.dto.DroneRideRequest;
import com.hatem.drone.task.dto.LoadItemRequest;
import com.hatem.drone.task.dto.RuleResult;
import com.hatem.drone.task.enums.State;
import com.hatem.drone.task.model.*;
import com.hatem.drone.task.repository.DroneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RideValidation {

    @Value("${minimum.battery.value}")
    private Double minimumBatteryValue;

    @Value("${ride.validation.rules}")
    private String rideValidationRulesStringList;
    @Autowired
    DroneRepo droneRepo;
    @Autowired
    Map<String, ValidationRuleRunner> rideValidationRuleRunnersMap;

    public LoadItemRequest validateRide(DroneRideRequest droneRideRequest) {
        LoadItemRequest loadItemRequest =new LoadItemRequest();
        List<RuleResult> ruleResults= applyRideValidationRules(droneRideRequest);
        for (RuleResult ruleResult: ruleResults){
            if (Boolean.FALSE.equals(ruleResult.getPassed())){
                loadItemRequest.setRideRequestStatus(false);
                loadItemRequest.setFailedRule(ruleResult.getRuleName());
                loadItemRequest.setFailedRuleMessage(ruleResult.getMessage());
                updateDroneStatus(droneRideRequest.getDrone().getId(), State.IDLE);
                return loadItemRequest;
            }
        }
        updateDroneStatus(droneRideRequest.getDrone().getId(), State.LOADED);
        loadItemRequest.setDroneState(State.LOADED);
        loadItemRequest.setRideRequestStatus(true);
        return loadItemRequest;
    }

    private List<RuleResult> applyRideValidationRules(DroneRideRequest droneRideRequest) {
        String[] rideValidationRules=rideValidationRulesStringList.split(",");
        List<RuleResult> ruleResults=new ArrayList<>();
        for (String rule : rideValidationRules){
            ValidationRuleRunner  validationRuleRunner=rideValidationRuleRunnersMap.get(rule);
            ruleResults.add(validationRuleRunner.applyRule(droneRideRequest));
        }
        return ruleResults;
    }


    public Boolean checkDroneBattery(Optional<Drone> drone){
        if (drone.get().getBatteryCapacity()<minimumBatteryValue){
            return false;
        }
        updateDroneStatus(drone.get().getId(),State.LOADING);
        // update UI with the status
        return true;
    }
    public void updateDroneStatus(String droneId, State state){
        Optional<Drone> optionalDrone=droneRepo.findById(droneId);
        if (optionalDrone.isPresent()){
            Drone drone=optionalDrone.get();
            drone.setState(state);
            droneRepo.save(drone);
        }
    }

    /*
    - check the drone state again after choose the drone from the UI
    - this second check to prevent the concurrency issues
     */

    public boolean isAvailableDrone(String id) {
        Optional<Drone> optionalDrone=droneRepo.findById(id);
        if (optionalDrone.isPresent()){
            Drone drone=optionalDrone.get();
            return State.IDLE.equals(drone.getState());
        }
        return false;
    }
}
