package com.hatem.drone.task.service;

import com.hatem.drone.task.model.Drone;
import com.hatem.drone.task.model.DroneAudit;
import com.hatem.drone.task.repository.DroneAuditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DroneBatteryAudit {

    @Autowired
    DroneDispatcher droneDispatcher;

    @Autowired
    DroneAuditRepo droneAuditRepo;


    @Scheduled(cron = "0 0 */1 * * *")
    public void checkDronesBatteryLevel(){
        List<Drone> dronesList=droneDispatcher.getAllDrones();
        List<DroneAudit> droneAuditList=new ArrayList<>();
        for (Drone drone: dronesList){
            droneAuditList.add(createAuditObject(drone));
        }
        droneAuditRepo.saveAll(droneAuditList);
    }

    private DroneAudit createAuditObject(Drone drone){
        DroneAudit droneAudit=new DroneAudit();
        droneAudit.setBatteryLevel(drone.getBatteryCapacity());
        droneAudit.setDroneId(drone.getId());
        droneAudit.setState(drone.getState());
        droneAudit.setLocalDateTime(LocalDateTime.now());
        return droneAudit;
    }
}
