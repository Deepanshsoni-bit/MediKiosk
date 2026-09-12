package com.aireX.medikiosk.service;

import com.aireX.medikiosk.dto.PatientRequestDto;
import com.aireX.medikiosk.dto.PatientResponseDto;

import java.util.List;

public interface PatientService {

    PatientResponseDto createPatient(PatientRequestDto request);

    PatientResponseDto getPatientById(Long id);

    PatientResponseDto getPatientByMobileNumber(String mobileNumber);

    List<PatientResponseDto> getAllPatients();

    PatientResponseDto updatePatient(Long id, PatientRequestDto request);

    void deletePatient(Long id);
}
