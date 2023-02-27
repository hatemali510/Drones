package com.hatem.drone.task.model;

import com.hatem.drone.task.dto.Medication;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;


@Document("drone_ride")
@Data
public class DroneRide implements Serializable {

    @Id
    public String id;
    public String droneId;
    public List<Medication> medicationList;
}
