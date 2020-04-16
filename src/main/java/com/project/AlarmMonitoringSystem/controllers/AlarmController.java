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

import com.project.AlarmMonitoringSystem.models.Alarm;
import com.project.AlarmMonitoringSystem.repositories.AlarmRepository;

@RestController
@RequestMapping("/api")
public class AlarmController {
	private AlarmRepository alarmRepository;

	public AlarmController(AlarmRepository alarmRepository) {
		super();
		this.alarmRepository = alarmRepository;
	}
	
	@GetMapping("/alarms")
	Collection<Alarm> alarms(){
		return alarmRepository.findAll();
	}
	
	@GetMapping("/alarm/{id}")
	ResponseEntity<?> getAlarm(@PathVariable Long id){
		Optional<Alarm> alarm = alarmRepository.findById(id);
		return alarm.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/alarm")
	ResponseEntity<Alarm> createAlarm(@Valid @RequestBody Alarm alarm) throws URISyntaxException{
		Alarm result = alarmRepository.save(alarm);
		return ResponseEntity.created(new URI("/api/alarm" + result.getId())).body(result);
	}
	
	@PutMapping("/alarm/{id}")
	ResponseEntity<Alarm> updateAlarm(@Valid @RequestBody Alarm alarm){
		Alarm result = alarmRepository.save(alarm);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/alarm/{id}")
	ResponseEntity<?> deleteAlarm(@PathVariable Long id){
		alarmRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
