package in.co.sveps.utils;

public class EmailUtilsMessages {
	public static final String COMPANYNAME="Priya Soultions";
	
	public static final String SUBJECT_NEW_EMPLOYE_HIRE="Welcome to "+COMPANYNAME;


//About new employee message
	public static final String BODY_NEW_EMPLOYE_HIRE_Dear="Congratulations {0},\r\n";

	public static final String BODY_NEW_EMPLOYE_HIRE_1="Welcome to "+COMPANYNAME+",You are  starting date from {2}. \r\n";
	
	public static final String BODY_NEW_EMPLOYE_HIRE_2="and your reporting manager is {3}, Please conatct him via {4}/{5} for project \r\n";
	public static final String Thanks="Thanks, \r\n "+COMPANYNAME;
	
	public static final String BODY_NEW_EMPLOYE_HIRE=BODY_NEW_EMPLOYE_HIRE_Dear+BODY_NEW_EMPLOYE_HIRE_1+BODY_NEW_EMPLOYE_HIRE_2+Thanks;


	
	//About new employee OTP
	public static final String SUBJECT_REST_PASSWORD="OTP FOR REST PASSWORD ";
	public static final String BODY_REST_PASSWORD_Dear="Hi {0},\r\n";
	
	public static final String BODY_REST_PASSWORD_1="Please use this OTP to Rest password : \r\n";
	public static final String BODY_REST_PASSWORD_2="OTP  : {1},\r\n";
	public static final String BODY_REST_PASSWORD_3="Expire on  : {2},\r\n";
	
	public static final String BODY_REST_PASSWORD=BODY_REST_PASSWORD_Dear+BODY_REST_PASSWORD_1+BODY_REST_PASSWORD_2+BODY_REST_PASSWORD_3+Thanks;


	//About End Date of assignment 
	public static final String SUBJECT_UnAssignProject="Project UnAssigned";
	public static final String BODY_UnAssignProject_Dear_employeeAndManager="Hi {0} and {1},\r\n";
	
	public static final String BODY_UnAssignProject_1="{0} working as {2} in : \r\n";
	public static final String BODY_UnAssignProject_2="Project Id  : {3},\r\n";
	public static final String BODY_UnAssignProject_3="Project Name : {4},\r\n";	
	public static final String BODY_UnAssignProject_4="has been unAssigned. \r\n";
	
	public static final String BODY_UnAssignProject_5="{1}, please assign new project to {0}\r\n";
	
	public static final String BODY_UnAssignProject=BODY_UnAssignProject_Dear_employeeAndManager+
			                                      BODY_UnAssignProject_1+
			                                     BODY_UnAssignProject_2+
			                                     BODY_UnAssignProject_3+
			                                     BODY_UnAssignProject_4+
			                                     BODY_UnAssignProject_5+
			                                     Thanks;
	
	//About End Date of assignment 
	public static final String SUBJECT_AssignProject="Project Assigned";
	public static final String BODY_AssignProject_Dear_employeeAndManager="Hi {0} and {1},\r\n";
	
	public static final String BODY_AssignProject_1="{0}  has assigned to project and going to work as {2} in : \r\n";
	public static final String BODY_AssignProject_2="Project Id  : {3},\r\n";
	public static final String BODY_AssignProject_3="Project Name : {4}.\r\n";	

	
	public static final String BODY_AssignProject=BODY_AssignProject_Dear_employeeAndManager+
			                                      BODY_AssignProject_1+
			                                     BODY_AssignProject_2+
			                                     BODY_AssignProject_3+
			                                     Thanks;



}
