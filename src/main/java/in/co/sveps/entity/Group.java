package in.co.sveps.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "sveps_groups")
public class Group {
    @Id
    private String id;
    private String name;
    private List<String> permissions;
}
