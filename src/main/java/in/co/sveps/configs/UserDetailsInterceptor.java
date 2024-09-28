package in.co.sveps.configs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import in.co.sveps.service.EmployeeService;

@Component
public class UserDetailsInterceptor implements HandlerInterceptor {
  	 @Autowired
    private EmployeeService employeeService;

 	
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
	}
  
	
}