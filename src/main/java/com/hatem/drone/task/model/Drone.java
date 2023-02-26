package com.hatem.drone.task.model;

import com.hatem.drone.task.enums.Model;
import com.hatem.drone.task.enums.State;
import lombok.Data;

import java.io.Serializable;


@Data
public class Drone implements Serializable {
    private String id;
    private String serialNumber;
    private Model model;
    private Double weightLimit;
    private Double batteryCapacity;
    private State state;
}
