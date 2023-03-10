package com.hatem.drone.task.model;

import com.hatem.drone.task.enums.Model;
import com.hatem.drone.task.enums.State;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.io.Serializable;


@Document("drone")
@Data
public class Drone implements Serializable {
    @Id
    private String id;
    private String serialNumber;
    private Model model;
    private Double batteryCapacity;
    private State state;
}
