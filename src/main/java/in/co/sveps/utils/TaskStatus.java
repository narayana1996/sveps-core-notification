package in.co.sveps.utils;

public enum TaskStatus {
	  TO_DO("TO DO"),
      IN_PROGRESS("In Progress"),
      DEV_COMPLETED("Developement Completed"),
      READY_FOR_REVIEW("Ready for Review"),    
      DONE("DONE");
    

    private final String status;


	TaskStatus(String status) {
        this.status = status;
    }


	public String getStatus() {
		return status;
	}

   


}
