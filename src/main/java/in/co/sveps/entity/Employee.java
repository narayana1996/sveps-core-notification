package in.co.sveps.entity;


import in.co.sveps.validators.PasswordMatches;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.*;
import java.util.List;

@Data
@Document(collection = "sveps_users")
@PasswordMatches
public class Employee {
    @Id
    private ObjectId id;

   @NotBlank
    private String firstName;
   @NotBlank
    private String lastName;
    @NotBlank
    @Email
    private String email;
    
     @Pattern(regexp = "\\d{10}$", message = "Phone number must be 10 digits")
     private String phone;
    @NotBlank
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    @NotBlank
    private String cpassword;
    private String pUUID;

    private boolean enabled;
    private List<String> groups;
    private List<String> permissions;

	//@DBRef
	private AccessLevel accessLevel;



}


