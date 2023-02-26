package com.hatem.drone.task.RideValidationRules;

import com.hatem.drone.task.model.DroneRideRequest;
import com.hatem.drone.task.model.RuleResult;

public interface ValidationRuleRunner {

     RuleResult applyRule(DroneRideRequest droneRideRequest);
}
