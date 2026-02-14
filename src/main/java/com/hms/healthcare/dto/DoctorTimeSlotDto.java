package com.hms.healthcare.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.Data;

@Data
public class DoctorTimeSlotDto {
	@JsonProperty(access = Access.READ_ONLY)
	private Long id;
	private String name;
	private String specialization;
	private LocalDateTime timeSlot;
	private Double fee;
}
