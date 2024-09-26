package in.co.sveps.service;

import com.github.f4b6a3.uuid.UuidCreator;
import in.co.sveps.entity.Employee;

import in.co.sveps.entity.Group;
import in.co.sveps.repo.EmployeeRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private GroupService groupService;


	@Autowired
	private CustomPasswordEncoder passwordEncoder;

	


	public Employee findByEmail(String email) {
		 return employeeRepository.findByEmail(email)
				 .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + email));
	}

	public Employee registerUser(Employee employee) {

		employee.setPassword(passwordEncoder.encode(employee.getPassword()));

	return employeeRepository.save(employee);
	}

	public Page<Employee> getPaginatedEmployees(int page, int size) {
		return employeeRepository.findAll(PageRequest.of(page, size));
	}

	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}

	public void deleteById(ObjectId id) {
		employeeRepository.deleteById(id);
	}

	public Employee findById(ObjectId id) {
		return employeeRepository.findById(id).orElseThrow();
	}

	public void updateEmployee(Employee employee) {
	}

	public List<Group> getGroupsByIds(List<String> groupIds) {
		return groupService.findAllByIds(groupIds);
	}
}
