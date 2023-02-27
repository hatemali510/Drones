package com.hatem.drone.task.dto;


import lombok.Data;

@Data
public class RuleResult {

    private Boolean passed;
    private String message;
    private String ruleName;
}
