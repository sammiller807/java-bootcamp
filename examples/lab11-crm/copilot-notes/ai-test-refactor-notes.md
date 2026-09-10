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

While on the surface this test seems fine, there's one major problem. There's no findByStatus function implemented. If this test wasn't properly checked, the project wouldn't compile.

## lab11-002 — CustomerServiceTest
- Notes: 
```
    Issue: CustomerService mixes business logic, validation and in-memory storage (customers list) — single-responsibility and testability smells.
    Refactor: Extract a CustomerRepository interface and inject it into CustomerService.
    Example interface:
        • interface CustomerRepository { Customer save(Customer c); Optional<Customer> findByCustomerId(String id); List<Customer> findByStatus(CustomerStatus s); List<Customer> listAll(); }
    Change CustomerService to depend on CustomerRepository + CustomerNotifier. Provide an InMemoryCustomerRepository used by the no-arg constructor to preserve behavior. Benefits: smaller class, easier unit testing, swaps to a DB impl later.
```

## lab11-003 — CustomerNotifier extract + Mockito
- Notes:
```
Not covered (tests missing)
• addCustomer rejects null Customer and rejects null/blank customerId.
• addCustomer auto-sets createdAt when null.
• addCustomer defaults status to PROSPECT when status is null.
• findByStatus returns matching customers.
• listAll returns all customers (and returns an immutable copy).
• updateStatus validates customerId null/blank.
• updateStatus invokes notifier with (customerId, oldStatus, newStatus) — CustomerNotifierMockTest is still TODO.
• Behavior when newStatus equals oldStatus (not asserted).
• Constructor wiring: injecting a custom CustomerNotifier or CustomerRepository isn’t asserted.
• Concurrency/atomicity of in-memory repository (save semantics) not tested.
Highest-priority single test to add (recommended)
• Complete CustomerNotifierMockTest: create service with the mocked notifier, add CUS-1002 with PROSPECT, call updateStatus("CUS-1002", ACTIVE), then verify(notifier).notifyStatusChange("CUS-1002", CustomerStatus.PROSPECT, CustomerStatus.ACTIVE).
```

## lab11-004 — coverage gaps / acceptance guidelines
- Notes:
Acceptance guidelines for AI-generated tests and refactors:
1. Every assertion must be able to fail — if I can't describe an input that
   breaks it, it isn't a real test.
2. Every refactor must be backed by a passing test suite run before and after.
3. No accepted suggestion may introduce a dependency not already in pom.xml.
4. I can explain, without re-reading Copilot's explanation, why the code
   is correct.
5. Coverage gaps are documented, not silently ignored.
