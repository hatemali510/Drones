package com.hatem.drone.task.model;


import com.hatem.drone.task.enums.State;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document("drone_audit")
public class DroneAudit {

    @Id
    private String id;
    private LocalDateTime localDateTime;

    private String droneId;
    private Double batteryLevel;
    private State state;
}
