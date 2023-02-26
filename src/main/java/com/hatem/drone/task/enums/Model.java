package com.hatem.drone.task.enums;

public enum Model {


    Lightweight("LW"),
    Middleweight("MW"),
    Cruiserweight("CW"),
    Heavyweight("HW");
    public final String modelWeight;

    Model(String weight) {
        this.modelWeight=weight;
    }
}
