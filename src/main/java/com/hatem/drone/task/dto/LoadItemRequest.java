package com.hatem.drone.task.dto;

import com.hatem.drone.task.enums.State;
import lombok.Data;


@Data
public class LoadItemRequest {
    private Boolean rideRequestStatus;
    private String failedRule;
    private State droneState;

    private String failedRuleMessage;


}
