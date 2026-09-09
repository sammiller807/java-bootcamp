/**
 * Plain Java 21 record for a Northstar CRM customer.
 * No Spring, no JPA, no framework dependencies.
 */
public record Customer(String id, String fullName, Status status) {

    public enum Status {
        PROSPECT,
        ACTIVE,
        INACTIVE,
        BLOCKED
    }

    public Customer {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Customer id is required.");
        }
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Customer fullName is required.");
        }
        id = id.trim();
        fullName = fullName.trim();
        if (status == null) {
            status = Status.PROSPECT;
        }
    }

    public static final Customer AMINA_KHAN_ACTIVE = new Customer("CUS-1001", "Amina Khan", Status.ACTIVE);
}
