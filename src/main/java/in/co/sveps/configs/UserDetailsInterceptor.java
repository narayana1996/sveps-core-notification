package in.co.sveps.configs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import in.co.sveps.service.AccessLevelService;
import in.co.sveps.service.EmployeeService;
import in.co.sveps.service.ProjectService;

@Component
public class UserDetailsInterceptor implements HandlerInterceptor {
  	 @Autowired
    private EmployeeService employeeService;
  	 
  	 @Autowired
    private AccessLevelService accessLevelService;
  	 

  	 
 	
 	@Autowired
     private ProjectService projectService;
 	
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
	}
  
	
}