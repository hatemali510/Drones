package com.hatem.drone.task.RideValidationRules;

import com.hatem.drone.task.dto.DroneRideRequest;
import com.hatem.drone.task.dto.RuleResult;

public interface ValidationRuleRunner {

     RuleResult applyRule(DroneRideRequest droneRideRequest);
}
