package in.co.sveps.contoller;



import in.co.sveps.entity.Employee;
import in.co.sveps.entity.Group;
import in.co.sveps.entity.LoggedInUser;
import in.co.sveps.service.*;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class EmployeeController {



	 @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private ASPEmailService aspEmailService;
    
    @Autowired
    private ProjectService projectService;

    @Autowired
    GroupService groupService;


    
    @Autowired
    private AccessLevelService accessLevelService;
    
  

  
    
    

    /**
     * route to create a new employee
     * @param model mapped to single employee
     * @return the employeeform html
     */
    @GetMapping("/register")
    public String newEmployee(Model model){

        model.addAttribute("employee", new Employee());

        return "register";
    }

    /**
     * map Post request and /employee url to controller method
     * @param employee object
     * @param bindingResult checks the fields for validation
     * @return the employeeform html if there are errors
     * or else return the employee detail page
     */
    @PostMapping("/register")
    public String saveEmployee(@Valid Employee employee,BindingResult bindingResult,Model model){

    	   if (bindingResult.hasErrors()) {
    		 //setting values in model    	
    	        model.addAttribute("employee", employee);
               return "register"; // Return to the registration form if there are validation errors
           }
    	
    	//calling the employee service to save the employee in data base
    	if(ObjectUtils.isEmpty(employee.getId())) {
    		
       // employeeService.saveEmployee(employee);
            employeeService.registerUser(employee);

    	}else {
    	//	 employeeService.updateEmployee(employee);
    	}
        //once employee creation is done
        //redirecting the request  from employee creation form to dashborad
        return "redirect:/login";
    }

    /**
     * route to get a single employee page
     * @param id  grab the employee id
     * @param model mapped to single employee
     * @return single employee page
     */
    @GetMapping("/{id}")
    public String showEmployee(@PathVariable Integer id, Model model){
    	// getting the employee based on 'id'
    	// calling employeeservice to fetch the employee by gievn id from database
    	// Employee employeeById = employeeService.getEmployeeById(id);
    	
    	//setting employee details  in model, to display in ui 
       // model.addAttribute("employee",employeeById);
      //redirecting the request  from dashboard creation form to employeeshow
        return "employeeshow";
    }

    @GetMapping("/employees")
    public String  viewEmployeesPage(Model model,
                                            @RequestParam(defaultValue = "0") int page,  // Default to first page
                                            @RequestParam(defaultValue = "5") int size  // Default page size to 5
    ) {
        Page<Employee> employeePage = employeeService.getPaginatedEmployees(page, size);
        model.addAttribute("employeePage", employeePage);

        // Add current page and total pages info for pagination
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employeePage.getTotalPages());

        return "employees";  // Refers to employees.html Thymeleaf template
    }

    /**
     * map Get request and /employees url to controller method
     * @param model mapped to all Employees
     * @return list of employees in employees.html
     */
    @GetMapping
    public String dashboard(Model model){
    	//calling the employee service to fetch all the employes in data base
    	//List<Employee> findAllEmployees = employeeService.findAllEmployees();
    	
    	//setting employes details  in model, to display in ui 
       // model.addAttribute("employees", findAllEmployees);
        //display  list of employess in the dashboard
        return "dashboardEmp";
    }

    /**
     * Route to edit an employee
     * @param id grab the employee id
     * @param model mapped to a single employee
     * @return employeeform.html page
     */
    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
    	
     	// list of employes to display the reporting manager Info
    	//List<Employee> findAllEmployees =
    	//		employeeService.findAllEmployees();

        	
     	// list of access to active projects
    	//List<Project> findActiveProjects = projectService.findActiveProjects();
    	
    	// calling employeeservice to fetch the employee by gievn id from database
    //	Employee employeeById = employeeService.getEmployeeById(id);
    	
    	//setting values in model 
       // model.addAttribute("employee", employeeById);
        //model.addAttribute("employeeList", findAllEmployees);

        //model.addAttribute("projects", findActiveProjects);
        
        //display the employee form to update old employee
        return "employeeform";
    }

    @GetMapping("employee/delete/{id}")
    public String deleteEmployee(@PathVariable ObjectId id) {
        employeeService.deleteById(id);
        return "redirect:/employees";
    }


    @GetMapping("/employees/{id}")
    public String showUpdateForm(@PathVariable ObjectId id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("groups", groupService.findAll());
        return "employeeDetails"; // Ensure this matches your HTML filename
    }

    @PostMapping("/employees/update/{id}")
    public String updateEmployee(@PathVariable ObjectId id, @ModelAttribute Employee employee,
                                 @RequestParam List<String> newGroup) {
        // Set the employee ID
        employee.setId(id);

        // Fetch and set the groups
        employee.setGroups(newGroup);

        // Update employee in database
        employeeService.updateEmployee(employee);
        return "redirect:/employees"; // Redirect to the employee list or another view
    }
    
    
    private LoggedInUser loadBasicDataRequried(Model model) {
    	// list of employes to display the reporting manager Info
    	
    	LoggedInUser loggedInUser =(LoggedInUser) model.getAttribute("userDetails");

    	
    	return loggedInUser;
      
    }

   

}
