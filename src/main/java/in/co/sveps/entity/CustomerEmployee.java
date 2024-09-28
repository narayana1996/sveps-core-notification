package in.co.sveps.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Document(collection = "sveps_customer_employees")
public class CustomerEmployee {

    @Id
    private ObjectId id;

    @NotBlank
    private String name;

    @Email
    private String email;

    @Pattern(regexp = "\\d{10}$", message = "Phone number must be 10 digits")
    private String phone;

    private String designation;

    private String department;

    private boolean active;

    private LocalDate dateOfBirth;


}

