package com.hatem.drone.task.repository;

import com.hatem.drone.task.model.Medication;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MedicationRepo extends MongoRepository<Medication,String> {
}
