package com.airex.medikiosk.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airex.medikiosk.entity.Visit;

public interface VisitRepository extends JpaRepository<Visit, Long>{

	List<Visit> findByPatientIdOrderByCheckInTimeDesc(Long patientId);  
}
