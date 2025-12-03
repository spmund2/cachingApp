package com.bsl.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class EmployeeDtos {

    public static class CreateEmployeeRequest {
        @NotBlank @Size(max = 100) public String name;
        @NotBlank @Email @Size(max = 150) public String email;
        @NotBlank @Size(max = 60) public String department;
        @Positive public BigDecimal salary;
    }

    public static class UpdateEmployeeRequest {
        @NotBlank @Size(max = 100) public String name;
        @NotBlank @Size(max = 60) public String department;
        @Positive public BigDecimal salary;
    }

    public static class EmployeeResponse {
        public Long id;
        public String name;
        public String email;
        public String department;
        public BigDecimal salary;
        public String createdAt;
        public String updatedAt;
    }
}

