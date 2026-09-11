package com.northstar.crm.api;

import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.mapper.CustomerMapper;
import com.northstar.crm.dto.CustomerRequestDTO;
import com.northstar.crm.dto.CustomerResponseDTO;
import com.northstar.crm.entity.Customer;
import com.northstar.crm.service.CustomerService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * API edge: validate → map → service → response DTO.
 * Correlation: lab-request-001 on failures.
 */
public class CustomerApiFacade {
    private final CustomerService service;
    private final Validator validator;

    public CustomerApiFacade(CustomerService service) {
        this.service = service;
        // ValidatorFactory already wired — keep this ctor pattern (GUIDE Step 5)
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public CustomerResponseDTO create(CustomerRequestDTO request, String correlationId) {
        // validate request; on violations log correlationId and throw IllegalArgumentException
        // map → createCustomer → toResponse (never return Customer entity)
        Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(request);
        if(!violations.isEmpty()) {
            throw new IllegalArgumentException("validation failed [" + correlationId + "]: " +
                    violations.stream().map(v -> v.getPropertyPath() + ": " + v.getMessage()).collect(Collectors.joining("; ")));
        }

        return CustomerMapper.toResponse(service.createCustomer(request.getCustomerId(), request.getFullName(), request.getEmail(), request.getPhone(), CustomerStatus.valueOf(request.getStatus())));
    }

    public CustomerResponseDTO getById(String customerId, String correlationId) {
        // getCustomer → toResponse; include correlationId in not-found message/log
        var entity = service.findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("customer not found [" + correlationId + "]: " + customerId));
        return CustomerMapper.toResponse(entity);
    }

    private void validateOrThrow(CustomerRequestDTO request, String correlationId) {
        Set<ConstraintViolation<CustomerRequestDTO>> violations = validator.validate(request);
        if (!violations.isEmpty()) {
            String msg = violations.stream()
                    .map(v -> v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.joining("; "));
            // TODO: log correlationId + msg (no secrets)
            throw new IllegalArgumentException("[" + correlationId + "] " + msg);
        }
    }
}
