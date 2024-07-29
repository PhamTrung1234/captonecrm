package crm.validation;

import java.util.regex.Pattern;

public class Validation {
	public boolean checkNullOrEmpty (String value,String key) {
  	  if(value.equals("")) {
  		  System.out.println(key + " không được trống");
  		  return false;
  	  }else {
			return true ;
		}
    }
	
	public static boolean patternMatches(String emailAddress) {
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
		        + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)
	      .matches();
	}
} 
