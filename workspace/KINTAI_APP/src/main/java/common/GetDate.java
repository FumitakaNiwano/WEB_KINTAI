package common;

import java.util.Date;

import attendance.Constant;

public class GetDate {
	
	/*
	 * 現在日付を取得
	 */
	public static String getDate(Date date) {
		//年の取得
		String today = Constant.DATE_FORMAT.format(date); 
		
		return today;
	}
	
	/*
	 * 現在日付の年を取得
	 */
	public static String getYear(Date date) {
		String year = Constant.YEAR_FORMAT.format(date); 
		
		return year;
	}
	
	/*
	 * 現在日付の月を取得
	 */
	public static String getMonth (Date date) {
		String month = Constant.MONTH_FORMAT.format(date); 
		
		return month;
	}
	
	/*
	 * 現在日付の日を取得
	 */
	public static String getDay(Date date) {
		String day = Constant.DAY_FORMAT.format(date);
		
		return day;
	}
	
	/*
	 * 現在の時刻を取得
	 */
	public static String getTime(Date date) {
		String inTime = Constant.TIME_FORMAT.format(date);
		
		return inTime;
	}
}
