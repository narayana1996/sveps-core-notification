package in.co.sveps.service;

import in.co.sveps.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUtilService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	

	
	
	@Autowired
	private ASPEmailService aspEmailService;
	
	
	/*
	public LoginModel getEmployeByUserNameOrEmail(LoginModel loginModel) {
		
		 employeeRepository.findByEmail(loginModel.getUserNameOrEmail());
	if(findByUsernameOrEmail==null) {
		loginModel.setUserFound(false);
		return loginModel;
	}
	loginModel.setUserFound(true);
	loginModel.setEmail(findByUsernameOrEmail.getEmail());
	loginModel.setUserName(findByUsernameOrEmail.getUsername());
	loginModel.setUserId(findByUsernameOrEmail.getId());
	loginModel.setName(findByUsernameOrEmail.getFirstName()
			+" "+findByUsernameOrEmail.getLastName());

		loginModel.setUserAcctive(findByUsernameOrEmail.isEnabled());
	
	
	sendOTP(findByUsernameOrEmail);
	
	return loginModel;
	}
	
	private void sendOTP(Employee employee) {
		
		OtpDetails otp= new OtpDetails();
		String generateOtp = UtilService.generateOtp();
		LocalDateTime expireTime = LocalDateTime.now().plusMinutes(2);
		otp.setExpirationTime(expireTime);
		otp.setEmployeeId(employee);
		otp.setOtp(generateOtp);
		otpDetailsRepository.save(otp);
		
		String[] mailValues = { employee.getFirstName(), generateOtp,expireTime.toString()};
		aspEmailService.sendSimpleMessage(employee.getEmail(), EmailUtilsMessages.SUBJECT_REST_PASSWORD,
				EmailUtilsMessages.BODY_REST_PASSWORD, mailValues);
		
	}
	
	
	public LoginModel validateOTP(LoginModel loginModel) {
		
		List<OtpDetails> findByEmployeeIdAndOtp 
		= otpDetailsRepository.findByEmployeeIdAndOtp(loginModel.getUserId(),loginModel.getOtp());
		if(findByEmployeeIdAndOtp.size()>0) {
		
			Optional<Employee> findById = employeeRepository.findById(loginModel.getUserId());
			Employee employeeEntity = findById.get();
			employeeEntity.setPassword(loginModel.getPassword());
			employeeRepository.save(employeeEntity);
		}
		
		
	return loginModel;
	}
*/
}
