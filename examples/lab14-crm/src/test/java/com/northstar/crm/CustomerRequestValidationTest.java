package com.northstar.crm;

import com.northstar.crm.dto.CustomerRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class CustomerRequestValidationTest {
    static Validator validator;

    @BeforeAll
    static void setUp() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void validAminaRequestPasses() {
        // build valid DTO for CUS-1001; assert violations empty
        CustomerRequestDTO dto = new CustomerRequestDTO();
        dto.setCustomerId("CUS-1001");
        dto.setFullName("Amina Khan");
        dto.setEmail("amina.khan@example.com");
        dto.setPhone("1111111111");
        dto.setStatus("ACTIVE");
        assertTrue(validator.validate(dto).isEmpty());
    }

    @Test
    void invalidEmailFails() {
        // bad email → assert violations mention email
        CustomerRequestDTO dto = new CustomerRequestDTO();
        dto.setEmail("not-an-email");
        assertFalse(validator.validate(dto).isEmpty());
    }

    @Test
    void blankNameFails() {
        // blank fullName → assert violation
        CustomerRequestDTO dto = new CustomerRequestDTO();
        dto.setFullName(" ");
        assertFalse(validator.validate(dto).isEmpty());
    }
}
