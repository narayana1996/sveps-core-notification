package in.co.sveps.repo;

import in.co.sveps.entity.CustomerEmployee;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerEmployeeRepository extends MongoRepository<CustomerEmployee, ObjectId> {
}
