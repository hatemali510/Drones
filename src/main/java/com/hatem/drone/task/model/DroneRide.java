package com.hatem.drone.task.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;


@Data
public class DroneRide implements Serializable {

    @Id
    public String id;
    public String droneId;
    public List<Medication> medicationList;
}
