package com.pm.patientservice.dto;

import com.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PatientRequestDTO {
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name can not exceed 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email can not exceed 100 characters")
    private String email;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address can not exceed 255 characters")
    private String address;

    @NotBlank(message = "Date of birth is required")
    @Size(max = 10, message = "Date of birth can not exceed 10 characters")
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registered date is required")
    @Size(max = 10, message = "Registered date can not exceed 10 characters")
    private String registeredDate;

    public @NotBlank(message = "Name is required") @Size(max = 100, message = "Name can not exceed 100 characters") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") @Size(max = 100, message = "Name can not exceed 100 characters") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be valid") @Size(max = 100, message = "Email can not exceed 100 characters") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") @Size(max = 100, message = "Email can not exceed 100 characters") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Address is required") @Size(max = 255, message = "Address can not exceed 255 characters") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address is required") @Size(max = 255, message = "Address can not exceed 255 characters") String address) {
        this.address = address;
    }

    public @NotBlank(message = "Date of birth is required") @Size(max = 10, message = "Date of birth can not exceed 10 characters") String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotBlank(message = "Date of birth is required") @Size(max = 10, message = "Date of birth can not exceed 10 characters") String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate (String registeredDate) {
        this.registeredDate = registeredDate;
    }
}
