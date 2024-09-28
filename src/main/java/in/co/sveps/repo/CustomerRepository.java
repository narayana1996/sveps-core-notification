package in.co.sveps.repo;

import in.co.sveps.entity.Customer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, ObjectId> {
}

