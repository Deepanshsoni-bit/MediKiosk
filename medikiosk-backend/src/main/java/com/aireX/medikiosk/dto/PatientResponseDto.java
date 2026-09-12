package com.aireX.medikiosk.dto;

import com.aireX.medikiosk.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDto {

    private Long id;
    private String fullName;
    private Gender gender;
    private LocalDate dateOfBirth;
    private String mobileNumber;
    private String address;
    private String abhaNumber;
    private String abhaAddress;
    private boolean abhaVerified;
    private LocalDateTime registeredAt;
    // profilePhotoBase64 intentionally omitted from list/detail responses by default
    // to keep payloads light - add a dedicated endpoint if the frontend needs it.
}
