package com.project.AlarmMonitoringSystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.AlarmMonitoringSystem.models.Alarm;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {
	Alarm findByName(String name);
}
