package com.hatem.drone.task.controller;


import com.hatem.drone.task.model.Drone;
import com.hatem.drone.task.model.Medication;
import com.hatem.drone.task.service.DroneDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drone")
public class DroneController {


    @Autowired
    DroneDispatcher droneDispatcher;

    /*
    registering a drone api
     */
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Drone drone){
        return ResponseEntity.status(HttpStatus.OK).body(droneDispatcher.register(drone));
    }

    @PostMapping("/load-items/{droneId}")
    public ResponseEntity loadDroneItems(@PathVariable String droneId, @RequestBody List<Medication> medicationList){
        return ResponseEntity.status(HttpStatus.OK).body(droneDispatcher.loadItems(droneId,medicationList));
    }
    @GetMapping("/get-available-drone")
    public ResponseEntity getAvailableDrones(){
        return ResponseEntity.status(HttpStatus.OK).body(droneDispatcher.getAvailableDrones());
    }


}
