package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.exception.CustomerNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * In-memory list-backed CustomerService for demo/testing.
 */
public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();

    public Customer addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer must not be null");
        }
        if (customer.getCustomerId() == null || customer.getCustomerId().trim().isEmpty()) {
            throw new IllegalArgumentException("customerId must not be null or blank");
        }
        // reject duplicate id
        Optional<Customer> existing = findByCustomerId(customer.getCustomerId());
        if (existing.isPresent()) {
            throw new IllegalArgumentException("Customer already exists: " + customer.getCustomerId());
        }
        customers.add(customer);
        return customer;
    }

    public Optional<Customer> findByCustomerId(String customerId) {
        if (customerId == null || customerId.trim().isEmpty()) {
            return Optional.empty();
        }
        return customers.stream()
                .filter(c -> Objects.equals(customerId, c.getCustomerId()))
                .findFirst();
    }

    public Customer updateStatus(String customerId, CustomerStatus status) {
        Customer cust = findByCustomerId(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        cust.setStatus(status);
        return cust;
    }
}
