package com.aireX.medikiosk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private LocalDate dateOfBirth;

    @Column(unique = true)
    private String mobileNumber;

    private String address;

    @Column(unique = true)
    private String abhaNumber;

    @Column(unique = true)
    private String abhaAddress;

    @Builder.Default
    private boolean abhaVerified = false;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String profilePhotoBase64;

    @Column(updatable = false)
    private LocalDateTime registeredAt;

    @PrePersist
    protected void onCreate() {
        this.registeredAt = LocalDateTime.now();
    }
}
