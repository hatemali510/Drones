package com.hatem.drone.task.model;


import lombok.Data;

@Data
public class RuleResult {

    private Boolean passed;
    private String message;
    private String ruleName;
}
