package in.co.sveps.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
@Data
@Document(collection = "sveps_customers")
public class Customer {

    @Id
    private ObjectId id;

    @NotBlank
    private String nameOfBusiness;

    @Email
    private String email;

    @Pattern(regexp = "\\d{10}$", message = "Phone number must be 10 digits")
    private String phone;

    private String website;

    @PastOrPresent
    private LocalDate startDateWithUs;

    private Address currentAddress;

    private List<Address> branchAddress;

    // One-to-Many relationship using @DBRef for customer employees
    @DBRef
    private List<CustomerEmployee> employees;

    // Getters and Setters
    // ...
}

