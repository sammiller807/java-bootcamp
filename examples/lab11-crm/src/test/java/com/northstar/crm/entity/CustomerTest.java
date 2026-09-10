package com.northstar.crm.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomerTest {
    @Test
    void equalsIsBasedOnCustomerIdOnly() {
        Customer customer1 = new Customer(
                "CUS-1001",
                "Ravi Kumar",
                "ravi@example.com",
                "555-0101",
                CustomerStatus.ACTIVE,
                LocalDateTime.now()
        );

        Customer customer2 = new Customer(
                "CUS-1001",
                "Priya Sharma",
                "priya@example.com",
                "555-9999",
                CustomerStatus.CLOSED,
                LocalDateTime.now()
        );

        assertEquals(customer1, customer2, "Customers with the same customerId must be considered equal");
    }

    @Test
    void toStringIncludesCustomerId() {
        Customer customer = new Customer(
                "CUS-1002",
                "Ravi Kumar",
                "ravi@example.com",
                "555-0102",
                CustomerStatus.PROSPECT,
                LocalDateTime.now()
        );

        assertTrue(customer.toString().contains("CUS-1002"));
    }
}
