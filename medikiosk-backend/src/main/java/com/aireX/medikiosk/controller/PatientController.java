package com.aireX.medikiosk.controller;

import com.aireX.medikiosk.dto.PatientRequestDto;
import com.aireX.medikiosk.dto.PatientResponseDto;
import com.aireX.medikiosk.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDto> createPatient(@Valid @RequestBody PatientRequestDto request) {
        PatientResponseDto created = patientService.createPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @GetMapping("/mobile/{mobileNumber}")
    public ResponseEntity<PatientResponseDto> getPatientByMobile(@PathVariable String mobileNumber) {
        return ResponseEntity.ok(patientService.getPatientByMobileNumber(mobileNumber));
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDto>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(
            @PathVariable Long id, @Valid @RequestBody PatientRequestDto request) {
        return ResponseEntity.ok(patientService.updatePatient(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
