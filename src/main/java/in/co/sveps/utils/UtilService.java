package in.co.sveps.utils;

import java.time.LocalDate;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

public class UtilService {
	
	 private static final int OTP_LENGTH = 6;
	
	public static java.sql.Date getTodaySqlDate() {
		java.util.Date data = new java.util.Date();
		return new java.sql.Date(data.getTime());
	}
	
	public static java.sql.Date getTodaySqlDate(java.util.Date date) {
		
		return new java.sql.Date(date.getTime());
	}
	
	public static java.sql.Date getDesignations() {
		java.util.Date data = new java.util.Date();
		return new java.sql.Date(data.getTime());
	}
	
	public static java.util.Date getTodayDate() {
		
		return  new java.util.Date();
	}
	
	public static boolean isVaildDate(LocalDate startDate,LocalDate endDate) {
		return startDate == null || endDate == null || endDate.isBefore(startDate);
	}
	


	    public static String generateOtp() {
	        RandomStringGenerator generator = new RandomStringGenerator.Builder()
	                .withinRange('0', '9')
	                .filteredBy(CharacterPredicates.DIGITS)
	                .build();

	        return generator.generate(OTP_LENGTH);
	    }
 
}