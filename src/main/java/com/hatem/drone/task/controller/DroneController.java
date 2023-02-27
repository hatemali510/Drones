package com.hatem.drone.task.controller;


import com.hatem.drone.task.dto.DroneDto;
import com.hatem.drone.task.dto.Medication;
import com.hatem.drone.task.service.DroneDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/drone")
public class DroneController {


    @Autowired
    DroneDispatcher droneDispatcher;

    /*
    registering a drone api
     */
    @PostMapping("/register")
    public ResponseEntity register(  @RequestBody @Valid DroneDto droneDto){
        return ResponseEntity.status(HttpStatus.OK).body(droneDispatcher.register(droneDto));
    }

    @PostMapping("/load-items/{droneId}")
    public ResponseEntity loadDroneItems(@PathVariable String droneId,@RequestBody @Valid List<Medication> medicationList){
        return ResponseEntity.status(HttpStatus.OK).body(droneDispatcher.loadItems(droneId,medicationList));
    }
    @GetMapping("/get-available-drone")
    public ResponseEntity getAvailableDrones(){
        return ResponseEntity.status(HttpStatus.OK).body(droneDispatcher.getAvailableDrones());
    }


}
