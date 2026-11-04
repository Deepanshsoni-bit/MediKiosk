package com.airex.medikiosk.service;

import com.airex.medikiosk.dto.PatientRequestDto;
import com.airex.medikiosk.dto.PatientResponseDto;

import java.util.List;

public interface PatientService {

    PatientResponseDto createPatient(PatientRequestDto request);

    PatientResponseDto getPatientById(Long id);

    PatientResponseDto getPatientByMobileNumber(String mobileNumber);

    List<PatientResponseDto> getAllPatients();

    PatientResponseDto updatePatient(Long id, PatientRequestDto request);

    void deletePatient(Long id);
}
