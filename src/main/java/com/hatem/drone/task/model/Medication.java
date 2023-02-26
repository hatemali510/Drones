package com.hatem.drone.task.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class Medication implements Serializable {

    private String name;
    private Double weight;
    private String code;
    private String image;

}
