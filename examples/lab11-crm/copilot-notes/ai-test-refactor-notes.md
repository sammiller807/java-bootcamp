# AI test/refactor notes — Lab 11

## lab11-001 — generated exploratory test
- Accepted / rejected trivial assertion? 
- Notes: Copilot generated the test: findByStatusReturnsOnlyMatchingCustomers:
```java
@Test
    void findByStatusReturnsOnlyMatchingCustomers() {
        customerService.addCustomer(new Customer(
                "CUS-1001",
                "Alice North",
                "alice@example.com",
                "555-0101",
                CustomerStatus.ACTIVE,
                LocalDateTime.of(2024, 1, 15, 9, 30)
        ));
        customerService.addCustomer(new Customer(
                "CUS-1002",
                "Bob West",
                "bob@example.com",
                "555-0103",
                CustomerStatus.PROSPECT,
                LocalDateTime.of(2024, 2, 1, 8, 0)
        ));

        assertEquals(1, customerService.findByStatus(CustomerStatus.PROSPECT).size());
        assertEquals("CUS-1002",
                customerService.findByStatus(CustomerStatus.PROSPECT).get(0).getCustomerId());
    }
```

While on the surface this test seems fine, there's one major problem. There's no findByStatus function. If this test wasn't properly checked, the project wouldn't compile.

## lab11-002 — CustomerServiceTest
- Notes:

## lab11-003 — CustomerNotifier extract + Mockito
- Notes:

## lab11-004 — coverage gaps / acceptance guidelines
- Notes:
