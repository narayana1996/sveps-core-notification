package in.co.sveps.contoller;

import in.co.sveps.entity.Customer;
import in.co.sveps.entity.CustomerEmployee;
import in.co.sveps.service.CustomerService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // Create or Update Customer
    @PostMapping("edit/save")
    public ResponseEntity<Customer> createOrUpdateCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(savedCustomer);
    }

    // Get Customer by ID
    @GetMapping("view/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") ObjectId id) {
        return customerService.findCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get All Customers
    @GetMapping("view/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.findAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // Delete Customer by ID
    @DeleteMapping("edit/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("id") ObjectId id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    // Add Employee to Customer
    @PostMapping("edit/{customerId}/employees")
    public ResponseEntity<Customer> addEmployeeToCustomer(@PathVariable("customerId") ObjectId customerId,
                                                          @RequestBody CustomerEmployee employee) {
        Customer updatedCustomer = customerService.addEmployeeToCustomer(customerId, employee);
        return ResponseEntity.ok(updatedCustomer);
    }

    // Remove Employee from Customer
    @DeleteMapping("edit/{customerId}/employees/{employeeId}")
    public ResponseEntity<Customer> removeEmployeeFromCustomer(@PathVariable("customerId") ObjectId customerId,
                                                               @PathVariable("employeeId") ObjectId employeeId) {
        Customer updatedCustomer = customerService.removeEmployeeFromCustomer(customerId, employeeId);
        return ResponseEntity.ok(updatedCustomer);
    }

    // CRUD for CustomerEmployee
    @PostMapping("edit/employees")
    public ResponseEntity<CustomerEmployee> createOrUpdateCustomerEmployee(@RequestBody CustomerEmployee employee) {
        CustomerEmployee savedEmployee = customerService.saveCustomerEmployee(employee);
        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping("view/employees/{id}")
    public ResponseEntity<CustomerEmployee> getCustomerEmployeeById(@PathVariable("id") ObjectId id) {
        return customerService.findCustomerEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("edit/employees/{id}")
    public ResponseEntity<Void> deleteCustomerEmployee(@PathVariable("id") ObjectId id) {
        customerService.deleteCustomerEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
}

