package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.exception.CustomerNotFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/** INTENTIONALLY MESSY — refactor in later steps. Do not commit this style. */
public class CustomerService {
    private final Map<String, Customer> customersById = new HashMap<>();
    private String correlationId;

    public Customer createCustomer(String customerId, String fullName, String email, String phone, CustomerStatus status) {
        requireNonBlank(customerId, "Customer ID");
        requireNonBlank(fullName, "Full Name");
        requireNonBlank(email, "Email");
        //requireNonBlank(phone, "Phone");

        requireUniqueId(customerId);

        Customer customer = new Customer(customerId, fullName, email, phone, status, LocalDateTime.now());
        customersById.put(customerId, customer);

        return customer;
    }

    public Customer getCustomer(String customerId) {
        Customer found = customersById.get(customerId);
        if (found == null) {
            throw new IllegalArgumentException(
                    "Customer not found: " + customerId + " correlationId=" + correlationId);
        }
        return found;
    }

    public Customer updateStatus(String customerId, CustomerStatus newStatus) {
        Customer customer = getCustomer(customerId);
        customer.setStatus(newStatus);

        return customer;
    }

    private void requireNonBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " can't be blank");
        }
    }
    private void requireUniqueId(String customerId) {
        Customer found = customersById.get(customerId);
        if (found != null) {
            throw new IllegalStateException("Duplicate Id");
        }
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    public String getCorrelationId() {
        return correlationId;
    }

}