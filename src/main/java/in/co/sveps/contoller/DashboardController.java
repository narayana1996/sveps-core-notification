package in.co.sveps.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import in.co.sveps.entity.LoggedInUser;
import in.co.sveps.service.DashboardService;
import in.co.sveps.service.EmployeeService;
import in.co.sveps.service.LoginUtilService;

@Controller
public class DashboardController {

@Autowired
private LoginUtilService loginUtilService;

@Autowired
private DashboardService dashboardService;


@Autowired
private EmployeeService employeeService;






  // Login form
  @RequestMapping("/login")
  public String login() {
    return "login";
  }
  
  @GetMapping("/login?logout=true")
  public String logout() {
      return "redirect:/login";
  }
  

	@RequestMapping("/dashboardView")
	public String dashBoard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		LoggedInUser userDetails = (LoggedInUser) authentication.getPrincipal();

        return "dashboard";


	}
    @RequestMapping("/dashboard")
    public String dashBoardNew(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LoggedInUser userDetails = (LoggedInUser) authentication.getPrincipal();


        return "redirect:/dashboardView";

    }

}
