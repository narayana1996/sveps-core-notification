package in.co.sveps.service;

import in.co.sveps.entity.Customer;
import in.co.sveps.entity.CustomerEmployee;
import in.co.sveps.repo.CustomerEmployeeRepository;
import in.co.sveps.repo.CustomerRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerEmployeeRepository customerEmployeeRepository;

    // Create or Update Customer
    public Customer saveCustomer(Customer customer) {
        Optional.ofNullable(customer.getEmployees())
                .ifPresent(employees -> employees.forEach(customerEmployeeRepository::save));
        return customerRepository.save(customer);
    }

    // Find Customer by ID
    public Optional<Customer> findCustomerById(ObjectId id) {
        return customerRepository.findById(id);
    }

    // Find all Customers
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    // Delete Customer by ID
    public void deleteCustomerById(ObjectId id) {
        customerRepository.deleteById(id);
    }

    // Add Employee to Customer
    public Customer addEmployeeToCustomer(ObjectId customerId, CustomerEmployee employee) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        customerEmployeeRepository.save(employee); // Save employee first
        customer.getEmployees().add(employee); // Add employee to customer
        return customerRepository.save(customer); // Save updated customer
    }

    // Remove Employee from Customer
    public Customer removeEmployeeFromCustomer(ObjectId customerId, ObjectId employeeId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        CustomerEmployee employee = customerEmployeeRepository.findById(employeeId).orElseThrow();
        customer.getEmployees().remove(employee); // Remove employee
        return customerRepository.save(customer); // Save updated customer
    }

    // CRUD for CustomerEmployee
    public CustomerEmployee saveCustomerEmployee(CustomerEmployee employee) {
        return customerEmployeeRepository.save(employee);
    }

    public Optional<CustomerEmployee> findCustomerEmployeeById(ObjectId id) {
        return customerEmployeeRepository.findById(id);
    }

    public List<CustomerEmployee> findAllCustomerEmployees() {
        return customerEmployeeRepository.findAll();
    }

    public void deleteCustomerEmployeeById(ObjectId id) {
        customerEmployeeRepository.deleteById(id);
    }
}

