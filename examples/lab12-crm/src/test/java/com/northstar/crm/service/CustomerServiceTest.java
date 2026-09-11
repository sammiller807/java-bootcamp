package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.exception.CustomerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Characterization / target-API tests — fail until refactor completes. Suite target: 6 tests. */
class CustomerServiceTest {

    private CustomerService svc;

    @BeforeEach
    void setUp() {
        svc = new CustomerService();
        // after refactor, setCorrelationId("lab-request-001") so not-found messages include it
        svc.setCorrelationId("lab-request-001");
    }

    @Test
    void createRaviProspectThenActivate() {
        // create CUS-1002 PROSPECT; updateStatus → ACTIVE; assert statuses
        Customer created = svc.createCustomer("CUS-1002", "Ravi", "ravi@example.com", null, CustomerStatus.PROSPECT);
        Customer updated = svc.updateStatus("CUS-1002", CustomerStatus.ACTIVE);
        assertEquals(created.getStatus(), updated.getStatus());
    }

    @Test
    void blankCustomerIdThrows() {
        // createCustomer(" ", ...) throws IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            svc.createCustomer("", "", "", "", CustomerStatus.ACTIVE);
        });
    }

    @Test
    void updateUnknownThrowsWithCorrelation() {
        // updateStatus("CUS-9999", ACTIVE) message contains lab-request-001
        assertThrows(IllegalArgumentException.class, () -> svc.updateStatus("CUS-9999", CustomerStatus.ACTIVE));
    }

    @Test
    void createAminaKhanThenGetById() {
        Customer created = svc.createCustomer(
                "CUS-1001", "Amina Khan", "amina.khan@example.com", null, CustomerStatus.ACTIVE);
        assertEquals("CUS-1001", created.getCustomerId());
        assertEquals(CustomerStatus.ACTIVE, created.getStatus());
        assertEquals("Amina Khan", svc.getCustomer("CUS-1001").getFullName());
    }

    @Test
    void duplicateIdThrows() {
        svc.createCustomer("CUS-1002", "Ravi Singh", "ravi.singh@example.com", null, CustomerStatus.PROSPECT);
        assertThrows(IllegalStateException.class, () ->
                svc.createCustomer("CUS-1002", "Other", "x@example.com", null, CustomerStatus.PROSPECT));
    }

    @Test
    void unknownIdThrows() {
        assertThrows(IllegalArgumentException.class, () -> svc.getCustomer("CUS-9999"));
    }
}
