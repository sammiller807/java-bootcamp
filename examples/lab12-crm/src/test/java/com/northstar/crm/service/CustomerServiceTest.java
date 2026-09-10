package com.northstar.crm.service;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/** Characterization / target-API tests — fail until refactor completes. Suite target: 6 tests. */
class CustomerServiceTest {

    private CustomerService svc;

    @BeforeEach
    void setUp() {
        svc = new CustomerService();
        // TODO: after refactor, setCorrelationId("lab-request-001") so not-found messages include it
    }

    @Test
    void createRaviProspectThenActivate() {
        // TODO: create CUS-1002 PROSPECT; updateStatus → ACTIVE; assert statuses
        throw new UnsupportedOperationException("TODO: activate Ravi");
    }

    @Test
    void blankCustomerIdThrows() {
        // TODO: createCustomer(" ", ...) throws IllegalArgumentException
        throw new UnsupportedOperationException("TODO: blank id");
    }

    @Test
    void updateUnknownThrowsWithCorrelation() {
        // TODO: updateStatus("CUS-9999", ACTIVE) message contains lab-request-001
        throw new UnsupportedOperationException("TODO: update unknown + correlation");
    }

    @Test
    void createAminaKhanThenGetById() {
        CustomerService svc = new CustomerService();
        Customer created = svc.createCustomer(
                "CUS-1001", "Amina Khan", "amina.khan@example.com", null, CustomerStatus.ACTIVE);
        assertEquals("CUS-1001", created.getCustomerId());
        assertEquals(CustomerStatus.ACTIVE, created.getStatus());
        assertEquals("Amina Khan", svc.getCustomer("CUS-1001").getFullName());
    }

    @Test
    void duplicateIdThrows() {
        CustomerService svc = new CustomerService();
        svc.createCustomer("CUS-1002", "Ravi Singh", "ravi.singh@example.com", null, CustomerStatus.PROSPECT);
        assertThrows(IllegalStateException.class, () ->
                svc.createCustomer("CUS-1002", "Other", "x@example.com", null, CustomerStatus.PROSPECT));
    }

    @Test
    void unknownIdThrows() {
        CustomerService svc = new CustomerService();
        assertThrows(IllegalArgumentException.class, () -> svc.getCustomer("CUS-9999"));
    }
}
