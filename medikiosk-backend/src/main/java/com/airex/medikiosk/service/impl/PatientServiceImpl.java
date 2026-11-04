package com.airex.medikiosk.service.impl;

import com.airex.medikiosk.dto.PatientRequestDto;
import com.airex.medikiosk.dto.PatientResponseDto;
import com.airex.medikiosk.entity.Patient;
import com.airex.medikiosk.exception.DuplicateResourceException;
import com.airex.medikiosk.exception.ResourceNotFoundException;
import com.airex.medikiosk.repository.PatientRepository;
import com.airex.medikiosk.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    @Transactional
    public PatientResponseDto createPatient(PatientRequestDto request) {
        if (request.getMobileNumber() != null
                && patientRepository.existsByMobileNumber(request.getMobileNumber())) {
            throw new DuplicateResourceException("A patient with this mobile number already exists");
        }

        Patient patient = Patient.builder()
                .fullName(request.getFullName())
                .gender(request.getGender())
                .dateOfBirth(request.getDateOfBirth())
                .mobileNumber(request.getMobileNumber())
                .address(request.getAddress())
                .abhaNumber(request.getAbhaNumber()) 
                .abhaAddress(request.getAbhaAddress())
                .profilePhotoBase64(request.getProfilePhotoBase64())
                .build();

        return toResponseDto(patientRepository.save(patient));
    }

    @Override
    public PatientResponseDto getPatientById(Long id) {
        return toResponseDto(findOrThrow(id));
    }

    @Override
    public PatientResponseDto getPatientByMobileNumber(String mobileNumber) {
        Patient patient = patientRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No patient found with mobile number " + mobileNumber));
        return toResponseDto(patient);
    }

    @Override
    public List<PatientResponseDto> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(this::toResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public PatientResponseDto updatePatient(Long id, PatientRequestDto request) {
        Patient patient = findOrThrow(id);
        
        //check if while updating, the patient do not enter a number that already exists.
        if (request.getMobileNumber() != null) {
        	Optional<Patient> owner = patientRepository.findByMobileNumber(request.getMobileNumber());
        	
        	if(owner.isPresent() && !owner.get().getId().equals(id)) {
        		throw new DuplicateResourceException("A Patient With This Mobile Number Already Exists!");
        	}
        }
        
        //check if while updating, the patient do not enter a abhaNumber that already exists.
        if(request.getAbhaNumber() != null) {
        	Optional<Patient> owner = patientRepository.findByAbhaNumber(request.getAbhaNumber());
        	
        	if(owner.isPresent() && !owner.get().getId().equals(id)) {
        		throw new DuplicateResourceException("A Patient With This Abha Number Already Exists!");
        	}
        }
        
        //check if while updating, the patient do not enter a abhaAddress that already exists.
        if(request.getAbhaAddress() != null) {
        	Optional<Patient> owner = patientRepository.findByAbhaAddress(request.getAbhaAddress());
        	
        	if(owner.isPresent() && !owner.get().getId().equals(id)) {
        		throw new DuplicateResourceException("A Patient With This AbhaAddress Alreay Exists!");
        	}
        }
        
        patient.setFullName(request.getFullName());
        patient.setGender(request.getGender());
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setMobileNumber(request.getMobileNumber());
        patient.setAddress(request.getAddress());
        patient.setAbhaNumber(request.getAbhaNumber());
        patient.setAbhaAddress(request.getAbhaAddress());
        patient.setAbhaVerified(true);
 
        if (request.getProfilePhotoBase64() != null) {
            patient.setProfilePhotoBase64(request.getProfilePhotoBase64());
        }

        return toResponseDto(patientRepository.save(patient));
    }

    @Override
    @Transactional
    public void deletePatient(Long id) {
        Patient patient = findOrThrow(id);
        patientRepository.delete(patient);
    }

    private Patient findOrThrow(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No patient found with id " + id));
    }

    private PatientResponseDto toResponseDto(Patient patient) {
        return PatientResponseDto.builder()
                .id(patient.getId())
                .fullName(patient.getFullName())
                .gender(patient.getGender())
                .dateOfBirth(patient.getDateOfBirth())
                .mobileNumber(patient.getMobileNumber())
                .address(patient.getAddress())
                .abhaNumber(patient.getAbhaNumber())
                .abhaAddress(patient.getAbhaAddress())
                .abhaVerified(patient.isAbhaVerified())
                .registeredAt(patient.getRegisteredAt())
                .build();
    }
}
