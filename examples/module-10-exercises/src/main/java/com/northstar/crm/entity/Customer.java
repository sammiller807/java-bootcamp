package src.main.java.com.northstar.crm.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Plain Java domain entity for a customer record.
 * Framework-free for module exercises (no Spring/JPA imports).
 */
public class Customer {
    public enum Status { PROSPECT, ACTIVE, INACTIVE, BLOCKED }

    private String customerId;
    private String fullName;
    private String email;
    private String phone;
    private Status status;
    private LocalDateTime createdAt;

    public Customer() {
        this.status = Status.PROSPECT;
        this.createdAt = LocalDateTime.now();
    }

    public Customer(String customerId, String fullName, String email, String phone, Status status) {
        this.customerId = requireNonBlank(customerId, "customerId");
        this.fullName = requireNonBlank(fullName, "fullName");
        this.email = requireValidEmail(email);
        this.phone = requireNonBlank(phone, "phone");
        this.status = Objects.requireNonNullElse(status, Status.PROSPECT);
        this.createdAt = LocalDateTime.now();
    }

    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = requireNonBlank(customerId, "customerId"); }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = requireNonBlank(fullName, "fullName"); }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = requireValidEmail(email); }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = requireNonBlank(phone, "phone"); }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = Objects.requireNonNullElse(status, Status.PROSPECT); }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt; }

    private static String requireNonBlank(String value, String name) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(name + " is required");
        }
        return value.trim();
    }

    private static String requireValidEmail(String email) {
        String v = email == null ? null : email.trim();
        if (v == null || !v.contains("@")) {
            throw new IllegalArgumentException("email must be a valid address");
        }
        return v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerId, customer.customerId);
    }

    @Override
    public int hashCode() { return Objects.hash(customerId); }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}
