package com.hatem.drone.task.RideValidationRules;

import com.hatem.drone.task.dto.DroneRideRequest;
import com.hatem.drone.task.dto.Medication;
import com.hatem.drone.task.dto.RuleResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("checkLoadedItem")
public class CheckLoadedItem implements ValidationRuleRunner {

    @Value("${medication.maximum.weight}")
    private Double maximumWeight;

    private static final String RULE_FAILED_MESSAGE="medications weight is more than the maximum weight";
    @Override
    public RuleResult applyRule(DroneRideRequest droneRideRequest) {
        RuleResult ruleResult=new RuleResult();
        Double totalWeight=getMedicationsTotalWeight(droneRideRequest.getMedicationList());
        if (totalWeight>maximumWeight){
            ruleResult.setPassed(false);
            ruleResult.setMessage(RULE_FAILED_MESSAGE);
        }
        ruleResult.setPassed(true);
        return ruleResult;
    }
    private Double getMedicationsTotalWeight(List<Medication> medicationList){
        Double totalWeight=0.0;
        for (Medication medication :medicationList){
            totalWeight+=medication.getWeight();
        }
        return totalWeight;
    }
}
