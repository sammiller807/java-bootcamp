package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Lab 10 baseline. Lab 11 TODOs: inject CustomerNotifier; call notifyStatusChange
 * from updateStatus; extract validateCustomerId; keep behavior for CUS-1001 / CUS-1002.
 */
public class CustomerService {
    private final List<Customer> customers = new ArrayList<>();
    private final CustomerNotifier notifier;

    public CustomerService() {
        // no-op notifier keeps previous behavior
        this((customerId, oldStatus, newStatus) -> {});
    }

    public CustomerService(CustomerNotifier notifier) {
        this.notifier = notifier == null ? (customerId, oldStatus, newStatus) -> {} : notifier;
    }

    public Customer addCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customerId is required");
        }
        validateCustomerId(customer.getCustomerId());
        if (findByCustomerId(customer.getCustomerId()).isPresent()) {
            throw new IllegalStateException("Duplicate customerId: " + customer.getCustomerId());
        }
        if (customer.getCreatedAt() == null) {
            customer.setCreatedAt(LocalDateTime.now());
        }
        if (customer.getStatus() == null) {
            customer.setStatus(CustomerStatus.PROSPECT);
        }
        customers.add(customer);
        return customer;
    }

    public Optional<Customer> findByCustomerId(String customerId) {
        return customers.stream()
                .filter(c -> c.getCustomerId().equals(customerId))
                .findFirst();
    }

    public Customer updateStatus(String customerId, CustomerStatus newStatus) {
        validateCustomerId(customerId);
        Customer c = findByCustomerId(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found: " + customerId));
        CustomerStatus oldStatus = c.getStatus();
        c.setStatus(newStatus);
        notifier.notifyStatusChange(customerId, oldStatus, newStatus);
        return c;
    }

    private void validateCustomerId(String customerId) {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("customerId is required");
        }
    }

    // TODO (required for Tests run: 8): findByStatus(CustomerStatus) and listAll()
}
