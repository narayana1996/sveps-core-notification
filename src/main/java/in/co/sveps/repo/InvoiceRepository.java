package in.co.sveps.repo;

import in.co.sveps.entity.Invoice;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface InvoiceRepository extends MongoRepository<Invoice, ObjectId> {
    List<Invoice> findByCustomerId(ObjectId customerId);
}

