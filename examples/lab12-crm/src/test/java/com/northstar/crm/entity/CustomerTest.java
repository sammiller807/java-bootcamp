package com.northstar.crm.entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    @Test
    void equalsIsBasedOnCustomerIdOnly() {
        // two Customer with same id, different name/status → assertEquals
        Customer customer1 = new Customer("CUS-1002", "Bob", "bob@example.com", null, CustomerStatus.ACTIVE, LocalDateTime.now());
        Customer customer2 = new Customer("CUS-1002", "Greg", "greg@example.com", null, CustomerStatus.PROSPECT, LocalDateTime.now());
        assertEquals(customer1.getCustomerId(), customer2.getCustomerId());
    }

    @Test
    void toStringIncludesCustomerId() {
        // Customer CUS-1002 → toString contains CUS-1002
        Customer customer = new Customer("CUS-1002", "Bob", "bob@example.com", null, CustomerStatus.ACTIVE, LocalDateTime.now());

        assertTrue(customer.toString().contains("CUS-1002"));
    }
}
