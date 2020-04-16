package com.project.AlarmMonitoringSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.AlarmMonitoringSystem.models.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
//	Location findByLocationName(String locationName);
}
