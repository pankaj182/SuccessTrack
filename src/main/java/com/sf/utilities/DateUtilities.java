package com.sf.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilities {
	
	public static String getCurrentDateAsString() {
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		String strDate = formatter.format(date);
		return strDate;
	}
	
	public static boolean isCurrentDateInRangeOf(String startDate,String endDate) {
		String currentDate = getCurrentDateAsString();
		SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date sDate = parser.parse(startDate);
			Date eDate = parser.parse(endDate);
			Date cDate = parser.parse(currentDate);
			if(cDate.compareTo(sDate)>=0 && eDate.compareTo(cDate)>=0)
				return true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	public static String formatStringDateAs(String date,String givenFormat,String reqFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(reqFormat);
		SimpleDateFormat parser = new SimpleDateFormat(givenFormat);
		Date d = null;
		try {
			d = parser.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formatter.format(d);
	}
	public static String getPresentableDate(String strDate) throws ParseException {
		String str="";
		SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = parser.parse(strDate);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		str = formatter.format(date);
		return str;
	}
	public static String getLocaleBasedStringDate(String strDate) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    SimpleDateFormat formatter = new SimpleDateFormat("MMM dd,yyyy");
	    Date date = null;
	    String result = "";
	    try {
	        date = sdf.parse(strDate);
	        result = formatter.format(date);
	    } catch (ParseException e) {
	    }
	    return result;
	}
}
