package com.northstar.crm;

import com.northstar.crm.entity.Customer;
import com.northstar.crm.entity.CustomerStatus;
import com.northstar.crm.service.CustomerService;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("Northstar customer service booting");

        CustomerService svc = new CustomerService();

        Customer c1 = new Customer("CUS-1001", "Amina Khan", "amina.khan@example.com", "555-0101", CustomerStatus.ACTIVE, LocalDateTime.now());
        Customer c2 = new Customer("CUS-1002", "Ravi Singh", "ravi.singh@example.com", "555-0202", CustomerStatus.PROSPECT, LocalDateTime.now());

        svc.addCustomer(c1);
        svc.addCustomer(c2);

        System.out.println("Amina: " + svc.findByCustomerId("CUS-1001").orElse(null));
        System.out.println("Ravi before: " + svc.findByCustomerId("CUS-1002").orElse(null));

        // change Ravi to ACTIVE
        svc.updateStatus("CUS-1002", CustomerStatus.ACTIVE);

        // print both customers - Copilot
        //System.out.println(svc.findByCustomerId("CUS-1001").orElse(null));
        System.out.println("Ravi after: " + svc.findByCustomerId("CUS-1002").orElse(null));
    }
}
