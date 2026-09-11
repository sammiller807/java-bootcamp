package com.northstar.crm;

import com.northstar.crm.api.CustomerApiFacade;
import com.northstar.crm.dto.CustomerRequestDTO;
import com.northstar.crm.dto.CustomerResponseDTO;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.service.CustomerService;

public class Main {
    public static void main(String[] args) {
        CustomerApiFacade api = new CustomerApiFacade(new CustomerService());
        // create CUS-1001 / CUS-1002 via DTOs; print CustomerResponseDTO only
        // attempt invalid email; show correlation lab-request-001 in failure
        CustomerResponseDTO dto1 = api.create(new CustomerRequestDTO("CUS-1001", "Amina Khan", "amina.khan@example.com", "1111111111", "ACTIVE"), "lab-request-001");
        CustomerResponseDTO dto2 = api.create(new CustomerRequestDTO("CUS-1002", "Bob Greg", "bob.greg@example.com", "2222222222", "PROSPECT"), "lab-request-001");

        System.out.println(dto1);
        System.out.println(dto2);
    }
}
