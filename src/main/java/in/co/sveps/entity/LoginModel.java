package in.co.sveps.entity;

public class LoginModel {
	
	private Integer userId;

	private String userName;
	
	private String name;
	
	private String userNameOrEmail;
	
	private String email;
	
	private String otp;
	
	private String password;
	
	private boolean userFound=false;
	
	private boolean userAcctive=false;
	
	
	public Integer getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public boolean isUserFound() {
		return userFound;
	}

	public void setUserFound(boolean userFound) {
		this.userFound = userFound;
	}

	public boolean isUserAcctive() {
		return userAcctive;
	}

	public void setUserAcctive(boolean userAcctive) {
		this.userAcctive = userAcctive;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNameOrEmail() {
		return userNameOrEmail;
	}

	public void setUserNameOrEmail(String userNameOrEmail) {
		this.userNameOrEmail = userNameOrEmail;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}
	
	
	
}
