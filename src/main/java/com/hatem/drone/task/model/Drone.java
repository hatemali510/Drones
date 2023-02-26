package com.hatem.drone.task.model;

import com.hatem.drone.task.enums.Model;
import com.hatem.drone.task.enums.State;

import lombok.Data;

import javax.validation.constraints.Size;
import java.io.Serializable;


@Data
public class Drone implements Serializable {
    private String id;
    @Size( max = 100, message = "${validation.drone.serial.size.message}")
    private String serialNumber;
    private Model model;
    private Double batteryCapacity;
    private State state;
}
