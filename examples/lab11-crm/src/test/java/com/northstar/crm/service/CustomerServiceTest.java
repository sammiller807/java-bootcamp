package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerServiceTest {
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        customerService = new CustomerService();
    }

    @Test
    void addCustomerStoresNewCustomer() {
        Customer customer = new Customer(
                "CUS-1001",
                "Alice North",
                "alice@example.com",
                "555-0101",
                CustomerStatus.ACTIVE,
                LocalDateTime.now()
        );

        Customer saved = customerService.addCustomer(customer);

        assertEquals("CUS-1001", saved.getCustomerId());
        assertTrue(customerService.findByCustomerId("CUS-1001").isPresent());
        assertEquals(CustomerStatus.ACTIVE,
                customerService.findByCustomerId("CUS-1001").orElseThrow().getStatus());
    }

    @Test
    void addCustomerRejectsDuplicateId() {
        Customer first = new Customer(
                "CUS-1001",
                "Alice North",
                "alice@example.com",
                "555-0101",
                CustomerStatus.ACTIVE,
                LocalDateTime.now()
        );
        Customer duplicate = new Customer(
                "CUS-1001",
                "Alice North Duplicate",
                "alice.duplicate@example.com",
                "555-0102",
                CustomerStatus.PROSPECT,
                LocalDateTime.now()
        );

        customerService.addCustomer(first);

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> customerService.addCustomer(duplicate));
        assertTrue(exception.getMessage().contains("CUS-1001"));
    }

    @Test
    void updateStatusChangesExistingCustomer() {
        Customer customer = new Customer(
                "CUS-1002",
                "Bob West",
                "bob@example.com",
                "555-0103",
                CustomerStatus.PROSPECT,
                LocalDateTime.now()
        );
        customerService.addCustomer(customer);

        Customer updated = customerService.updateStatus("CUS-1002", CustomerStatus.ACTIVE);

        assertEquals(CustomerStatus.ACTIVE, updated.getStatus());
        assertEquals(CustomerStatus.ACTIVE,
                customerService.findByCustomerId("CUS-1002").orElseThrow().getStatus());
    }

    @Test
    void updateStatusThrowsForUnknownCustomer() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> customerService.updateStatus("CUS-9999", CustomerStatus.ACTIVE));
        assertTrue(exception.getMessage().contains("CUS-9999"));
    }
}
