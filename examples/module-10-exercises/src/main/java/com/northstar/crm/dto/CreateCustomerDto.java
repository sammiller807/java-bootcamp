package src.main.java.com.northstar.crm.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * DTO used when creating a new Customer.
 * Validation annotations ensure incoming data is validated by a JSR-380/JSR-349 provider.
 */
public class CreateCustomerDto {

    @NotBlank(message = "fullName is required")
    private String fullName;

    @NotBlank(message = "email is required")
    @Email(message = "email must be a valid email address")
    private String email;

    public CreateCustomerDto() { }

    public CreateCustomerDto(String fullName, String email) {
        this.fullName = fullName;
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
