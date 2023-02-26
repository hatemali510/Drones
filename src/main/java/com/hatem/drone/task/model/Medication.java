package com.hatem.drone.task.model;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;


@Data
public class Medication implements Serializable {


    @Pattern(regexp = "^[a-zA-Z0-9-_]+$",message = "${validation.medication.name.message}")
    private String name;
    private Double weight;

    @Pattern(regexp = "^[A-Z0-9_]+$",message = "${validation.medication.code.message}")
    private String code;
    private String image;

}
