package com.hatem.drone.task.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;


@Data
public class Medication implements Serializable {


    @Pattern(regexp = "^[a-zA-Z0-9-_]+$",message = "allowed only letters, numbers, ‘-‘, ‘_’")
    private String name;
    private Double weight;

    @Pattern(regexp = "^[A-Z0-9_]+$",message = "allowed only upper case letters, underscore and numbers")
    private String code;
    private String image;

}
