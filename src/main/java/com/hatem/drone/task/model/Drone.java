package com.hatem.drone.task.model;

import com.hatem.drone.task.enums.Model;
import com.hatem.drone.task.enums.State;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;


@Data
public class Drone implements Serializable {
    private String id;
    @Size( max = 100, message = "${validation.drone.serial.size.message}")
    private String serialNumber;
    private Model model;
    @DecimalMax(value = "500.0",message = "${validation.drone.weight.limit.message}")
    private Double weightLimit;
    private Double batteryCapacity;
    private State state;
}
