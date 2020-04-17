package com.project.AlarmMonitoringSystem.controllers;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.AlarmMonitoringSystem.models.Sensor;
import com.project.AlarmMonitoringSystem.repositories.SensorRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SensorController {
	private SensorRepository sensorRepository;

	public SensorController(SensorRepository sensorRepository) {
		super();
		this.sensorRepository = sensorRepository;
	}
	
	@GetMapping("/sensors")
	Collection<Sensor> sensors(){
		return sensorRepository.findAll();
	}
	
	@GetMapping("/sensor/{id}")
	ResponseEntity<?> getSensor(@PathVariable Long id){
		Optional<Sensor> sensor = sensorRepository.findById(id);
		return sensor.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/sensor")
	ResponseEntity<Sensor> createSensor(@Valid @RequestBody Sensor sensor) throws URISyntaxException{
		Sensor result = sensorRepository.save(sensor);
		return ResponseEntity.created(new URI("/api/sensor" + result.getId())).body(result);
	}
	
	@PutMapping("/sensor/{id}")
	ResponseEntity<Sensor> updateSensor(@Valid @RequestBody Sensor sensor){
		Sensor result = sensorRepository.save(sensor);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/sensor/{id}")
	ResponseEntity<?> deleteSensor(@PathVariable Long id){
		sensorRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
