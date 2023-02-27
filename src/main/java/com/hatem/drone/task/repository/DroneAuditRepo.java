package com.hatem.drone.task.repository;

import com.hatem.drone.task.model.DroneAudit;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DroneAuditRepo extends MongoRepository<DroneAudit,String> {
}
