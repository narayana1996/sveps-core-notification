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
@RequestMapping("/user/")
public class EmployeeController {



	 @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private ASPEmailService aspEmailService;
    @Autowired
    GroupService groupService;


    /**
     * route to get a single employee page
     * @param id  grab the employee id
     * @param model mapped to single employee
     * @return single employee page
     */
    @GetMapping("view/{id}")
    public String showEmployee(@PathVariable Integer id, Model model){

        return "employeeshow";
    }

    @GetMapping("all")
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
     * Route to edit an employee
     * @param id grab the employee id
     * @param model mapped to a single employee
     * @return employeeform.html page
     */

    @GetMapping("{id}")
    public String showUpdateForm(@PathVariable ObjectId id, Model model) {
        Employee employee = employeeService.findById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("groups", groupService.findAll());
        return "employeeDetails"; // Ensure this matches your HTML filename
    }

    @PostMapping("update/{id}")
    public String updateEmployee(@PathVariable("id") ObjectId id,
                                 @ModelAttribute("employee") Employee employee,
                                 @RequestParam(value = "newGroups", required = false) List<String> newGroupNames,
                                 Model model) {
        employeeService.updateEmployeeFields(id, employee, newGroupNames);
        return "redirect:../all";
    }
    

}
