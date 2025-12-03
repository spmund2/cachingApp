package com.bsl.controller;

import com.bsl.service.EmployeeService;
import com.bsl.model.dto.EmployeeDtos.CreateEmployeeRequest;
import com.bsl.model.dto.EmployeeDtos.UpdateEmployeeRequest;
import com.bsl.model.dto.EmployeeDtos.EmployeeResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employees", description = "CRUD operations for employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List all employees", description = "Returns all employees")
    public List<EmployeeResponse> list() {
        return service.list();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID", description = "Fetch a single employee by its ID")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public EmployeeResponse get(
            @PathVariable @Parameter(description = "Employee ID") Long id) {
        return service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create employee", description = "Creates a new employee")
    @ApiResponse(responseCode = "409", description = "Duplicate email")
    public EmployeeResponse create(@Valid @RequestBody CreateEmployeeRequest req) {
        return service.create(req);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update employee", description = "Updates fields of an existing employee")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public EmployeeResponse update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateEmployeeRequest req) {
        return service.update(id, req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete employee", description = "Deletes an employee by ID")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
