package crm.validation;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class ConvertTime {
	public Timestamp convertStringToTimetamp(String date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 try {
			 java.util.Date parsedDate = dateFormat.parse(date);
	          Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
	          return timestamp;
		} catch (Exception e) {
			return null;
		}
	}
}
