package in.co.sveps.utils;

public enum AppraisalStatus {



WAITITNG_FOR_LEAD_FEEDBACK("Waititng for  Lead Feedback"),
WAITITNG_FOR_MANAGER_FEEDBACK("Waititng for Manager Feedback"),
FEEDBACK_SUBMITED("Feedback Submited");


private final String status;


AppraisalStatus(String status) {
  this.status = status;
}


public String getStatus() {
	return status;
}




}
