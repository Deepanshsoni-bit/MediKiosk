package com.airex.medikiosk.repository;

import com.airex.medikiosk.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByMobileNumber(String mobileNumber);

    Optional<Patient> findByAbhaNumber(String abhaNumber);

    Optional<Patient> findByAbhaAddress(String abhaAddress);

    boolean existsByMobileNumber(String mobileNumber);
}
