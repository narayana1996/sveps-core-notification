package in.co.sveps.entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;
@Data
@Document(collection = "invoices")
public class Invoice {

    @Id
    private ObjectId id;

    private ObjectId customerId; // Reference to the Customer
    private String invoiceNumber;
    private LocalDate invoiceDate;
    private LocalDate dueDate;
    private double totalAmount;
    private double paidAmount;
    private InvoiceStatus status;

    private List<LineItem> lineItems;

    // Getters and Setters
    // ...
}

enum InvoiceStatus {
    PAID, UNPAID, PARTIAL, OVERDUE;
}

