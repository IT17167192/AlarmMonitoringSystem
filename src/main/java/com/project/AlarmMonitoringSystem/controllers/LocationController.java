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

import com.project.AlarmMonitoringSystem.models.Location;
import com.project.AlarmMonitoringSystem.repositories.LocationRepository;

@RestController
@RequestMapping("/api")
public class LocationController {
	private LocationRepository locationRepository;

	public LocationController(LocationRepository locationRepository) {
		super();
		this.locationRepository = locationRepository;
	}
	
	@GetMapping("/locations")
	Collection<Location> locations(){
		return locationRepository.findAll();
	}
	
	@GetMapping("/location/{id}")
	ResponseEntity<?> getLocation(@PathVariable Long id){
		Optional<Location> location = locationRepository.findById(id);
		return location.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/location")
	ResponseEntity<Location> createLocation(@Valid @RequestBody Location location) throws URISyntaxException{
		Location result = locationRepository.save(location);
		return ResponseEntity.created(new URI("/api/location" + result.getId())).body(result);
	}
	
	@PutMapping("/location/{id}")
	ResponseEntity<Location> updateLocation(@Valid @RequestBody Location location){
		Location result = locationRepository.save(location);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/location/{id}")
	ResponseEntity<?> deleteLocation(@PathVariable Long id){
		locationRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
}
