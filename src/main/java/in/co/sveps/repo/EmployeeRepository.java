package in.co.sveps.repo;

import java.util.List;
import java.util.Optional;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


import in.co.sveps.entity.Employee;


public interface EmployeeRepository extends MongoRepository<Employee, ObjectId> {

    Optional<Employee> findByEmail(String email);
}
