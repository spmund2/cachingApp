package com.bsl.service;

import com.bsl.model.Employee;
import com.bsl.repo.EmployeeRepository;
import com.bsl.model.dto.EmployeeDtos.CreateEmployeeRequest;
import com.bsl.model.dto.EmployeeDtos.UpdateEmployeeRequest;
import com.bsl.model.dto.EmployeeDtos.EmployeeResponse;

import com.bsl.exception.DuplicateEmailException;
import com.bsl.exception.EmployeeNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository repo;
    private static final DateTimeFormatter ISO = DateTimeFormatter.ISO_INSTANT;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public List<EmployeeResponse> list() {
        return repo.findAll().stream().map(this::toResponse).toList();
    }

    //@Cacheable(value = "employees", key = "#id")
    @Cacheable(cacheNames = "employees", key = "#id")
    public EmployeeResponse get(Long id) {
        var e = repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return toResponse(e);
    }
@CachePut(cacheNames = "employees", key = "#result.id")
    public EmployeeResponse create(CreateEmployeeRequest req) {
        if (repo.existsByEmail(req.email)) {
            throw new DuplicateEmailException(req.email);
        }
        var e = new Employee();
        e.setName(req.name);
        e.setEmail(req.email);
        e.setDepartment(req.department);
        e.setSalary(req.salary);
        var saved = repo.save(e);
        return toResponse(saved);
    }
@CachePut(cacheNames = "employees", key = "#id")
    public EmployeeResponse update(Long id, UpdateEmployeeRequest req) {
        var e = repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        e.setName(req.name);
        e.setDepartment(req.department);
        e.setSalary(req.salary);
        var saved = repo.save(e);
        return toResponse(saved);
    }
    @CacheEvict(cacheNames = "employees", key = "#id")
    public void delete(Long id) {
        var e = repo.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        repo.delete(e);
    }

    private EmployeeResponse toResponse(Employee e) {
        var r = new EmployeeResponse();
        r.id = e.getId();
        r.name = e.getName();
        r.email = e.getEmail();
        r.department = e.getDepartment();
        r.salary = e.getSalary();
        r.createdAt = ISO.format(e.getCreatedAt());
        r.updatedAt = ISO.format(e.getUpdatedAt());
        return r;
    }
}
