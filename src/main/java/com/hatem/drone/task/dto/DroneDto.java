package com.hatem.drone.task.dto;

import com.hatem.drone.task.enums.Model;
import com.hatem.drone.task.enums.State;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class DroneDto {

    @Size( max = 100, message = "serial number must be less than 100")
    private String serialNumber;
    private Model model;
    private Double batteryCapacity;
    private State state;
}
