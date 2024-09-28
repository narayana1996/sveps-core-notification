package in.co.sveps.contoller;

import in.co.sveps.entity.Employee;
import in.co.sveps.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private EmployeeService employeeService;



    @GetMapping("register")
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
    @PostMapping("register")
    public String saveEmployee(@Valid Employee employee, BindingResult bindingResult, Model model){

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

}
