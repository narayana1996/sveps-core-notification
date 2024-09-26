package in.co.sveps.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import javax.validation.constraints.NotBlank;

@Data
@Document(collection = "sveps_access_levels")
public class AccessLevel {

    @Id
    private String id;

    @NotBlank
    private String name;
}
