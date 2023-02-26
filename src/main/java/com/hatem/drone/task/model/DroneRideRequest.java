package com.hatem.drone.task.model;

import lombok.Data;

import java.util.List;
import java.util.Optional;


@Data
public class DroneRideRequest {

    private Drone drone;
    private List<Medication> medicationList;

    public DroneRideRequest(Optional<Drone> optionalDrone, List<Medication> medicationList) {
        this.medicationList=medicationList;
        this.drone=optionalDrone.get();
    }
}
