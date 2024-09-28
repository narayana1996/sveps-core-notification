package in.co.sveps.service;

import in.co.sveps.entity.Invoice;
import in.co.sveps.repo.InvoiceRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice getInvoiceById(ObjectId id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    public Invoice updateInvoice(ObjectId id, Invoice updatedInvoice) {
        Optional<Invoice> existingInvoice = invoiceRepository.findById(id);
        if (existingInvoice.isPresent()) {
            Invoice invoice = existingInvoice.get();
            invoice.setInvoiceDate(updatedInvoice.getInvoiceDate());
            invoice.setDueDate(updatedInvoice.getDueDate());
            invoice.setPaidAmount(updatedInvoice.getPaidAmount());
            invoice.setStatus(updatedInvoice.getStatus());
            invoice.setLineItems(updatedInvoice.getLineItems());
            return invoiceRepository.save(invoice);
        } else {
            return null;
        }
    }

    public void deleteInvoice(ObjectId id) {
        invoiceRepository.deleteById(id);
    }
}

