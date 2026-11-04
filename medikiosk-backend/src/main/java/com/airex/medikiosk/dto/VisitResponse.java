package com.airex.medikiosk.dto;

import java.time.LocalDateTime;

import com.airex.medikiosk.entity.VisitStatus;

import lombok.Data;

@Data
public class VisitResponse {

	private Long id;
	private Long patientId;
	private String patientName;
	private VisitStatus status;
	private LocalDateTime checkTime; 
	
}
	