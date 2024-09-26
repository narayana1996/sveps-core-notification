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
import in.co.sveps.service.ProjectService;

@Controller
public class DashboardController {

@Autowired
private LoginUtilService loginUtilService;

@Autowired
private DashboardService dashboardService;



@Autowired
private ProjectService projectService;


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
  
 /* @RequestMapping("/send-otp")
  public String resetPassword(Model model) {
	  model.addAttribute("loginModel", new LoginModel());
	  return "send-otp";
  }*/
  
 /* @RequestMapping(path = "/send-otp",method =RequestMethod.POST)
  public String sendOtp(LoginModel loginModel,BindingResult result,Model model) {
	  LoginModel employeByUserNameOrEmail 
	  = loginUtilService.getEmployeByUserNameOrEmail(loginModel);
	  
	    if (!employeByUserNameOrEmail.isUserFound()) {
	        ObjectError error = new ObjectError("globalError", "User not Found");
	        result.addError(error);
	        return "send-otp";
	    }

	  
	  employeByUserNameOrEmail.setOtp(null);
	  model.addAttribute("loginModel", employeByUserNameOrEmail);
	  return "reset-password-form";
  }*/
  
/*  @RequestMapping(path = "/reset-password",method =RequestMethod.POST)
  public String resetPasswordDone(LoginModel loginModel,Model model) {
	  LoginModel employeByUserNameOrEmail 
	  = loginUtilService.validateOTP(loginModel);
	 
	  return "login";
  }	*/
  
/*  @RequestMapping("/")
  public String dashBoard(Model model) {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	  LoggedInUser userDetails = (LoggedInUser) authentication.getPrincipal();
     
	  Employee employeeById = employeeService.getEmployeeById(userDetails.getId());
	  
	  //find employess who are repoting to him
	  List<Employee> allEmployyesUnderCurrentLogedIn = dashboardService
			  .getAllEmployyesUnderCurrentLogedIn(userDetails.getId());
	  model.addAttribute("employesUnderMe", allEmployyesUnderCurrentLogedIn);
	  
	  //find projects who are repoting to him
	  List<Project> allProjectsUnderCurrentLogedIn 
	  = dashboardService.getAllProjectsUnderCurrentLogedIn(allEmployyesUnderCurrentLogedIn);
	  model.addAttribute("projectsUnderMe", allProjectsUnderCurrentLogedIn);
	 
	  //find all tasks who are repoting to him
	  List<Task> findAllTasks = taskService.findAllTasks();
	  model.addAttribute("taskSize", findAllTasks.size());
	  
	  //find all tasks with group by rating
	  Map<Integer, List<Task>> groupByRating = taskService.groupByRating();
	  ArrayList<Task> arrayList = new ArrayList<>();
	  model.addAttribute("groupByRating_1", groupByRating.getOrDefault(1,arrayList).size());
	  model.addAttribute("groupByRating_2", groupByRating.getOrDefault(2,arrayList).size());
	  model.addAttribute("groupByRating_3", groupByRating.getOrDefault(3,arrayList).size());
	  model.addAttribute("groupByRating_4", groupByRating.getOrDefault(4,arrayList).size());
	  model.addAttribute("groupByRating_5", groupByRating.getOrDefault(5,arrayList).size());
	  
	//find all tasks with group by Status
	  Map<TaskStatus, List<Task>> groupByStatus = taskService.groupByStatus();
	  
	  model.addAttribute(TaskStatus.TO_DO.name(), groupByStatus.getOrDefault(TaskStatus.TO_DO,arrayList));
	  model.addAttribute(TaskStatus.IN_PROGRESS.name(),groupByStatus.getOrDefault(TaskStatus.IN_PROGRESS,arrayList));
	  model.addAttribute(TaskStatus.READY_FOR_REVIEW.name(), groupByStatus.getOrDefault(TaskStatus.READY_FOR_REVIEW,arrayList));
	  model.addAttribute(TaskStatus.DONE.name(), groupByStatus.getOrDefault(TaskStatus.DONE,arrayList));
	  model.addAttribute(TaskStatus.DEV_COMPLETED.name(), groupByStatus.getOrDefault(TaskStatus.DEV_COMPLETED,arrayList));
	
	  List<Project> findAllProjects = projectService.findAllProjects();
	  List<Project> findActiveProjects = projectService.findActiveProjects();
	  
	int allTaskSize = findAllProjects.stream()
	  .flatMap(data ->data.getTask().stream())
	  .collect(Collectors.toList()).size();
	
	int allActiveTaskSize = findActiveProjects.stream()
			  .flatMap(data ->data.getTask().stream())
			  .collect(Collectors.toList()).size();
	
	  model.addAttribute("findAllProjects", findAllProjects);
	  model.addAttribute("findActiveProjects", findActiveProjects);
	  model.addAttribute("allTaskSize", allTaskSize);
	  model.addAttribute("allActiveTaskSize", allActiveTaskSize);
	  
	  
	  
	  List<Task> allTasksUnderCurrentLogedIn = 
			  dashboardService.getAllTasksUnderCurrentLogedIn(userDetails.getId());
	  model.addAttribute("my_taskSize", allTasksUnderCurrentLogedIn.size());
	  Map<Integer, List<Task>> groupByRatingforLogedInUser = taskService.groupByRating(allTasksUnderCurrentLogedIn);
	  
	  model.addAttribute("groupByRating_forLogedInUser_1", groupByRatingforLogedInUser.getOrDefault(1,arrayList).size());
	  model.addAttribute("groupByRating_forLogedInUser_2", groupByRatingforLogedInUser.getOrDefault(2,arrayList).size());
	  model.addAttribute("groupByRating_forLogedInUser_3", groupByRatingforLogedInUser.getOrDefault(3,arrayList).size());
	  model.addAttribute("groupByRating_forLogedInUser_4", groupByRatingforLogedInUser.getOrDefault(4,arrayList).size());
	  model.addAttribute("groupByRating_forLogedInUser_5", groupByRatingforLogedInUser.getOrDefault(5,arrayList).size());
	  
	  Map<TaskStatus, List<Task>> groupByStatusforLogedInUser = taskService.groupByStatus(allTasksUnderCurrentLogedIn);
	  
	  model.addAttribute(TaskStatus.TO_DO.name()+"_forLogedInUser", groupByStatusforLogedInUser.getOrDefault(TaskStatus.TO_DO,arrayList));
	  model.addAttribute(TaskStatus.IN_PROGRESS.name()+"_forLogedInUser",groupByStatusforLogedInUser.getOrDefault(TaskStatus.IN_PROGRESS,arrayList));
	  model.addAttribute(TaskStatus.READY_FOR_REVIEW.name()+"_forLogedInUser", groupByStatusforLogedInUser.getOrDefault(TaskStatus.READY_FOR_REVIEW,arrayList));
	  model.addAttribute(TaskStatus.DONE.name()+"_forLogedInUser", groupByStatusforLogedInUser.getOrDefault(TaskStatus.DONE,arrayList));
	  model.addAttribute(TaskStatus.DEV_COMPLETED.name()+"_forLogedInUser", groupByStatusforLogedInUser.getOrDefault(TaskStatus.DEV_COMPLETED,arrayList));
	
	  ArrayList<LeaveRequest> arrayListLeave = new ArrayList<>();
	  List<LeaveRequest> allLeaveRequestsByLogggedInUser = leaveService.getAllLeaveRequestsByLogggedInUser(userDetails.getId());
	  model.addAttribute("leavesRequestByLoggedIn", allLeaveRequestsByLogggedInUser.size());
		
	  Map<LeaveStatus, List<LeaveRequest>> allLeaveRequestsToBeApprovedByLogggedInUser 
	  = leaveService.getAllLeaveRequestsToBeApprovedByLogggedInUser(allLeaveRequestsByLogggedInUser);
	  
	  model.addAttribute(LeaveStatus.APPROVED.name(), allLeaveRequestsToBeApprovedByLogggedInUser.getOrDefault(LeaveStatus.APPROVED,arrayListLeave).size());
      model.addAttribute(LeaveStatus.REJECTED.name(), allLeaveRequestsToBeApprovedByLogggedInUser.getOrDefault(LeaveStatus.REJECTED,arrayListLeave).size());
	  model.addAttribute(LeaveStatus.PENDING.name(), allLeaveRequestsToBeApprovedByLogggedInUser.getOrDefault(LeaveStatus.PENDING,arrayListLeave).size());
		
	 
	
	  return "dashboard";
  }*/

	@RequestMapping("/")
	public String dashBoard(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		LoggedInUser userDetails = (LoggedInUser) authentication.getPrincipal();



		return "redirect:/dashboard";
	}
    @RequestMapping("/dashboard")
    public String dashBoardNew(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        LoggedInUser userDetails = (LoggedInUser) authentication.getPrincipal();



        return "dashboard";
    }

}
