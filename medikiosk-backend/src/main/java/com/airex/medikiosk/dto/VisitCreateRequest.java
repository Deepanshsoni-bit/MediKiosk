package com.airex.medikiosk.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VisitCreateRequest {

	@NotNull
	private Long patientId;
} 
 