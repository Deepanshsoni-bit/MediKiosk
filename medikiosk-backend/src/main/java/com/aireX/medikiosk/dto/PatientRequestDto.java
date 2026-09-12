package com.aireX.medikiosk.dto;

import com.aireX.medikiosk.entity.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientRequestDto {

    @NotBlank(message = "fullName is required")
    private String fullName;

    @NotNull(message = "gender is required")
    private Gender gender;

    private LocalDate dateOfBirth;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "mobileNumber must be a valid 10-digit Indian mobile number")
    private String mobileNumber;

    private String address;

    private String abhaNumber;

    private String abhaAddress;

    private String profilePhotoBase64;
}
