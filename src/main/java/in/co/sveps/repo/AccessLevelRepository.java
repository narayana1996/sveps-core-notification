package in.co.sveps.repo;

import in.co.sveps.entity.AccessLevel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccessLevelRepository extends MongoRepository<AccessLevel, String> {
}
