package com.project.AlarmMonitoringSystem.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@NoArgsConstructor
@Data
@Table(name="location")
public class Location {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;	
	private int roomId;
	private long floorId;
	@NonNull
	private String locationName;
}
