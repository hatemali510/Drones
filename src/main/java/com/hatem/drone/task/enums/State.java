package com.hatem.drone.task.enums;

public enum State {

    IDLE("1"), LOADING("2"), LOADED("3"), DELIVERING("4"), DELIVERED("5"), RETURNING("6");
    public  final String state;
    State(String state) {
        this.state=state;
    }
}
