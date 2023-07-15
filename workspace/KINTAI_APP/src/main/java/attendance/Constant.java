package attendance;

import java.text.SimpleDateFormat;

public class Constant{
	
	public static SimpleDateFormat DATE_FORMAT  = new SimpleDateFormat("yyyy/MM/dd");    // 日付フォーマット
	public static SimpleDateFormat YEAR_FORMAT  = new SimpleDateFormat("yyyy");          // 年フォーマット
	public static SimpleDateFormat MONTH_FORMAT = new SimpleDateFormat("MM");            // 月フォーマット
	public static SimpleDateFormat DAY_FORMAT   = new SimpleDateFormat("dd");            // 日フォーマット
	public static SimpleDateFormat TIME_FORMAT  = new SimpleDateFormat("HH:mm:ss");      // 時刻フォーマット
	public static SimpleDateFormat HOURS_FORMAT = new SimpleDateFormat("HH");            // 時フォーマット
	public static SimpleDateFormat MINUTES_FORMAT  = new SimpleDateFormat("mm");         // 分フォーマット
	
	
	public static enum AttendanceValue{
		WORK_START_TIME("09:00:00"); //始業時間
		
		
		private final String value;
		
		private AttendanceValue(String value) {
			this.value = value;
		}
		
		String getValue() {
			return value;
		}
	}
}